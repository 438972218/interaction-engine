package com.xdcplus.interaction.common.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 配置VO
 *
 * @author Rong.Jia
 * @date 2021/06/23
 */
@Data
public class ConfigInfoVO implements Serializable {

    private static final long serialVersionUID = -4169087166980633397L;

    /**
     * 线信息
     */
    @Data
    @ApiModel("线信息")
    public static class ConfigLineVO implements Serializable{

        private static final long serialVersionUID = 2609570107823955312L;

        /**
         *  线的开始
         */
        @ApiModelProperty(value = "线的开始")
        private String from;

        /**
         * 线的结束
         */
        @ApiModelProperty(value = "线的结束")
        private String to;

    }

    /**
     *  节点信息
     */
    @Data
    @ApiModel("节点信息")
    public static class ConfigNodeVO implements Serializable {

        private static final long serialVersionUID = -3093160118142424953L;

        /**
         * 节点名
         */
        @ApiModelProperty(value = "节点名")
        private String name;

        /**
         *  流程状态标识
         */
        @ApiModelProperty(value = "流程状态标识")
        private String statusMark;

        /**
         * 节点类型-》开始: 0;结束: -1;一般: 1;
         * 会签节点:2;条件判断节点：3;
         * 查阅节点：4;子流程节点：5
         */
        @ApiModelProperty(value = "节点类型 （详见数据字典）")
        private Integer type;

        /**
         * 位置左， 单位"px"
         */
        @ApiModelProperty(value = "位置左， 单位\"px\"")
        private String left;

        /**
         * 下， 单位"px"
         */
        @ApiModelProperty(value = "位置下， 单位\"px\"")
        private String top;

        /**
         * 图标
         */
        @ApiModelProperty(value = "图标")
        private String ico;

        /**
         * 状态， success: 成功，warning: 警告，error: 错误，running：运行中
         */
        @ApiModelProperty(value = "节点状态  success: 成功，warning: 警告，error: 错误，running：运行中")
        private String state;

        /**
         * 超时时间（超时后可流转下一节点）默认24小时， 单位：毫秒
         */
        @ApiModelProperty(value = "超时时间（超时后可流转下一节点）默认24小时， 单位：毫秒")
        public Long timeoutAction;

        /**
         *  用户ID
         */
        @ApiModelProperty("用户ID")
        private Long toUserId;

        /**
         * 角色ID
         */
        @ApiModelProperty(value = "角色ID")
        private Long toRoleId;

        /**
         *  条件
         */
        @ApiModelProperty("条件")
        private QualifierVO condition;

        /**
         * 描述
         */
        @ApiModelProperty("描述")
        private String description;



    }

}
