package com.xdcplus.interaction.factory;

import com.xdcplus.interaction.InteractionEngineApplicationTests;
import com.xdcplus.interaction.factory.user.UserDestinationFactory;
import com.xdcplus.interaction.factory.user.UserDestinationParam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户目标工厂测试类
 *
 * @author Rong.Jia
 * @date 2021/07/19
 */
public class UserDestinationFactoryTest extends InteractionEngineApplicationTests {

    @Autowired
    private UserDestinationFactory userDestinationFactory;

    @Test
    void postProcess() {

        UserDestinationParam userDestinationParam = UserDestinationParam.builder()
                .userToType(5L)
                .userTo(1410181068562178050L)
                .build();

        Long aLong = userDestinationFactory.postProcess(userDestinationParam);
        System.out.println(aLong);

    }
















}
