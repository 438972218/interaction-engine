package com.xdcplus.interaction.common.pojo.dto;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 流程操作VO
 *
 * @author Rong.Jia
 * @date 2021/06/01
 */
@ApiModel("流程操作信息 对照对象")
@EqualsAndHashCode(callSuper = true)
@Data
public class FlowOptionDTO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 2481128740881521730L;

    /**
     * 数字
     */
    @NotNull(message = "数字  不能为空")
    @ApiModelProperty(value = "数字", required = true)
    private Integer value;

    /**
     * 数字含义
     */
    @NotBlank(message = "数字含义  不能为空")
    @ApiModelProperty(value = "数字含义", required = true)
    private String valueString;






    
}
