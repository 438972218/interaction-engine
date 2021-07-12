package com.xdcplus.interaction.factory.user;

import cn.hutool.core.lang.Validator;
import com.xdcplus.interaction.perm.ResponseUtils;
import com.xdcplus.tool.constants.NumberConstant;

/**
 * 用户去向处理
 *
 * @author Rong.Jia
 * @date 2021/07/01
 */
public class UserToProcess {

    /**
     * 获取去向用户标识
     * @param toUserId  去向用户标识
     * @param userId  审批用户标识
     * @param requestCreatedUser  表单创建人
     * @return  用户标识
     */
    public static Long getToUserId(Long toUserId, Long userId, String requestCreatedUser) {

        Long userMark = null;

        if (Validator.equal(NumberConstant.ONE.longValue(), toUserId)) {
            userMark = ResponseUtils.getSysUserByUserIdOrUserName(null, requestCreatedUser);
        }else if (Validator.equal(NumberConstant.TWO.longValue(), toUserId)) {
            userMark = ResponseUtils.getSysUserByUserIdOrUserName(toUserId, null);
        }else if (Validator.equal(NumberConstant.THREE.longValue(), toUserId)) {

        }else if (Validator.equal(NumberConstant.FOUR.longValue(), toUserId)) {

        }

        return userMark;
    }





}
