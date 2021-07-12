package com.xdcplus.interaction.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 *  流程状态VO
 *
 * @author Rong.Jia
 * @date 2021/06/01
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("流程状态信息 对照对象")
public class ProcessStatusVO extends BaseBO implements Serializable {

    private static final long serialVersionUID = -7536844701008843422L;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String name;

    /**
     * 状态标识
     */
    @ApiModelProperty(value = "状态标识")
    private String mark;















}
