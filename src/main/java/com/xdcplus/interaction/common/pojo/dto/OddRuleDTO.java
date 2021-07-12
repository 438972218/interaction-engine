package com.xdcplus.interaction.common.pojo.dto;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 单号的规则dto
 *
 * @author Rong.Jia
 * @date 2021/05/31
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("单号的规则 参数对照对象")
public class OddRuleDTO extends BaseBO implements Serializable {

    private static final long serialVersionUID = 1842038215816708077L;

    /**
     * 名称
     */
    @NotBlank(message = "名称 不能为空")
    @ApiModelProperty(value = "名称", required = true)
    private String name;

    /**
     * 前缀
     */
    @Pattern(regexp = "[a-zA-Z0-9_\\-+@#]{1,35}", message = "前缀格式不正确 只允许 (数字,字母, _-+@#)")
    @NotBlank(message = "前缀 不能为空")
    @ApiModelProperty(value = "前缀", required = true)
    private String prefix;

    /**
     * 算法（1-时间年月日时分秒毫秒，2-随机，3-自增长，4-时间年月日+自增长）
     */
    @NotNull(message = "算法 不能为空")
    @ApiModelProperty(value = "算法(详见数据字典)", required = true)
    private Integer algorithm;

    public String getPrefix() {
        return prefix.toUpperCase();
    }
}
