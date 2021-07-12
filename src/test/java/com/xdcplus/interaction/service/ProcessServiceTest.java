package com.xdcplus.interaction.service;

import com.xdcplus.interaction.InteractionEngineApplicationTests;
import com.xdcplus.interaction.common.pojo.dto.ProcessDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 流程业务层接口测试
 *
 * @author Rong.Jia
 * @date 2021/06/18
 */
class ProcessServiceTest extends InteractionEngineApplicationTests {

    @Autowired
    private ProcessService processService;

    @Test
    void saveProcess() {

        ProcessDTO processDTO = new ProcessDTO();
        processDTO.setDescription("请假");
        processDTO.setCreatedUser("admin");
        processDTO.setName("请假流程");


        System.out.println(processService.saveProcess(processDTO));

    }

    @Test
    void updateProcess() {
    }

    @Test
    void deleteProcess() {
    }

    @Test
    void findProcess() {
    }

    @Test
    void findOne() {
    }
}