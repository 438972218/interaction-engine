package com.xdcplus.interaction.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 流程配置表
 *
 * @author Rong.Jia
 * @date  2021-05-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("xdc_t_process_config")
public class ProcessConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 流程明细表ID
     */
    private Long processId;

    /**
     * 上一个流程状态ID
     */
    private Long fromStatusId;

    /**
     * 目标流程状态ID
     */
    private Long toStatusId;

    /**
     * 角色ID
     */
    private Long toRoleId;

    /**
     * 用户ID
     */
    private Long toUserId;

    /**
     * 规则条件ID
     */
    private Long qualifierId;

    /**
     *  超时时间（超时后可流转下一节点）默认24小时， 单位：毫秒
     */
    private Long timeoutAction;

    /**
     *  版本号
     */
    private String version;

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
