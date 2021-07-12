package com.xdcplus.interaction.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.xdcplus.interaction.common.enums.ResponseEnum;
import com.xdcplus.interaction.common.pojo.dto.RequestConfigDTO;
import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.tool.constants.NumberConstant;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.tool.utils.PageableUtils;
import com.xdcplus.tool.utils.PropertyUtils;
import com.xdcplus.interaction.common.pojo.dto.RequestDTO;
import com.xdcplus.interaction.common.pojo.dto.RequestFilterDTO;
import com.xdcplus.interaction.common.pojo.entity.Request;
import com.xdcplus.interaction.common.pojo.entity.RequestRelation;
import com.xdcplus.interaction.common.pojo.query.RequestQuery;
import com.xdcplus.interaction.common.pojo.vo.OddRuleVO;
import com.xdcplus.interaction.common.pojo.vo.RequestVO;
import com.xdcplus.interaction.factory.algorithm.OddAlgorithmFactory;
import com.xdcplus.interaction.mapper.RequestMapper;
import com.xdcplus.interaction.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 流程表单 服务实现类
 *
 * @author Rong.Jia
 * @date  2021-05-31
 */
@Slf4j
@Service
public class RequestServiceImpl extends BaseServiceImpl<Request, RequestVO, Request, RequestMapper> implements RequestService {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ProcessService processService;

    @Autowired
    private ProcessStatusService processStatusService;

    @Autowired
    private OddRuleService oddRuleService;

    @Autowired
    private OddAlgorithmFactory oddAlgorithmFactory;

    @Autowired
    private RequestRelationService requestRelationService;

    @Autowired
    private RequestFlowService requestFlowService;

    @Autowired
    private ProcessConfigService processConfigService;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public RequestVO saveRequest(RequestDTO requestDTO) {

        Request request = requestMapper.findRequestByTitle(requestDTO.getTitle());
        Assert.isNull(request, ResponseEnum.THE_REQUEST_ALREADY_EXISTS.getMessage());
        Assert.notNull(processService.findOne(requestDTO.getProcessId()),
                ResponseEnum.THE_PROCESS_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        OddRuleVO oddRuleVO = oddRuleService.findOne(requestDTO.getRuleId());
        Assert.notNull(oddRuleVO, ResponseEnum.THE_ORDER_NUMBER_RULE_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        Assert.isTrue(processConfigService.existConfigByProcessIdAndVersion(requestDTO.getProcessId(), requestDTO.getConfigVersion()),
                ResponseEnum.THE_PROCESS_CONFIGURATION_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        request = new Request();
        BeanUtil.copyProperties(requestDTO, request);
        request.setCreatedTime(DateUtil.current());
        request.setStatusId(NumberConstant.ONE.longValue());

        String oddNumber = oddAlgorithmFactory.algorithmProcessor(oddRuleVO.getId(), oddRuleVO.getAutoNumber(),
                oddRuleVO.getPrefix(), oddRuleVO.getAlgorithm());
        request.setOddNumber(oddNumber);

        boolean save = this.save(request);

        Set<Long> parentIds = requestDTO.getParentIds();
        if (CollectionUtil.isNotEmpty(parentIds)) {
            for (Long parentId : parentIds) {
                requestRelationService.saveRequestRelation(request.getId(), parentId);
            }
        }

        requestFlowService.startTransfor(request.getId());

        return this.objectConversion(request);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteRequest(Long requestId) {

        Assert.notNull(requestId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        Request request = this.getById(requestId);

        Assert.notNull(request, ResponseEnum.THE_REQUEST_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        return this.removeById(requestId);
    }

    @Override
    public Boolean existRequestByRuleId(Long ruleId) {

        if (ObjectUtil.isNotNull(ruleId)) {
            List<Request> requestList = requestMapper.findRequestByRuleId(ruleId);
            return CollectionUtil.isNotEmpty(requestList) ? Boolean.TRUE : Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    @Override
    public Boolean existRequestByProcessId(Long processId) {

        if (ObjectUtil.isNotNull(processId)) {
            List<Request> requestList = requestMapper.findRequestByProcessId(processId);
            return CollectionUtil.isNotEmpty(requestList) ? Boolean.TRUE : Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    @Override
    public RequestVO findOne(Long requestId) {

        Assert.notNull(requestId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(requestId));
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateStatusIdById(Long id, Long statusId) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        Request request = this.getById(id);
        Assert.notNull(request, ResponseEnum.THE_REQUEST_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        requestMapper.updateStatusIdById(id, statusId);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateProcessIdAndVersionById(RequestConfigDTO requestConfigDTO) {

        Long processId = requestConfigDTO.getProcessId();
        Long requestId = requestConfigDTO.getRequestId();
        String version = requestConfigDTO.getVersion();

        Assert.notNull(requestId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        Request request = this.getById(requestId);
        Assert.notNull(request, ResponseEnum.THE_REQUEST_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        Assert.isTrue(processConfigService.existConfigByProcessIdAndVersion(processId, version),
                ResponseEnum.THE_PROCESS_CONFIGURATION_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        requestMapper.updateProcessIdAndVersionById(requestId, processId, version);
    }

    @Override
    public PageVO<RequestVO> findRequest(RequestFilterDTO requestFilterDTO) {

        PageVO<RequestVO> pageVO = new PageVO<>();

        if (requestFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(requestFilterDTO);
        }

        RequestQuery requestQuery = BeanUtil.copyProperties(requestFilterDTO, RequestQuery.class);

        Set<Long> ids = CollectionUtil.newHashSet();
        if (Validator.isNotNull(requestFilterDTO.getParentId())) {
            List<RequestRelation> requestRelationList = requestRelationService.findRequestRelationByParentId(requestFilterDTO.getParentId());
            if (CollectionUtil.isNotEmpty(requestRelationList)) {
                ids = requestRelationList.stream()
                        .filter(a -> ObjectUtil.notEqual(NumberConstant.ZERO.longValue(), a.getRequestId()))
                        .map(RequestRelation::getRequestId)
                        .collect(Collectors.toSet());
            }

            if (CollectionUtil.isEmpty(ids) || ids.size() <= NumberConstant.ZERO) {
                ids.add(NumberConstant.ZERO.longValue());
            }
            requestQuery.setIds(ids);
        }

        List<Request> requestList = requestMapper.findRequest(requestQuery);

        PageInfo<Request> pageInfo = new PageInfo<>(requestList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(requestList));

        return pageVO;

    }

    @Override
    public RequestVO objectConversion(Request request) {

        RequestVO requestVO = super.objectConversion(request);

        requestVO.setProcess(processService.findOne(request.getProcessId()));
        requestVO.setStatus(processStatusService.findOne(request.getStatusId()));
        List<RequestRelation> requestRelationList = requestRelationService.findRequestRelationByRequestId(request.getId());
        if (CollectionUtil.isNotEmpty(requestRelationList)) {
            Set<Long> ids = requestRelationList.stream()
                    .filter(a -> ObjectUtil.notEqual(NumberConstant.ZERO.longValue(), a.getRequestId()))
                    .map(RequestRelation::getParentId).collect(Collectors.toSet());

            List<Request> requestList = requestMapper.findRequestByIds(ids);
            requestVO.setParent(this.objectConversion(requestList));
        }

        return requestVO;
    }
}