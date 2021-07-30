package com.xdcplus.interaction.service;

import com.xdcplus.interaction.perm.domain.Department;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * HTTP 调用业务层接口
 *
 * @author Rong.Jia
 * @date 2021/07/20
 */
public interface HttpService {

    /**
     *  获取总经理用户标识
     * @return 总经理用户标识
     */
    Long getBigBossUser();

    /**
     * 根据用户标识/用户名获取上级的用户标识
     * @param userId 用户标识
     * @param username 用户名
     * @return 上级的用户标识
     */
    Long getSuperiorUserByUser(Long userId, String username);

    /**
     * 获取部门信息
     * @return 部门信息
     */
    List<Department> getDepartments();

    /**
     * 根据部门标识获取部门负责人标识
     * @param departmentId 部门标识
     * @return 部门负责人标识
     */
    Long getDepartmentHeadByDepartmentId(Long departmentId);

    /**
     * 根据用户名获取用户标识
     * @param username 用户名
     * @return 用户标识
     */
    Long getUserInfoByName(String username);

    /**
     * 刷新部门缓存
     */
    void refreshDepartmentsCache();

















}
