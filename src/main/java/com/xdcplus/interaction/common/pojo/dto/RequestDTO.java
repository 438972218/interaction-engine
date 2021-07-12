package com.xdcplus.interaction.common.pojo.dto;

import cn.hutool.core.collection.CollectionUtil;
import com.xdcplus.tool.constants.NumberConstant;
import com.xdcplus.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 *  表单DTO
 * @author Rong.Jia
 * @date 2021/06/02
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("表单信息参数 对照对象")
public class RequestDTO extends BaseBO implements Serializable {

    private static final long serialVersionUID = 4902882783377028458L;

    /**
     *  流程ID
     */
    @NotNull(message = "流程ID 不能为空")
    @ApiModelProperty(value = "流程ID", required = true)
    private Long processId;

    /**
     * 标题
     */
    @NotBlank(message = "标题 不能为空")
    @ApiModelProperty(value = "标题", required = true)
    private String title;

    /**
     * 单号规则ID
     */
    @NotNull(message = "单号规则ID 不能为空")
    @ApiModelProperty(value = "单号规则ID", required = true)
    private Long ruleId;

    /**
     *  父级表单
     */
    @ApiModelProperty(value = "父级表单")
    private Set<Long> parentIds;

    /**
     * 流程配置版本号
     */
    @NotBlank(message = "流程配置版本号 不能为空")
    @ApiModelProperty(value = "流程配置版本号", required = true)
    private String configVersion;

    public Set<Long> getParentIds() {

        if (CollectionUtil.isEmpty(parentIds)) {
            parentIds = CollectionUtil.newHashSet();
            parentIds.add(NumberConstant.ZERO.longValue());
        }

        return parentIds;
    }
}
