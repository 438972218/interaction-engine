package com.xdcplus.interaction.service;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.interaction.common.pojo.dto.OddRuleDTO;
import com.xdcplus.interaction.common.pojo.dto.OddRuleFilterDTO;
import com.xdcplus.interaction.common.pojo.entity.OddRule;
import com.xdcplus.interaction.common.pojo.vo.OddRuleVO;

/**
 * 单号规则 服务类
 *
 * @author Rong.Jia
 * @date  2021-05-31
 */
public interface OddRuleService extends BaseService<OddRule, OddRuleVO, OddRule> {

    /**
     * 修改自增长基数
     * @param id   id
     * @param autoNumber 自动数量
     */
    void updateAutoNumber(Long id, Long autoNumber);

    /**
     * 添加单号规则
     * @param oddRuleDTO 奇怪的规则dto
     * @return 是否新增成功
     */
    Boolean saveOddRule(OddRuleDTO oddRuleDTO);

    /**
     * 修改单号的规则
     *
     * @param oddRuleDTO 奇怪的规则dto
     * @return {@link Boolean} 是否修改成功
     */
    Boolean updateOddRule(OddRuleDTO oddRuleDTO);

    /**
     * 删除规则
     * @param id 主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteOddRule(Long id);

    /**
     * 查询规则列表
     *
     * @param oddRuleFilterDTO 查询对象
     * @return {@link PageVO<OddRuleVO>} 规则信息
     */
    PageVO<OddRuleVO> findOddRule(OddRuleFilterDTO oddRuleFilterDTO);

    /**
     * 查询一个
     *
     * @param ruleId 规则主键
     * @return {@link OddRuleVO}
     */
    OddRuleVO findOne(Long ruleId);

    /**
     * 验证规则存在
     *
     * @param nameOrPrefix 规则名称或者前缀
     * @return {@link Boolean} 是否存在
     */
    Boolean validationRuleExists(String nameOrPrefix);





























}
