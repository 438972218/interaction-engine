package com.xdcplus.interaction.factory.flow;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 同步 流转信息
 * @author Rong.Jia
 * @date 2021/06/09
 */
@Data
@Builder
public class SyncFlow implements Serializable {

    private static final long serialVersionUID = 943015823276880049L;

    /**
     * 表单ID
     */
    private Long requestId;

    /**
     * 流转信息ID
     */
    private Long flowId;

    /**
     * 操作
     */
    private Integer flowOptionValue;

    /**
     * 描述
     */
    private String description;

    /**
     * 上一个状态ID
     */
    private Long fromStatusId;

    /**
     * 下一个状态ID
     */
    private Long toStatusId;

    /**
     * 目标角色ID
     */
    private Long toRoleId;

    /**
     * 目标用户D
     */
    private Long toUserId;

    /**
     * 上一个用户ID
     */
    private Long fromUserId;

    /**
     *  审核时间
     */
    private Long endTime;

    /**
     * 配置版本
     */
    private String configVersion;



}
