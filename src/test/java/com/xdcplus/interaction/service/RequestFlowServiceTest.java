package com.xdcplus.interaction.service;

import cn.hutool.core.collection.CollectionUtil;
import com.xdcplus.interaction.InteractionEngineApplicationTests;
import com.xdcplus.interaction.common.pojo.dto.ProcessTransforDTO;
import com.xdcplus.interaction.common.pojo.vo.RequestFlowVO;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 请求流业务层接口测试
 *
 * @author Rong.Jia
 * @date 2021/06/03
 */
class RequestFlowServiceTest extends InteractionEngineApplicationTests {

    @Autowired
    private RequestFlowService requestFlowService;

    @Test
    void findFullFlowRequestId() {

        List<RequestFlowVO> requestFlowVOList = requestFlowService.findRequestFlowRequestId(1402059375887683586L);
        System.out.println(requestFlowVOList.toString());

    }

    /**
     *  同意
     */
    @Test
    void processTransforAgree() {

        ProcessTransforDTO processTransforDTO = new ProcessTransforDTO();
        processTransforDTO.setFlowOption(1);
        processTransforDTO.setRequestId(1412984051426521089L);
        processTransforDTO.setUserId(20L);

        FlowCondition flowCondition = new FlowCondition();
        flowCondition.setRequestRelationRequestStatusId(2);

        ProcessTransforDTO.Agree agree = new ProcessTransforDTO.Agree();
        agree.setFlowConditions(flowCondition);
        agree.setRoleIds(CollectionUtil.newArrayList(1L));

        processTransforDTO.setAgree(agree);

        requestFlowService.processTransfor(processTransforDTO);

    }

    @Data
    private static class  FlowCondition {
        private Integer age;
        private Integer requestRelationRequestStatusId;
    }


    /**
     * 退回
     */
    @Test
    void processTransforSendBack() {

        ProcessTransforDTO processTransforDTO = new ProcessTransforDTO();
        processTransforDTO.setFlowOption(2);
        processTransforDTO.setRequestId(1402077442730606594L);
        processTransforDTO.setDescription("退回");

        ProcessTransforDTO.SendBack sendBack = new ProcessTransforDTO.SendBack();
        sendBack.setToStatusId(100L);
        sendBack.setToUserId(10L);

        processTransforDTO.setSendBack(sendBack);

        requestFlowService.processTransfor(processTransforDTO);

    }

    /**
     *  前加签
     */
    @Test
    void processTransforPreSignature() {


        ProcessTransforDTO processTransforDTO = new ProcessTransforDTO();
        processTransforDTO.setFlowOption(6);
        processTransforDTO.setRequestId(1402077442730606594L);
        processTransforDTO.setDescription("前加签");

        ProcessTransforDTO.AdditionalSign additionalSign = new ProcessTransforDTO.AdditionalSign();
        additionalSign.setToUserIds(CollectionUtil.newArrayList(30L));
        processTransforDTO.setAdditional(additionalSign);

        requestFlowService.processTransfor(processTransforDTO);



    }

    /**
     *  后加签
     */
    @Test
    void processTransforAfterSignature() {

        ProcessTransforDTO processTransforDTO = new ProcessTransforDTO();
        processTransforDTO.setFlowOption(5);
        processTransforDTO.setRequestId(1402077442730606594L);
        processTransforDTO.setDescription("后加签");

        ProcessTransforDTO.AdditionalSign additionalSign = new ProcessTransforDTO.AdditionalSign();
        additionalSign.setToUserIds(CollectionUtil.newArrayList(30L));
        processTransforDTO.setAdditional(additionalSign);


        requestFlowService.processTransfor(processTransforDTO);


    }

    /**
     *  取消
     */
    @Test
    void processTransforCancel() {

        ProcessTransforDTO processTransforDTO = new ProcessTransforDTO();
        processTransforDTO.setFlowOption(4);
        processTransforDTO.setRequestId(1403235981025071106L);
        processTransforDTO.setDescription("取消");


        requestFlowService.processTransfor(processTransforDTO);


    }

    /**
     *  提交
     */
    @Test
    void processTransforSubmit() {

        ProcessTransforDTO processTransforDTO = new ProcessTransforDTO();
        processTransforDTO.setFlowOption(3);
        processTransforDTO.setRequestId(1412984051426521089L);
        processTransforDTO.setDescription("提交");

        requestFlowService.processTransfor(processTransforDTO);


    }



}