package com.xdcplus.interaction.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.xdcplus.interaction.common.enums.ResponseEnum;
import com.xdcplus.interaction.common.exception.InteractionEngineException;
import com.xdcplus.interaction.common.pojo.dto.*;
import com.xdcplus.interaction.common.pojo.entity.*;
import com.xdcplus.interaction.common.pojo.query.ProcessConfigTemplateQuery;
import com.xdcplus.interaction.common.pojo.vo.*;
import com.xdcplus.interaction.common.utils.ProcessValidationUtils;
import com.xdcplus.interaction.mapper.ProcessConfigLineTemplateMapper;
import com.xdcplus.interaction.mapper.ProcessConfigNodeTemplateMapper;
import com.xdcplus.interaction.mapper.ProcessConfigTemplateMapper;
import com.xdcplus.interaction.service.ProcessConfigTemplateService;
import com.xdcplus.interaction.service.ProcessStatusService;
import com.xdcplus.interaction.service.QualifierService;
import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.tool.constants.NumberConstant;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.tool.utils.PageableUtils;
import com.xdcplus.tool.utils.PropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 流程配置模板信息 服务实现类
 *
 * @author Rong.Jia
 * @date 2021-06-22
 */
@Slf4j
@Service
public class ProcessConfigTemplateServiceImpl extends BaseServiceImpl<ProcessConfigTemplate, ProcessConfigTemplateVO, ProcessConfigTemplate, ProcessConfigTemplateMapper> implements ProcessConfigTemplateService {

    @Autowired
    private ProcessConfigLineTemplateMapper processConfigLineTemplateMapper;

    @Autowired
    private ProcessConfigNodeTemplateMapper processConfigNodeTemplateMapper;

    @Autowired
    private ProcessStatusService processStatusService;

    @Autowired
    private ProcessConfigTemplateMapper processConfigTemplateMapper;

