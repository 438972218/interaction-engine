package com.xdcplus.interaction.service;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xdcplus.interaction.InteractionEngineApplicationTests;
import com.xdcplus.interaction.common.pojo.dto.ProcessConfigDTO;
import com.xdcplus.interaction.common.pojo.dto.ProcessConfigLineDTO;
import com.xdcplus.interaction.common.pojo.dto.ProcessConfigNodeDTO;
import com.xdcplus.interaction.common.pojo.vo.ProcessConfigInfoVO;
import com.xdcplus.interaction.common.pojo.vo.ProcessConfigVO;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 过程配置业务层接口测试
 *
 * @author Rong.Jia
 * @date 2021/06/02
 */
class ProcessConfigServiceTest extends InteractionEngineApplicationTests {

    @Autowired
    private ProcessConfigService processConfigService;

    @Test
    void findConfigByRequestId() {

        List<ProcessConfigVO> processConfigVO = processConfigService.findConfigByRequestId(1399924632689811458L);

        System.out.println(JSON.toJSONString(processConfigVO));


    }

    @Test
    void findProcessConfig() {

        List<ProcessConfigInfoVO> processConfigInfoVOList = processConfigService.findProcessConfig(5000L, null);

        System.out.println(JSON.toJSONString(processConfigInfoVOList));




    }


    @Test
    void saveProcessConfig() {

        String source = FileUtil.readString("C:\\Users\\rong.jia\\Desktop\\demo1.json", "utf-8");

        NodeLink nodeLink = JSONObject.parseObject(source, NodeLink.class);

        ProcessConfigDTO processConfigDTO = new ProcessConfigDTO();
        processConfigDTO.setProcessId(1408230069725970433L);
        processConfigDTO.setVersion("V1.0.0");

        List<ProcessConfigNodeDTO> processConfigNodeDTOList = nodeLink.getNodeList().stream().map(a -> {

            ProcessConfigNodeDTO processConfigNodeDTO = new ProcessConfigNodeDTO();
            processConfigNodeDTO.setName(a.getName());
            processConfigNodeDTO.setStatusMark(a.getId());
            processConfigNodeDTO.setType(Convert.toInt(a.getType()));
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
            processConfigLineDTO.setLabel(a.getLabel());

            return processConfigLineDTO;
        }).collect(Collectors.toList());

        processConfigDTO.setLines(processConfigLineDTOList);
        processConfigDTO.setNodes(processConfigNodeDTOList);

        Boolean aBoolean = processConfigService.saveProcessConfig(processConfigDTO);

        System.out.println(aBoolean);

    }


    @NoArgsConstructor
    @Data
    public static class NodeLink {

        private String name;
        private List<NodeListBean> nodeList;
        private List<LineListBean> lineList;

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
            private String label;

            @Override
            public String toString() {
                return "from='" + from + '\'' +
                        ", to='" + to + '\'';
            }
        }
    }

    @Getter
    public enum NodeType {

        START("start",0),
        END("end",-1),
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

            NodeType[] values = NodeType.values();
            for (NodeType nodeType : values) {
                if (StrUtil.equals(nodeType.getType(), type)) {
                    return nodeType.getValue();
                }
            }

            return null;
        }










    }










}