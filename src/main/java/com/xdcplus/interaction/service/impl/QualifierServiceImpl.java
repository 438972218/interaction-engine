package com.xdcplus.interaction.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageInfo;
import com.xdcplus.interaction.common.enums.ResponseEnum;
import com.xdcplus.interaction.common.exception.InteractionEngineException;
import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.tool.constants.AuthConstant;
import com.xdcplus.tool.constants.NumberConstant;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.tool.utils.PageableUtils;
import com.xdcplus.tool.utils.PropertyUtils;
import com.xdcplus.interaction.common.pojo.dto.QualifierDTO;
import com.xdcplus.interaction.common.pojo.dto.QualifierFilterDTO;
import com.xdcplus.interaction.common.pojo.entity.Qualifier;
import com.xdcplus.interaction.common.pojo.query.QualifierQuery;
import com.xdcplus.interaction.common.pojo.vo.QualifierVO;
import com.xdcplus.interaction.mapper.QualifierMapper;
import com.xdcplus.interaction.service.QualifierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 流程规则 服务实现类
 *
 * @author Rong.Jia
 * @date  2021-05-31
 */
@Slf4j
@Service
public class QualifierServiceImpl extends BaseServiceImpl<Qualifier, QualifierVO, Qualifier, QualifierMapper> implements QualifierService {

    @Autowired
    private QualifierMapper qualifierMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveQualifier(QualifierDTO qualifierDTO) {

        Qualifier qualifier = qualifierMapper.findQualifierByName(qualifierDTO.getName());
        if (ObjectUtil.isNotNull(qualifier)) {
            log.error("saveQualifier() The qualifier already exists");
            throw new InteractionEngineException(ResponseEnum.THE_QUALIFIER_ALREADY_EXISTS);
        }

        qualifier = new Qualifier();
        BeanUtil.copyProperties(qualifierDTO, qualifier);
        qualifier.setCreatedTime(DateUtil.current());

        return this.save(qualifier);

    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateQualifier(QualifierDTO qualifierDTO) {

        Qualifier qualifier = this.getById(qualifierDTO.getId());
        if (ObjectUtil.isNull(qualifier)) {
            log.error("updateQualifier() The qualifier does not exist or has been deleted");
            throw new InteractionEngineException(ResponseEnum.THE_QUALIFIER_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED);
        }

        if (!StrUtil.equals(qualifier.getName(), qualifierDTO.getName())) {
            if (ObjectUtil.isNotNull(qualifierMapper.findQualifierByName(qualifierDTO.getName()))) {
                log.error("updateQualifier() The qualifier already exists");
                throw new InteractionEngineException(ResponseEnum.THE_QUALIFIER_ALREADY_EXISTS);
            }
        }

        qualifier.setName(qualifierDTO.getName());
        qualifier.setScript(qualifierDTO.getScript());
        qualifier.setUpdatedUser(qualifierDTO.getUpdatedUser());
        qualifier.setUpdatedTime(DateUtil.current());
        qualifier.setDescription(qualifierDTO.getDescription());

        return this.updateById(qualifier);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteQualifier(Long qualifierId) {

        Assert.notNull(qualifierId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        Qualifier qualifier = this.getById(qualifierId);

        if (ObjectUtil.isNull(qualifier)) {
            log.error("deleteQualifier() The  qualifier does not exist or has been deleted");
            throw new InteractionEngineException(ResponseEnum.THE_QUALIFIER_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED);
        }

        // 判断流转配置是否关联

        return this.removeById(qualifierId);
    }

    @Override
    public PageVO<QualifierVO> findQualifier(QualifierFilterDTO qualifierFilterDTO) {

        PageVO<QualifierVO> pageVO = new PageVO<>();

        if (qualifierFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(qualifierFilterDTO);
        }

        QualifierQuery qualifierQuery = BeanUtil.copyProperties(qualifierFilterDTO, QualifierQuery.class);
        List<Qualifier> qualifierList = qualifierMapper.findQualifier(qualifierQuery);

        PageInfo<Qualifier> pageInfo = new PageInfo<>(qualifierList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(qualifierList));

        return pageVO;
    }

    @Override
    public QualifierVO findOne(Long qualifierId) {

        Assert.notNull(qualifierId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(qualifierId));
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Long getQualifier(QualifierDTO qualifierDTO) {

        Qualifier qualifier = qualifierMapper.findQualifierByName(qualifierDTO.getName());
        if (ObjectUtil.isNull(qualifier)) {
            qualifier = new Qualifier();
            qualifier.setScript(qualifier.getName());
            qualifier.setName(qualifier.getName());
            qualifier.setCreatedTime(DateUtil.current());
            qualifier.setCreatedUser(AuthConstant.ADMINISTRATOR);

            this.save(qualifier);
        }

        return qualifier.getId();
    }

    @Override
    public Boolean validationExists(String name) {

        Assert.notBlank(name, ResponseEnum.THE_NAME_CANNOT_BE_EMPTY.getMessage());

        return ObjectUtil.isNotNull(qualifierMapper.findQualifierByName(name))
                ? Boolean.TRUE : Boolean.FALSE;
    }
}
