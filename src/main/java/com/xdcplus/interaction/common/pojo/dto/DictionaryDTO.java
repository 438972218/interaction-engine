package com.xdcplus.interaction.common.pojo.dto;

import com.xdcplus.interaction.common.validator.groupvlidator.DictionaryGroupValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *  数据字典管理 对照对象
 * @author Rong.Jia
 * @date 2019/02/28 15:27:22
 */
@Getter
@Setter
@ToString
@ApiModel("数据字典管理 对照对象")
public class DictionaryDTO implements Serializable {

    private static final long serialVersionUID = -672365384562241959L;

    /**
     * 含义
     */
    @ApiModelProperty(value = "含义", required = true)
    @NotBlank(groups = DictionaryGroupValidator.class, message = "含义 不能为空")
    private String meaning;

    /**
     * 数值
     */
    @ApiModelProperty(value = "数值", required = true)
    @NotNull(groups = DictionaryGroupValidator.class, message = "数值 不能为空")
    private Integer numerical;

    /**
     * 类别
     */
    @ApiModelProperty(value = "类别", required = true)
    @NotBlank(groups = DictionaryGroupValidator.class, message = "类别 不能为空")
    private String dictionaryClass;

    /**
     * 类别
     */
    @ApiModelProperty(value = "类别 中文含义",required = true)
    @NotBlank(groups = DictionaryGroupValidator.class, message = "类别 中文含义 不能为空")
    private String dictionaryChinese;

}
