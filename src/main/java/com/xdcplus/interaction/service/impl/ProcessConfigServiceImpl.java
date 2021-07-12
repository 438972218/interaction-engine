package com.xdcplus.interaction.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.xdcplus.interaction.common.enums.ResponseEnum;
import com.xdcplus.interaction.common.exception.InteractionEngineException;
import com.xdcplus.interaction.common.pojo.entity.ProcessConfigLine;
import com.xdcplus.interaction.common.pojo.entity.ProcessConfigNode;
import com.xdcplus.interaction.common.pojo.vo.ConfigInfoVO;
import com.xdcplus.interaction.common.pojo.vo.ProcessConfigInfoVO;
import com.xdcplus.interaction.common.pojo.vo.ProcessStatusVO;
import com.xdcplus.interaction.common.utils.ProcessValidationUtils;
import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.mp.utils.AuthUtils;
import com.xdcplus.tool.constants.AuthConstant;
import com.xdcplus.interaction.common.pojo.bo.ProcessConfigBO;
import com.xdcplus.interaction.common.pojo.dto.*;
import com.xdcplus.interaction.common.pojo.entity.ProcessConfig;
import com.xdcplus.interaction.common.pojo.vo.ProcessConfigVO;
import com.xdcplus.interaction.mapper.ProcessConfigMapper;
import com.xdcplus.interaction.service.*;
import com.xdcplus.tool.constants.NumberConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 流程配置表 服务实现类
 *
 * @author Rong.Jia
 * @date 2021-05-31
 */
@Slf4j
@Service
public class ProcessConfigServiceImpl extends BaseServiceImpl<ProcessConfigBO, ProcessConfigVO, ProcessConfig, ProcessConfigMapper> implements ProcessConfigService {

    @Autowired
    private ProcessConfigMapper processConfigMapper;

    @Autowired
    private ProcessStatusService processStatusService;

    @Autowired
    private ProcessService processService;

    @Autowired
    private QualifierService qualifierService;

    @Autowired
    private ProcessConfigLineService processConfigLineService;

    @Autowired
    private ProcessConfigNodeService processConfigNodeService;

