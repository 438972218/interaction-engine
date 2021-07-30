package com.xdcplus.interaction.factory.matters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * 表单办理事项处理器工厂类
 *
 * @author Rong.Jia
 * @date 2021/07/21
 */
@Component
public class RequestHandleMattersProcessorFactory {

    @Autowired
    private List<RequestHandleMattersProcessor> processors;

    /**
     * 处理
     * @param requestHandleMattersParam 表单办理事项参数
     * @return {@link Set<Long>} 表单ID集合
     */
    public Set<Long> handleMattersProcessor(RequestHandleMattersParam requestHandleMattersParam) {

        Integer handleOption = requestHandleMattersParam.getHandleOption();
        for (RequestHandleMattersProcessor processor : processors) {
            if (processor.supportType(handleOption)) {
                return processor.postProcess(requestHandleMattersParam);
            }
        }

        return null;
    }









}
