package com.xdcplus.interaction.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 *  用户去向信息
 *
 * @author Rong.Jia
 * @date  2021-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("xdc_t_user_to")
public class UserTo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 标识
     */
    private Integer mark;

    /**
     * 表达式
     */
    private String expression;

    /**
     * 添加人
     */
    private String createdUser;

    /**
     * 修改人
     */
    private String updatedUser;

    /**
     * 创建时间
     */
    private Long createdTime;

    /**
     * 修改时间
     */
    private Long updatedTime;

    /**
     * 描述
     */
    private String description;


}
