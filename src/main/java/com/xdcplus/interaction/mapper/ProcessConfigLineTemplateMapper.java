package com.xdcplus.interaction.mapper;

import com.xdcplus.interaction.common.pojo.entity.ProcessConfigLineTemplate;
import com.xdcplus.mp.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 流程配置线模板 Mapper 接口
 *
 * @author Rong.Jia
 * @date  2021-06-22
 */
public interface ProcessConfigLineTemplateMapper extends IBaseMapper<ProcessConfigLineTemplate> {

    /**
     * 查询模板线通过模板主键
     *
     * @param templateId 模板主键
     * @return {@link List<ProcessConfigLineTemplate>} 线信息
     */
    List<ProcessConfigLineTemplate> findTemplateLinesByTemplateId(@Param("templateId") Long templateId);














}
