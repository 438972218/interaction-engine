package com.xdcplus.interaction.factory.oddrule;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.xdcplus.tool.constants.NumberConstant;
import org.springframework.stereotype.Component;

/**
 * 单号算法的处理器(随机规则)
 * @author Rong.Jia
 * @date 2021/05/31
 */
@Component
public class RandomAlgorithmProcessor extends BaseOddAlgorithmProcessor {

    @Override
    public String postProcess(Long oddRuleId, String prefix, Long autoNumber) {

        return jointOddNumber(prefix, StrUtil.sub(IdUtil.fastSimpleUUID(),
                NumberConstant.ZERO, TOTAL_NUMBER_OF_ORDER_NUMBERS));
    }

    @Override
    public Boolean supportType(Integer algorithm) {
        return ObjectUtil.equal(NumberConstant.TWO, algorithm);
    }
}
