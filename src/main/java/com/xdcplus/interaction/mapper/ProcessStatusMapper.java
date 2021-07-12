package com.xdcplus.interaction.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.interaction.common.pojo.entity.ProcessStatus;
import com.xdcplus.interaction.common.pojo.query.ProcessStatusQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 流程状态表 Mapper 接口
 * @author  Rong.Jia
 * @date  2021-05-31
 */
public interface ProcessStatusMapper extends IBaseMapper<ProcessStatus> {

    /**
     * 查询流程状态通过的名字
     *
     * @param name 的名字
     * @return {@link ProcessStatus}
     */
    ProcessStatus findProcessStatusByName(@Param("name") String name);

    /**
     * 查询过程状态
     *
     * @param processStatusQuery 进程状态查询
     * @return {@link List<ProcessStatus>}
     */
    List<ProcessStatus> findProcessStatus(ProcessStatusQuery processStatusQuery);

    /**
     * 查询过程状态标识
     *
     * @param statusId 状态主键
     * @return {@link String} 状态标识
     */
    String findProcessStatusMarkByStatusId(@Param("statusId") Long statusId);

    /**
     * 查询过程状态通过标识
     *
     * @param mark 标识
     * @return {@link ProcessStatus} 状态信息
     */
    ProcessStatus findProcessStatusByMark(@Param("mark") String mark);

    /**
     * 查询过程状态通过标识
     *
     * @param marks 标识
     * @return {@link List<ProcessStatus>}  状态信息
     */
    List<ProcessStatus> findProcessStatusByMarks(@Param("marks") List<String> marks);




















}
