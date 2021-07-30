package com.xdcplus.interaction.factory.flow;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.xdcplus.interaction.common.enums.ResponseEnum;
import com.xdcplus.interaction.common.exception.InteractionEngineException;
import com.xdcplus.interaction.factory.user.UserDestinationFactory;
import com.xdcplus.interaction.factory.user.UserDestinationParam;
import com.xdcplus.tool.constants.NumberConstant;
import com.xdcplus.interaction.common.pojo.dto.RequestFlowDTO;
import com.xdcplus.interaction.common.pojo.vo.ProcessConfigVO;
import com.xdcplus.interaction.common.pojo.vo.ProcessStatusVO;
import com.xdcplus.interaction.common.pojo.vo.RequestFlowVO;
import com.xdcplus.interaction.service.ProcessConfigService;
import com.xdcplus.interaction.service.ProcessStatusService;
import com.xdcplus.interaction.service.RequestFlowService;
import com.xdcplus.interaction.service.RequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 流转转移过程
 *
 * @author Rong.Jia
 * @date 2021/06/07
 */
@Slf4j
public class BaseProcessTransfor implements ProcessTransfor {

    @Autowired
    RequestFlowService requestFlowService;

    @Autowired
    RequestService requestService;

    @Autowired
    ProcessStatusService processStatusService;

    @Autowired
    ProcessConfigService processConfigService;

    @Autowired
    UserDestinationFactory userDestinationFactory;

    /**
     * 查询配置配置
     * @param processId 流程主键
     * @param requestCreatedUser  表单创建人
     * @param userId 审批者用户标识
     * @param statusId  状态主键
     * @param version 版本号
     * @return {@link List<ProcessConfigVO>} 流程配置信息
     */
    List<ProcessConfigVO> findConfigByProcessIdAndFromStatusId(Long processId,
                                                               Long statusId, String version,
                                                               Long userId, String requestCreatedUser) {

        List<ProcessConfigVO> processConfigVOList =  processConfigService.findConfigByProcessIdAndFromStatusId(processId, statusId, version);

        if (CollectionUtil.isEmpty(processConfigVOList)) {
            log.error("A valid process configuration was not found");
            throw new InteractionEngineException(ResponseEnum.A_VALID_PROCESS_CONFIGURATION_WAS_NOT_FOUND);
        }


        processConfigVOList.forEach(a -> {

            UserDestinationParam userDestinationParam = UserDestinationParam.builder()
                    .createUserName(requestCreatedUser)
                    .userId(userId)
                    .userTo(a.getUserTo())
                    .userToType(a.getToUserId())
                    .build();

            Optional.ofNullable(userDestinationFactory.postProcess(userDestinationParam))
                    .ifPresent(a::setToUserId);
        });
        return processConfigVOList;
    }

    /**
     * 保存、修改流程信息
     *
     * @param syncFlow 流程信息
     */
    void syncFlow(SyncFlow syncFlow) {

        RequestFlowDTO requestFlowDTO = new RequestFlowDTO();
        BeanUtil.copyProperties(syncFlow, requestFlowDTO);

        requestFlowDTO.setId(syncFlow.getFlowId());

        if (ObjectUtil.isNull(syncFlow.getFlowId())) {

            requestFlowDTO.setBeginTime(DateUtil.current());

            requestFlowService.saveRequestFlow(requestFlowDTO);
        } else {
            requestFlowService.updateRequestFlow(requestFlowDTO);
        }
    }

    /**
     * 根据表单主键修改 流程状态
     *
     * @param requestId 表单主键
     * @param statusId  流程状态
     */
    void updateRequestStatusIdById(Long requestId, Long statusId) {
        requestService.updateStatusIdById(requestId, statusId);
    }

    /**
     * 查询过程状态通过标识
     *
     * @param mark 标识
     * @return {@link ProcessStatusVO} 过程状态
     */
    ProcessStatusVO findProcessStatusByMark(String mark) {

        ProcessStatusVO processStatusVO = processStatusService.findProcessStatusByMark(mark);
        if (ObjectUtil.isNull(processStatusVO)) {
            log.error("findProcessStatusByMark() Abnormal flow, signature status does not exist");
            throw new InteractionEngineException(ResponseEnum.ABNORMAL_FLOW_SIGNATURE_STATUS_DOES_NOT_EXIST);
        }

        return processStatusVO;
    }

