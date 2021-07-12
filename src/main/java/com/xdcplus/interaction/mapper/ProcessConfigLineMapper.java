package com.xdcplus.interaction.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.interaction.common.pojo.entity.ProcessConfigLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 流程配置线信息 Mapper 接口
 *
 * @author Rong.Jia
 * @date  2021-06-17
 */
public interface ProcessConfigLineMapper extends IBaseMapper<ProcessConfigLine> {

    /**
     * 查询配置行通过过程主键和版本
     *
     * @param processId 过程主键
     * @param version   版本
     * @return {@link List<ProcessConfigLine>} 流程配置线信息集合
     */
    List<ProcessConfigLine> findConfigLineByProcessIdAndVersion(@Param("processId") Long processId,
                                                                @Param("version") String version);












}
