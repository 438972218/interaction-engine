package com.xdcplus.interaction.common.pojo.bo;

import com.xdcplus.interaction.common.pojo.entity.*;
import com.xdcplus.interaction.common.pojo.entity.Process;
import com.xdcplus.tool.pojo.bo.BaseBO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 过程配置BO
 * @author Rong.Jia
 * @date 2021/06/02
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProcessConfigBO extends BaseBO implements Serializable {

    private static final long serialVersionUID = 6837194986740115625L;

    /**
     * 用户ID
     */
    private Long toUserId;

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
     * 角色id
     */
    private Long toRoleId;

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
     * 流程信息
     */
    private Process process;

    /**
     * 上一个流程状态
     */
    private ProcessStatus fromStatus;

    /**
     * 下一个流程状态
     */
    private ProcessStatus toStatus;

    /**
     * 流程规则
     */
    private Qualifier qualifier;

















}
