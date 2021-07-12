package com.xdcplus.interaction.common.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *  数据字典管理结果对照对象
 * @author Rong.Jia
 * @date 2019/01/25 16:18
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@ApiModel("数据字典管理结果对照对象")
public class DictionaryVO  implements Serializable {

    private static final long serialVersionUID = 2592998177425835193L;

    /**
     * 数据字典  (key: 数据字典类别, value:  数据字典信息集合)
     */
    @ApiModelProperty("数据字典 (key: 数据字典类别, value:  数据字典信息集合)")
    private Map<String, List<DictionaryVO>> dataDictionaries;

    /**
     * 含义
     */
    @ApiModelProperty(value = "含义")
    private String meaning;

    /**
     * 数值
     */
    @ApiModelProperty(value = "数值")
    private Integer numerical;

    /**
     * 类别
     */
    @ApiModelProperty(value = "类别")
    private String dictionaryClass;

    /**
     * 类别 中文含义
     */
    @ApiModelProperty(value = "类别 中文含义")
    private String dictionaryChinese;

}
