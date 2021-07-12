package com.xdcplus.interaction.factory.algorithm;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.xdcplus.tool.constants.NumberConstant;
import org.springframework.stereotype.Component;

/**
 * 单号算法的处理器(随机规则)
 * @author Rong.Jia
 * @date 2021/05/31
 */
@Component
public class RandomAlgorithmProcessor implements OddAlgorithmProcessor {

    @Override
    public String postProcess(Long oddRuleId, String prefix, Long autoNumber) {
        return prefix.toUpperCase() + IdUtil.fastSimpleUUID();
    }

    @Override
    public Boolean supportType(Integer algorithm) {
        return ObjectUtil.equal(NumberConstant.TWO, algorithm);
    }
}
