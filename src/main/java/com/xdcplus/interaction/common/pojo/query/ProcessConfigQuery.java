package com.xdcplus.interaction.common.pojo.query;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 *  流程配置查询对象
 *
 * @author Rong.Jia
 * @date 2021/06/01
 */
@Data
public class ProcessConfigQuery implements Serializable {

    private static final long serialVersionUID = -7536844701008843422L;

    /**
     * 流程明细表ID
     */
    private Long processId;

    /**
     * 上一个流程状态ID
     */
    private Long fromStatusId;

    /**
     * 下一个流程状态ID
     */
    private Long toStatusId;

    /**
     * 角色ID
     */
    private Long toRoleId;

    /**
     * 用户ID
     */
    private Long toUserId;

    /**
     *  主键
     */
    private Set<Long> ids;

    /**
     * 是否归档
     */
    private Boolean whetherArchive;

    /**
     * 归档标识
     */
    private Long archiveId;










}
