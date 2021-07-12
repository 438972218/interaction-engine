package com.xdcplus.interaction.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xdcplus.interaction.common.pojo.entity.RequestRelation;

import java.util.List;

/**
 * 表单关联表 服务类
 *
 * @author Rong.Jia
 * @date  2021-05-31
 */
public interface RequestRelationService extends IService<RequestRelation> {

    /**
     * 添加表单关系
     *
     * @param requestId 表单主键
     * @param parentId  父主键
     * @return {@link Boolean}
     */
    Boolean saveRequestRelation(Long requestId, Long parentId);

    /**
     * 查询表单关系通过表单主键
     *
     * @param requestId 表单主键
     * @return {@link List<RequestRelation>}
     */
    List<RequestRelation> findRequestRelationByRequestId(Long requestId);

    /**
     * 查询表单关系通过表单父级主键
     *
     * @param parentId 表单父级主键
     * @return {@link List<RequestRelation>}
     */
    List<RequestRelation> findRequestRelationByParentId(Long parentId);

    /**
     * 修改表单关系
     *
     * @param requestId 表单主键
     * @param parentId  父主键
     * @param id  主键
     * @return {@link Boolean}
     */
    Boolean updateRequestRelation(Long id, Long requestId, Long parentId);
















}