    @Autowired
    private QualifierService qualifierService;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveTemplate(ProcessConfigTemplateDTO processConfigTemplateDTO) {

        ProcessConfigTemplate processConfigTemplate = processConfigTemplateMapper.findTemplateByName(processConfigTemplateDTO.getName());
        Assert.isNull(processConfigTemplate,
                ResponseEnum.THE_PROCESS_CONFIGURATION_TEMPLATE_ALREADY_EXISTS.getMessage());

        processConfigTemplate = new ProcessConfigTemplate();
        BeanUtil.copyProperties(processConfigTemplateDTO, processConfigTemplate);
        processConfigTemplate.setCreatedTime(DateUtil.current());

        return this.save(processConfigTemplate);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateTemplate(ProcessConfigTemplateDTO processConfigTemplateDTO) {

        ProcessConfigTemplate processConfigTemplate = this.getById(processConfigTemplateDTO.getId());
        Assert.notNull(processConfigTemplate,
                ResponseEnum.THE_PROCESS_CONFIGURATION_TEMPLATE_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        Assert.isNull(processConfigTemplateMapper.findTemplateByName(processConfigTemplateDTO.getName()),
                ResponseEnum.THE_PROCESS_CONFIGURATION_TEMPLATE_ALREADY_EXISTS.getMessage());

        processConfigTemplate.setName(processConfigTemplateDTO.getName());
        processConfigTemplate.setUpdatedTime(DateUtil.current());
        processConfigTemplate.setDescription(processConfigTemplateDTO.getDescription());

        return this.updateById(processConfigTemplate);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteTemplate(Long id) {

        validationTemplate(id);

        List<ProcessConfigLineTemplate> configLines = processConfigLineTemplateMapper.findTemplateLinesByTemplateId(id);
        List<ProcessConfigNodeTemplate> configNodes = processConfigNodeTemplateMapper.findTemplateNodesByTemplateId(id);
        if (CollectionUtil.isNotEmpty(configLines) || CollectionUtil.isNotEmpty(configNodes)) {
            throw new InteractionEngineException(ResponseEnum.DATA_QUOTE);
        }

        return this.removeById(id);
    }

    @Override
    public PageVO<ProcessConfigTemplateVO> findTemplate(ProcessConfigTemplateFilterDTO filterDTO) {

        PageVO<ProcessConfigTemplateVO> pageVO = new PageVO<>();

        if (filterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(filterDTO);
        }

        ProcessConfigTemplateQuery query = BeanUtil.copyProperties(filterDTO, ProcessConfigTemplateQuery.class);
        List<ProcessConfigTemplate> configTemplateList = processConfigTemplateMapper.findTemplate(query);

        PageInfo<ProcessConfigTemplate> pageInfo = new PageInfo<>(configTemplateList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(pageInfo.getList()));

        return pageVO;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveTemplateConfig(ProcessTemplateConfigDTO processTemplateConfigDTO) {

        Long templateId = processTemplateConfigDTO.getTemplateId();
        validationTemplate(templateId);
        syncTemplateConfig(processTemplateConfigDTO);

    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateTemplateConfig(ProcessTemplateConfigDTO processTemplateConfigDTO) {

        Long templateId = processTemplateConfigDTO.getTemplateId();
        validationTemplate(templateId);
        deleteNodeLine(templateId);

        syncTemplateConfig(processTemplateConfigDTO);

    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteTemplateConfig(Long templateId) {

        validationTemplate(templateId);
        deleteNodeLine(templateId);
    }

    @Override
    public ProcessConfigTemplateInfoVO findTemplateConfig(Long templateId) {

        validationTemplate(templateId);

        List<ProcessConfigLineTemplate> configLines = processConfigLineTemplateMapper.findTemplateLinesByTemplateId(templateId);
        List<ProcessConfigNodeTemplate> configNodes = processConfigNodeTemplateMapper.findTemplateNodesByTemplateId(templateId);

        return this.objectConversion(templateId, configLines, configNodes);
    }

    /**
     * 对象转换
     *
     * @param templateId 模板信息主键ID
     * @param configLineList    配置行列表
     * @param configNodeList    配置节点列表
     * @return {@link List<ProcessConfigInfoVO>} 流程配置信息
     */
    private ProcessConfigTemplateInfoVO objectConversion(Long templateId,
                                                         List<ProcessConfigLineTemplate> configLineList,
                                                         List<ProcessConfigNodeTemplate> configNodeList) {

        if (CollectionUtil.isEmpty(configLineList) || CollectionUtil.isEmpty(configNodeList)) {
            return null;
        }

        ProcessConfigTemplateInfoVO processConfigTemplateInfoVO = new ProcessConfigTemplateInfoVO();
        processConfigTemplateInfoVO.setTemplate(this.objectConversion(this.getById(templateId)));

        List<ConfigInfoVO.ConfigLineVO> configLineVOList = configLineList.stream()
                .map(line -> {
                    ConfigInfoVO.ConfigLineVO lineVO = new ConfigInfoVO.ConfigLineVO();
                    lineVO.setFrom(line.getFromMark());
                    lineVO.setTo(line.getToMark());
                    return lineVO;
                }).collect(Collectors.toList());

        List<ConfigInfoVO.ConfigNodeVO> configNodeVOList = configNodeList.stream()
                .map(node -> {

                    ConfigInfoVO.ConfigNodeVO nodeVO = new ConfigInfoVO.ConfigNodeVO();
                    BeanUtil.copyProperties(node, nodeVO);
                    nodeVO.setLeft(node.getLocationLeft());
                    nodeVO.setTop(node.getLocationTop());
                    nodeVO.setStatusMark(node.getMark());

                    Optional.ofNullable(node.getQualifierId())
                            .ifPresent(d -> nodeVO.setCondition(qualifierService.findOne(d)));
                    return nodeVO;
                }).collect(Collectors.toList());

        processConfigTemplateInfoVO.setLines(configLineVOList);
        processConfigTemplateInfoVO.setNodes(configNodeVOList);

        return  processConfigTemplateInfoVO;

    }

    /**
     * 验证模板
     *
     * @param templateId 模板主键
     */
    private void validationTemplate(Long templateId) {

        ProcessConfigTemplate processConfigTemplate = this.getById(templateId);
        Assert.notNull(processConfigTemplate,
                ResponseEnum.THE_PROCESS_CONFIGURATION_TEMPLATE_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

    }

    /**
     * 删除节点, 线
     *
     * @param templateId 模板主键
     */
    private void deleteNodeLine(Long templateId) {

        Optional.ofNullable(processConfigNodeTemplateMapper.findTemplateNodesByTemplateId(templateId))
                .ifPresent(a -> processConfigNodeTemplateMapper.deleteBatchIds(a.stream()
                        .map(ProcessConfigNodeTemplate::getId).collect(Collectors.toSet())));

        Optional.ofNullable(processConfigLineTemplateMapper.findTemplateLinesByTemplateId(templateId))
                .ifPresent(a -> processConfigLineTemplateMapper.deleteBatchIds(a.stream()
                        .map(ProcessConfigLineTemplate::getId).collect(Collectors.toSet())));
    }

    /**
     * 同步模板配置
     *
     * @param processTemplateConfigDTO 过程模板配置DTO
     */
    private void syncTemplateConfig(ProcessTemplateConfigDTO processTemplateConfigDTO) {

        List<ProcessConfigLineDTO> configLines = processTemplateConfigDTO.getLines();
        List<ProcessConfigNodeDTO> configNodes = processTemplateConfigDTO.getNodes();

        if (CollectionUtil.isEmpty(configLines) || CollectionUtil.isEmpty(configNodes)) {
            log.error("saveTemplateConfig() The process configuration information is empty");
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

        saveLine(processTemplateConfigDTO.getTemplateId(), configLines);
        saveNode(processTemplateConfigDTO.getTemplateId(), configNodes);

    }

    /**
     * 保存线信息
     *
     * @param templateId         模板信息主键
     * @param processConfigLines 过程配置线
     */
    private void saveLine(Long templateId, List<ProcessConfigLineDTO> processConfigLines) {

        if (CollectionUtil.isNotEmpty(processConfigLines)) {

            for (ProcessConfigLineDTO processConfigLine : processConfigLines) {

                ProcessConfigLineTemplate processConfigLineTemplate = new ProcessConfigLineTemplate();
                BeanUtil.copyProperties(processConfigLine, processConfigLineTemplate);
                processConfigLineTemplate.setCreatedTime(DateUtil.current());
                processConfigLineTemplate.setTemplateId(templateId);
                processConfigLineTemplate.setFromMark(processConfigLine.getFrom());
                processConfigLineTemplate.setToMark(processConfigLine.getTo());

                processConfigLineTemplateMapper.insert(processConfigLineTemplate);
            }
        }
    }

    /**
     * 添加节点
     *
     * @param templateId         模板信息主键
     * @param processConfigNodes 过程配置节点
     */
    private void saveNode(Long templateId, List<ProcessConfigNodeDTO> processConfigNodes) {

        if (CollectionUtil.isNotEmpty(processConfigNodes)) {

            for (ProcessConfigNodeDTO processConfigNode : processConfigNodes) {

                ProcessConfigNodeTemplate processConfigNodeTemplate = new ProcessConfigNodeTemplate();
                BeanUtil.copyProperties(processConfigNode, processConfigNodeTemplate);
                processConfigNodeTemplate.setCreatedTime(DateUtil.current());
                processConfigNodeTemplate.setLocationLeft(processConfigNode.getLeft());
                processConfigNodeTemplate.setLocationTop(processConfigNode.getTop());
                processConfigNodeTemplate.setMark(processConfigNode.getStatusMark());

                QualifierDTO condition = processConfigNode.getCondition();
                if (ObjectUtil.isNotNull(condition)) {
                    processConfigNodeTemplate.setQualifierId(processStatusService.getProcessStatus(condition.getName(), condition.getScript()).getId());
                }

                processConfigNodeTemplate.setTemplateId(templateId);

                processConfigNodeTemplateMapper.insert(processConfigNodeTemplate);
            }
        }
    }


}