    /**
     * 查询当前流转节点
     *
     * @param statusId  状态ID
     * @param toUserId  来用户主键
     * @param roleIds   用户拥有角色
     * @param requestId 表单主键
     * @return {@link RequestFlowVO} 流转节点
     */
    RequestFlowVO getCurrentRequestFlow(Long requestId, Long statusId, Long toUserId, List<Long> roleIds) {

        List<RequestFlowVO> requestFlowVOList = getCurrentRequestFlows(requestId, statusId, toUserId, roleIds);
        RequestFlowVO requestFlowVO;

        if (CollectionUtil.isNotEmpty(requestFlowVOList) && requestFlowVOList.size() > NumberConstant.ONE) {
            requestFlowVO = requestFlowVOList.stream()
                    .filter(a -> ObjectUtil.equal(a.getFlowOption().getValue(), NumberConstant.A_NEGATIVE))
                    .findAny().orElse(null);

        } else {
            requestFlowVO = getCurrentRequestFlows(requestId, statusId, toUserId, roleIds).stream().findAny().orElse(null);
        }

        if (ObjectUtil.isNull(requestFlowVO)) {
            log.error("Process exceptions, The current node could not be found");
            throw new InteractionEngineException(ResponseEnum.FLOW_ABNORMAL_CURRENT_NODE_CANNOT_BE_FOUND);
        }

        return requestFlowVO;
    }

    /**
     * 查询当前的流转信息
     *
     * @param requestId 表单主键
     * @param statusId  状态主键
     * @param toUserId  来用户主键
     * @param roleIds   用户拥有角色
     * @return {@link List<RequestFlowVO>} 流转信息
     */
    List<RequestFlowVO> getCurrentRequestFlows(Long requestId, Long statusId, Long toUserId, List<Long> roleIds) {

        List<RequestFlowVO> currentRequestFlows = this.getRequestFlows(requestId, statusId);

        return currentRequestFlows.stream()
                .filter(a -> {

                    boolean flag = Boolean.TRUE;

                    if (ObjectUtil.isAllNotEmpty(toUserId, a.getToUserId())) {
                        flag = ObjectUtil.equal(a.getToUserId(), toUserId);
                    }

                    if (ObjectUtil.isAllNotEmpty(roleIds, a.getToRoleId())) {
                        flag = flag || roleIds.contains(a.getToRoleId());
                    }

                    return flag && ObjectUtil.equal(requestId, a.getRequest().getId());
                }).collect(Collectors.toList());

    }

    /**
     * 查询流转节点
     *
     * @param requestId 表单主键
     * @param statusId  表单状态主键
     * @return {@link List<RequestFlowVO>}  流程当前节点, 如果是会签，或者多加签则为多个
     */
    List<RequestFlowVO> getRequestFlows(Long requestId, Long statusId) {

        List<RequestFlowVO> requestFlowVOList = requestFlowService.findRequestFlowByRequestIdAndToStatusId(requestId, statusId);

        if (CollectionUtil.isEmpty(requestFlowVOList)) {
            log.error("getCurrentRequestFlows() Flow abnormal, current node cannot be found");
            throw new InteractionEngineException(ResponseEnum.FLOW_ABNORMAL_CURRENT_NODE_CANNOT_BE_FOUND);
        }

        return requestFlowVOList;
    }

    /**
     * 获取最近一条非加签流转记录
     *
     * @param requestFlowVOList 流转信息集合
     * @param currentFlowId 当前流转节点ID
     * @param mark 标识
     * @return {@link RequestFlowVO} 非加签流转记录
     */
    RequestFlowVO getRecentUnsignedFlow(List<RequestFlowVO> requestFlowVOList, Long currentFlowId, Integer mark) {

        requestFlowVOList.removeIf(a -> ObjectUtil.equal(a.getId(), currentFlowId));
        requestFlowVOList.removeIf(a -> ObjectUtil.notEqual(a.getFlowOption(), mark));

        requestFlowVOList = requestFlowVOList.stream()
                .sorted(Comparator.comparingLong(RequestFlowVO::getEndTime).reversed())
                .collect(Collectors.toList());

        // 非加签流转记录
        RequestFlowVO nonSignedFlowRecords = null;

        if (CollectionUtil.isNotEmpty(requestFlowVOList) && requestFlowVOList.size() >= NumberConstant.ONE) {
            nonSignedFlowRecords = requestFlowVOList.get(NumberConstant.ZERO);
        }

        if (ObjectUtil.isNull(nonSignedFlowRecords)) {
            log.error("The unsigned flow record was not obtained correctly");
            throw new InteractionEngineException(ResponseEnum.THE_UNSIGNED_FLOW_RECORD_WAS_NOT_OBTAINED_CORRECTLY);
        }

        return nonSignedFlowRecords;
    }


}
