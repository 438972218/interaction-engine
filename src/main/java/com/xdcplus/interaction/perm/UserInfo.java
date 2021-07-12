package com.xdcplus.interaction.perm;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户信息
 *
 * @author Rong.Jia
 * @date 2021/07/01
 */
@NoArgsConstructor
@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 2466086650516522825L;

    private Long employeeId;
    private String mail;
    private String phone;
    private String realName;
    private String employeeNo;
    private Integer sex;
    private Integer status;
    private String officeAddress;
    private String speciality;
    private String hobby;
    private String officePhone;
    private String office;

}
