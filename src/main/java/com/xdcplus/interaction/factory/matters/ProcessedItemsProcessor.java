package com.xdcplus.interaction.factory.matters;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Validator;
import com.xdcplus.interaction.common.pojo.vo.RequestFlowVO;
import com.xdcplus.tool.constants.NumberConstant;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * 已办理事项处理器
 *
 * @author Rong.Jia
 * @date 2021/07/21
 */
@Component
public class ProcessedItemsProcessor extends BaseRequestHandleMattersProcessor {

    @Override
    public Boolean supportType(Integer handleOption) {
        return Validator.equal(NumberConstant.THREE, handleOption);
    }

    @Override
    public Set<Long> postProcess(RequestHandleMattersParam requestHandleMattersParam) {

        List<RequestFlowVO> requestFlowVOList = getRequestFlows(requestHandleMattersParam.getRoleIds(),
                requestHandleMattersParam.getUserId());

        return getRequestIds(requestFlowVOList, NumberConstant.A_NEGATIVE, Boolean.FALSE);

    }
}
