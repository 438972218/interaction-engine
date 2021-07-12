package com.xdcplus.interaction.common.pojo.dto;

import com.xdcplus.tool.pojo.bo.BaseBO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 流转DTO
 * @author Rong.Jia
 * @date 2021/06/07
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RequestFlowDTO extends BaseBO implements Serializable {

    private static final long serialVersionUID = 6593860846844381019L;

    /**
     *  表单ID
     */
    private Long requestId;

    /**
     *  上一个状态ID
     */
    private Long fromStatusId;

    /**
     *  下一个状态ID
     */
    private Long toStatusId;

    /**
     *  角色ID
     */
    private Long toRoleId;

    /**
     * 用户ID
     */
    private Long toUserId;

    /**
     * 流程操作
     */
    private Integer flowOptionValue;

    /**
     * 开始时间
     */
    private Long beginTime;

    /**
     * 结束时间
     */
    private Long endTime;

    /**
     *  分配者ID
     */
    private Long fromUserId;

    /**
     *  流程配置版本
     */
    private String configVersion;















}
