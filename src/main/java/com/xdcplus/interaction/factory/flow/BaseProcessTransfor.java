package com.xdcplus.interaction.factory.flow;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.xdcplus.interaction.common.enums.ResponseEnum;
import com.xdcplus.interaction.common.exception.InteractionEngineException;
import com.xdcplus.interaction.common.pojo.vo.RequestVO;
import com.xdcplus.interaction.factory.user.UserToProcess;
import com.xdcplus.tool.constants.NumberConstant;
import com.xdcplus.interaction.common.config.FlowableConfig;
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

import java.util.List;
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
    FlowableConfig flowableConfig;

    @Autowired
    ProcessStatusService processStatusService;

    @Autowired
    ProcessConfigService processConfigService;

    /**
     * 是否等待前加签同意
     *
     * @return boolean true/false
     */
    boolean isSignatureWait() {
        return flowableConfig.getSignatureWait();
    }

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
                                                               Long statusId, String version, Long userId, String requestCreatedUser) {

        List<ProcessConfigVO> processConfigVOList =  processConfigService.findConfigByProcessIdAndFromStatusId(processId, statusId, version);

        if (CollectionUtil.isEmpty(processConfigVOList)) {
            log.error("A valid process configuration was not found");
            throw new InteractionEngineException(ResponseEnum.A_VALID_PROCESS_CONFIGURATION_WAS_NOT_FOUND);
        }

        processConfigVOList.forEach(a -> a.setToUserId(UserToProcess.getToUserId(a.getToUserId(), userId, requestCreatedUser)));
        return processConfigVOList;
    }

    /**
     * 判断 当前流程是否有加签，有则返回
     *
     * @param requestId 表单主键
     * @param statusId  状态主键
     * @return {@link List<RequestFlowVO>}  加签过程中数据
     */
    List<RequestFlowVO> getBeforeCountersign(Long requestId, Long statusId) {

        // 如果是非被加签人操作， 需要获取是否有加签
        List<RequestFlowVO> requestFlowVOList = requestFlowService.findRequestFlowByRequestIdAndFromStatusId(requestId, statusId);
        if (CollectionUtil.isNotEmpty(requestFlowVOList)) {

            return requestFlowVOList.stream()
                    .filter(a -> ObjectUtil.equal(NumberConstant.SIX, a.getToStatus().getMark())
                            && ObjectUtil.equal(NumberConstant.A_NEGATIVE, a.getFlowOption().getValue()))
                    .collect(Collectors.toList());
        }

        return null;
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
     * 获取流操作， 用于切换  是否等待前加签
     *
     * @param flowOption 流选项
     * @return {@link Integer} 流选项  value
     */
    Integer getFlowOption(Integer flowOption) {
        return flowableConfig.getSignatureWait() ? flowOption : NumberConstant.A_NEGATIVE;
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
     * @return {@link RequestFlowVO}
     */
    RequestFlowVO getCurrentRequestFlow(Long requestId, Long statusId, Long toUserId, List<Long> roleIds) {

        List<RequestFlowVO> requestFlowVOList = getCurrentRequestFlows(requestId, statusId, toUserId, roleIds);
        RequestFlowVO requestFlowVO = null;

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
     * @return {@link List<RequestFlowVO>}
     */
    List<RequestFlowVO> getCurrentRequestFlows(Long requestId, Long statusId, Long toUserId, List<Long> roleIds) {

        List<RequestFlowVO> currentRequestFlows = this.getCurrentRequestFlows(requestId, statusId);

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
     * 查询当前的流转节点
     *
     * @param requestId 表单主键
     * @param statusId  表单状态主键
     * @return {@link List<RequestFlowVO>}  流程当前节点, 如果是会签，或者多加签则为多个
     */
    List<RequestFlowVO> getCurrentRequestFlows(Long requestId, Long statusId) {

        List<RequestFlowVO> requestFlowVOList = requestFlowService.findRequestFlowByRequestIdAndToStatusId(requestId, statusId);

        if (CollectionUtil.isEmpty(requestFlowVOList)) {
            log.error("getCurrentRequestFlows() Flow abnormal, current node cannot be found");
            throw new InteractionEngineException(ResponseEnum.FLOW_ABNORMAL_CURRENT_NODE_CANNOT_BE_FOUND);
        }

        return requestFlowVOList;
    }

    /**
     * 查询上一个状态
     *
     * @param requestFlowVOList 流转信息集合
     * @param fromStatusId      上一个状态ID
     * @param mark              状态标识
     * @return {@link RequestFlowVO} 流转信息
     */
    RequestFlowVO getLastStatusRequestFlow(List<RequestFlowVO> requestFlowVOList, Long fromUserId,
                                           Long fromStatusId, Long currentFlowId, Integer mark) {

        if (ObjectUtil.isNotNull(currentFlowId)) {
            requestFlowVOList.removeIf(a -> ObjectUtil.equal(a.getId(), currentFlowId));
        }

        RequestFlowVO requestFlowVO = null;

        while (true) {

            List<RequestFlowVO> lastStatusRequestFlows = getLastStatusRequestFlows(requestFlowVOList, fromStatusId);
            if (existCountersignLastStatusRequestFlow(lastStatusRequestFlows, mark)) {
                for (RequestFlowVO lastStatusRequestFlow : lastStatusRequestFlows) {
                    fromStatusId = lastStatusRequestFlow.getToStatus().getId();
                    requestFlowVOList.removeIf(a -> ObjectUtil.equal(a.getId(), lastStatusRequestFlow.getId()));
                }
            } else {

                requestFlowVO = lastStatusRequestFlows.stream()
                        .filter(a -> ObjectUtil.equal(fromUserId, a.getToUserId()))
                        .findAny().orElse(null);
                break;
            }
        }

        return requestFlowVO;
    }

    /**
     * 在历史流程中判断 当前节点的上一个节点是否存在加签
     *
     * @param requestFlowVOList 历史流程
     * @param mark              状态标识
     * @return {@link Boolean} true: 存在， false: 不存在
     */
    Boolean existCountersignLastStatusRequestFlow(List<RequestFlowVO> requestFlowVOList, Integer mark) {
        return requestFlowVOList.stream().anyMatch(a -> ObjectUtil.equal(mark, a.getToStatus().getMark()));
    }

    /**
     * 查询当前流程的上一个流程节点
     *
     * @param requestFlowVOList 流转信息集合
     * @param fromStatusId      上一个状态ID
     * @return {@link RequestFlowVO}  流转信息集合
     */
    private List<RequestFlowVO> getLastStatusRequestFlows(List<RequestFlowVO> requestFlowVOList, Long fromStatusId) {

        return requestFlowVOList.stream().filter(a -> ObjectUtil.equal(a.getToStatus().getId(), fromStatusId))
                .collect(Collectors.toList());

    }


}
