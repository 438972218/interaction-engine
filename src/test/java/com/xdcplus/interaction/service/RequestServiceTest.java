package com.xdcplus.interaction.service;

import com.alibaba.fastjson.JSON;
import com.xdcplus.interaction.InteractionEngineApplicationTests;
import com.xdcplus.interaction.common.pojo.dto.HandleMattersFilterDTO;
import com.xdcplus.interaction.common.pojo.dto.RequestDTO;
import com.xdcplus.interaction.common.pojo.dto.RequestFilterDTO;
import com.xdcplus.interaction.common.pojo.vo.RequestVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 表单业务层接口测试
 *
 * @author Rong.Jia
 * @date 2021/06/02
 */
class RequestServiceTest extends InteractionEngineApplicationTests {

    @Autowired
    private RequestService requestService;

    @Test
    void saveRequest() {

        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setProcessId(5000L);
        requestDTO.setRuleId(1406871163489734658L);
        requestDTO.setTitle("请假测试12313");

        RequestVO saveRequest = requestService.saveRequest(requestDTO);
        System.out.println(saveRequest);

    }

    @Test
    void findRequest() {

        RequestFilterDTO requestFilterDTO = new RequestFilterDTO();
        requestFilterDTO.setCurrentPage(1);
        requestFilterDTO.setPageSize(20);
        requestFilterDTO.setOddNumber("QJ");

        System.out.println(JSON.toJSONString(requestService.findRequest(requestFilterDTO)));

    }

    @Test
    void handleMatters() {

        HandleMattersFilterDTO handleMattersFilterDTO = new HandleMattersFilterDTO();
        handleMattersFilterDTO.setHandleOption(2);
        handleMattersFilterDTO.setUserId(1L);
        handleMattersFilterDTO.setCurrentPage(1);
        handleMattersFilterDTO.setPageSize(20);

        System.out.println(requestService.handleMatters(handleMattersFilterDTO));

    }












}