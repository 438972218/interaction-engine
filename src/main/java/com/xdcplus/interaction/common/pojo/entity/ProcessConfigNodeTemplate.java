package com.xdcplus.interaction.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 流程配置节点模板
 *
 * @author Rong.Jia
 * @date  2021-06-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("xdc_t_process_config_node_template")
public class ProcessConfigNodeTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 节点名
     */
    private String name;

    /**
     * 模板ID
     */
    private Long templateId;

    /**
     * 标识
     */
    private String mark;

    /**
     * 节点类型
     */
    private Integer type;

    /**
     * 位置左， 单位"px"
     */
    private String locationLeft;

    /**
     * 下， 单位"px"
     */
    private String locationTop;

    /**
     * 图标
     */
    private String ico;

    /**
     * 状态， success: 成功，warning: 警告，error: 错误，running：运行中
     */
    private String state;

    /**
     * 条件主键
     */
    private Long qualifierId;

    /**
     * 超时时间（超时后可流转下一节点）默认24小时， 单位：毫秒
     */
    private Long timeoutAction;

    /**
     * 用户ID
     */
    private Long toUserId;

    /**
     * 角色ID
     */
    private Long toRoleId;

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
