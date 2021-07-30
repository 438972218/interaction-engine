package com.xdcplus.interaction.factory.oddrule;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.xdcplus.tool.constants.NumberConstant;
import com.xdcplus.interaction.service.OddRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 单号算法的处理器(时间+自增长规则)
 * @author Rong.Jia
 * @date 2021/05/31
 */
@Component
public class AutoIncrementDateAlgorithmProcessor extends BaseOddAlgorithmProcessor {

    @Override
    public String postProcess(Long oddRuleId, String prefix, Long autoNumber) {

        String date = DateUtil.format(new Date(), DatePattern.PURE_DATE_FORMAT);
        Long next = autoNumber + NumberConstant.ONE;
        oddRuleService.updateAutoNumber(oddRuleId, next);

        return jointOddNumber(prefix, date, fillDigits(next, WATER_DIGITS, Boolean.TRUE));
    }

    @Override
    public Boolean supportType(Integer algorithm) {
        return ObjectUtil.equal(NumberConstant.FOUR, algorithm);
    }
}
