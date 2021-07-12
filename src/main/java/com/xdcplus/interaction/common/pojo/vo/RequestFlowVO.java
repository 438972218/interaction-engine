package com.xdcplus.interaction.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 *  流转过程VO
 *
 * @author Rong.Jia
 * @date 2021/06/07
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("流转过程 信息 对照对象")
public class RequestFlowVO extends BaseBO implements Serializable {

    private static final long serialVersionUID = 1072122173481810650L;

    /**
     * 流程信息
     */
    @ApiModelProperty("流程信息")
    private ProcessVO process;

    /**
     * 表单信息
     */
    @ApiModelProperty("表单信息")
    private RequestVO request;

    /**
     * 上一个状态
     */
    @ApiModelProperty("上一个状态信息")
    private ProcessStatusVO fromStatus;

    /**
     * 下一个状态
     */
    @ApiModelProperty("下一个状态")
    private ProcessStatusVO toStatus;

    /**
     * 分配者
     */
    @ApiModelProperty("分配者")
    private Long fromUserId;

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Long toUserId;

    /**
     * 角色ID
     */
    @ApiModelProperty("角色ID")
    private Long toRoleId;

    /**
     * 流程操作
     */
    @ApiModelProperty("流程操作信息")
    private FlowOptionVO flowOption;

    /**
     * 开始审批时间
     */
    @ApiModelProperty("开始审批时间")
    private Long beginTime;

    /**
     * 审批时间
     */
    @ApiModelProperty("审批时间")
    private Long endTime;

    /**
     *  流程配置版本号
     */
    @ApiModelProperty("流程配置版本号")
    private String configVersion;











}
