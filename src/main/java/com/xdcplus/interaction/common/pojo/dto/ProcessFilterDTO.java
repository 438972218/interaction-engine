package com.xdcplus.interaction.common.pojo.dto;

import com.xdcplus.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 *  流程 过滤DTO
 *
 * @author Rong.Jia
 * @date 2021/06/01
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("流程 过滤对象 对照对象")
public class ProcessFilterDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = -1683932204602235309L;

    /**
     * 流程名
     */
    @ApiModelProperty(value = "流程名")
    private String name;













}
