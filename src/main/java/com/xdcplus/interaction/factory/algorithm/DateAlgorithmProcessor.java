package com.xdcplus.interaction.factory.algorithm;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.xdcplus.tool.constants.NumberConstant;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 单号算法的处理器(时间规则)
 * @author Rong.Jia
 * @date 2021/05/31
 */
@Component
public class DateAlgorithmProcessor implements OddAlgorithmProcessor {

    @Override
    public String postProcess(Long oddRuleId, String prefix, Long autoNumber) {
        String date = DateUtil.format(new Date(), DatePattern.PURE_DATETIME_MS_FORMAT);
        return prefix.toUpperCase() +  date;
    }

    @Override
    public Boolean supportType(Integer algorithm) {
        return ObjectUtil.equal(NumberConstant.ONE, algorithm);
    }
}
