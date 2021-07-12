package com.xdcplus.interaction.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.xdcplus.interaction.common.enums.ResponseEnum;
import com.xdcplus.interaction.common.exception.InteractionEngineException;
import com.xdcplus.interaction.common.pojo.entity.RequestRelation;
import com.xdcplus.interaction.mapper.RequestRelationMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdcplus.interaction.service.RequestRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 表单关联表 服务实现类
 *
 * @author Rong.Jia
 * @date  2021-05-31
 */
@Slf4j
@Service
public class RequestRelationServiceImpl extends ServiceImpl<RequestRelationMapper, RequestRelation> implements RequestRelationService {

    @Autowired
    private RequestRelationMapper requestRelationMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveRequestRelation(Long requestId, Long parentId) {

        RequestRelation requestRelation = requestRelationMapper.findRequestRelation(requestId, parentId);
        if (ObjectUtil.isNotNull(requestRelation)) {
            log.error("saveRequestRelation() Form duplicate association");
            throw new InteractionEngineException(ResponseEnum.FORM_DUPLICATE_ASSOCIATION);
        }

        requestRelation = new RequestRelation();
        requestRelation.setParentId(parentId);
        requestRelation.setRequestId(requestId);

        return this.save(requestRelation);
    }

    @Override
    public List<RequestRelation> findRequestRelationByRequestId(Long requestId) {

        Assert.notNull(requestId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return requestRelationMapper.findRequestRelationByRequestId(requestId);
    }

    @Override
    public List<RequestRelation> findRequestRelationByParentId(Long parentId) {

        Assert.notNull(parentId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return requestRelationMapper.findRequestRelationByParentId(parentId);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateRequestRelation(Long id, Long requestId, Long parentId) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        RequestRelation requestRelation = this.getById(id);
        if (ObjectUtil.isNotNull(requestRelation)) {
            requestRelation.setRequestId(requestId);
            requestRelation.setParentId(parentId);

            return this.updateById(requestRelation);
        }

        return Boolean.FALSE;
    }
}
