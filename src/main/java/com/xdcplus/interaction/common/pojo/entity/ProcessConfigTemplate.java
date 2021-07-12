package com.xdcplus.interaction.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 流程配置模板信息
 *
 * @author Rong.Jia
 * @date  2021-06-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("xdc_t_process_config_template")
public class ProcessConfigTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 模板名
     */
    private String name;

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
