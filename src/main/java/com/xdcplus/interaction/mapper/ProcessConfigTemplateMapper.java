package com.xdcplus.interaction.mapper;

import com.xdcplus.interaction.common.pojo.entity.ProcessConfigTemplate;
import com.xdcplus.interaction.common.pojo.query.ProcessConfigTemplateQuery;
import com.xdcplus.mp.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 流程配置模板信息 Mapper 接口
 *
 * @author Rong.Jia
 * @date  2021-06-22
 */
public interface ProcessConfigTemplateMapper extends IBaseMapper<ProcessConfigTemplate> {

    /**
     * 查询模板
     *
     * @param name 名字
     * @return {@link ProcessConfigTemplate} 模板信息
     */
    ProcessConfigTemplate findTemplateByName(@Param("name") String name);

    /**
     * 查询模板
     *
     * @param query 查询条件
     * @return {@link List<ProcessConfigTemplate>} 模板信息
     */
    List<ProcessConfigTemplate> findTemplate(ProcessConfigTemplateQuery query);




}
