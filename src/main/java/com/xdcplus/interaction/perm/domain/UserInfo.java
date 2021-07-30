package com.xdcplus.interaction.perm.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户信息
 *
 * @author Rong.Jia
 * @date 2021/07/01
 */
@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 2466086650516522825L;

    private Long createdTime;
    private String createdUser;
    private Long employeeId;
    private Long id;
    private String lockStatus;
    private Long loginDate;
    private String loginIp;
    private String mail;
    private String name;
    private String password;
    private String phone;
    private String sourceType;
    private String status;
    private Long updatedTime;
    private String updatedUser;
    private String userName;
    private List<Long> sysRoleVoList;
}
