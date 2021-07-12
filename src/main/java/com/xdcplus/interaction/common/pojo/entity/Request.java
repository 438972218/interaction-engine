package com.xdcplus.interaction.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 流程表单
 *
 * @author Rong.Jia
 * @date  2021-05-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("xdc_t_request")
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 单号
     */
    private String oddNumber;

    /**
     *  流程ID
     */
    private Long processId;

    /**
     * 标题
     */
    private String title;

    /**
     * 流程状态ID
     */
    private Long statusId;

    /**
     * 单号规则ID
     */
    private Long ruleId;

    /**
     * 流程配置版本号
     */
    private String configVersion;

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
