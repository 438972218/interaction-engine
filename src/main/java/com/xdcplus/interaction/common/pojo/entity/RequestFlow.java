package com.xdcplus.interaction.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 流转意见表
 *
 * @author Rong.Jia
 * @date  2021-05-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("xdc_t_request_flow")
public class RequestFlow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 表单ID
     */
    private Long requestId;

    /**
     * 上一个状态ID
     */
    private Long fromStatusId;

    /**
     * 下一个状态ID
     */
    private Long toStatusId;

    /**
     * 分配者ID
     */
    private Long fromUserId;

    /**
     * 角色ID
     */
    private Long toRoleId;

    /**
     *  用户ID
     */
    private Long toUserId;

    /**
     * 流程操作
     */
    private Integer flowOptionValue;

    /**
     * 开始时间
     */
    private Long beginTime;

    /**
     * 结束时间
     */
    private Long endTime;

    /**
     *  流程配置版本号
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
