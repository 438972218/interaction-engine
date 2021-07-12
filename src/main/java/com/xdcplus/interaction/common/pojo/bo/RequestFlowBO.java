package com.xdcplus.interaction.common.pojo.bo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import com.xdcplus.interaction.common.pojo.entity.FlowOption;
import com.xdcplus.interaction.common.pojo.entity.Process;
import com.xdcplus.interaction.common.pojo.entity.ProcessStatus;
import com.xdcplus.interaction.common.pojo.entity.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 *  流转BO对象
 * @author Rong.Jia
 * @date 2021/06/07
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RequestFlowBO extends BaseBO implements Serializable {

    private static final long serialVersionUID = 552854926226850172L;

    /**
     *  上一个状态ID
     */
    private Long fromStatusId;

    /**
     *  下一个状态ID
     */
    private Long toStatusId;

    /**
     * 流程信息
     */
    private Process process;

    /**
     * 表单信息
     */
    private Request request;

    /**
     * 上一个状态
     */
    private ProcessStatus fromStatus;

    /**
     * 下一个状态
     */
    private ProcessStatus toStatus;

    /**
     * 分配者
     */
    private Long fromUserId;

    /**
     *  用户ID
     */
    private Long toUserId;

    /**
     *  角色ID
     */
    private Long toRoleId;

    /**
     * 流程操作
     */
    private FlowOption flowOption;

    /**
     * 开始审批时间
     */
    private Long beginTime;

    /**
     * 审批时间
     */
    private Long endTime;

    /**
     *  流程配置版本号
     */
    private String configVersion;











}
