package com.xdcplus.interaction.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 过程配置模板VO
 *
 * @author Rong.Jia
 * @date 2021/06/23
 */
@ApiModel("流程配置模板信息 对照对象")
@EqualsAndHashCode(callSuper = true)
@Data
public class ProcessConfigTemplateVO extends BaseBO implements Serializable {

    private static final long serialVersionUID = 8073704744638349101L;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;


}
