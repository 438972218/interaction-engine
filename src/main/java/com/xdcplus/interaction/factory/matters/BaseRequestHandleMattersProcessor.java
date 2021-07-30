package com.xdcplus.interaction.factory.matters;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Validator;
import com.xdcplus.interaction.common.pojo.vo.RequestFlowVO;
import com.xdcplus.interaction.service.RequestFlowService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 表单办理事项处理器-公共
 *
 * @author Rong.Jia
 * @date 2021/07/21
 */
public abstract class BaseRequestHandleMattersProcessor implements RequestHandleMattersProcessor {

    @Autowired
    RequestFlowService requestFlowService;

    /**
     * 获取流转信息
     *
     * @param roleIds 角色id
     * @param userId  用户id
     * @return {@link List<RequestFlowVO>} 流转信息
     */
    List<RequestFlowVO> getRequestFlows(Set<Long> roleIds, Long userId) {
        return requestFlowService.findRequestFlowByRoleIdsOrUserIds(roleIds, userId);
    }

    /**
     * 获取表单ID集合
     *
     * @param requestFlowVOList 流转信息集合
     * @param flowOptions       操作
     * @param flag true: 匹配flowOptions, false: 反之
     * @return {@link Set<Long>} 表单ID集合
     */
    Set<Long> getRequestIds(List<RequestFlowVO> requestFlowVOList, Integer flowOptions, Boolean flag) {

        if (CollectionUtil.isNotEmpty(requestFlowVOList)) {

            return requestFlowVOList.stream()
                    .filter(b -> {
                        if (flag) {
                            return Validator.equal(flowOptions, b.getFlowOption().getValue());
                        }else {
                            return !Validator.equal(flowOptions, b.getFlowOption().getValue());
                        }
                    }).map(c -> c.getRequest().getId()).collect(Collectors.toSet());

        }

        return null;
    }






}
