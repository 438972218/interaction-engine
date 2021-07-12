package com.xdcplus.interaction.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 过程配置行VO
 *
 * @author Rong.Jia
 * @date 2021/06/17
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("流程配置-线信息 对照对象")
public class ProcessConfigLineVO extends BaseBO implements Serializable {

    private static final long serialVersionUID = -1938927003065245852L;

    /**
     * 流程主键
     */
    @ApiModelProperty("流程主键")
    private Long processId;

    /**
     * 状态标识
     */
    @ApiModelProperty("状态标识")
    private Long fromMark;

    /**
     * 状态标识
     */
    @ApiModelProperty("状态标识")
    private Long toMark;

    /**
     * 版本
     */
    @ApiModelProperty("版本")
    private String version;

    /**
     * 标签
     */
    @ApiModelProperty("标签")
    private String label;










}
