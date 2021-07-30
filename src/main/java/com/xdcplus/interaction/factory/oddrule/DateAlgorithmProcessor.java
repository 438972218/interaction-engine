package com.xdcplus.interaction.factory.oddrule;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.format.FastDateFormat;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.xdcplus.tool.constants.NumberConstant;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 单号算法的处理器(时间规则)
 * @author Rong.Jia
 * @date 2021/05/31
 */
@Component
public class DateAlgorithmProcessor extends BaseOddAlgorithmProcessor {

    private static final FastDateFormat SIMPLE_TIME_FORMAT = FastDateFormat.getInstance("yyMMddHHmmss");

    @Override
    public String postProcess(Long oddRuleId, String prefix, Long autoNumber) {
        String date = DateUtil.format(new Date(), SIMPLE_TIME_FORMAT);
        return jointOddNumber(prefix,
                fillDigits(date + RandomUtil.randomNumbers(NumberConstant.ONE),
                TOTAL_NUMBER_OF_ORDER_NUMBERS, Boolean.FALSE));
    }

    @Override
    public Boolean supportType(Integer algorithm) {
        return ObjectUtil.equal(NumberConstant.ONE, algorithm);
    }
}
