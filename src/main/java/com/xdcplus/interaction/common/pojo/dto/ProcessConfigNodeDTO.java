package com.xdcplus.interaction.common.pojo.dto;

import cn.hutool.core.date.DateUtil;
import com.xdcplus.tool.constants.NumberConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 过程配置节点 DTO
 *
 * @author Rong.Jia
 * @date 2021/06/17
 */
@Data
@ApiModel("流程配置-节点参数 对照对象")
public class ProcessConfigNodeDTO implements Serializable {

    private static final long serialVersionUID = 2482731485622288714L;

    /**
     * 用户去向ID
     */
    @ApiModelProperty(value = "用户去向ID")
    private Long userTo;

    /**
     * 节点名
     */
    @NotBlank(message = "节点名 不能为空")
    @ApiModelProperty(value = "节点名", required = true)
    private String name;

    /**
     *  流程状态标识
     */
    @NotNull(message = "流程状态标识 不能为空")
    @ApiModelProperty(value = "流程状态标识", required = true)
    private String statusMark;

    /**
     * 节点类型-》开始: 0;结束: -1;一般: 1;
     * 会签节点:2;条件判断节点：3;
     * 查阅节点：4;子流程节点：5
     */
    @NotNull(message = "节点类型 不能为空")
    @ApiModelProperty(value = "节点类型 （详见数据字典）", required = true)
    private Integer type;

    /**
     * 位置左， 单位"px"
     */
    @ApiModelProperty(value = "位置左， 单位\"px\"")
    private String left;

    /**
     * 下， 单位"px"
     */
    @ApiModelProperty(value = "位置下， 单位\"px\"")
    private String top;

    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    private String ico;

    /**
     * 状态， success: 成功，warning: 警告，error: 错误，running：运行中
     */
    @NotBlank(message = "节点状态 不能为空")
    @ApiModelProperty(value = "节点状态  success: 成功，warning: 警告，error: 错误，running：运行中", required = true)
    private String state;

    /**
     * 超时时间（超时后可流转下一节点）默认24小时， 单位：毫秒
     */
    @ApiModelProperty(value = "超时时间（超时后可流转下一节点）默认24小时， 单位：毫秒")
    public Long timeoutAction;

    /**
     *  用户去向标识
     */
    @ApiModelProperty("用户去向标识")
    private Long toUserId;

    /**
     * 角色ID
     */
    @NotNull(message = "角色ID 不能为空")
    @ApiModelProperty(value = "角色ID", required = true)
    private Long toRoleId;

    /**
     *  条件
     */
    @ApiModelProperty("条件")
    private QualifierDTO condition;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String description;

    public Long getTimeoutAction() {
        return timeoutAction == null ? DateUtil.offsetDay(new Date(), NumberConstant.ONE).getTime() : timeoutAction;
    }
}
