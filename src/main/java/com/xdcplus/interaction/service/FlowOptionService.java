package com.xdcplus.interaction.service;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.interaction.common.pojo.dto.FlowOptionDTO;
import com.xdcplus.interaction.common.pojo.entity.FlowOption;
import com.xdcplus.interaction.common.pojo.vo.FlowOptionVO;

import java.util.List;

/**
 * 流程操作表 服务类
 *
 * @author Rong.Jia
 * @date  2021-05-31
 */
public interface FlowOptionService extends BaseService<FlowOption, FlowOptionVO, FlowOption> {

    /**
     * 添加流选项
     *
     * @param flowOptionDTO 流选项dto
     * @return {@link Boolean}
     */
    Boolean saveFlowOption(FlowOptionDTO flowOptionDTO);

    /**
     * 修改流选项
     *
     * @param flowOptionDTO 流选项dto
     * @return {@link Boolean}
     */
    Boolean updateFlowOption(FlowOptionDTO flowOptionDTO);

    /**
     * 删除流选项
     *
     * @param flowOptionId 流选项主键
     * @return {@link Boolean}
     */
    Boolean deleteFlowOption(Long flowOptionId);

    /**
     * 查询流选项
     *
     * @return {@link List<FlowOptionVO>}
     */
    List<FlowOptionVO> findFlowOption();












}
