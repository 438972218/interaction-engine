package com.xdcplus.interaction.common.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 表单配置DTO
 *
 * @author Rong.Jia
 * @date 2021/06/21
 */
@Data
@ApiModel("表单配置参数 对照对象")
public class RequestConfigDTO implements Serializable {

    private static final long serialVersionUID = 7370145995737129468L;

    /**
     * 表单ID
     */
    @NotNull(message = "表单ID 不能为空")
    @ApiModelProperty(value = "表单ID", required = true)
    private Long requestId;

    /**
     *  流程ID
     */
    @NotNull(message = "流程ID 不能为空")
    @ApiModelProperty(value = "流程ID", required = true)
    private Long processId;

    /**
     *  流程配置版本
     */
    @NotBlank(message = "流程配置版本 不能为空")
    @ApiModelProperty(value = "流程配置版本", required = true)
    private String version;



}
