package com.xdcplus.interaction.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 流程VO对象
 *
 * @author Rong.Jia
 * @date 2021/06/01
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("流程信息 对照对象")
public class ProcessVO extends BaseBO implements Serializable {

    private static final long serialVersionUID = 5765680751939328640L;

    /**
     * 流程名
     */
    @ApiModelProperty(value = "流程名")
    private String name;

}
