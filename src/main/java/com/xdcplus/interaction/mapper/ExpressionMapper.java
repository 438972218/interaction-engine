package com.xdcplus.interaction.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.interaction.common.pojo.entity.Expression;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 表达式标识表 Mapper 接口
 *
 * @author  Rong.Jia
 * @date  2021-05-31
 */
public interface ExpressionMapper extends IBaseMapper<Expression> {

    /**
     * 查询表达式通过的名字
     *
     * @param name 的名字
     * @return {@link Expression}
     */
    Expression findExpressionByName(@Param("name") String name);

    /**
     * 查询表达式通过类型
     *
     * @param type 类型
     * @return {@link Expression}
     */
    Expression findExpressionByType(@Param("type") Integer type);

    /**
     * 查询所有
     *
     * @return {@link List<Expression>}
     */
    List<Expression> findAll();












}
