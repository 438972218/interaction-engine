package com.xdcplus.interaction.factory.flow;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ObjectUtil;
import com.xdcplus.interaction.common.enums.ResponseEnum;
import com.xdcplus.interaction.common.exception.InteractionEngineException;
import com.xdcplus.interaction.common.validator.groupvlidator.ProcessTransforGroupValidator;
import com.xdcplus.tool.constants.NumberConstant;
import com.xdcplus.interaction.common.pojo.vo.ProcessConfigVO;
import com.xdcplus.interaction.common.pojo.vo.ProcessStatusVO;
import com.xdcplus.interaction.common.pojo.vo.RequestVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 提交 流转过程
 * @author Rong.Jia
 * @date 2021/06/07
 */
@Slf4j
@Component
public class SubmitProcessTransfor extends BaseProcessTransfor {

    @Override
    public Boolean supportType(Integer flowOption) {
        return Validator.equal(NumberConstant.THREE, flowOption);
    }

    @Override
    public void postProcess(ProcessTransforParam processTransforParam) {

        RequestVO requestVO = processTransforParam.getRequest();
        Long userId = processTransforParam.getUserId();

        ProcessStatusVO processStatusVO = super.findProcessStatusByMark(Convert.toStr(NumberConstant.ZERO));

        List<ProcessConfigVO> processConfigVOList = super.findConfigByProcessIdAndFromStatusId(requestVO.getProcess().getId(),
                processStatusVO.getId(), requestVO.getConfigVersion(), userId, requestVO.getCreatedUser());

        ProcessConfigVO processConfigVO = processConfigVOList.stream()
                .filter(a -> ObjectUtil.equal(processStatusVO.getId(), a.getFromStatus().getId()))
                .findAny().orElse(null);

        if (ObjectUtil.isNull(processConfigVO)) {
            log.error("A valid process configuration was not found");
            throw new InteractionEngineException(ResponseEnum.A_VALID_PROCESS_CONFIGURATION_WAS_NOT_FOUND);
        }

        SyncFlow syncFlow = SyncFlow.builder()
                .requestId(requestVO.getId())
                .fromStatusId(requestVO.getStatus().getId())
                .toStatusId(processConfigVO.getToStatus().getId())
                .toRoleId(processConfigVO.getToRoleId())
                .fromUserId(userId)
                .toUserId(processConfigVO.getToUserId())
                .flowOptionValue(NumberConstant.A_NEGATIVE)
                .configVersion(requestVO.getConfigVersion())
                .build();

        super.syncFlow(syncFlow);

        super.updateRequestStatusIdById(requestVO.getId(), processConfigVO.getToStatus().getId());

    }


}
