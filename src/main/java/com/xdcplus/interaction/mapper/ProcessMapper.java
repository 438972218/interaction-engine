package com.xdcplus.interaction.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.interaction.common.pojo.entity.Process;
import com.xdcplus.interaction.common.pojo.query.ProcessQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 流程表 Mapper 接口
 *
 * @author Rong.Jia
 * @date  2021-05-31
 */
public interface ProcessMapper extends IBaseMapper<Process> {

    /**
     * 查询流程通过的名字
     *
     * @param name 的名字
     * @return {@link Process}
     */
    Process findProcessByName(@Param("name") String name);

    /**
     * 查询流程
     *
     * @param processQuery 流程查询
     * @return {@link List<Process>}
     */
    List<Process> findProcess(ProcessQuery processQuery);









}
