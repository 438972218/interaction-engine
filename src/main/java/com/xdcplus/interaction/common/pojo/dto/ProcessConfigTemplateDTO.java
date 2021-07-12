package com.xdcplus.interaction.common.pojo.dto;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 流程配置模板信息DTO
 *
 * @author Rong.Jia
 * @date 2021/06/22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("流程配置模板信息 参数对照对象")
public class ProcessConfigTemplateDTO extends BaseBO implements Serializable {

    private static final long serialVersionUID = -3690272525055935329L;

    /**
     *  名称
     */
    @ApiModelProperty(value = "名称", required = true)
    @NotBlank(message = "名称 不能为空")
    private String name;






}
