package com.xdcplus.interaction.factory.algorithm;

import cn.hutool.core.util.ObjectUtil;
import com.xdcplus.tool.constants.NumberConstant;
import com.xdcplus.interaction.service.OddRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 单号算法的处理器(自增长规则)
 * @author Rong.Jia
 * @date 2021/05/31
 */
@Component
public class AutoIncrementAlgorithmProcessor implements OddAlgorithmProcessor {

    @Autowired
    private OddRuleService oddRuleService;

    @Override
    public String postProcess(Long oddRuleId, String prefix, Long autoNumber) {

        Long next = autoNumber + NumberConstant.ONE;
        oddRuleService.updateAutoNumber(oddRuleId, next);

        return prefix.toUpperCase() +next;
    }

    @Override
    public Boolean supportType(Integer algorithm) {
        return ObjectUtil.equal(NumberConstant.THREE, algorithm);
    }
}
