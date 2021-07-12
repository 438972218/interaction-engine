package com.xdcplus.interaction.service;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.interaction.common.pojo.dto.ProcessStatusDTO;
import com.xdcplus.interaction.common.pojo.dto.ProcessStatusFilterDTO;
import com.xdcplus.interaction.common.pojo.entity.ProcessStatus;
import com.xdcplus.interaction.common.pojo.vo.ProcessStatusVO;

import java.util.List;

/**
 * 流程状态表 服务类
 *
 * @author Rong.Jia
 * @date  2021-05-31
 */
public interface ProcessStatusService extends BaseService<ProcessStatus, ProcessStatusVO, ProcessStatus> {

    /**
     * 添加流程状态
     *
     * @param processStatusDTO 流程状态dto
     * @return {@link Boolean} 是否成功
     */
    Boolean saveProcessStatus(ProcessStatusDTO processStatusDTO);

    /**
     * 修改流程状态
     *
     * @param processStatusDTO 流程状态dto
     * @return {@link Boolean} 是否成功
     */
    Boolean updateProcessStatus(ProcessStatusDTO processStatusDTO);

    /**
     * 删除过程状态
     *
     * @param statusId 状态主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteProcessStatus(Long statusId);

    /**
     * 查询过程状态
     *
     * @param processStatusFilterDTO 过程状态过滤dto
     * @return {@link PageVO<ProcessStatusVO>} 状态信息
     */
    PageVO<ProcessStatusVO> findProcessStatus(ProcessStatusFilterDTO processStatusFilterDTO);

    /**
     * 查询一个
     *
     * @param statusId 状态主键
     * @return {@link ProcessStatusVO} 状态信息
     */
    ProcessStatusVO findOne(Long statusId);

    /**
     * 查询过程状态标识
     *
     * @param statusId 状态主键
     * @return {@link String} 状态标识
     */
    String findProcessStatusMarkByStatusId(Long statusId);

    /**
     * 查询过程状态通过标识
     *
     * @param mark 标识
     * @return {@link ProcessStatusVO} 状态信息
     */
    ProcessStatusVO findProcessStatusByMark(String mark);

    /**
     * 查询过程状态通过标识
     *
     * @param marks 标识
     * @return {@link List<ProcessStatusVO>} 状态信息
     */
    List<ProcessStatusVO> findProcessStatusByMarks(List<String> marks);

    /**
     * 查询过程状态
     *
     * @param name 名字
     * @param mark 标识
     * @return {@link ProcessStatusVO} 状态
     */
    ProcessStatusVO getProcessStatus(String name, String mark);









}
