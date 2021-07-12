package com.xdcplus.interaction.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.interaction.common.pojo.entity.Qualifier;
import com.xdcplus.interaction.common.pojo.query.QualifierQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 流程规则 Mapper 接口
 * @author Rong.Jia
 * @since 2021-05-31
 */
public interface QualifierMapper extends IBaseMapper<Qualifier> {

    /**
     * 查询限定符通过的名字
     *
     * @param name 名字
     * @return {@link Qualifier}
     */
    Qualifier findQualifierByName(@Param("name") String name);

    /**
     * 查询限定符
     *
     * @param qualifierQuery 限定符的查询
     * @return {@link List<Qualifier>}
     */
    List<Qualifier> findQualifier(QualifierQuery qualifierQuery);











}
