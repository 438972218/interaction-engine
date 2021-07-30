package com.xdcplus.interaction.factory.user;

import lombok.Builder;
import lombok.Data;

/**
 * 用户去向参数
 *
 * @author Rong.Jia
 * @date 2021/07/19
 */
@Data
@Builder
public class UserDestinationParam {

    /**
     * 用户去向类型
     */
    private Long userToType;

    /**
     *  用户标识
     */
    private Long userId;

    /**
     * 添加人
     */
    private String createUserName;

    /**
     * 用户去向
     */
    private Long userTo;















}
