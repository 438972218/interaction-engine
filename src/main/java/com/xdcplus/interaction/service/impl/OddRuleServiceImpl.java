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
import com.xdcplus.tool.constants.NumberConstant;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.tool.utils.PageableUtils;
import com.xdcplus.tool.utils.PropertyUtils;
import com.xdcplus.interaction.common.pojo.dto.OddRuleDTO;
import com.xdcplus.interaction.common.pojo.dto.OddRuleFilterDTO;
import com.xdcplus.interaction.common.pojo.entity.OddRule;
import com.xdcplus.interaction.common.pojo.query.OddRuleQuery;
import com.xdcplus.interaction.common.pojo.vo.OddRuleVO;
import com.xdcplus.interaction.mapper.OddRuleMapper;
import com.xdcplus.interaction.service.OddRuleService;
import com.xdcplus.interaction.service.RequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 单号规则 服务实现类
 *
 * @author Rong.Jia
 * @date  2021-05-31
 */
@Slf4j
@Service
public class OddRuleServiceImpl extends BaseServiceImpl<OddRule, OddRuleVO, OddRule, OddRuleMapper> implements OddRuleService {

    @Autowired
    private OddRuleMapper oddRuleMapper;

    @Autowired
    private RequestService requestService;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateAutoNumber(Long id, Long autoNumber) {

        if (ObjectUtil.isAllNotEmpty(id, autoNumber)) {
            oddRuleMapper.updateAutoNumber(id, autoNumber);
        }

    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveOddRule(OddRuleDTO oddRuleDTO) {

        OddRule oddRule = oddRuleMapper.findOddRuleByPrefix(oddRuleDTO.getPrefix());
        Assert.isNull(oddRule, ResponseEnum.THE_ORDER_NUMBER_RULE_ALREADY_EXISTS.getMessage());
        oddRule = oddRuleMapper.findOddRuleByName(oddRuleDTO.getName());
        Assert.isNull(oddRule, ResponseEnum.THE_ORDER_NUMBER_RULE_ALREADY_EXISTS.getMessage());

        oddRule = new OddRule();
        BeanUtil.copyProperties(oddRuleDTO, oddRule);
        oddRule.setCreatedTime(DateUtil.current());

        return this.save(oddRule);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateOddRule(OddRuleDTO oddRuleDTO) {

        OddRule oddRule = this.getById(oddRuleDTO.getId());
        Assert.notNull(oddRule,
                ResponseEnum.THE_ORDER_NUMBER_RULE_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        if (!StrUtil.equals(oddRule.getPrefix(), oddRuleDTO.getPrefix())) {
            Assert.isNull(oddRuleMapper.findOddRuleByPrefix(oddRuleDTO.getPrefix()),
                    ResponseEnum.THE_ORDER_NUMBER_RULE_ALREADY_EXISTS.getMessage());
        }

        if (!StrUtil.equals(oddRule.getName(), oddRuleDTO.getName())) {
            Assert.isNull(oddRuleMapper.findOddRuleByName(oddRuleDTO.getName()),
                    ResponseEnum.THE_ORDER_NUMBER_RULE_ALREADY_EXISTS.getMessage());
        }

        oddRule.setName(oddRuleDTO.getName());
        oddRule.setPrefix(oddRuleDTO.getPrefix());
        oddRule.setAlgorithm(oddRuleDTO.getAlgorithm());
        oddRule.setUpdatedUser(oddRuleDTO.getUpdatedUser());
        oddRule.setUpdatedTime(DateUtil.current());
        oddRule.setDescription(oddRuleDTO.getDescription());

        return this.updateById(oddRule);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteOddRule(Long id) {

        OddRule oddRule = this.getById(id);

        Assert.notNull(oddRule,
                ResponseEnum.THE_ORDER_NUMBER_RULE_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());
        Assert.isFalse(requestService.existRequestByRuleId(oddRule.getId()), ResponseEnum.DATA_QUOTE.getMessage());

        return this.removeById(id);

    }

    @Override
    public PageVO<OddRuleVO> findOddRule(OddRuleFilterDTO oddRuleFilterDTO) {

        PageVO<OddRuleVO> pageVO = new PageVO<>();

        if (oddRuleFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(oddRuleFilterDTO.getCurrentPage(), oddRuleFilterDTO.getPageSize(),
                    oddRuleFilterDTO.getOrderType(), oddRuleFilterDTO.getOrderField());
        }

        OddRuleQuery oddRuleQuery = BeanUtil.copyProperties(oddRuleFilterDTO, OddRuleQuery.class);
        List<OddRule> oddRuleList = oddRuleMapper.findOddRule(oddRuleQuery);

        PageInfo<OddRule> pageInfo = new PageInfo<>(oddRuleList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(oddRuleList));

        return pageVO;
    }

    @Override
    public OddRuleVO findOne(Long ruleId) {

        Assert.notNull(ruleId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(ruleId));
    }

    @Override
    public Boolean validationRuleExists(String nameOrPrefix) {

        return ObjectUtil.isNotNull(oddRuleMapper.findOddRuleByName(nameOrPrefix))
                || ObjectUtil.isNotNull(oddRuleMapper.findOddRuleByPrefix(nameOrPrefix))
                ? Boolean.TRUE : Boolean.FALSE;

    }


}
