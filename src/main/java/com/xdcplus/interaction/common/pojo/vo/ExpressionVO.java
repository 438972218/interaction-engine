package com.xdcplus.interaction.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 表达式VO
 *
 * @author Rong.Jia
 * @date 2021/06/01
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("表达式信息参照对象")
public class ExpressionVO extends BaseBO implements Serializable {

    private static final long serialVersionUID = 426736831536065556L;

    /**
     * 标识含义
     */
    @ApiModelProperty(value = "标识含义")
    private String name;

    /**
     * 标识类型
     */
    @ApiModelProperty(value = "标识类型")
    private Integer type;

    /**
     * 标识
     */
    @ApiModelProperty("标识")
    private String symbol;




}
