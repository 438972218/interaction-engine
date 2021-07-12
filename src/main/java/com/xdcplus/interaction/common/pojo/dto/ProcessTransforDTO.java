package com.xdcplus.interaction.common.pojo.dto;

import com.xdcplus.interaction.common.validator.groupvlidator.ProcessTransforAgreeGroupValidator;
import com.xdcplus.interaction.common.validator.groupvlidator.ProcessTransforCountersignGroupValidator;
import com.xdcplus.interaction.common.validator.groupvlidator.ProcessTransforGroupValidator;
import com.xdcplus.interaction.common.validator.groupvlidator.ProcessTransforSendBackGroupValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 流转DTO
 * @author Rong.Jia
 * @date 2021/06/04
 */
@Data
@ApiModel("流转 参数对照对象")
public class ProcessTransforDTO implements Serializable {

    private static final long serialVersionUID = 6593860846844381019L;

    /**
     * 表单ID
     */
    @NotNull(message = "表单ID 不能为空", groups = ProcessTransforGroupValidator.class)
    @ApiModelProperty(value = "表单ID", required = true)
    private Long requestId;

    /**
     *  流程操作 ，详见流程操作信息
     */
    @NotNull(message = "流程操作 不能为空", groups = ProcessTransforGroupValidator.class)
    @ApiModelProperty(value = "流程操作", required = true)
    private Integer flowOption;

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID 不能为空", groups = ProcessTransforGroupValidator.class)
    @ApiModelProperty(value = "用户ID", required = true)
    private Long userId;

    /**
     *  去向用户标识
     */
    @ApiModelProperty(value = "去向用户标识")
    private Long toUserId;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String description;

    /**
     *  同意操作
     */
    @ApiModelProperty("同意操作")
    private Agree agree = new Agree();

    /**
     *  退回操作
     */
    @ApiModelProperty("退回操作")
    private SendBack sendBack = new SendBack();

    /**
     * 同意操作参数信息
     */
    @Data
    @ApiModel("同意操作参数信息")
    public static class Agree implements Serializable {

        private static final long serialVersionUID = 878241430984786813L;

        /**
         *   用户拥有角色
         */
        @ApiModelProperty(value = "用户拥有角色")
        private List<Long> roleIds;

        /**
         * 流转条件,
         */
        @NotNull(message = "流转条件", groups = ProcessTransforAgreeGroupValidator.class)
        @ApiModelProperty(value = "流转条件")
        private Object flowConditions;

    }

    /**
     * 退回操作参数信息
     */
    @Data
    @ApiModel("退回操作参数信息")
    public static class SendBack implements Serializable {

        private static final long serialVersionUID = -2128087880387998380L;

        /**
         *  退回状态ID
         */
        @NotNull(message = "退回状态ID", groups = ProcessTransforSendBackGroupValidator.class)
        @ApiModelProperty(value = "退回状态ID")
        private Long toStatusId;

    }













}
