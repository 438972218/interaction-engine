package com.xdcplus.interaction.common.pojo.dto;

import com.xdcplus.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 单号的规则过滤查询dto
 *
 * @author Rong.Jia
 * @date 2021/05/31
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("单号规则过滤查询 对照对象")
public class OddRuleFilterDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = 3412399029988364693L;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 前缀
     */
    @ApiModelProperty(value = "前缀")
    private String prefix;
















}
