package com.xdcplus.interaction.service;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.interaction.common.pojo.dto.QualifierDTO;
import com.xdcplus.interaction.common.pojo.dto.QualifierFilterDTO;
import com.xdcplus.interaction.common.pojo.entity.Qualifier;
import com.xdcplus.interaction.common.pojo.vo.QualifierVO;

/**
 * 流程规则 服务类
 *
 * @author Rong.Jia
 * @date  2021-05-31
 */
public interface QualifierService extends BaseService<Qualifier, QualifierVO, Qualifier> {

    /**
     * 添加限定符
     *
     * @param qualifierDTO 限定符dto
     * @return {@link Boolean}
     */
    Boolean saveQualifier(QualifierDTO qualifierDTO);

    /**
     * 修改限定符
     *
     * @param qualifierDTO 限定符dto
     * @return {@link Boolean}
     */
    Boolean updateQualifier(QualifierDTO qualifierDTO);

    /**
     * 删除限定符
     *
     * @param qualifierId 限定符主键
     * @return {@link Boolean}
     */
    Boolean deleteQualifier(Long qualifierId);

    /**
     * 查询限定符
     *
     * @param qualifierFilterDTO 限定符过滤dto
     * @return {@link PageVO<QualifierVO>}
     */
    PageVO<QualifierVO> findQualifier(QualifierFilterDTO qualifierFilterDTO);

    /**
     * 查询一个
     * @param qualifierId 限定符主键
     * @return {@link QualifierVO}
     */
    QualifierVO findOne(Long qualifierId);

    /**
     * 查询限定符
     *
     * @param qualifierDTO 限定符DTO
     * @return {@link Long} 主键ID
     */
    Long getQualifier(QualifierDTO qualifierDTO);


    /**
     * 验证流程规则信息是否存在
     *
     * @param name 名字
     * @return {@link Boolean} 是否存在
     */
    Boolean validationExists(String name);
}
