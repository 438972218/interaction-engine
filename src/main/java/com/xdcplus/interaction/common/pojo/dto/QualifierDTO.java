package com.xdcplus.interaction.common.pojo.dto;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 限定符DTO
 * @author Rong.Jia
 * @date 2021/06/02
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("限定规则参数 对照对象")
public class QualifierDTO extends BaseBO implements Serializable {

    private static final long serialVersionUID = 4079655144529110374L;

    /**
     * 名称
     */
    @NotBlank(message = "名称 不能为空")
    @ApiModelProperty(value = "名称", required = true)
    private String name;

    /**
     * 算法脚本
     */
    @NotBlank(message = "算法脚本 不能为空")
    @ApiModelProperty(value = "算法脚本", required = true)
    private String script;















}
