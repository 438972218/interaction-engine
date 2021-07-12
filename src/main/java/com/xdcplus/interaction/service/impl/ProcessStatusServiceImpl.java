package com.xdcplus.interaction.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.IdUtil;
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
import com.xdcplus.interaction.common.pojo.dto.ProcessStatusDTO;
import com.xdcplus.interaction.common.pojo.dto.ProcessStatusFilterDTO;
import com.xdcplus.interaction.common.pojo.entity.ProcessStatus;
import com.xdcplus.interaction.common.pojo.query.ProcessStatusQuery;
import com.xdcplus.interaction.common.pojo.vo.ProcessStatusVO;
import com.xdcplus.interaction.mapper.ProcessStatusMapper;
import com.xdcplus.interaction.service.ProcessStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 流程状态表 服务实现类
 *
 * @author Rong.Jia
 * @date  2021-05-31
 */
@Slf4j
@Service
public class ProcessStatusServiceImpl extends BaseServiceImpl<ProcessStatus, ProcessStatusVO, ProcessStatus, ProcessStatusMapper> implements ProcessStatusService {

    @Autowired
    private ProcessStatusMapper processStatusMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveProcessStatus(ProcessStatusDTO processStatusDTO) {

        ProcessStatus processStatus = processStatusMapper.findProcessStatusByName(processStatusDTO.getName());
        if (ObjectUtil.isNotNull(processStatus)) {
            log.error("saveProcessStatus() The process status type already exists");
            throw new InteractionEngineException(ResponseEnum.THE_PROCESS_STATUS_ALREADY_EXISTS);
        }

        processStatus = new ProcessStatus();
        BeanUtil.copyProperties(processStatusDTO, processStatus);
        processStatus.setCreatedTime(DateUtil.current());
        processStatus.setMark(IdUtil.fastSimpleUUID() + DateUtil.current());

        return this.save(processStatus);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateProcessStatus(ProcessStatusDTO processStatusDTO) {

        ProcessStatus processStatus = this.getById(processStatusDTO.getId());
        if (ObjectUtil.isNull(processStatus)) {
            log.error("updateProcessStatus() The process status type does not exist or has been deleted");
            throw new InteractionEngineException(ResponseEnum.THE_PROCESS_STATUS_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED);
        }

        if (!StrUtil.equals(processStatus.getName(), processStatusDTO.getName())) {
            if (ObjectUtil.isNotNull( processStatusMapper.findProcessStatusByName(processStatusDTO.getName()))) {
                log.error("updateProcessStatus() The process status type already exists");
                throw new InteractionEngineException(ResponseEnum.THE_PROCESS_STATUS_ALREADY_EXISTS);
            }
        }

        processStatus.setName(processStatusDTO.getName());
        processStatus.setUpdatedUser(processStatusDTO.getUpdatedUser());
        processStatus.setUpdatedTime(DateUtil.current());
        processStatus.setDescription(processStatusDTO.getDescription());

        return this.updateById(processStatus);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteProcessStatus(Long statusId) {

        Assert.notNull(statusId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        ProcessStatus processStatus = this.getById(statusId);

        if (ObjectUtil.isNull(processStatus)) {
            log.error("deleteProcessStatus() The process status does not exist or has been deleted");
            throw new InteractionEngineException(ResponseEnum.THE_PROCESS_STATUS_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED);
        }

        // 判断 流转，表单，流程配置 是否关联

        return this.removeById(statusId);

    }

    @Override
    public PageVO<ProcessStatusVO> findProcessStatus(ProcessStatusFilterDTO processStatusFilterDTO) {

        PageVO<ProcessStatusVO> pageVO = new PageVO<>();

        if (processStatusFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(processStatusFilterDTO.getCurrentPage(), processStatusFilterDTO.getPageSize(),
                    processStatusFilterDTO.getOrderType(), processStatusFilterDTO.getOrderField());
        }

        ProcessStatusQuery processStatusQuery = BeanUtil.copyProperties(processStatusFilterDTO, ProcessStatusQuery.class);
        List<ProcessStatus> processStatusList = processStatusMapper.findProcessStatus(processStatusQuery);

        PageInfo<ProcessStatus> pageInfo = new PageInfo<>(processStatusList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(processStatusList));

        return pageVO;
    }

    @Override
    public ProcessStatusVO findOne(Long statusId) {

        Assert.notNull(statusId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(statusId));
    }

    @Override
    public String findProcessStatusMarkByStatusId(Long statusId) {

        Assert.notNull(statusId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return processStatusMapper.findProcessStatusMarkByStatusId(statusId);
    }

    @Override
    public ProcessStatusVO findProcessStatusByMark(String mark) {
        return this.objectConversion(processStatusMapper.findProcessStatusByMark(mark));
    }

    @Override
    public List<ProcessStatusVO> findProcessStatusByMarks(List<String> marks) {

        if (CollectionUtil.isEmpty(marks)) {
            marks = CollectionUtil.newArrayList(Convert.toStr(NumberConstant.B_NEGATIVE));
        }

        return this.objectConversion(processStatusMapper.findProcessStatusByMarks(marks));
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public ProcessStatusVO getProcessStatus(String name, String mark) {

        ProcessStatus processStatus = processStatusMapper.findProcessStatusByMark(name);
        if (ObjectUtil.isNull(processStatus)) {
            processStatus = processStatusMapper.findProcessStatusByName(mark);
        }

        if (ObjectUtil.isNull(processStatus)) {
            processStatus = new ProcessStatus();
            processStatus.setMark(mark);
            processStatus.setName(name);
            processStatus.setCreatedTime(DateUtil.current());
            processStatus.setCreatedUser(AuthConstant.ADMINISTRATOR);

            this.save(processStatus);
        }

        return this.objectConversion(processStatus);
    }
}
