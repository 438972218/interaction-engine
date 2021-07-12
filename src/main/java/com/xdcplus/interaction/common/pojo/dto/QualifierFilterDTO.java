package com.xdcplus.interaction.common.pojo.dto;

import com.xdcplus.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 限定符DTO
 * @author Rong.Jia
 * @date 2021/06/02
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("限定规则参数 对照对象")
public class QualifierFilterDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = 4079655144529110374L;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;















}
