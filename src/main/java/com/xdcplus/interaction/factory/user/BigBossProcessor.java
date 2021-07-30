package com.xdcplus.interaction.factory.user;

import cn.hutool.core.lang.Validator;
import com.xdcplus.tool.constants.NumberConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 老板处理器
 *
 * @author Rong.Jia
 * @date 2021/07/19
 */
@Slf4j
@Component
public class BigBossProcessor extends BaseUserDestinationProcessor {

    @Override
    public Boolean supportType(Integer userToType) {
        return Validator.equal(NumberConstant.THREE, userToType);
    }

    @Override
    public Long postProcess(UserDestinationParam userDestinationParam) {

        log.info("Boss processing");

        return httpService.getBigBossUser();

    }
}
