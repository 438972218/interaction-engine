package com.xdcplus.interaction.common.pojo.dto;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 *  流程 dto
 *
 * @author Rong.Jia
 * @date 2021/06/01
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("流程 参数对照对象")
public class ProcessDTO extends BaseBO implements Serializable {

    private static final long serialVersionUID = -1683932204602235309L;

    /**
     * 流程名
     */
    @NotBlank(message = "流程名")
    @ApiModelProperty(value = "流程名", required = true)
    private String name;













}
