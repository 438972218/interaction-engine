package com.xdcplus.interaction.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.xdcplus.interaction.common.enums.ResponseEnum;
import com.xdcplus.interaction.common.exception.InteractionEngineException;
import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.interaction.common.pojo.dto.ProcessConfigNodeDTO;
import com.xdcplus.interaction.common.pojo.entity.ProcessConfigNode;
import com.xdcplus.interaction.common.pojo.vo.ProcessConfigNodeVO;
import com.xdcplus.interaction.mapper.ProcessConfigNodeMapper;
import com.xdcplus.interaction.service.ProcessConfigNodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 流程配置节点信息 服务实现类
 * @author Rong.Jia
 * @date  2021-06-17
 */
@Slf4j
@Service
public class ProcessConfigNodeServiceImpl extends BaseServiceImpl<ProcessConfigNode, ProcessConfigNodeVO, ProcessConfigNode, ProcessConfigNodeMapper> implements ProcessConfigNodeService {

    @Autowired
    private ProcessConfigNodeMapper processConfigNodeMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveConfigNode(Long processId, ProcessConfigNodeDTO processConfigNodeDTO, String version) {

        Assert.notNull(processId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        List<ProcessConfigNode> processConfigNodeList = processConfigNodeMapper.findConfigNodeByProcessIdAndVersion(processId, version);

        if (CollectionUtil.isNotEmpty(processConfigNodeList)
                && processConfigNodeList.stream().anyMatch(a -> !StrUtil.equals(version, a.getVersion()))) {
            log.error("Process configuration versions are duplicated");
            throw new InteractionEngineException(ResponseEnum.PROCESS_CONFIGURATION_VERSIONS_ARE_DUPLICATED);
        }

        ProcessConfigNode processConfigNode = BeanUtil.copyProperties(processConfigNodeDTO, ProcessConfigNode.class);
        processConfigNode.setCreatedTime(DateUtil.current());
        processConfigNode.setProcessId(processId);
        processConfigNode.setVersion(version);
        processConfigNode.setLocationLeft(processConfigNodeDTO.getLeft());
        processConfigNode.setLocationTop(processConfigNodeDTO.getTop());

        return this.save(processConfigNode);
    }

    @Override
    public Boolean updateConfigNode(Long processId, ProcessConfigNodeDTO processConfigNodeDTO, String version) {

        this.deleteConfigNode(processId, version);

        return this.saveConfigNode(processId, processConfigNodeDTO, version);
    }

    @Override
    public Boolean deleteConfigNode(Long processId, String version) {

        List<ProcessConfigNode> processConfigNodeList = processConfigNodeMapper.findConfigNodeByProcessIdAndVersion(processId, version);
        Assert.notEmpty(processConfigNodeList, ResponseEnum.THE_PROCESS_CONFIGURATION_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        Set<Long> ids = processConfigNodeList.stream().map(ProcessConfigNode::getId).collect(Collectors.toSet());

        return this.removeByIds(ids);

    }

    @Override
    public List<ProcessConfigNode> findConfigNode(Long processId, @Nullable String version) {

        Assert.notNull(processId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return processConfigNodeMapper.findConfigNodeByProcessIdAndVersion(processId, version);
    }

    @Override
    public ProcessConfigNodeVO objectConversion(ProcessConfigNode processConfigNode) {

        ProcessConfigNodeVO processConfigNodeVO = super.objectConversion(processConfigNode);
        if (ObjectUtil.isNotNull(processConfigNodeVO)) {
            processConfigNodeVO.setLeft(processConfigNode.getLocationLeft());
            processConfigNodeVO.setTop(processConfigNode.getLocationTop());
        }

        return processConfigNodeVO;
    }

    @Override
    public ProcessConfigNode findConfigNode(Long processId, String mark, String version) {
        return processConfigNodeMapper.findConfigNodeByProcessIdAndVersionAndMark(processId, mark, version);
    }
}
