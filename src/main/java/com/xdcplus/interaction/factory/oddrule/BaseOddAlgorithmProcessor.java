package com.xdcplus.interaction.factory.oddrule;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.xdcplus.interaction.service.OddRuleService;
import com.xdcplus.tool.constants.NumberConstant;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 单号规则-公共
 *
 * @author Rong.Jia
 * @date 2021/07/21
 */
public abstract class BaseOddAlgorithmProcessor implements OddAlgorithmProcessor{

    /**
     *  流水位
     */
    static final Integer WATER_DIGITS = 5;

    /**
     *  时间位数
     */
    static final Integer TIME_DIGITS = 8;

    /**
     * 单号总位数
     */
    static final Integer TOTAL_NUMBER_OF_ORDER_NUMBERS = WATER_DIGITS + TIME_DIGITS;

    @Autowired
    OddRuleService oddRuleService;

    /**
     * 拼接单号
     * @param prefix 前缀
     * @param serialNumbers   流水号
     * @return {@link String} 单号
     */
    String jointOddNumber(String prefix, Object... serialNumbers) {

        StringBuilder oddNumber = new StringBuilder(prefix.toUpperCase());
        if (ArrayUtil.isNotEmpty(serialNumbers)) {
            for (Object serialNumber : serialNumbers) {
                oddNumber.append(serialNumber);
            }
        }

        return oddNumber.toString();
    }

    /**
     * 填补位数
     *
     * @param object 原单号
     * @param length 总长度
     * @param flag   标志。false: 后补位0， true: 前补0
     * @return {@link String}
     */
    String fillDigits(Object object, Integer length, Boolean flag) {

        String number = Convert.toStr(object);
        String zero = Convert.toStr(NumberConstant.ZERO);
        number = flag ? StrUtil.padPre(number, length, zero)
                : StrUtil.padAfter(number, length, zero);

        return number;
    }


}
