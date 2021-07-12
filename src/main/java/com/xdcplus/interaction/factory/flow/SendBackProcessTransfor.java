package com.xdcplus.interaction.factory.flow;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Validator;
import com.xdcplus.interaction.common.enums.ResponseEnum;
import com.xdcplus.interaction.common.exception.InteractionEngineException;
import com.xdcplus.tool.constants.NumberConstant;
import com.xdcplus.interaction.common.pojo.vo.RequestFlowVO;
import com.xdcplus.interaction.common.pojo.vo.RequestVO;
import com.xdcplus.interaction.service.RequestFlowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 退回 流转过程
 *
 * @author Rong.Jia
 * @date 2021/06/07
 */
@Slf4j
@Component
public class SendBackProcessTransfor extends BaseProcessTransfor {

    @Autowired
    private RequestFlowService requestFlowService;

    @Override
    public Boolean supportType(Integer flowOption) {
        return Validator.equal(NumberConstant.TWO, flowOption);
    }

    @Override
    public void postProcess(ProcessTransforParam processTransforParam) {

        log.info("Return handling operation");

        RequestVO requestVO = processTransforParam.getRequest();
        Long userId = processTransforParam.getUserId();
        Long toStatusId = processTransforParam.getSendBack().getToStatusId();

        List<RequestFlowVO> requestFlowVOList = requestFlowService.findRequestFlowRequestId(requestVO.getId());

        Set<Long> fromStatusIds = requestFlowVOList.stream().map(a -> a.getFromStatus().getId())
                .collect(Collectors.toSet());

        if (CollectionUtil.isEmpty(fromStatusIds)) fromStatusIds = CollectionUtil.newHashSet();
        fromStatusIds.addAll(requestFlowVOList.stream().map(a -> a.getToStatus().getId()).collect(Collectors.toSet()));

        if (!fromStatusIds.contains(toStatusId)) {
            log.error("Abnormal flow, specified return person does not exist");
            throw new InteractionEngineException(ResponseEnum.ABNORMAL_FLOW_SPECIFIED_RETURN_PERSON_DOES_NOT_EXIST);
        }

        // 筛选操作流程节点
        RequestFlowVO requestFlowVO = super.getCurrentRequestFlow(requestVO.getId(), requestVO.getStatus().getId(),
                userId, null);

        SyncFlow syncFlow = SyncFlow.builder()
                .requestId(requestVO.getId())
                .flowId(requestFlowVO.getId())
                .endTime(DateUtil.current())
                .flowOptionValue(processTransforParam.getFlowOption())
                .description(processTransforParam.getDescription())
                .toUserId(userId)
                .build();

        super.syncFlow(syncFlow);

        syncFlow = SyncFlow.builder()
                .requestId(requestVO.getId())
                .flowOptionValue(NumberConstant.A_NEGATIVE)
                .fromStatusId(requestVO.getStatus().getId())
                .toStatusId(toStatusId)
                .toUserId(processTransforParam.getToUserId())
                .fromUserId(userId)
                .build();

        super.syncFlow(syncFlow);

        super.updateRequestStatusIdById(requestVO.getId(), toStatusId);

    }


}
