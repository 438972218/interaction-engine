package com.xdcplus.interaction.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 流程操作表
 * @author Rong.Jia
 * @date  2021-05-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("xdc_t_flow_option")
public class FlowOption implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 数字
     */
    private Integer value;

    /**
     * 数字含义
     */
    private String valueString;

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
