package com.xdcplus.interaction.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 过程配置VO
 *
 * @author Rong.Jia
 * @date 2021/06/02
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("过程配置信息 对照对象")
public class ProcessConfigVO extends BaseBO implements Serializable {

    private static final long serialVersionUID = 8736687621031018695L;

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
     * 流程信息
     */
    @ApiModelProperty("流程信息")
    private ProcessVO process;

    /**
     * 上一个流程状态
     */
    @ApiModelProperty("上一个流程状态 ")
    private ProcessStatusVO fromStatus;

    /**
     * 下一个流程状态
     */
    @ApiModelProperty("下一个流程状态 ")
    private ProcessStatusVO toStatus;

    /**
     * 流程规则
     */
    @ApiModelProperty("流程规则 ")
    private QualifierVO qualifier;

    /**
     *  超时时间（超时后可流转下一节点）默认24小时， 单位：毫秒
     */
    @ApiModelProperty("超时时间（超时后可流转下一节点）默认24小时")
    private Long timeoutAction;

    /**
     *  版本号
     */
    @ApiModelProperty("版本号")
    private String version;











}
