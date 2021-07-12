package com.xdcplus.interaction.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.interaction.common.pojo.bo.ProcessConfigBO;
import com.xdcplus.interaction.common.pojo.entity.ProcessConfig;
import com.xdcplus.interaction.common.pojo.query.ProcessConfigQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 流程配置表 Mapper 接口
 * @author Rong.Jia
 * @date  2021-05-31
 */
public interface ProcessConfigMapper extends IBaseMapper<ProcessConfig> {

    /**
     * 查询过程配置
     *
     * @param processConfigQuery 过程配置查询
     * @return {@link List<ProcessConfigBO>}
     */
    List<ProcessConfigBO>  findProcessConfig(ProcessConfigQuery processConfigQuery);

    /**
     * 查询配置通过流程ID
     *
     * @param processId 流程ID
     * @return {@link List<ProcessConfig>}
     */
    List<ProcessConfig> findConfigByProcessId(@Param("processId") Long processId);

    /**
     * 查询流程配置通过表单主键ID
     * @param requestId 请求主键
     * @return {@link List<ProcessConfigBO>}
     */
    List<ProcessConfigBO> findConfigByRequestId(@Param("requestId") Long requestId);

    /**
     * 查询配置通过流程主键和上一个状态主键
     *
     * @param processId    流程主键
     * @param fromStatusId 上一个状态主键
     * @param version 版本号
     * @return {@link List<ProcessConfigBO>}
     */
    List<ProcessConfigBO> findConfigByProcessIdAndFromStatusId(@Param("processId") Long processId,
                                                         @Param("fromStatusId") Long fromStatusId, @Param("version") String version);

    /**
     * 根据流程ID和版本号判断是否有流程配置
     *
     * @param processId 流程ID
     * @param version  版本号
     * @return {@link List<ProcessConfig>} 流程配置
     */
    List<ProcessConfig> findConfigByProcessIdAndVersion(@Param("processId") Long processId,
                                                        @Param("version") String version);

    /**
     * 查询配置通过流程ID
     *
     * @param processId 流程ID
     * @param version  版本
     * @return {@link List<ProcessConfigBO>} 流程配置信息
     */
    List<ProcessConfigBO> findConfigAssociatedByProcessId(@Param("processId") Long processId,
                                                          @Param("version") String version);






}
