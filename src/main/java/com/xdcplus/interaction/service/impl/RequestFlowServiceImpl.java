package com.xdcplus.interaction.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ObjectUtil;
import com.xdcplus.interaction.common.enums.ResponseEnum;
import com.xdcplus.interaction.common.exception.InteractionEngineException;
import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.mp.utils.AuthUtils;
import com.xdcplus.tool.constants.AuthConstant;
import com.xdcplus.tool.constants.NumberConstant;
import com.xdcplus.interaction.common.pojo.bo.RequestFlowBO;
import com.xdcplus.interaction.common.pojo.dto.ProcessTransforDTO;
import com.xdcplus.interaction.common.pojo.dto.RequestFlowDTO;
import com.xdcplus.interaction.common.pojo.entity.*;
import com.xdcplus.interaction.common.pojo.entity.Process;
import com.xdcplus.interaction.common.pojo.vo.RequestFlowVO;
import com.xdcplus.interaction.common.pojo.vo.RequestVO;
import com.xdcplus.interaction.factory.flow.ProcessTransforFactory;
import com.xdcplus.interaction.factory.flow.ProcessTransforParam;
import com.xdcplus.interaction.mapper.RequestFlowMapper;
import com.xdcplus.interaction.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 流转意见表 服务实现类
 *
 * @author Rong.Jia
 * @date 2021-05-31
 */
@Slf4j
@Service
public class RequestFlowServiceImpl extends BaseServiceImpl<RequestFlowBO, RequestFlowVO, RequestFlow, RequestFlowMapper> implements RequestFlowService {

    @Autowired
    private RequestFlowMapper requestFlowMapper;

    @Autowired
    private ProcessTransforFactory processTransforFactory;

    @Autowired
    private ProcessService processService;

    @Autowired
    private ProcessStatusService processStatusService;

    @Autowired
    private FlowOptionService flowOptionService;

