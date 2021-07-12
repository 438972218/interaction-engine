package com.xdcplus.interaction.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xdcplus.interaction.InteractionEngineApplicationTests;
import com.xdcplus.interaction.common.pojo.dto.*;
import com.xdcplus.interaction.common.pojo.vo.ProcessConfigTemplateInfoVO;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 过程配置模板业务层接口实现类测试
 *
 * @author Rong.Jia
 * @date 2021/06/23
 */
class ProcessConfigTemplateServiceTest extends InteractionEngineApplicationTests {

    @Autowired
    private ProcessConfigTemplateService processConfigTemplateService;

    @Test
    void saveTemplate() {

        ProcessConfigTemplateDTO processConfigTemplateDTO = new ProcessConfigTemplateDTO();
        processConfigTemplateDTO.setName("请假流程模板");

        Boolean aBoolean = processConfigTemplateService.saveTemplate(processConfigTemplateDTO);
        System.out.println(aBoolean);

    }

    @Test
    void updateTemplate() {

        ProcessConfigTemplateDTO processConfigTemplateDTO = new ProcessConfigTemplateDTO();
        processConfigTemplateDTO.setName("dasdasda131231312321sdsds");
        processConfigTemplateDTO.setId(1407521489691549698L);

        Boolean aBoolean = processConfigTemplateService.updateTemplate(processConfigTemplateDTO);
        System.out.println(aBoolean);

    }

    @Test
    void deleteTemplate() {

        System.out.println(processConfigTemplateService.deleteTemplate(1407521489691549698L));

    }

    @Test
    void findTemplate() {

        ProcessConfigTemplateFilterDTO filterDTO = new ProcessConfigTemplateFilterDTO();
        filterDTO.setCurrentPage(1);
        filterDTO.setPageSize(20);

        System.out.println(processConfigTemplateService.findTemplate(filterDTO));

    }

    @Test
    void saveTemplateConfig() {

        String source = FileUtil.readString("C:\\Users\\rong.jia\\Desktop\\demo.json", "utf-8");

        NodeLink nodeLink = JSONObject.parseObject(source, NodeLink.class);

        ProcessTemplateConfigDTO processConfigDTO = new ProcessTemplateConfigDTO();
        processConfigDTO.setTemplateId(1407521952545624066L);

        List<ProcessConfigNodeDTO> processConfigNodeDTOList = nodeLink.getNodeList().stream().map(a -> {

            ProcessConfigNodeDTO processConfigNodeDTO = new ProcessConfigNodeDTO();
            processConfigNodeDTO.setName(a.getName());
            processConfigNodeDTO.setStatusMark(a.getId());
            processConfigNodeDTO.setType(NodeType.getValue(a.getType()));
            processConfigNodeDTO.setLeft(a.getLeft());
            processConfigNodeDTO.setTop(a.getTop());
            processConfigNodeDTO.setIco(a.getIco());
            processConfigNodeDTO.setState(a.getState());
            processConfigNodeDTO.setTimeoutAction(a.getTimeoutAction());
            processConfigNodeDTO.setToUserId(1L);
            processConfigNodeDTO.setToRoleId(1L);
            processConfigNodeDTO.setDescription("测试");

            return processConfigNodeDTO;
        }).collect(Collectors.toList());

        List<ProcessConfigLineDTO> processConfigLineDTOList = nodeLink.getLineList().stream().map(a -> {

            ProcessConfigLineDTO processConfigLineDTO = new ProcessConfigLineDTO();
            processConfigLineDTO.setFrom(a.getFrom());
            processConfigLineDTO.setTo(a.getTo());

            return processConfigLineDTO;
        }).collect(Collectors.toList());

        processConfigDTO.setLines(processConfigLineDTOList);
        processConfigDTO.setNodes(processConfigNodeDTOList);

        processConfigTemplateService.saveTemplateConfig(processConfigDTO);


    }

