package com.xdcplus.interaction.perm;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.xdcplus.http.utils.RestTemplateUtils;
import com.xdcplus.interaction.common.config.UrlConfig;
import com.xdcplus.interaction.common.constants.UrlConstant;
import com.xdcplus.tool.constants.NumberConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.lang.Nullable;

import java.util.Map;
import java.util.Optional;

/**
 *  调用响应处理工具类
 *
 * @author Rong.Jia
 * @date 2021/07/01
 */
@Slf4j
public class ResponseUtils {

    private static RestTemplateUtils restTemplateUtils = SpringUtil.getBean(RestTemplateUtils.class);
    private static UrlConfig urlConfig = SpringUtil.getBean(UrlConfig.class);

    /**
     * 查询sys用户通过用户主键或用户的名字
     *       id，name 二选一
     * @param id   主键
     * @param name 账号
     * @return {@link Long}
     */
    public static Long getSysUserByUserIdOrUserName(@Nullable Long id, @Nullable String name) {
        return getSysUserByUserIdOrUserName(id, name, NumberConstant.ZERO);
    }

    /**
     * 查询sys用户通过用户主键或用户的名字
     *       id，name 二选一
     * @param id   主键
     * @param name 账号
     * @param retryCount 重试次数
     * @return {@link Long}
     */
    private static Long getSysUserByUserIdOrUserName(@Nullable Long id, @Nullable String name, int retryCount) {

        Map<String, Object> params = CollectionUtil.newHashMap();

        Optional.ofNullable(id).ifPresent(a -> params.put("id", a));
        Optional.ofNullable(name).ifPresent(a -> params.put("userName", a));

        String url = getUrl(UrlConstant.PERM_CONTROL_ENGINE_IP, UrlConstant.PERM_CONTROL_ENGINE_PORT,
                urlConfig.getPath().getGetSysUserByUserIdOrUserName());

        if (retryCount >= NumberConstant.THREE){
            log.error("Maximum number of retries : url : {}, index : {}", url, retryCount);
            return null;
        }

        try {
            UserInfo userInfo = restTemplateUtils.get(url, params, null, new ParameterizedTypeReference<UserInfo>() {});
            if (ObjectUtil.isNotNull(url) && ObjectUtil.isNotNull(userInfo.getEmployeeId())){
                return userInfo.getEmployeeId();
            }
            retryCount++;
            getSysUserByUserIdOrUserName(id, name, retryCount);
        }catch (Exception e) {
            log.error("getSysUserByUserIdOrUserName {}", e.getMessage());
            retryCount++;
            getSysUserByUserIdOrUserName(id, name, retryCount);
        }

        return null;
    }

    /**
     * 查询url
     *
     * @param ipType   ip类型
     * @param portType 端口类型
     * @return {@link String}  URL
     */
    private static String getUrl(String ipType, String portType, String path) {
        return urlConfig.gainWholeUrl(ipType, portType, path);
    }
















}
