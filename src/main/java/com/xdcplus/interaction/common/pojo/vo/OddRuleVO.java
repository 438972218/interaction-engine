package com.xdcplus.interaction.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 单号规则VO
 *
 * @author Rong.Jia
 * @date 2021/05/31
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("单号规则信息 对照对象")
public class OddRuleVO extends BaseBO implements Serializable {

    private static final long serialVersionUID = -3900795874504517345L;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

    /**
     * 前缀
     */
    @ApiModelProperty("前缀")
    private String prefix;

    /**
     * 算法（1-时间年月日时分秒毫秒，2-随机，3-自增长，4-时间年月日+自增长）
     */
    @ApiModelProperty("算法(详见数据字典)")
    private Integer algorithm;

    /**
     * 自增长数（自增长算法有效）
     */
    @ApiModelProperty("自增长数（自增长算法有效）")
    private Long autoNumber;

















}
