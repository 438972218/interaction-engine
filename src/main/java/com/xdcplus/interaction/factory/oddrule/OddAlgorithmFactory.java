package com.xdcplus.interaction.factory.oddrule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *  单号算法 工厂类
 *
 * @author Rong.Jia
 * @date 2021/05/31
 */
@Component
public class OddAlgorithmFactory {

    @Autowired
    private List<OddAlgorithmProcessor> oddAlgorithmProcessors;

    /**
     * 算法的处理器
     *
     * @param oddRuleId  单号规则信息主键
     * @param prefix     前缀
     * @param autoNumber 自动数量
     * @param algorithmType 算法（1-时间年月日时分秒毫秒，2-随机，3-自增长，4-时间年月日+自增长）
     * @return {@link String}
     */
    public String algorithmProcessor(Long oddRuleId, Long autoNumber,
                                     String prefix, Integer algorithmType) {

        String odd = null;

        for (OddAlgorithmProcessor oddAlgorithmProcessor : oddAlgorithmProcessors) {
            if (oddAlgorithmProcessor.supportType(algorithmType)) {
                odd = oddAlgorithmProcessor.postProcess(oddRuleId, prefix, autoNumber);
            }
        }

        return odd;
    }












}