    @Test
    void updateTemplateConfig() {

        String source = FileUtil.readString("C:\\Users\\rong.jia\\Desktop\\demo.json", "utf-8");

        NodeLink nodeLink = JSONObject.parseObject(source, NodeLink.class);

        ProcessTemplateConfigDTO processConfigDTO = new ProcessTemplateConfigDTO();
        processConfigDTO.setTemplateId(1407521952545624066L);

        List<ProcessConfigNodeDTO> processConfigNodeDTOList = nodeLink.getNodeList().stream().map(a -> {

            ProcessConfigNodeDTO processConfigNodeDTO = new ProcessConfigNodeDTO();
            processConfigNodeDTO.setName(a.getName());
            processConfigNodeDTO.setStatusMark(a.getId());
            processConfigNodeDTO.setType(NodeType.getValue(a.getType()));
            processConfigNodeDTO.setLeft(a.getLeft());
            processConfigNodeDTO.setTop(a.getTop());
            processConfigNodeDTO.setIco(a.getIco());
            processConfigNodeDTO.setState(a.getState());
            processConfigNodeDTO.setTimeoutAction(a.getTimeoutAction());
            processConfigNodeDTO.setToUserId(2L);
            processConfigNodeDTO.setToRoleId(2L);
            processConfigNodeDTO.setDescription("测试1212312");

            return processConfigNodeDTO;
        }).collect(Collectors.toList());

        List<ProcessConfigLineDTO> processConfigLineDTOList = nodeLink.getLineList().stream().map(a -> {

            ProcessConfigLineDTO processConfigLineDTO = new ProcessConfigLineDTO();
            processConfigLineDTO.setFrom(a.getFrom());
            processConfigLineDTO.setTo(a.getTo());

            return processConfigLineDTO;
        }).collect(Collectors.toList());

        processConfigDTO.setLines(processConfigLineDTOList);
        processConfigDTO.setNodes(processConfigNodeDTOList);

        processConfigTemplateService.updateTemplateConfig(processConfigDTO);


    }

    @Test
    void findTemplateConfig() {

        ProcessConfigTemplateInfoVO templateInfoVO = processConfigTemplateService.findTemplateConfig(1407521952545624066L);

        System.out.println(JSON.toJSONString(templateInfoVO));

    }

    @Test
    void deleteTemplateConfig() {

        processConfigTemplateService.deleteTemplateConfig(1407521952545624066L);

    }


    @NoArgsConstructor
    @Data
    public static class NodeLink {

        private String name;
        private List<ProcessConfigServiceTest.NodeLink.NodeListBean> nodeList;
        private List<ProcessConfigServiceTest.NodeLink.LineListBean> lineList;

        @NoArgsConstructor
        @Data
        public static class NodeListBean {

            private String id;
            private String name;

            /**
             * 节点类型-》开始startRound;结束endRound;一般stepNode;
             * 会签节点:confluenceNode;条件判断节点：conditionNode;
             * 查阅节点：auditorNode;子流程节点：childNode
             */
            private String type;
            private String left;
            private String top;
            private String ico;
            private String state;

            /**
             * 超时时间（超时后可流转下一节点）默认24小时
             */
            public Long timeoutAction;


        }

        @NoArgsConstructor
        @Data
        public static class LineListBean {

            private String from;
            private String to;

            @Override
            public String toString() {
                return "from='" + from + '\'' +
                        ", to='" + to + '\'';
            }
        }
    }

    @Getter
    public enum NodeType {

        START("start", 0),
        END("end", -1),
        TIMER("timer", 1),
        TASK("task", 1),

        ;


        private Integer value;
        private String type;

        NodeType(String type, Integer value) {
            this.value = value;
            this.type = type;
        }

        public static Integer getValue(String type) {

            ProcessConfigServiceTest.NodeType[] values = ProcessConfigServiceTest.NodeType.values();
            for (ProcessConfigServiceTest.NodeType nodeType : values) {
                if (StrUtil.equals(nodeType.getType(), type)) {
                    return nodeType.getValue();
                }
            }

            return null;
        }

    }













}