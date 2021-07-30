package com.xdcplus.interaction.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageInfo;
import com.xdcplus.interaction.common.enums.ResponseEnum;
import com.xdcplus.interaction.common.exception.InteractionEngineException;
import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.tool.constants.AuthConstant;
import com.xdcplus.tool.constants.NumberConstant;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.tool.utils.PageableUtils;
import com.xdcplus.tool.utils.PropertyUtils;
import com.xdcplus.interaction.common.pojo.dto.ProcessDTO;
import com.xdcplus.interaction.common.pojo.dto.ProcessFilterDTO;
import com.xdcplus.interaction.common.pojo.entity.Process;
import com.xdcplus.interaction.common.pojo.query.ProcessQuery;
import com.xdcplus.interaction.common.pojo.vo.ProcessVO;
import com.xdcplus.interaction.mapper.ProcessMapper;
import com.xdcplus.interaction.service.ProcessConfigService;
import com.xdcplus.interaction.service.ProcessService;
import com.xdcplus.interaction.service.RequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 流程表 服务实现类
 *
 * @author Rong.Jia
 * @date  2021-05-31
 */
@Slf4j
@Service
public class ProcessServiceImpl extends BaseServiceImpl<Process, ProcessVO, Process, ProcessMapper> implements ProcessService {

    @Autowired
    private ProcessMapper processMapper;

    @Autowired
    private RequestService requestService;

    @Autowired
    private ProcessConfigService processConfigService;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveProcess(ProcessDTO processDTO) {

        Process process = processMapper.findProcessByName(processDTO.getName());
        if (ObjectUtil.isNotNull(process)) {
            log.error("saveProcess() The process already exists");
            throw new InteractionEngineException(ResponseEnum.THE_PROCESS_ALREADY_EXISTS);
        }

        process = new Process();
        BeanUtil.copyProperties(processDTO, process);
        process.setCreatedTime(DateUtil.current());

        return this.save(process);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateProcess(ProcessDTO processDTO) {

        Process process = this.getById(processDTO.getId());

        if (ObjectUtil.isNull(process)) {
            log.error("updateProcess() The process does not exist or has been deleted");
            throw new InteractionEngineException(ResponseEnum.THE_PROCESS_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED);
        }

        if (!StrUtil.equals(process.getName(), processDTO.getName())) {
            if (ObjectUtil.isNotNull(processMapper.findProcessByName(processDTO.getName()))) {
                log.error("updateProcess() The process already exists");
                throw new InteractionEngineException(ResponseEnum.THE_PROCESS_ALREADY_EXISTS);
            }
        }

        process.setName(processDTO.getName());
        process.setUpdatedUser(processDTO.getUpdatedUser());
        process.setUpdatedTime(DateUtil.current());
        process.setDescription(processDTO.getDescription());

        return this.updateById(process);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteProcess(Long processId) {

        Assert.notNull(processId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        Process process = this.getById(processId);

        if (ObjectUtil.isNull(process)) {
            log.error("deleteProcess() The process does not exist or has been deleted");
            throw new InteractionEngineException(ResponseEnum.THE_PROCESS_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED);
        }

        Assert.isFalse(requestService.existRequestByProcessId(processId), ResponseEnum.DATA_QUOTE.getMessage());
        Assert.isFalse(processConfigService.existConfigByProcessId(processId), ResponseEnum.DATA_QUOTE.getMessage());

        return this.removeById(processId);
    }

    @Override
    public PageVO<ProcessVO> findProcess(ProcessFilterDTO processFilterDTO) {

        PageVO<ProcessVO> pageVO = new PageVO<>();

        if (processFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(processFilterDTO);
        }

        ProcessQuery processQuery = BeanUtil.copyProperties(processFilterDTO, ProcessQuery.class);
        List<Process> processList = processMapper.findProcess(processQuery);

        PageInfo<Process> pageInfo = new PageInfo<>(processList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(processList));

        return pageVO;
    }

    @Override
    public ProcessVO findOne(Long processId) {

        Assert.notNull(processId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(processId));
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Long getProcess(String name) {

        Process process = processMapper.findProcessByName(name);
        if (ObjectUtil.isNull(process)) {
            process = new Process();
            process.setCreatedTime(DateUtil.current());
            process.setCreatedUser(AuthConstant.ADMINISTRATOR);
            process.setName(name);

            this.save(process);
        }

        return process.getId();
    }

    @Override
    public Boolean validationExists(String name) {

        Assert.notBlank(name, ResponseEnum.THE_NAME_CANNOT_BE_EMPTY.getMessage());

        return ObjectUtil.isNotNull(processMapper.findProcessByName(name))
                ? Boolean.TRUE : Boolean.FALSE;
    }
}
