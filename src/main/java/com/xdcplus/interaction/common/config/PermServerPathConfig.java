package com.xdcplus.interaction.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 权限服务路径配置
 *
 * @author Rong.Jia
 * @date 2021/07/19
 */
@Data
@Component
@ConfigurationProperties(prefix = "url.path")
public class PermServerPathConfig {

    /**
     * 根据用户获取上级用户信息
     */
    private String superiorUserByUser;

    /**
     * 获取总经理用户信息
     */
    private String bigBossUser;

    /**
     *  获取部门信息
     */
    private String departments;

    /**
     *  根据部门ID 获取部门负责人信息
     */
    private String departmentHeadByDepartmentId;

    /**
     * 根据用户名获取用户信息
     */
    private String userInfoByName;


}
