package com.xdcplus.interaction.factory.matters;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Validator;
import com.xdcplus.interaction.common.pojo.vo.RequestFlowVO;
import com.xdcplus.tool.constants.NumberConstant;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 监察事项处理器
 *
 * @author Rong.Jia
 * @date 2021/07/22
 */
@Component
public class SupervisoryMattersProcessor extends BaseRequestHandleMattersProcessor {

    @Override
    public Boolean supportType(Integer handleOption) {
        return Validator.equal(NumberConstant.FIVE, handleOption);
    }

    @Override
    public Set<Long> postProcess(RequestHandleMattersParam requestHandleMattersParam) {

        List<RequestFlowVO> requestFlowVOList = getRequestFlows(requestHandleMattersParam.getRoleIds(),
                requestHandleMattersParam.getUserId());

        if (CollectionUtil.isNotEmpty(requestFlowVOList)) {
            return requestFlowVOList.stream()
                    .map(a -> a.getRequest().getId())
                    .collect(Collectors.toSet());
        }

        return null;
    }
}