    @Autowired
    private RequestService requestService;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void processTransfor(ProcessTransforDTO processTransforDTO) {

        RequestVO requestVO = requestService.findOne(processTransforDTO.getRequestId());
        Assert.notNull(requestVO, ResponseEnum.THE_REQUEST_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        if (ObjectUtil.equal(NumberConstant.TWO, requestVO.getStatus().getMark())
                || ObjectUtil.equal(NumberConstant.FOUR, requestVO.getStatus().getMark())) {
            log.error("The flow has ended and cannot be continued");
            throw new InteractionEngineException(ResponseEnum.THE_FLOW_HAS_ENDED_AND_CANNOT_BE_CONTINUED);
        }

        ProcessTransforParam processTransforParam = new ProcessTransforParam();
        BeanUtil.copyProperties(processTransforDTO, processTransforParam);
        processTransforParam.setRequest(requestVO);

        // 开始流转
        processTransforFactory.transfor(processTransforParam);

    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void startTransfor(Long requestId) {

        RequestVO requestVO = requestService.findOne(requestId);
        Assert.notNull(requestVO, ResponseEnum.THE_REQUEST_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        ProcessTransforParam processTransforParam = new ProcessTransforParam();
        processTransforParam.setFlowOption(NumberConstant.THREE);
        processTransforParam.setRequest(requestVO);

        processTransforFactory.transfor(processTransforParam);

    }

    @Override
    public Boolean existRequestFlowByFlowOption(Integer flowOption) {

        if (ObjectUtil.isNotNull(flowOption)) {
            List<RequestFlow> requestFlows = requestFlowMapper.findRequestFlowByFlowOption(flowOption);
            return CollectionUtil.isNotEmpty(requestFlows) ? Boolean.TRUE : Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    @Override
    public List<RequestFlowVO> findRequestFlowRequestId(Long requestId) {

        Assert.notNull(requestId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        List<RequestFlowBO> requestFlowBOList = requestFlowMapper.findRequestFlowRequestId(requestId);
        return this.objectConversion(requestFlowBOList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveRequestFlow(RequestFlowDTO requestFlowDTO) {

        RequestFlow requestFlow = BeanUtil.copyProperties(requestFlowDTO, RequestFlow.class);
        requestFlow.setCreatedTime(DateUtil.current());
        try {
            requestFlow.setCreatedUser(AuthUtils.getCurrentUser());
        } catch (Exception e) {
            requestFlow.setCreatedUser(AuthConstant.ADMINISTRATOR);
        }

        requestFlow.setBeginTime(requestFlowDTO.getBeginTime());

        if (ObjectUtil.isNull(requestFlowDTO.getFlowOptionValue())) {
            requestFlow.setFlowOptionValue(NumberConstant.ONE);
        }

        Long assignorId  = requestFlowDTO.getFromUserId();
        if (Validator.isNull(assignorId)) {
            assignorId = NumberConstant.A_NEGATIVE.longValue();
        }

        requestFlow.setFromUserId(assignorId);

        return this.save(requestFlow);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateRequestFlow(RequestFlowDTO requestFlowDTO) {

        RequestFlow requestFlow = this.getById(requestFlowDTO.getId());
        Assert.notNull(requestFlow, ResponseEnum.THE_FLOW_INFORMATION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        requestFlow.setFlowOptionValue(requestFlowDTO.getFlowOptionValue());
        requestFlow.setEndTime(requestFlowDTO.getEndTime());

        try {
            requestFlow.setUpdatedUser(AuthUtils.getCurrentUser());
        } catch (Exception e) {
            requestFlow.setUpdatedUser(AuthConstant.ADMINISTRATOR);
        }
        requestFlow.setToUserId(requestFlowDTO.getToUserId());
        requestFlow.setUpdatedTime(DateUtil.current());
        requestFlow.setDescription(requestFlowDTO.getDescription());

        return this.updateById(requestFlow);
    }

    @Override
    public List<RequestFlowBO> findRequestFlowByFlowOptionAndRequestId(Integer flowOption, Long requestId) {

        Assert.notNull(requestId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        if (ObjectUtil.isNull(flowOption)) {
            flowOption = NumberConstant.A_NEGATIVE;
        }

        return requestFlowMapper.findRequestFlowByFlowOptionAndRequestId(flowOption, requestId);
    }

    @Override
    public Boolean existRequestFlowByFlowOptionAndRequestId(Integer flowOption, Long requestId) {
        List<RequestFlowBO> requestFlowBOList = this.findRequestFlowByFlowOptionAndRequestId(flowOption, requestId);
        return CollectionUtil.isNotEmpty(requestFlowBOList) ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public List<RequestFlowVO> findRequestFlowByRequestIdAndFromStatusId(Long requestId, Long statusId) {

        Assert.notNull(requestId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        return this.objectConversion(requestFlowMapper.findRequestFlowByRequestIdAndFromStatusId(requestId, statusId));
    }

    @Override
    public List<RequestFlowVO> findRequestFlowByRequestIdAndToStatusId(Long requestId, Long statusId) {

        Assert.notNull(requestId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        return this.objectConversion(requestFlowMapper.findRequestFlowByRequestIdAndToStatusId(requestId, statusId));
    }

    @Override
    public RequestFlowVO objectConversion(RequestFlowBO requestFlowBO) {

        RequestFlowVO requestFlowVO = super.objectConversion(requestFlowBO);

        Request request = requestFlowBO.getRequest();
        ProcessStatus lastStatus = requestFlowBO.getFromStatus();
        ProcessStatus nextStatus = requestFlowBO.getToStatus();
        FlowOption flowOption = requestFlowBO.getFlowOption();
        Process process = requestFlowBO.getProcess();

        Optional.ofNullable(request).ifPresent(r -> requestFlowVO.setRequest(requestService.objectConversion(r)));
        Optional.ofNullable(lastStatus).ifPresent(r -> requestFlowVO.setFromStatus(processStatusService.objectConversion(r)));
        Optional.ofNullable(nextStatus).ifPresent(r -> requestFlowVO.setToStatus(processStatusService.objectConversion(r)));
        Optional.ofNullable(flowOption).ifPresent(r -> requestFlowVO.setFlowOption(flowOptionService.objectConversion(r)));
        Optional.ofNullable(process).ifPresent(r -> requestFlowVO.setProcess(processService.objectConversion(r)));

        return requestFlowVO;
    }




}
