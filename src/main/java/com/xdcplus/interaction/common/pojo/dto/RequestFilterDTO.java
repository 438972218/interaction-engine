package com.xdcplus.interaction.common.pojo.dto;

import com.xdcplus.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 *  表单 过滤查询DTO
 * @author Rong.Jia
 * @date 2021/06/02
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("表单过滤查询参数 对照对象")
public class RequestFilterDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = 4902882783377028458L;

    /**
     * 表单ID
     */
    @ApiModelProperty(value = "表单ID")
    private Long id;

    /**
     *  流程ID
     */
    @ApiModelProperty(value = "流程ID")
    private Long processId;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     *  父级表单ID
     */
    @ApiModelProperty(value = "父级表单ID")
    private Long parentId;

    /**
     *  单号， 可模糊查询
     */
    @ApiModelProperty(value = "单号 可模糊查询")
    private String oddNumber;

}
