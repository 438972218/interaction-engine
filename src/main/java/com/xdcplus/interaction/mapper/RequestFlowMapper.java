package com.xdcplus.interaction.mapper;

import com.xdcplus.interaction.common.pojo.vo.RequestFlowVO;
import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.interaction.common.pojo.bo.RequestFlowBO;
import com.xdcplus.interaction.common.pojo.entity.RequestFlow;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 流转意见表 Mapper 接口
 * @author Rong.Jia
 * @date  2021-05-31
 */
public interface RequestFlowMapper extends IBaseMapper<RequestFlow> {

    /**
     * 存在流转信息流通过流选项
     *
     * @param flowOption 流选项
     * @return {@link List<RequestFlow>}
     */
    List<RequestFlow> findRequestFlowByFlowOption(@Param("flowOption") Integer flowOption);

    /**
     *  根据表单ID 查询完成的流转信息
     * @param requestId 请求主键
     * @return {@link List<RequestFlowBO>}
     */
    List<RequestFlowBO> findRequestFlowRequestId(@Param("requestId") Long requestId);

    /**
     *  根据表单ID和状态ID查询 当前的流转状态
     * @param requestId    表单ID
     * @param statusId 状态ID
     * @return {@link List<RequestFlowBO>}
     */
    List<RequestFlowBO> findRequestFlowByRequestIdAndFromStatusId(@Param("requestId") Long requestId,
                                                                @Param("statusId") Long statusId);

    /**
     *  根据表单ID和状态ID查询 当前的流转状态
     * @param requestId    表单ID
     * @param statusId 状态ID
     * @return {@link List<RequestFlowBO>}
     */
    List<RequestFlowBO> findRequestFlowByRequestIdAndToStatusId(@Param("requestId") Long requestId,
                                                                @Param("statusId") Long statusId);

    /**
     * 根据流程操作，表单ID查询流程信息
     *
     * @param flowOption 流选项
     * @param requestId 表单ID
     * @return {@link List<RequestFlow>}
     */
    List<RequestFlowBO> findRequestFlowByFlowOptionAndRequestId(@Param("flowOption") Integer flowOption,
                                                        @Param("requestId") Long requestId);

    /**
     * 查询流转信息通过角色id或用户id
     *
     * @param roleIds 角色id
     * @param userId  用户id
     * @return {@link List<RequestFlowBO>} 流转信息
     */
    List<RequestFlowBO> findRequestFlowByRoleIdsOrUserIds(@Param("roleIds") Set<Long> roleIds,
                                                          @Param("userId") Long userId);























}
