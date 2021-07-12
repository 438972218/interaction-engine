package com.xdcplus.interaction.service;


import com.xdcplus.interaction.common.pojo.dto.ProcessConfigTemplateDTO;
import com.xdcplus.interaction.common.pojo.dto.ProcessConfigTemplateFilterDTO;
import com.xdcplus.interaction.common.pojo.dto.ProcessTemplateConfigDTO;
import com.xdcplus.interaction.common.pojo.entity.ProcessConfigTemplate;
import com.xdcplus.interaction.common.pojo.vo.ProcessConfigTemplateInfoVO;
import com.xdcplus.interaction.common.pojo.vo.ProcessConfigTemplateVO;
import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;

/**
 * 流程配置模板信息 服务类
 *
 * @author Rong.Jia
 * @date  2021-06-22
 */
public interface ProcessConfigTemplateService extends BaseService<ProcessConfigTemplate, ProcessConfigTemplateVO, ProcessConfigTemplate> {

    /**
     * 添加模板信息
     *
     * @param processConfigTemplateDTO 过程配置模板dto
     * @return {@link Boolean} 是否成功
     */
    Boolean saveTemplate(ProcessConfigTemplateDTO processConfigTemplateDTO);

    /**
     * 修改模板信息
     *
     * @param processConfigTemplateDTO 过程配置模板dto
     * @return {@link Boolean} 是否成功
     */
    Boolean updateTemplate(ProcessConfigTemplateDTO processConfigTemplateDTO);

    /**
     * 删除模板
     *
     * @param id 主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteTemplate(Long id);

    /**
     * 查询模板
     *
     * @param processConfigTemplateFilterDTO 过程配置模板过滤DTO
     * @return {@link PageVO<ProcessConfigTemplateVO>} 模板信息
     */
    PageVO<ProcessConfigTemplateVO> findTemplate(ProcessConfigTemplateFilterDTO processConfigTemplateFilterDTO);

    /**
     * 添加模板配置
     *
     * @param processTemplateConfigDTO 过程模板配置DTO
     */
    void saveTemplateConfig(ProcessTemplateConfigDTO processTemplateConfigDTO);

    /**
     * 修改模板配置
     *
     * @param processTemplateConfigDTO 过程模板配置DTO
     */
    void updateTemplateConfig(ProcessTemplateConfigDTO processTemplateConfigDTO);

    /**
     * 删除模板配置
     *
     * @param templateId 模板主键
     */
    void deleteTemplateConfig(Long templateId);

    /**
     * 查询模板配置信息
     *
     * @param templateId 模板主键
     * @return {@link ProcessConfigTemplateInfoVO} 模板配置信息
     */
    ProcessConfigTemplateInfoVO findTemplateConfig(Long templateId);












































}
