package com.xdcplus.interaction.factory.oddrule;

/**
 * 单号算法的处理器
 * @author Rong.Jia
 * @date 2021/05/31
 */
public interface OddAlgorithmProcessor {

    /**
     *  算法具体实现方法
     * @param prefix  前缀
     * @param autoNumber  自增长数
     * @param oddRuleId  规则信息ID
     * @return {@link String} 单号
     */
    default String postProcess(Long oddRuleId, String prefix, Long autoNumber) {
        return prefix.toUpperCase() + autoNumber;
    }

    /**
     * 支持类型
     * （1-时间年月日时分秒毫秒，2-随机，3-自增长，4-时间年月日+自增长）
     * @param algorithm 算法
     * @return {@link Boolean} 是否支持
     */
    default Boolean supportType(Integer algorithm) {
        return Boolean.FALSE;
    }










}
