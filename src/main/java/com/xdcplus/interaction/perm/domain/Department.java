package com.xdcplus.interaction.perm.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 部门信息
 *
 * @author Rong.Jia
 * @date 2021/07/19
 */
@NoArgsConstructor
@Data
public class Department implements Serializable {

    private static final long serialVersionUID = -682377948857501722L;

    private Long id;
    private String createdUser;
    private Long createdTime;
    private String updatedUser;
    private Long updatedTime;
    private Object version;
    private Integer deleted;
    private String shortName;
    private String fullName;
    private Long companyId;
    private Long parentId;
    private Object managerVo;
    private String code;
    private Long manager;
}
