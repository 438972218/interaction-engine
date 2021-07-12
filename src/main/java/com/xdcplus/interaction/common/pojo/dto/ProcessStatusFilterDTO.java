package com.xdcplus.interaction.common.pojo.dto;

import com.xdcplus.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 *  流程状态过滤查询DTO
 *
 * @author Rong.Jia
 * @date 2021/06/01
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("流程状态过滤查询参数 对照对象")
public class ProcessStatusFilterDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = -7536844701008843422L;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String name;
















}
