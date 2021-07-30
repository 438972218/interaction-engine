package com.xdcplus.interaction.service;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.interaction.common.pojo.dto.ProcessDTO;
import com.xdcplus.interaction.common.pojo.dto.ProcessFilterDTO;
import com.xdcplus.interaction.common.pojo.entity.Process;
import com.xdcplus.interaction.common.pojo.vo.ProcessVO;

/**
 * 流程表 服务类
 *
 * @author Rong.Jia
 * @date  2021-05-31
 */
public interface ProcessService extends BaseService<Process, ProcessVO, Process> {

    /**
     * 添加流程
     *
     * @param processDTO 过程dto
     * @return {@link Boolean}
     */
    Boolean saveProcess(ProcessDTO processDTO);

    /**
     * 修改流程
     *
     * @param processDTO 过程dto
     * @return {@link Boolean}
     */
    Boolean updateProcess(ProcessDTO processDTO);

    /**
     * 删除流程
     *
     * @param processId 主键
     * @return {@link Boolean}
     */
    Boolean deleteProcess(Long processId);

    /**
     * 查询流程
     *
     * @param processFilterDTO 流程过滤查询对象
     * @return {@link PageVO<ProcessVO>}
     */
    PageVO<ProcessVO> findProcess(ProcessFilterDTO processFilterDTO);

    /**
     * 查询流程
     * @param processId 流程主键
     * @return {@link ProcessVO}
     */
    ProcessVO findOne(Long processId);

    /**
     * 获取流程主键ID
     * @param name 流程名
     * @return {@link Long} 主键ID
     */
    Long getProcess(String name);

    /**
     * 验证流程信息是否存在
     *
     * @param name 名字
     * @return {@link Boolean} 是否存在
     */
    Boolean validationExists(String name);



















}
