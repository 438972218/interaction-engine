package com.xdcplus.interaction.common.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 过程配置线 DTO
 *
 * @author Rong.Jia
 * @date 2021/06/17
 */
@Data
@ApiModel("流程配置-线参数 对照对象")
public class ProcessConfigLineDTO implements Serializable {

    private static final long serialVersionUID = 8891770877321266273L;

    /**
     *  线的开始
     */
    @NotNull(message = "开始 不能为空")
    @ApiModelProperty(value = "线的开始", required = true)
    private String from;

    /**
     * 线的结束
     */
    @NotNull(message = "结束 不能为空")
    @ApiModelProperty(value = "线的结束", required = true)
    private String to;

    /**
     * 标签
     */
    @ApiModelProperty("标签")
    private String label;



}
