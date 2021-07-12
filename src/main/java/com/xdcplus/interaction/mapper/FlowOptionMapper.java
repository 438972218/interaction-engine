package com.xdcplus.interaction.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.interaction.common.pojo.entity.FlowOption;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 流程操作表 Mapper 接口
 * @author Rong.Jia
 * @date  2021-05-31
 */
public interface FlowOptionMapper extends IBaseMapper<FlowOption> {

    /**
     * 查询流程操作通过 数字
     *
     * @param value 数字
     * @return {@link FlowOption}
     */
    FlowOption findFlowOptionByValue(@Param("value") Integer value);

    /**
     * 查询流程操作通过 数字含义
     *
     * @param valueStr  数字含义
     * @return {@link FlowOption}
     */
    FlowOption findFlowOptionByValueStr(@Param("valueStr") String valueStr);

    /**
     * 查询不包含指定的操作信息
     * @param values  操作
     * @return {@link List<FlowOption>}
     */
    List<FlowOption> findFlowOptionsNot(@Param("values") List<Integer> values);













}
