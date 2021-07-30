package com.xdcplus.interaction.common.pojo.dto;

import com.xdcplus.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 表单办理事项
 *
 * @author Rong.Jia
 * @date 2021/07/21
 */
@ApiModel("表单办理事项过滤查询 参数对照对象")
@EqualsAndHashCode(callSuper = true)
@Data
public class HandleMattersFilterDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = 6030086238130487120L;

    /**
     *  角色标识集合
     */
    @ApiModelProperty("角色标识集合")
    private Set<Long> roleIds;

    /**
     *  用户ID
     */
    @ApiModelProperty("用户标识")
    private Long userId;

    /**
     *  办理事项
     *  1 ：我的事项
     *  2 ：未办事项
     *  3 ：已办事项
     *  4 ：历史事项
     *  5 ：督办事项
     */
    @NotNull(message = "办理事项 不能为空")
    @ApiModelProperty(value = " 办理事项， 详见数据字典", required = true)
    private Integer handleOption;

    /**
     * 流程状态ID
     */
    @ApiModelProperty("流程状态ID")
    private Long statusId;

    /**
     * 流程信息ID
      */
    @ApiModelProperty("流程信息ID")
    private Long processId;

    /**
     *  创建人
     */
    @ApiModelProperty("创建人")
    private String createdUser;










}
