package com.xdcplus.interaction.mapper;

import com.xdcplus.interaction.common.pojo.entity.ProcessConfigNodeTemplate;
import com.xdcplus.mp.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 流程配置节点模板 Mapper 接口
 *
 * @author Rong.Jia
 * @date  2021-06-22
 */
public interface ProcessConfigNodeTemplateMapper extends IBaseMapper<ProcessConfigNodeTemplate> {

    /**
     * 查询模板节点通过模板主键
     *
     * @param templateId 模板主键
     * @return {@link List <ProcessConfigNodeTemplate>} 节点信息
     */
    List<ProcessConfigNodeTemplate> findTemplateNodesByTemplateId(@Param("templateId") Long templateId);


}
