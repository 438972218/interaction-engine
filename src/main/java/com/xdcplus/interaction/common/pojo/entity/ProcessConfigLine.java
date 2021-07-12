package com.xdcplus.interaction.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 流程配置线信息
 *
 * @author Rong.Jia
 * @date  2021-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("xdc_t_process_config_line")
public class ProcessConfigLine implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 流程主键
     */
    private Long processId;

    /**
     * 状态标识
     */
    private String fromMark;

    /**
     * 状态标识
     */
    private String toMark;

    /**
     * 版本
     */
    private String version;

    /**
     * 标签
     */
    private String label;

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
