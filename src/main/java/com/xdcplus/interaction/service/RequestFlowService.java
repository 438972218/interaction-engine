package com.xdcplus.interaction.service;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.interaction.common.pojo.bo.RequestFlowBO;
import com.xdcplus.interaction.common.pojo.dto.ProcessTransforDTO;
import com.xdcplus.interaction.common.pojo.dto.RequestFlowDTO;
import com.xdcplus.interaction.common.pojo.entity.RequestFlow;
import com.xdcplus.interaction.common.pojo.vo.RequestFlowVO;

import java.util.List;
import java.util.Set;

/**
 * 流转意见表 服务类
 *
 * @author Rong.Jia
 * @date  2021-05-31
 */
public interface RequestFlowService extends BaseService<RequestFlowBO, RequestFlowVO, RequestFlow> {

    /**
     * 流转
     * @param processTransforDTO 流选项
     */
    void processTransfor(ProcessTransforDTO processTransforDTO);


    /**
     * 开始流程
     * @param requestId 表单主键
     * @param processTransforDTO 流转条件
     */
    void startTransfor(Long requestId, ProcessTransforDTO processTransforDTO);

    /**
     * 存在流转信息 通过流选项
     *
     * @param flowOption 流选项
     * @return {@link Boolean}
     */
    Boolean  existRequestFlowByFlowOption(Integer flowOption);

    /**
     *  根据表单ID 查询完成的流转信息 （流转链）
     * @param requestId 请求主键
     * @return {@link List <RequestFlowVO>} 流转信息
     */
    List<RequestFlowVO> findRequestFlowRequestId(Long requestId);

    /**
     * 添加流转过程
     * @param requestFlowDTO 请求流dto
     * @return {@link Boolean}
     */
    Boolean saveRequestFlow(RequestFlowDTO requestFlowDTO);

    /**
     *  修改流转过程
     * @param requestFlowDTO 请求流dto
     * @return {@link Boolean}
     */
    Boolean updateRequestFlow(RequestFlowDTO requestFlowDTO);

    /**
     * 根据流程操作，表单ID查询流程信息
     *
     * @param flowOption 流选项
     * @param requestId 表单ID
     * @return {@link List<RequestFlow>}
     */
    List<RequestFlowBO> findRequestFlowByFlowOptionAndRequestId(Integer flowOption, Long requestId);

    /**
     * 根据流程操作，表单ID判断是否有流程信息
     *
     * @param flowOption 流选项
     * @param requestId 表单ID
     * @return {@link Boolean}
     */
    Boolean existRequestFlowByFlowOptionAndRequestId(Integer flowOption, Long requestId);

    /**
     *  根据表单ID和状态ID查询 当前的流转状态
     * @param requestId    表单ID
     * @param statusId 状态ID
     * @return {@link List<RequestFlowBO>}
     */
    List<RequestFlowVO> findRequestFlowByRequestIdAndFromStatusId(Long requestId, Long statusId);

    /**
     *  根据表单ID和状态ID查询 当前的流转状态
     * @param requestId    表单ID
     * @param statusId 状态ID
     * @return {@link List<RequestFlowBO>}
     */
    List<RequestFlowVO> findRequestFlowByRequestIdAndToStatusId(Long requestId, Long statusId);

    /**
     * 查询流转信息通过角色id或用户id
     *
     * @param roleIds 角色id
     * @param userId  用户id
     * @return {@link List<RequestFlowVO>} 流转信息
     */
    List<RequestFlowVO> findRequestFlowByRoleIdsOrUserIds(Set<Long> roleIds, Long userId);



















}
