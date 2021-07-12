package com.xdcplus.interaction.common.pojo.dto;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 *  流程状态DTO
 *
 * @author Rong.Jia
 * @date 2021/06/01
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("流程状态参数 对照对象")
public class ProcessStatusDTO extends BaseBO implements Serializable {

    private static final long serialVersionUID = -7536844701008843422L;

    /**
     * 状态
     */
    @NotBlank(message = "状态 不能为空")
    @ApiModelProperty(value = "状态", required = true)
    private String name;
















}
