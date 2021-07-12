package com.xdcplus.interaction.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 限定符VO
 * @author Rong.Jia
 * @date 2021/06/02
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("限定规则信息 对照对象")
public class QualifierVO extends BaseBO implements Serializable {

    private static final long serialVersionUID = 4079655144529110374L;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

    /**
     * 算法脚本
     */
    @ApiModelProperty("算法脚本")
    private String script;















}
