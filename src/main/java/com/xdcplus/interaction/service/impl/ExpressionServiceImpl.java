package com.xdcplus.interaction.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.xdcplus.interaction.common.enums.ResponseEnum;
import com.xdcplus.interaction.common.exception.InteractionEngineException;
import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.interaction.common.pojo.dto.ExpressionDTO;
import com.xdcplus.interaction.common.pojo.entity.Expression;
import com.xdcplus.interaction.common.pojo.vo.ExpressionVO;
import com.xdcplus.interaction.mapper.ExpressionMapper;
import com.xdcplus.interaction.service.ExpressionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 表达式标识表 服务实现类
 *
 * @author Rong.Jia
 * @date  2021-05-31
 */
@Slf4j
@Service
public class ExpressionServiceImpl extends BaseServiceImpl<Expression, ExpressionVO, Expression, ExpressionMapper> implements ExpressionService {

    @Autowired
    private ExpressionMapper expressionMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveExpression(ExpressionDTO expressionDTO) {

        Expression expression = expressionMapper.findExpressionByName(expressionDTO.getName());
        if (ObjectUtil.isNotNull(expression)) {
            log.error("saveExpression() The expression type already exists");
            throw new InteractionEngineException(ResponseEnum.THE_EXPRESSION_ALREADY_EXISTS);
        }

        expression = expressionMapper.findExpressionByType(expressionDTO.getType());
        if (ObjectUtil.isNotNull(expression)) {
            log.error("saveExpression() The expression type already exists");
            throw new InteractionEngineException(ResponseEnum.THE_EXPRESSION_ALREADY_EXISTS);
        }

        expression = new Expression();
        BeanUtil.copyProperties(expressionDTO, expression);
        expression.setCreatedTime(DateUtil.current());

        return this.save(expression);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateExpression(ExpressionDTO expressionDTO) {

        Expression expression = this.getById(expressionDTO.getId());
        if (ObjectUtil.isNull(expression)) {
            log.error("updateExpression() The expression type does not exist or has been deleted");
            throw new InteractionEngineException(ResponseEnum.THE_EXPRESSION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED);
        }

        if (!StrUtil.equals(expression.getName(), expressionDTO.getName())) {
            if (ObjectUtil.isNotNull( expressionMapper.findExpressionByName(expressionDTO.getName()))) {
                log.error("updateExpression() The expression type already exists");
                throw new InteractionEngineException(ResponseEnum.THE_EXPRESSION_ALREADY_EXISTS);
            }
        }

        if (!ObjectUtil.equals(expression.getType(), expressionDTO.getType())) {
            if (ObjectUtil.isNotNull(expressionMapper.findExpressionByType(expressionDTO.getType()))) {
                log.error("updateExpression() The expression type already exists");
                throw new InteractionEngineException(ResponseEnum.THE_EXPRESSION_ALREADY_EXISTS);
            }
        }

        expression.setName(expressionDTO.getName());
        expression.setType(expressionDTO.getType());
        expression.setUpdatedUser(expressionDTO.getUpdatedUser());
        expression.setUpdatedTime(DateUtil.current());
        expression.setDescription(expressionDTO.getDescription());

        return this.updateById(expression);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteExpression(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        Expression expression = this.getById(id);

        if (ObjectUtil.isNull(expression)) {
            log.error("deleteExpression() The expression does not exist or has been deleted");
            throw new InteractionEngineException(ResponseEnum.THE_EXPRESSION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED);
        }

        return this.removeById(id);
    }

    @Override
    public List<ExpressionVO> findExpression() {

        List<Expression> expressionList = expressionMapper.findAll();
        return this.objectConversion(expressionList);
    }
}
