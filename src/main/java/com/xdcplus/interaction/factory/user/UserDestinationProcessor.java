package com.xdcplus.interaction.factory.user;

/**
 * 用户去向处理器
 *
 * @author Rong.Jia
 * @date 2021/07/19
 */
public interface UserDestinationProcessor {

    /**
     * 支持操作
     * @param userToType 去向类型
     * @return {@link Boolean} 是否支持
     */
    default Boolean supportType(Integer userToType) {
        return Boolean.FALSE;
    }

    /**
     * 处理器
     * @param userDestinationParam 用户去向参数
     * @return {@link Long} 用户标识
     */
    default Long postProcess(UserDestinationParam userDestinationParam) {
        return null;
    }










}
