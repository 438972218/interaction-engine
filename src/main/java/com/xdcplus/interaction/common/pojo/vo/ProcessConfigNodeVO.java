package com.xdcplus.interaction.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 过程配置节点VO
 *
 * @author Rong.Jia
 * @date 2021/06/17
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("流程配置-节点信息 对照对象")
public class ProcessConfigNodeVO extends BaseBO implements Serializable {

    private static final long serialVersionUID = 8114964092623522792L;

    /**
     * 用户去向
     */
    @ApiModelProperty(value = "用户去向")
    private Long userTo;

    /**
     * 流程主键
     */
    @ApiModelProperty("流程主键")
    private Long processId;

    /**
     * 流程状态标识
     */
    @ApiModelProperty("流程状态标识")
    private Long statusMark;

    /**
     * 节点类型，开始: 0;结束: -1;一般: 1;会签节点:2;条件判断节点：3;查阅节点：4;子流程节点：5
     */
    @ApiModelProperty("节点类型 (详见数据字典)")
    private Integer type;

    /**
     * 位置左， 单位"px"
     */
    @ApiModelProperty("位置左， 单位\"px\"")
    private String left;

    /**
     * 下， 单位"px"
     */
    @ApiModelProperty("下， 单位\"px\"")

    private String top;

    /**
     * 图标
     */
    @ApiModelProperty("图标")
    private String ico;

    /**
     * 状态， success: 成功，warning: 警告，error: 错误，running：运行中
     */
    @ApiModelProperty("态， success: 成功，warning: 警告，error: 错误，running：运行中")
    private String state;

    /**
     * 版本
     */
    @ApiModelProperty("版本")
    private String version;




}
