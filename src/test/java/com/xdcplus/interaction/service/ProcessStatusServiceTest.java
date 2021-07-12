package com.xdcplus.interaction.service;

import com.xdcplus.interaction.InteractionEngineApplicationTests;
import com.xdcplus.interaction.common.pojo.dto.ProcessStatusDTO;
import com.xdcplus.interaction.common.pojo.dto.ProcessStatusFilterDTO;
import com.xdcplus.interaction.common.pojo.vo.ProcessStatusVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 过程状态业务层接口测试
 *
 * @author Rong.Jia
 * @date 2021/06/01
 */
class ProcessStatusServiceTest extends InteractionEngineApplicationTests {

    @Autowired
    private ProcessStatusService processStatusService;

    @Test
    void saveProcessStatus() {

        ProcessStatusDTO processStatusDTO = new ProcessStatusDTO();
        processStatusDTO.setName("已退回2123213132");

        System.out.println(processStatusService.saveProcessStatus(processStatusDTO));

    }

    @Test
    void updateProcessStatus() {

        ProcessStatusDTO processStatusDTO = new ProcessStatusDTO();
        processStatusDTO.setName("已退回1");
        processStatusDTO.setId(1399636972863746050L);

        System.out.println(processStatusService.updateProcessStatus(processStatusDTO));


    }

    @Test
    void deleteProcessStatus() {
        System.out.println(processStatusService.deleteProcessStatus(1399636972863746050L));
    }

    @Test
    void findProcessStatus() {

        ProcessStatusFilterDTO processStatusFilterDTO = new ProcessStatusFilterDTO();
        processStatusFilterDTO.setCurrentPage(1);
        processStatusFilterDTO.setPageSize(20);

        System.out.println(processStatusService.findProcessStatus(processStatusFilterDTO));


    }

    @Test
    void findProcessStatusByMark() {

        System.out.println(processStatusService.findProcessStatusByMark("1"));

    }

    @Test
    void getProcessStatus() {

        ProcessStatusVO processStatusVO = processStatusService.getProcessStatus("已归档", "5");
        System.out.println(processStatusVO.toString());

    }




}