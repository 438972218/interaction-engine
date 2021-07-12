package com.xdcplus.interaction.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 流程操作VO
 *
 * @author Rong.Jia
 * @date 2021/06/01
 */
@ApiModel("流程操作信息 对照对象")
@EqualsAndHashCode(callSuper = true)
@Data
public class FlowOptionVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 2481128740881521730L;

    /**
     * 数字
     */
    @ApiModelProperty("数字")
    private Integer value;

    /**
     * 数字含义
     */
    @ApiModelProperty("数字含义")
    private String valueString;














}
