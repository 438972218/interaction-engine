package com.xdcplus.interaction.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 流程配置节点信息
 *
 * @author Rong.Jia
 * @date  2021-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("xdc_t_process_config_node")
public class ProcessConfigNode implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *  主键
     */
    private Long id;

    /**
     * 流程主键
     */
    private Long processId;

    /**
     * 流程状态标识
     */
    private String statusMark;

    /**
     * 节点类型，开始: 0;结束: -1;一般: 1;会签节点:2;条件判断节点：3;查阅节点：4;子流程节点：5
     */
    private Integer type;

    /**
     * 位置左， 单位"px"
     */
    private String locationLeft;

    /**
     * 下， 单位"px"
     */
    private String locationTop;

    /**
     * 图标
     */
    private String ico;

    /**
     * 状态， success: 成功，warning: 警告，error: 错误，running：运行中
     */
    private String state;

    /**
     * 版本
     */
    private String version;

    /**
     * 添加人
     */
    private String createdUser;

    /**
     * 修改人
     */
    private String updatedUser;

    /**
     * 创建时间
     */
    private Long createdTime;

    /**
     * 修改时间
     */
    private Long updatedTime;

    /**
     * 描述
     */
    private String description;


}
