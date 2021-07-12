package com.xdcplus.interaction.common.pojo.dto;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 表达式VO
 *
 * @author Rong.Jia
 * @date 2021/06/01
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("表达式信息参照对象")
public class ExpressionDTO extends BaseBO implements Serializable {

    private static final long serialVersionUID = 426736831536065556L;

    /**
     * 标识含义
     */
    @NotBlank(message = "标识含义 不能为空")
    @ApiModelProperty(value = "标识含义", required = true)
    private String name;

    /**
     * 标识类型
     */
    @NotBlank(message = "标识类型 不能为空")
    @ApiModelProperty(value = "标识类型", required = true)
    private Integer type;






}
