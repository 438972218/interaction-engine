package com.xdcplus.interaction.factory.flow;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Validator;
import com.xdcplus.tool.constants.NumberConstant;
import com.xdcplus.interaction.common.pojo.vo.ProcessStatusVO;
import com.xdcplus.interaction.common.pojo.vo.RequestFlowVO;
import com.xdcplus.interaction.common.pojo.vo.RequestVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 前加签流转过程
 * @author Rong.Jia
 * @date 2021/06/07
 */
@Slf4j
@Component
public class BeforeCountersignProcessTransfor extends BaseProcessTransfor {

    @Override
    public Boolean supportType(Integer flowOption) {
        return Validator.equal(NumberConstant.SIX, flowOption);
    }

    @Override
    public void postProcess(ProcessTransforParam processTransforParam) {

        log.info("Pre-signature processing operation");

        Long toUserId = processTransforParam.getToUserId();
        Long userId = processTransforParam.getUserId();
        RequestVO requestVO = processTransforParam.getRequest();

        RequestFlowVO requestFlowVO = super.getCurrentRequestFlow(requestVO.getId(),
                requestVO.getStatus().getId(), userId, null);

        ProcessStatusVO processStatusVO = super.findProcessStatusByMark(Convert.toStr(processTransforParam.getFlowOption()));

        // 修改
        SyncFlow syncFlow = SyncFlow.builder()
                .requestId(requestVO.getId())
                .flowId(requestFlowVO.getId())
                .endTime(DateUtil.current())
                .flowOptionValue(getFlowOption(processTransforParam.getFlowOption()))
                .description(processTransforParam.getDescription())
                .toUserId(userId).build();

        super.syncFlow(syncFlow);

        // 新增
        syncFlow = SyncFlow.builder()
                .requestId(requestVO.getId())
                .flowOptionValue(NumberConstant.A_NEGATIVE)
                .fromStatusId(requestVO.getStatus().getId())
                .toStatusId(processStatusVO.getId())
                .toUserId(toUserId)
                .fromUserId(userId)
                .build();

        super.syncFlow(syncFlow);

        // 需要等待被加签审核才修改表单状态
        if (flowableConfig.getSignatureWait()) {
            super.updateRequestStatusIdById(requestVO.getId(), processStatusVO.getId());
        }

    }


}
