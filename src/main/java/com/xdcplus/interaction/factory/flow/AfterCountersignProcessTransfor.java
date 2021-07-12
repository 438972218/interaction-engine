package com.xdcplus.interaction.factory.flow;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Validator;
import com.xdcplus.interaction.common.validator.groupvlidator.ProcessTransforGroupValidator;
import com.xdcplus.tool.constants.NumberConstant;
import com.xdcplus.interaction.common.pojo.vo.ProcessStatusVO;
import com.xdcplus.interaction.common.pojo.vo.RequestFlowVO;
import com.xdcplus.interaction.common.pojo.vo.RequestVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 后加签流转过程
 * @author Rong.Jia
 * @date 2021/06/07
 */
@Slf4j
@Component
public class AfterCountersignProcessTransfor extends BaseProcessTransfor {

    @Override
    public Boolean supportType(Integer flowOption) {
        return Validator.equal(NumberConstant.FIVE, flowOption);
    }

    @Override
    public void postProcess(ProcessTransforParam processTransforParam) {

        log.info("Postsignature processing operation");

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
                .flowOptionValue(processTransforParam.getFlowOption())
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
                .configVersion(requestVO.getConfigVersion())
                .fromUserId(userId)
                .build();

        super.syncFlow(syncFlow);

        super.updateRequestStatusIdById(requestVO.getId(), processStatusVO.getId());

    }



}
