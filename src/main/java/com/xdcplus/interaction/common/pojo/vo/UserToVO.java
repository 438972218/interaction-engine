package com.xdcplus.interaction.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户去向VO
 *
 * @author Rong.Jia
 * @date 2021/07/01
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("用户去向信息 对照对象")
public class UserToVO extends BaseBO implements Serializable {

    private static final long serialVersionUID = -5106762225366398037L;

    /**
     * 标识
     */
    @ApiModelProperty("标识")
    private Integer mark;

    /**
     * 表达式
     */
    @ApiModelProperty("表达式")
    private String expression;



}
