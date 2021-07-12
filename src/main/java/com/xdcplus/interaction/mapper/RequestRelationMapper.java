package com.xdcplus.interaction.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.interaction.common.pojo.entity.RequestRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 表单关联表 Mapper 接口
 * @author Rong.Jia
 * @date  2021-05-31
 */
public interface RequestRelationMapper extends IBaseMapper<RequestRelation> {

    /**
     * 查询表单关系
     *
     * @param requestId 表单主键
     * @param parentId  父主键
     * @return {@link RequestRelation}
     */
    RequestRelation findRequestRelation(@Param("requestId") Long requestId, @Param("parentId") Long parentId);

    /**
     * 查询表单关系通过表单主键
     *
     * @param requestId 表单主键
     * @return {@link List <RequestRelation>}
     */
    List<RequestRelation> findRequestRelationByRequestId(@Param("requestId") Long requestId);

    /**
     * 查询表单关系通过表单父级主键
     *
     * @param parentId 表单父级主键
     * @return {@link List<RequestRelation>}
     */
    List<RequestRelation> findRequestRelationByParentId(@Param("parentId") Long parentId);










}
