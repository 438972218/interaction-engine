package com.xdcplus.interaction.factory.user;

import cn.hutool.core.lang.Validator;
import com.xdcplus.tool.constants.NumberConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 拥有者处理器
 *
 * @author Rong.Jia
 * @date 2021/07/20
 */
@Slf4j
@Component
public class OwnerProcessor extends BaseUserDestinationProcessor {

    @Override
    public Boolean supportType(Integer userToType) {
        return Validator.equal(NumberConstant.FOUR, userToType);
    }

    @Override
    public Long postProcess(UserDestinationParam userDestinationParam) {

        log.info("Owner processing");

        return httpService.getUserInfoByName(userDestinationParam.getCreateUserName());
    }
}
