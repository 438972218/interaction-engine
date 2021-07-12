package com.xdcplus.interaction.common.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 过程配置过滤查询DTO
 *
 * @author Rong.Jia
 * @date 2021/06/22
 */
@Data
@ApiModel("过程配置过滤查询 对照对象")
public class ProcessConfigFilterDTO implements Serializable {

    private static final long serialVersionUID = 8203074873347721235L;

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
    @ApiModelProperty(value = "流程配置版本")
    private String version;







}
