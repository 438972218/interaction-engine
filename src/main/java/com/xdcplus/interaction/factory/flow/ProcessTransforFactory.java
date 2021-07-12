package com.xdcplus.interaction.factory.flow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 流转过程工厂类
 *
 * @author Rong.Jia
 * @date 2021/06/07
 */
@Component
public class ProcessTransforFactory {

    @Autowired
    private List<ProcessTransfor> processTransfors;

    /**
     * 流转
     * @param processTransforParam 流转参数
     */
    public void transfor(ProcessTransforParam processTransforParam) {

        Integer flowOption = processTransforParam.getFlowOption();
        processTransfors.forEach(processTransfor -> {
            if (processTransfor.supportType(flowOption)) {
                processTransfor.postProcess(processTransforParam);
            }
        });
    }













}
