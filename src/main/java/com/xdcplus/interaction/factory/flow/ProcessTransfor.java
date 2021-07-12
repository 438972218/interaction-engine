package com.xdcplus.interaction.factory.flow;

import cn.hutool.core.lang.Validator;
import com.xdcplus.tool.constants.NumberConstant;

/**
 *  流转转移过程
 * @author Rong.Jia
 * @date 2021/06/07
 */
public interface ProcessTransfor {

    /**
     *  流转处理类
     * @param processTransforParam  流转参数
     */
    default void postProcess(ProcessTransforParam processTransforParam) {
    }

    /**
     * 支持的操作
     * @param flowOption 算法
     * @return {@link Boolean} 是否支持
     */
    default Boolean supportType(Integer flowOption) {
        return Boolean.FALSE;
    }

    /**
     * 是否执行条件
     * @param qualifierId  流程规则ID
     * @return {@link Boolean}
     */
    default Boolean isExecutionCondition(Long qualifierId) {
        return Validator.equal(NumberConstant.A_NEGATIVE.longValue(), qualifierId);
    }





}