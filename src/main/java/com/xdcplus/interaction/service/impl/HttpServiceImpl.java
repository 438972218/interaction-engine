package com.xdcplus.interaction.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.http.Method;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xdcplus.cache.redis.RedisUtils;
import com.xdcplus.interaction.common.config.PermServerPathConfig;
import com.xdcplus.interaction.common.constants.CacheConstant;
import com.xdcplus.interaction.common.constants.QueryConstant;
import com.xdcplus.interaction.perm.ResponseUtils;
import com.xdcplus.interaction.perm.domain.Department;
import com.xdcplus.interaction.perm.domain.UserInfo;
import com.xdcplus.interaction.service.HttpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * HTTP 调用业务层接口实现类
 *
 * @author Rong.Jia
 * @date 2021/07/20
 */
@Service
@Slf4j
public class HttpServiceImpl implements HttpService {

    @Autowired
    private PermServerPathConfig permServerPathConfig;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public Long getBigBossUser() {
        String url = ResponseUtils.jointUrl(permServerPathConfig.getBigBossUser());
        return getUserInfoId(url, null, Method.GET, UserInfo.class, QueryConstant.ID);
    }

    @Override
    public Long getSuperiorUserByUser(Long userId, String username) {

        Map<String, Object> params = CollectionUtil.newHashMap();

        Optional.ofNullable(userId).ifPresent(a -> params.put(QueryConstant.ID, a));
        Optional.ofNullable(username).ifPresent(a -> params.put(QueryConstant.USER_NAME, a));

        String url = ResponseUtils.jointUrl(permServerPathConfig.getSuperiorUserByUser());

        return getUserInfoId(url, params, Method.POST, UserInfo.class, QueryConstant.ID);
    }

    @Override
    public List<Department> getDepartments() {

        if (!redisUtils.hasKey(CacheConstant.DEPARTMENTS)) {
            String url = ResponseUtils.jointUrl(permServerPathConfig.getDepartments());
            List<Department> departmentList = ResponseUtils.invocations(url, null, Method.GET, new TypeReference<List<Department>>(){});
            if (CollectionUtil.isNotEmpty(departmentList)) {
                redisUtils.set(CacheConstant.DEPARTMENTS, JSON.toJSONString(departmentList));
            }
        }

        return redisUtils.get(CacheConstant.DEPARTMENTS, new TypeReference<List<Department>>(){});
    }

    @Override
    public Long getDepartmentHeadByDepartmentId(Long departmentId) {

        String url = ResponseUtils.jointUrl(permServerPathConfig.getDepartmentHeadByDepartmentId());
        return getUserInfoId(url, UserInfo.class, QueryConstant.ID, departmentId);
    }

    @Override
    public Long getUserInfoByName(String username) {

        String url = ResponseUtils.jointUrl(permServerPathConfig.getUserInfoByName());
        return getUserInfoId(url, UserInfo.class, QueryConstant.ID, username);
    }

    @Override
    public void refreshDepartmentsCache() {

        List<Department> departmentList = ResponseUtils.invocations(ResponseUtils.jointUrl(permServerPathConfig.getDepartments()),
                null, Method.GET, new TypeReference<List<Department>>(){});
        if (CollectionUtil.isNotEmpty(departmentList)) {
            redisUtils.set(CacheConstant.DEPARTMENTS, JSON.toJSONString(departmentList));
        }

    }

    /**
     * 获取用户信息标识
     *
     * @param url        url
     * @param params     参数信息
     * @param method     方法
     * @param clazz      整体对象
     * @param fieldName  字段名
     * @return {@link Long} 用户去向
     */
    private <T> Long getUserInfoId(String url, Object params, Method method, Class<T> clazz, String fieldName) {

        T invocations = ResponseUtils.invocations(url, params, method, clazz);
        if (ObjectUtil.isNotNull(invocations)) {
            try {
                return Convert.convert(Long.class, ReflectUtil.getFieldValue(invocations, fieldName));
            }catch (Exception e) {
                log.error("Failed to obtain the value of field {}, exception info {}", fieldName, e.getMessage());
            }

        }

        return null;
    }

    /**
     * 获取用户信息标识
     *
     * @param url        url
     * @param uriVariables URL 参数信息
     * @param <T> 去向对象
     * @param clazz      整体对象
     * @param fieldName  字段名
     * @return {@link Long} 用户去向
     */
    private <T> Long getUserInfoId(String url, Class<T> clazz, String fieldName, Object... uriVariables) {

        T invocations = ResponseUtils.invocations(url, clazz, uriVariables);
        if (ObjectUtil.isNotNull(invocations)) {
            try {
                return Convert.convert(Long.class, ReflectUtil.getFieldValue(invocations, fieldName));
            }catch (Exception e) {
                log.error("Failed to obtain the value of field {}, exception info {}", fieldName, e.getMessage());
            }

        }

        return null;
    }



}
