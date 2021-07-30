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
 * 历史事件办理事项处理器
 *
 * @author Rong.Jia
 * @date 2021/07/21
 */
@Component
public class HistoricalMattersProcessor extends BaseRequestHandleMattersProcessor {

    @Override
    public Boolean supportType(Integer handleOption) {
        return Validator.equal(NumberConstant.FOUR, handleOption);
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
