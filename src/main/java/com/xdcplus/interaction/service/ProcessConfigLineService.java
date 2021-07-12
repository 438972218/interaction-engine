package com.xdcplus.interaction.service;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.interaction.common.pojo.dto.ProcessConfigLineDTO;
import com.xdcplus.interaction.common.pojo.entity.ProcessConfigLine;
import com.xdcplus.interaction.common.pojo.vo.ProcessConfigLineVO;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * 流程配置线信息 服务类
 * @author Rong.Jia
 * @date  2021-06-17
 */
public interface ProcessConfigLineService extends BaseService<ProcessConfigLine, ProcessConfigLineVO, ProcessConfigLine> {

    /**
     * 添加配置流程配置-线
     *
     * @param processId            流程主键
     * @param processConfigLineDTO 过程配置线DTO
     * @param version              版本
     * @return {@link Boolean} 是否成功
     */
    Boolean saveConfigLine(Long processId, ProcessConfigLineDTO processConfigLineDTO, String version);

    /**
     * 查询配置行通过流程主键和版本
     *
     * @param processId 过程主键
     * @param version   版本
     * @return {@link List<ProcessConfigLine>}
     */
    List<ProcessConfigLine> findConfigLine(Long processId, @Nullable String version);

    /**
     * 删除配置线
     *
     * @param processId 流程主键
     * @param version   版本
     * @return {@link Boolean}
     */
    Boolean deleteConfigLine(Long processId, String version);

    /**
     * 修改配置流程配置-线
     *
     * @param processId            流程主键
     * @param processConfigLineDTO 过程配置线DTO
     * @param version              版本
     * @return {@link Boolean} 是否成功
     */
    Boolean updateConfigLine(Long processId, ProcessConfigLineDTO processConfigLineDTO, String version);














}
