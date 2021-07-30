package com.xdcplus.interaction.perm;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.http.Method;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.xdcplus.http.utils.RestTemplateUtils;
import com.xdcplus.interaction.common.config.UrlConfig;
import com.xdcplus.interaction.common.constants.UrlConstant;
import com.xdcplus.interaction.common.pojo.vo.ResponseVO;
import com.xdcplus.tool.constants.NumberConstant;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

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
     * 重试调用
     *
     * @param url        url
     * @param params     参数
     * @param method     请求方式
     * @param retryCount 重试计数
     * @param clazz      clazz
     * @return {@link T}
     */
    private static <T> T retryInvocations(String url, Object params, Method method, int retryCount, Class<T> clazz) {

        String result = retryInvocations(url, params, method, retryCount);
        if (StrUtil.isNotBlank(result)) {
            return JSONObject.parseObject(result, clazz);
        }

        return null;
    }

    /**
     * 重试调用
     *
     * @param url        url
     * @param params     参数
     * @param method     请求方式
     * @param retryCount 重试计数
     * @return {@link String} 返回信息 JSON字符串
     */
    private static String retryInvocations(String url, Object params, Method method, int retryCount) {

        if (retryCount >= NumberConstant.THREE){
            log.error("Maximum number of retries : url : {}, index : {}", url, retryCount);
            return null;
        }

        try {

            ResponseVO responseVO = null;

            if (Method.GET.equals(method)) {
                Map<String, ?> map = null;
                if (ObjectUtil.isNotNull(params)) {
                    map = JSONObject.parseObject(JSON.toJSONString(params), new TypeReference<Map<String, ?>>(){});
                }
                responseVO = restTemplateUtils.get(url, map, null, ResponseVO.class);
            }else if(Method.POST.equals(method)){
                responseVO = restTemplateUtils.post(url, params, ResponseVO.class);
            }

            if (ObjectUtil.isNotNull(responseVO) && isSuccess(responseVO.getCode())
                    && ObjectUtil.isNotNull(responseVO.getData())) {
                return JSON.toJSONString(responseVO.getData());
            }
            retryCount++;
            retryInvocations(url, params, method, retryCount);
        }catch (Exception e) {
            log.error("getSysUserByUserIdOrUserName {}", e.getMessage());
            retryCount++;
            retryInvocations(url, params, method, retryCount);
        }

        return null;
    }

    /**
     * 重试调用
     *
     * @param url        url
     * @param params     参数
     * @param method     请求方式
     * @param clazz      clazz
     * @return {@link T}
     */
    public static <T> T invocations(String url, Object params, Method method, Class<T> clazz) {
        return retryInvocations(url, params, method, NumberConstant.ZERO, clazz);
    }

    /**
     * 重试调用
     *
     * @param url        url
     * @param params     参数
     * @param method     请求方式
     * @return {@link T}
     */
    public static <T> T invocations(String url, Object params, Method method, TypeReference<T> reference) {

        String result = retryInvocations(url, params, method, NumberConstant.ZERO);
        if (StrUtil.isNotBlank(result)) {
            return JSONObject.parseObject(result, reference);
        }

        return null;
    }

    /**
     * 重试调用
     *
     * @param url        url
     * @param clazz      clazz
     * @param uriVariables URL 参数信息
     * @return {@link T} 返回信息
     */
    public static <T> T invocations(String url, Class<T> clazz, Object... uriVariables) {

        String result = retryInvocations(url, NumberConstant.ZERO, uriVariables);
        if (StrUtil.isNotBlank(result)) {
            return JSONObject.parseObject(result, clazz);
        }

        return null;
    }


    /**
     * 重试调用
     *
     * @param url        url
     * @param reference  返回对象类型
     * @param uriVariables URL 参数信息
     * @return {@link T} 返回信息
     */
    public static <T> T invocations(String url, TypeReference<T> reference, Object... uriVariables) {

        String result = retryInvocations(url, NumberConstant.ZERO, uriVariables);
        if (StrUtil.isNotBlank(result)) {
            return JSONObject.parseObject(result, reference);
        }

        return null;
    }

    /**
     * 重试调用
     *
     * @param url        url
     * @param uriVariables URL 参数信息
     * @param retryCount 重试计数
     * @return {@link String} 返回信息 JSON字符串
     */
    private static String retryInvocations(String url, int retryCount, Object... uriVariables) {

        if (retryCount >= NumberConstant.THREE){
            log.error("Maximum number of retries : url : {}, index : {}", url, retryCount);
            return null;
        }

        try {

            ResponseVO responseVO = restTemplateUtils.get(url, ResponseVO.class, uriVariables);

            if (isSuccess(responseVO.getCode()) && ObjectUtil.isNotNull(responseVO.getData())) {
                return JSON.toJSONString(responseVO.getData());
            }
            retryCount++;
            retryInvocations(url, retryCount, uriVariables);
        }catch (Exception e) {
            log.error("getSysUserByUserIdOrUserName {}", e.getMessage());
            retryCount++;
            retryInvocations(url, retryCount, uriVariables);
        }

        return null;
    }


    /**
     * 判断是否成功
     *
     * @param code 代码
     * @return {@link Boolean} 是否成功
     */
    private static Boolean isSuccess(Integer code) {
        return Validator.equal(NumberConstant.ZERO, code);
    }

    /**
     * 拼接URL
     *
     * @param ipType   ip类型
     * @param portType 端口类型
     * @param path 请求路径
     * @return {@link String}  URL
     */
    public static String jointUrl(String ipType, String portType, String path) {
        return urlConfig.gainWholeUrl(ipType, portType, path);
    }

    /**
     * 拼接URL
     * @param path 请求路径
     * @return {@link String}  URL
     */
    public static String jointUrl(String path) {
        return jointUrl(UrlConstant.PERM_CONTROL_ENGINE_IP,
                UrlConstant.PERM_CONTROL_ENGINE_PORT, path);
    }


















}
