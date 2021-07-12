package com.xdcplus.interaction.common.pojo.dto;

import com.xdcplus.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 流程配置过滤查询对象
 *
 * @author Rong.Jia
 * @date 2021/06/22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("流程配置过滤查询 参数对照对象")
public class ProcessConfigTemplateFilterDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = -6416701832808567465L;

    /**
     *  名称
     */
    @ApiModelProperty(value = "名称")
    private String name;






}
