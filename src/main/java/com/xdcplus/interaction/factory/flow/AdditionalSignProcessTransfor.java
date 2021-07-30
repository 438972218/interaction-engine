package com.xdcplus.interaction.factory.flow;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Validator;
import com.xdcplus.interaction.common.enums.ResponseEnum;
import com.xdcplus.interaction.common.exception.InteractionEngineException;
import com.xdcplus.interaction.common.pojo.vo.ProcessStatusVO;
import com.xdcplus.interaction.common.pojo.vo.RequestFlowVO;
import com.xdcplus.interaction.common.pojo.vo.RequestVO;
import com.xdcplus.tool.constants.NumberConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 加签流转过程
 *
 * @author Rong.Jia
 * @date 2021/07/16
 */
@Slf4j
@Component
public class AdditionalSignProcessTransfor extends BaseProcessTransfor {

    @Override
    public Boolean supportType(Integer flowOption) {
        return Validator.equal(NumberConstant.FIVE, flowOption)
                || Validator.equal(NumberConstant.SIX, flowOption);
    }

    @Override
    public void postProcess(ProcessTransforParam processTransforParam) {

        log.info("Additional sign processing operation");

        List<Long> toUserIds = processTransforParam.getAdditional().getToUserIds();
        Long userId = processTransforParam.getUserId();
        RequestVO requestVO = processTransforParam.getRequest();

        RequestFlowVO requestFlowVO = super.getCurrentRequestFlow(requestVO.getId(),
                requestVO.getStatus().getId(), userId, null);

        attachLabelAfterVerification(requestVO.getId(), processTransforParam.getFlowOption(), requestFlowVO);

        ProcessStatusVO processStatusVO = super.findProcessStatusByMark(Convert.toStr(processTransforParam.getFlowOption()));

        // 修改
        SyncFlow syncFlow = SyncFlow.builder()
                .requestId(requestVO.getId())
                .flowId(requestFlowVO.getId())
                .endTime(DateUtil.current())
                .flowOptionValue(processTransforParam.getFlowOption())
                .description(processTransforParam.getDescription())
                .toUserId(userId).build();

        super.syncFlow(syncFlow);

        // 新增
        for (Long toUserId : toUserIds) {

            syncFlow = SyncFlow.builder()
                    .requestId(requestVO.getId())
                    .flowOptionValue(NumberConstant.A_NEGATIVE)
                    .fromStatusId(requestVO.getStatus().getId())
                    .toStatusId(processStatusVO.getId())
                    .toUserId(toUserId)
                    .configVersion(requestVO.getConfigVersion())
                    .fromUserId(userId)
                    .build();

            super.syncFlow(syncFlow);
        }

        super.updateRequestStatusIdById(requestVO.getId(), processStatusVO.getId());

    }

    /**
     * 后加签验证
     * @param flowOption 流程操作
     * @param requestId 表单ID
     * @param currentFlow 当前流转记录
     */
    private void attachLabelAfterVerification(Long requestId, Integer flowOption, RequestFlowVO currentFlow) {

        if (Validator.equal(NumberConstant.FIVE, flowOption)) {

            // 上一个流转记录
            List<RequestFlowVO> lastCurrentFlows = getRequestFlows(requestId, currentFlow.getFromStatus().getId());

            if (lastCurrentFlows.stream()
                    .anyMatch(a -> Validator.equal(NumberConstant.SIX, a.getFlowOption()))) {
                log.error("Pre - signing is not allowed during pre - signing");
                throw new InteractionEngineException(ResponseEnum.PRE_SIGNING_IS_NOT_ALLOWED_DURING_PRE_SIGNING);
            }
        }
    }
















}