    @Override
    public Boolean existConfigByProcessId(Long processId) {

        if (ObjectUtil.isNotNull(processId)) {
            List<ProcessConfig> processConfigList = processConfigMapper.findConfigByProcessId(processId);
            return CollectionUtil.isNotEmpty(processConfigList) ? Boolean.TRUE : Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    @Override
    public List<ProcessConfigVO> findConfigByRequestId(Long requestId) {

        List<ProcessConfigBO> processConfigBOList = processConfigMapper.findConfigByRequestId(requestId);

        return this.objectConversion(processConfigBOList);
    }

    @Override
    public List<ProcessConfigVO> findConfigByProcessIdAndFromStatusId(Long processId, Long fromStatusId, String version) {

        if (ObjectUtil.isAllNotEmpty(processId, fromStatusId)) {
            return this.objectConversion(processConfigMapper.findConfigByProcessIdAndFromStatusId(processId, fromStatusId, version));
        }

        return null;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveProcessConfig(ProcessConfigDTO processConfigDTO) {

        String version = processConfigDTO.getVersion();
        Long processId = processConfigDTO.getProcessId();

        Assert.notNull(processService.findOne(processId),
                ResponseEnum.THE_PROCESS_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        // 流程配置 线信息
        List<ProcessConfigLineDTO> configLines = processConfigDTO.getLines();

        // 流程配置 节点信息
        List<ProcessConfigNodeDTO> configNodes = processConfigDTO.getNodes();

        if (CollectionUtil.isEmpty(configLines) || CollectionUtil.isEmpty(configNodes)) {
            log.error("saveProcessConfig() The process configuration information is empty");
            throw new InteractionEngineException(ResponseEnum.THE_PROCESS_CONFIGURATION_INFORMATION_IS_EMPTY);
        }

        // 开始节点, 验证开始节点
        ProcessConfigNodeDTO startNode = ProcessValidationUtils.getStartNode(configNodes);
//        ProcessValidationUtils.verifyStartNode(startNode.getStatusMark());

        // 结束节点， 验证尾节点
        ProcessConfigNodeDTO endNode = ProcessValidationUtils.getEndNode(configNodes);
//        ProcessValidationUtils.verifyEndNode(endNode.getStatusMark());

        // 开始线
        ProcessConfigLineDTO startLine = ProcessValidationUtils.getStartLine(configLines, startNode.getStatusMark());

        // 验证合法信息
        ProcessValidationUtils.validationOfValidity(configLines, configNodes, startLine.getTo());

        refreshNodeLine(configNodes, configLines);

        // 转换对象
        List<ProcessConfig> processConfigList = combination(processId, version, configNodes, configLines);

        saveLine(processId, version, configLines);
        saveNode(processId, version, configNodes);

        boolean flag = Boolean.FALSE;

        for (ProcessConfig processConfig : processConfigList) {
            flag = this.save(processConfig);
        }

        return flag;
    }

    @Override
    public Boolean existConfigByProcessIdAndVersion(Long processId, String version) {

        List<ProcessConfig> processConfigList = processConfigMapper.findConfigByProcessIdAndVersion(processId, version);

        return CollectionUtil.isNotEmpty(processConfigList) ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public List<ProcessConfigInfoVO> findProcessConfig(Long processId, @Nullable String version) {

        Assert.notNull(processId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        // 流程配置信息
        List<ProcessConfigBO> processConfigList = processConfigMapper.findConfigAssociatedByProcessId(processId, version);

        //  节点，线信息
        List<ProcessConfigLine> configLineList = processConfigLineService.findConfigLine(processId, version);
        List<ProcessConfigNode> configNodeList = processConfigNodeService.findConfigNode(processId, version);

        return objectConversion(processConfigList, configLineList, configNodeList);
    }

    @Override
    public ProcessConfigVO objectConversion(ProcessConfigBO processConfigBO) {

        ProcessConfigVO processConfigVO = super.objectConversion(processConfigBO);
        processConfigVO.setFromStatus(processStatusService.objectConversion(processConfigBO.getFromStatus()));
        processConfigVO.setToStatus(processStatusService.objectConversion(processConfigBO.getToStatus()));
        processConfigVO.setProcess(processService.objectConversion(processConfigBO.getProcess()));

        if (ObjectUtil.isNotNull(processConfigBO.getQualifier())) {
            processConfigVO.setQualifier(qualifierService.objectConversion(processConfigBO.getQualifier()));
        }

        return processConfigVO;
    }

    /**
     * 对象转换
     *
     * @param processConfigList 过程配置列表
     * @param configLineList    配置行列表
     * @param configNodeList    配置节点列表
     * @return {@link List<ProcessConfigInfoVO>} 流程配置信息
     */
    private List<ProcessConfigInfoVO> objectConversion(List<ProcessConfigBO> processConfigList,
                                                       List<ProcessConfigLine> configLineList, List<ProcessConfigNode> configNodeList) {

        if (CollectionUtil.isEmpty(processConfigList)
                || CollectionUtil.isEmpty(configLineList) || CollectionUtil.isEmpty(configNodeList)) {
            return null;
        }

        // 节点信息 Map, key：版本，value: 节点信息
        Map<String, List<ProcessConfigNode>> configNodeMap = configNodeList.stream()
                .collect(Collectors.groupingBy(ProcessConfigNode::getVersion));

        // 线信息 Map, key：版本，value: 线信息
        Map<String, List<ProcessConfigLine>> configLineMap = configLineList.stream()
                .collect(Collectors.groupingBy(ProcessConfigLine::getVersion));

        // 配置信息 Map, key：版本，value: 配置信息集合
        Map<String, List<ProcessConfigBO>> processConfigMap = processConfigList.stream()
                .collect(Collectors.groupingBy(ProcessConfigBO::getVersion));

        return processConfigMap.entrySet().stream().map(config -> {

            String version = config.getKey();
            List<ProcessConfigBO> processConfigBOList = config.getValue();

            ProcessConfigInfoVO processConfigInfoVO = new ProcessConfigInfoVO();
            processConfigInfoVO.setVersion(version);

            processConfigBOList.stream()
                    .filter(a -> ObjectUtil.equal(version, a.getVersion())).findAny()
                    .ifPresent(a -> processConfigInfoVO.setProcess(processService.objectConversion(a.getProcess())));

            List<ConfigInfoVO.ConfigLineVO> configLineVOList = configLineMap.get(version)
                    .stream().map(line -> {
                        ConfigInfoVO.ConfigLineVO lineVO = new ConfigInfoVO.ConfigLineVO();
                        lineVO.setFrom(line.getFromMark());
                        lineVO.setTo(line.getToMark());
                        return lineVO;
                    }).collect(Collectors.toList());

            List<ConfigInfoVO.ConfigNodeVO> configNodeVOList = configNodeMap.get(version)
                    .stream().map(node -> {

                        ConfigInfoVO.ConfigNodeVO nodeVO = new ConfigInfoVO.ConfigNodeVO();
                        BeanUtil.copyProperties(node, nodeVO);
                        nodeVO.setLeft(node.getLocationLeft());
                        nodeVO.setTop(node.getLocationTop());

                        ProcessStatusVO processStatusVO = processStatusService.findProcessStatusByMark(node.getStatusMark());
                        Optional.ofNullable(processStatusVO).ifPresent(a -> nodeVO.setName(a.getName()));

                        processConfigBOList.stream().filter(a ->
                                configLineVOList.stream()
                                        .anyMatch(b -> ObjectUtil.equal(a.getFromStatus().getMark(), b.getFrom())
                                                && ObjectUtil.equal(a.getToStatus().getMark(), b.getTo()))).findAny()
                                .ifPresent(c -> {
                                    Optional.ofNullable(c.getQualifier()).ifPresent(d -> nodeVO.setCondition(qualifierService.objectConversion(d)));
                                    nodeVO.setToRoleId(c.getToRoleId());
                                    nodeVO.setToUserId(c.getToUserId());
                                    nodeVO.setTimeoutAction(c.getTimeoutAction());
                                });

                        return nodeVO;
                    }).collect(Collectors.toList());

            processConfigInfoVO.setLines(configLineVOList);
            processConfigInfoVO.setNodes(configNodeVOList);

            return processConfigInfoVO;
        }).collect(Collectors.toList());

    }

    /**
     * 保存线信息
     *
     * @param processId          流程主键
     * @param version            版本
     * @param processConfigLines 过程配置线
     */
    private void saveLine(Long processId, String version, List<ProcessConfigLineDTO> processConfigLines) {

        if (CollectionUtil.isNotEmpty(processConfigLines)) {

            for (ProcessConfigLineDTO processConfigLine : processConfigLines) {
                processConfigLineService.saveConfigLine(processId, processConfigLine, version);
            }
        }
    }

    /**
     * 添加节点
     *
     * @param processId          流程主键
     * @param version            版本
     * @param processConfigNodes 过程配置节点
     */
    private void saveNode(Long processId, String version, List<ProcessConfigNodeDTO> processConfigNodes) {

        if (CollectionUtil.isNotEmpty(processConfigNodes)) {

            for (ProcessConfigNodeDTO processConfigNode : processConfigNodes) {
                processConfigNodeService.saveConfigNode(processId, processConfigNode, version);
            }
        }
    }

    /**
     *  组合对象
     * @param processId  流程ID
     * @param configNodes 配置节点
     * @param configLines 配置线
     * @return {@link List<ProcessConfig>} 流程配置集合
     */
    private List<ProcessConfig> combination(Long processId, String version, List<ProcessConfigNodeDTO> configNodes,
                                            List<ProcessConfigLineDTO> configLines) {

        // key: 标识，value: 节点信息
        Map<String, ProcessConfigNodeDTO> configNodeMap = configNodes.stream()
                .collect(Collectors.toMap(ProcessConfigNodeDTO::getStatusMark, o->o,(o,o1) -> o));

        return configLines.stream().map(line -> {

            ProcessConfigNodeDTO processConfigNodeDTO = configNodeMap.get(line.getFrom());

            ProcessConfig processConfig = new ProcessConfig();
            processConfig.setProcessId(processId);
            processConfig.setFromStatusId(getProcessStatus(processConfigNodeDTO.getName(), line.getFrom()).getId());
            processConfig.setToStatusId(getProcessStatus(configNodeMap.get(line.getTo()).getName(), line.getTo()).getId());
            processConfig.setVersion(version);
            processConfig.setTimeoutAction(processConfigNodeDTO.getTimeoutAction());
            processConfig.setToRoleId(processConfigNodeDTO.getToRoleId());
            processConfig.setToUserId(processConfigNodeDTO.getToUserId());
            if (isAddConditions(line.getLabel()) && ObjectUtil.isNotNull(processConfigNodeDTO.getCondition())) {
                processConfig.setQualifierId(qualifierService.getQualifier(processConfigNodeDTO.getCondition()));
            }

            try {
                processConfig.setCreatedUser(AuthUtils.getCurrentUser());
            } catch (Exception e) {
                processConfig.setCreatedUser(AuthConstant.ADMINISTRATOR);
            }

            processConfig.setCreatedTime(DateUtil.current());

            return processConfig;
        }).collect(Collectors.toList());

    }

    /**
     * 是否添加条件
     *
     * @param label 标签
     * @return {@link Boolean} true/false
     */
    private Boolean isAddConditions(String label) {

        Boolean flag = Boolean.FALSE;

        if (StrUtil.isNotBlank(label)) {
            if (StrUtil.equals(label, Convert.toStr(NumberConstant.ONE))) {
                flag = Boolean.TRUE;
            }else  if (StrUtil.equals(label, Convert.toStr(NumberConstant.TWO))){
                flag = Boolean.FALSE;
            }
        }

        return flag;
    }

    /**
     * 刷新节点，线信息
     *
     * @param configNodes 配置节点
     * @param configLines 配置线
     */
    private void refreshNodeLine(List<ProcessConfigNodeDTO> configNodes,
                                 List<ProcessConfigLineDTO> configLines) {

        Map<String, ProcessConfigNodeDTO> configNodeMap = configNodes.stream()
                .collect(Collectors.toMap(ProcessConfigNodeDTO::getStatusMark, o->o,(o,o1) -> o));

        configLines.forEach(line -> {
            line.setFrom(getProcessStatus(configNodeMap.get(line.getFrom()).getName(), line.getFrom()).getMark());
            line.setTo(getProcessStatus(configNodeMap.get(line.getTo()).getName(), line.getTo()).getMark());
        });

        configNodes.forEach(node -> {
            node.setStatusMark(getProcessStatus(node.getName(), node.getStatusMark()).getMark());
        });
    }

    /**
     * 查询过程状态
     *
     * @param name 状态名
     * @param mark 标识
     * @return {@link ProcessStatusVO} 状态信息
     */
    private ProcessStatusVO getProcessStatus(String name, String mark) {
        return processStatusService.getProcessStatus(name, mark);
    }








}
