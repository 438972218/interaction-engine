package com.xdcplus.interaction.factory.user;

import cn.hutool.core.convert.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户去向工厂类
 *
 * @author Rong.Jia
 * @date 2021/07/19
 */
@Component
public class UserDestinationFactory {

    @Autowired
    private List<UserDestinationProcessor> processors;

    /**
     * 用户去向处理
     * @param userDestinationParam 用户去向参数
     */
    public Long postProcess(UserDestinationParam userDestinationParam) {

        Long userToType = userDestinationParam.getUserToType();

        for (UserDestinationProcessor processor : processors) {
            if (processor.supportType(Convert.toInt(userToType))) {
                return processor.postProcess(userDestinationParam);
            }
        }

        return null;
    }




}
