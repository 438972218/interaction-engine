package com.xdcplus.interaction.factory.user;

import cn.hutool.core.lang.Validator;
import com.xdcplus.tool.constants.NumberConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * xxx的上级处理
 *
 * @author Rong.Jia
 * @date 2021/07/19
 */
@Slf4j
@Component
public class SuperiorProcessor extends BaseUserDestinationProcessor {

    @Override
    public Boolean supportType(Integer userToType) {
        return Validator.equal(NumberConstant.ONE, userToType) ||
                Validator.equal(NumberConstant.TWO, userToType);
    }

    @Override
    public Long postProcess(UserDestinationParam userDestinationParam) {

        log.info("XXX superior person to handle");

        Long userToType = userDestinationParam.getUserToType();

        Long userMark = null;

        if (Validator.equal(NumberConstant.ONE.longValue(), userToType)) {
            userMark = httpService.getSuperiorUserByUser(null, userDestinationParam.getCreateUserName());
        }else if (Validator.equal(NumberConstant.TWO.longValue(), userToType)) {
            userMark = httpService.getSuperiorUserByUser(userDestinationParam.getUserId(), null);
        }

        return userMark;

    }

}
