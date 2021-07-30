package com.xdcplus.interaction.common.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 过程配置  V2 版本DTO
 * @author Rong.Jia
 * @date 2021/06/17
 */
@Data
@ApiModel("流程配置参数 对照对象")
public class ProcessConfigDTO implements Serializable {

    private static final long serialVersionUID = 6500568181488522166L;

    /**
     * 流程ID
     */
    @NotNull(message = "流程ID 不能为空")
    @ApiModelProperty(value = "流程ID", required = true)
    private Long processId;

    /**
     * 节点信息
     */
    @NotNull(message = "节点信息 不能为空")
    @ApiModelProperty(value = "节点信息", required = true)
    private List<ProcessConfigNodeDTO> nodes;

    /**
     * 线信息
     */
    @NotNull(message = "线信息 不能为空")
    @ApiModelProperty(value = "线信息", required = true)
    private List<ProcessConfigLineDTO> lines;
















}
