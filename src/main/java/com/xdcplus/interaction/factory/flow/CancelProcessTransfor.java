package com.xdcplus.interaction.factory.flow;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ObjectUtil;
import com.xdcplus.interaction.common.enums.ResponseEnum;
import com.xdcplus.interaction.common.exception.InteractionEngineException;
import com.xdcplus.tool.constants.NumberConstant;
import com.xdcplus.interaction.common.pojo.vo.ProcessStatusVO;
import com.xdcplus.interaction.common.pojo.vo.RequestFlowVO;
import com.xdcplus.interaction.common.pojo.vo.RequestVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 取消 流转过程
 * @author Rong.Jia
 * @date 2021/06/07
 */
@Slf4j
@Component
public class CancelProcessTransfor extends BaseProcessTransfor {

    @Override
    public Boolean supportType(Integer flowOption) {
        return Validator.equal(NumberConstant.FOUR, flowOption);
    }

    @Override
    public void postProcess(ProcessTransforParam processTransforParam) {

        log.info("Cancel the processing operation");

        Long userId = processTransforParam.getUserId();
        RequestVO requestVO = processTransforParam.getRequest();

        RequestFlowVO requestFlowVO = super.getCurrentRequestFlow(requestVO.getId(),
                requestVO.getStatus().getId(), userId, null);

        ProcessStatusVO processStatusVO = super.findProcessStatusByMark(Convert.toStr(NumberConstant.ZERO));

        List<RequestFlowVO> requestFlowVOList = super.requestFlowService.findRequestFlowByRequestIdAndFromStatusId(requestVO.getId(), processStatusVO.getId());
        if (ObjectUtil.isNull(requestFlowVOList.stream()
                .filter(a -> ObjectUtil.equal(userId, a.getToUserId()))
                .findAny().orElse(null))) {
            log.error("A list can only be cancelled by the creator");
            throw new InteractionEngineException(ResponseEnum.A_LIST_CAN_ONLY_BE_CANCELLED_BY_THE_CREATOR);
        }

        processStatusVO = super.findProcessStatusByMark(Convert.toStr(processTransforParam.getFlowOption()));

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
                .flowOptionValue(NumberConstant.ONE)
                .fromStatusId(requestVO.getStatus().getId())
                .toStatusId(processStatusVO.getId())
                .fromUserId(userId)
                .toUserId(userId)
                .configVersion(requestVO.getConfigVersion())
                .endTime(DateUtil.current())
                .build();

        super.syncFlow(syncFlow);

        super.updateRequestStatusIdById(requestVO.getId(), processStatusVO.getId());












    }

}
