package com.xdcplus.interaction.service;

import com.xdcplus.interaction.common.pojo.dto.HandleMattersFilterDTO;
import com.xdcplus.interaction.common.pojo.dto.RequestConfigDTO;
import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.interaction.common.pojo.dto.RequestDTO;
import com.xdcplus.interaction.common.pojo.dto.RequestFilterDTO;
import com.xdcplus.interaction.common.pojo.entity.Request;
import com.xdcplus.interaction.common.pojo.vo.RequestVO;

/**
 * 流程表单 服务类
 *
 * @author Rong.Jia
 * @date  2021-05-31
 */
public interface RequestService extends BaseService<Request, RequestVO, Request> {

    /**
     * 添加表单
     *
     * @param requestDTO 表单dto
     * @return {@link Boolean}
     */
    RequestVO saveRequest(RequestDTO requestDTO);

    /**
     * 查询表单
     *
     * @param requestFilterDTO 表单过滤dto
     * @return {@link PageVO<RequestVO>}
     */
    PageVO<RequestVO> findRequest(RequestFilterDTO requestFilterDTO);

    /**
     * 删除表单
     *
     * @param requestId 表单主键
     * @return {@link Boolean}
     */
    Boolean deleteRequest(Long requestId);

    /**
     * 存在表单通过规则主键
     *
     * @param ruleId 规则主键
     * @return {@link Boolean}
     */
    Boolean existRequestByRuleId(Long ruleId);

    /**
     * 存在表单通过流程主键
     *
     * @param processId 流程主键
     * @return {@link Boolean}
     */
    Boolean existRequestByProcessId(Long processId);

    /**
     * 查询一个
     *
     * @param requestId 表单主键
     * @return {@link RequestVO}
     */
    RequestVO findOne(Long requestId);

    /**
     * 修改状态主键通过主键
     *
     * @param id       主键
     * @param statusId 状态主键
     */
    void updateStatusIdById(Long id, Long statusId);

    /**
     * 修改流程主键和流程配置版本 通过表单主键
     *
     * @param requestConfigDTO 修改参数
     */
    void updateProcessIdAndVersionById(RequestConfigDTO requestConfigDTO);

    /**
     * 验证表单信息是否存在
     *
     * @param title 名字
     * @return {@link Boolean} 是否存在
     */
    Boolean validationExists(String title);

    /**
     * 表单办理事项
     *
     * @param handleMattersFilterDTO 事项参数
     * @return {@link PageVO<RequestVO>} 表单信息
     */
    PageVO<RequestVO> handleMatters(HandleMattersFilterDTO handleMattersFilterDTO);

    /**
     * 根据状态ID标识 计算表单个数
     *
     * @param statusId 状态标识
     * @return {@link Integer} 表单个数
     */
    Integer countRequestByStatusId(Long statusId);












}
