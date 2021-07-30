package com.xdcplus.interaction.factory.matters;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * 表单处理事项 参数
 *
 * @author Rong.Jia
 * @date 2021/07/21
 */
@Data
public class RequestHandleMattersParam implements Serializable {

    private static final long serialVersionUID = 4822755034988137370L;

    /**
     *  角色标识集合
     */
    private Set<Long> roleIds;

    /**
     *  用户ID
     */
    private Long userId;

    /**
     *  办理事项
     *  1 ：我的事项
     *  2 ：未办事项
     *  3 ：已办事项
     *  4 ：历史事项
     *  5 ：督办事项
     */
    private Integer handleOption;













}
