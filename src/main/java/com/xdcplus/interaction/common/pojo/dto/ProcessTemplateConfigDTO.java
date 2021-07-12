package com.xdcplus.interaction.common.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 流程配置模板信息DTO
 *
 * @author Rong.Jia
 * @date 2021/06/22
 */
@Data
@ApiModel("流程配置模板信息 参数对照对象")
public class ProcessTemplateConfigDTO  implements Serializable {

    private static final long serialVersionUID = -3690272525055935329L;

    /**
     * 模板主键ID
     */
    @NotNull(message = "模板主键ID 不能为空")
    @ApiModelProperty(value = "模板主键ID", required = true)
    private Long templateId;

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
