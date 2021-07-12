package com.xdcplus.interaction.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 流程配置线模板
 *
 * @author Rong.Jia
 * @date  2021-06-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("xdc_t_process_config_line_template")
public class ProcessConfigLineTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 开始
     */
    private String fromMark;

    /**
     * 目标
     */
    private String toMark;

    /**
     * 模板ID
     */
    private Long templateId;

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
