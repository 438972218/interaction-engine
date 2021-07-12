package com.xdcplus.interaction.service;

import com.xdcplus.interaction.InteractionEngineApplicationTests;
import com.xdcplus.interaction.common.pojo.dto.FlowOptionDTO;
import com.xdcplus.interaction.common.pojo.vo.FlowOptionVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 流选项业务层接口测试
 *
 * @author Rong.Jia
 * @date 2021/06/01
 */
class FlowOptionServiceTest extends InteractionEngineApplicationTests {

    @Autowired
    private FlowOptionService flowOptionService;

    @Test
    void saveFlowOption() {

        FlowOptionDTO flowOptionDTO = new FlowOptionDTO();
        flowOptionDTO.setValue(1);
        flowOptionDTO.setValueString("同意");

        System.out.println(flowOptionService.saveFlowOption(flowOptionDTO));

    }

    @Test
    void updateFlowOption() {
        FlowOptionDTO flowOptionDTO = new FlowOptionDTO();
        flowOptionDTO.setValue(1);
        flowOptionDTO.setValueString("不同意");
        flowOptionDTO.setId(1399605997173633025L);

        System.out.println(flowOptionService.updateFlowOption(flowOptionDTO));
    }

    @Test
    void deleteFlowOption() {
        System.out.println(flowOptionService.deleteFlowOption(1399605997173633025L));
    }

    @Test
    void findFlowOption() {

        List<FlowOptionVO> optionVOList = flowOptionService.findFlowOption();
        System.out.println(optionVOList.toString());
    }
}