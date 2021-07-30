package com.xdcplus.interaction.common.utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.StrUtil;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 版本工具类
 *
 * @author Rong.Jia
 * @date 2021/07/17 20:02:11
 */
public class VersionUtils {

    private static final String DEFAULT_VERSION = "1.0.0.0";
    private static final Integer HEXADECIMAL_NUMBER = 10;
    private static final Integer DIGITS = 4;

    /**
     * 升级版本号
     *
     * @param version 版本号
     * @return {@link String} 版本号
     */
    public static String upgradeVersion(String version) {
        String upgradeVersion;
        if (StrUtil.isBlank(version)) {
            upgradeVersion =DEFAULT_VERSION;
        }else {
            List<Integer> versionNumbers = StrUtil.split(version, CharUtil.DOT).stream().map(Convert::toInt).collect(Collectors.toList());

            //递归调用
            upgradeVersion(versionNumbers, versionNumbers.size() - 1);

            //数组转字符串
            upgradeVersion = CollectionUtil.join(versionNumbers, StrUtil.DOT);
        }

        return upgradeVersion;
    }

    /**
     * 升级版本
     *
     * @param versionNumbers 版本号
     * @param index          索引
     */
    private static void upgradeVersion(List<Integer> versionNumbers, int index) {

        if (index == 0) {
            versionNumbers.set(0, versionNumbers.get(0) + 1);
        } else {
            int value = versionNumbers.get(index)+ 1;
            if (value < HEXADECIMAL_NUMBER) {
                versionNumbers.set(index, value);
            } else {
                versionNumbers.set(index, 0);
                upgradeVersion(versionNumbers, index - 1);
            }
        }
    }

    /**
     * 获取最大版本号
     *
     * @param versionNumbers 版本号集合
     * @return {@link String} 最大版本号
     */
    public static String maxVersion(List<String> versionNumbers) {

        if (CollectionUtil.isEmpty(versionNumbers)) {
            return null;
        }

        return versionNumbers.stream()
                .max(Comparator.naturalOrder()).orElse(DEFAULT_VERSION);
    }



}
