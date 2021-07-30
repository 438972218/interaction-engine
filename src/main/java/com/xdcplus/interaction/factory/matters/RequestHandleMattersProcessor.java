package com.xdcplus.interaction.factory.matters;

import java.util.Set;

/**
 * 表单办理事项处理器
 *
 * @author Rong.Jia
 * @date 2021/07/21
 */
public interface RequestHandleMattersProcessor {

    /**
     * 支持操作
     * @param handleOption 办理事项
     * @return {@link Boolean} 是否支持
     */
    default Boolean supportType(Integer handleOption) {
        return Boolean.FALSE;
    }

    /**
     * 处理器
     * @param requestHandleMattersParam 办理事项参数
     * @return {@link Set<Long>} 表单ID集合
     */
    default Set<Long> postProcess(RequestHandleMattersParam requestHandleMattersParam) {
        return null;
    }












}
