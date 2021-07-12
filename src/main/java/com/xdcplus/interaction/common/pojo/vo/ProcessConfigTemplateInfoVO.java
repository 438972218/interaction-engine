package com.xdcplus.interaction.common.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 流程配置模板信息VO
 *
 * @author Rong.Jia
 * @date 2021/06/22
 */
@Data
@ApiModel("流程配置模板信息 对照对象")
public class ProcessConfigTemplateInfoVO implements Serializable {

    private static final long serialVersionUID = -8288555429215680998L;

    /**
     * 模板信息
     */
    @ApiModelProperty(value = "模板信息")
    private ProcessConfigTemplateVO template;

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
