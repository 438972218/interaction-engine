package com.xdcplus.interaction.common.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 流程配置信息VO
 *
 * @author Rong.Jia
 * @date 2021/06/22
 */
@Data
@ApiModel("流程配置信息 对照对象")
public class ProcessConfigInfoVO implements Serializable {

    private static final long serialVersionUID = -8288555429215680998L;

    /**
     * 流程信息
     */
    @ApiModelProperty(value = "流程信息")
    private ProcessVO process;

    /**
     * 版本
     */
    @ApiModelProperty(value = "流程版本")
    private String version;

    /**
     * 节点信息
     */
    @ApiModelProperty(value = "节点信息")
    private List<ConfigInfoVO.ConfigNodeVO> nodes;

    /**
     * 线信息
     */
    @ApiModelProperty(value = "线信息")
    private List<ConfigInfoVO.ConfigLineVO> lines;





















}
