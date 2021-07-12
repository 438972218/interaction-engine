package com.xdcplus.interaction.mapper;


import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.interaction.common.pojo.entity.ProcessConfigNode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 流程配置节点信息 Mapper 接口
 * @author Rong.Jia
 * @date  2021-06-17
 */
public interface ProcessConfigNodeMapper extends IBaseMapper<ProcessConfigNode> {

    /**
     * 查询配置节点通过过程主键和版本
     *
     * @param processId 过程主键
     * @param version   版本
     * @return {@link List <ProcessConfigNode>} 流程配置节点信息集合
     */
    List<ProcessConfigNode> findConfigNodeByProcessIdAndVersion(@Param("processId") Long processId,
                                                                @Param("version") String version);


}
