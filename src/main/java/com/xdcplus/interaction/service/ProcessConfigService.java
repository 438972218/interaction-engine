package com.xdcplus.interaction.service;

import com.xdcplus.interaction.common.pojo.vo.ProcessConfigInfoVO;
import com.xdcplus.mp.service.BaseService;
import com.xdcplus.interaction.common.pojo.bo.ProcessConfigBO;
import com.xdcplus.interaction.common.pojo.dto.ProcessConfigDTO;
import com.xdcplus.interaction.common.pojo.entity.ProcessConfig;
import com.xdcplus.interaction.common.pojo.vo.ProcessConfigVO;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * 流程配置表 服务类
 *
 * @author Rong.Jia
 * @date  2021-05-31
 */
public interface ProcessConfigService extends BaseService<ProcessConfigBO, ProcessConfigVO, ProcessConfig> {

    /**
     * 存在配置通过流程ID
     *
     * @param processId 流程ID
     * @return {@link Boolean}
     */
    Boolean existConfigByProcessId(Long processId);

    /**
     * 查询配置通过表单主键ID
     *
     * @param requestId 请求主键
     * @return {@link List<ProcessConfigVO>}
     */
    List<ProcessConfigVO> findConfigByRequestId(Long requestId);

    /**
     * 查询流程配置
     *
     * @param processId    流程主键
     * @param fromStatusId 上一个状态主键
     * @param version 版本号
     * @return {@link List<ProcessConfigVO>}
     */
    List<ProcessConfigVO> findConfigByProcessIdAndFromStatusId(Long processId, Long fromStatusId, String version);

    /**
     * 添加过程配置
     *
     * @param processConfigDTO 过程配置v2 DTO
     * @return {@link Boolean} 是否成功
     */
    Boolean saveProcessConfig(ProcessConfigDTO processConfigDTO);

    /**
     * 根据流程ID和版本号判断是否有流程配置
     *
     * @param processId 流程ID
     * @param version  版本号
     * @return {@link Boolean} true: 存在，false : 不存在
     */
    Boolean existConfigByProcessIdAndVersion(Long processId, String version);

    /**
     * 查询流程配置
     *
     * @param processId 流程主键
     * @param version 版本号
     * @return {@link List<ProcessConfigInfoVO> } 流程配置信息
     */
    List<ProcessConfigInfoVO> findProcessConfig(Long processId, @Nullable String version);



























































}
