package com.xdcplus.interaction.common.pojo.vo;

import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 *  表单VO
 * @author Rong.Jia
 * @date 2021/06/02
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("表单信息 对照对象")
public class RequestVO extends BaseBO implements Serializable {

    private static final long serialVersionUID = 4902882783377028458L;

    /**
     * 单号
     */
    @ApiModelProperty("单号")
    private String oddNumber;

    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;

    /**
     *  表单状态
     */
    @ApiModelProperty("表单状态")
    private ProcessStatusVO status;

    /**
     * 流程信息
     */
    @ApiModelProperty("流程信息")
    private ProcessVO process;

    /**
     * 父级表单信息
     */
    @ApiModelProperty("父级表单信息")
    private List<RequestVO> parent;

    /**
     * 流程配置版本号
     */
    @ApiModelProperty("流程配置版本号")
    private String configVersion;














}
