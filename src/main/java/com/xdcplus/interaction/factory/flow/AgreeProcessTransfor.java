package com.xdcplus.interaction.factory.flow;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.*;
import com.xdcplus.interaction.common.enums.ResponseEnum;
import com.xdcplus.interaction.common.exception.InteractionEngineException;
import com.xdcplus.tool.constants.NumberConstant;
import com.xdcplus.interaction.common.pojo.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 同意 流转过程
 * @author Rong.Jia
 * @date 2021/06/07
 */
@Slf4j
@Component
public class AgreeProcessTransfor extends BaseProcessTransfor {

    private static final String REGEX = ".*[a-zA-Z]+.*";

    @Override
    public Boolean supportType(Integer flowOption) {
        return Validator.equal(NumberConstant.ONE, flowOption);
    }

    @Override
    public void postProcess(ProcessTransforParam processTransforParam) {

        log.info("Processing consent operation");

        Long toUserId = processTransforParam.getToUserId();
        Long userId = processTransforParam.getUserId();
        List<Long> roleIds = processTransforParam.getAgree().getRoleIds();
        RequestVO requestVO = processTransforParam.getRequest();

        // 流程当前配置，如果是加签则是空
        List<ProcessConfigVO> currentConfigList = super.findConfigByProcessIdAndFromStatusId(requestVO.getProcess().getId(),
                requestVO.getStatus().getId(), requestVO.getConfigVersion(), userId, requestVO.getCreatedUser());

        // 判断是否是加签
        Boolean extraNode = CollectionUtil.isEmpty(currentConfigList) ? Boolean.TRUE : Boolean.FALSE;

        //  筛选操作流程节点
        RequestFlowVO requestFlowVO = super.getCurrentRequestFlow(requestVO.getId(),
                requestVO.getStatus().getId(), userId, roleIds);

        if (!super.isSignatureWait()) {
            List<RequestFlowVO> requestFlowVOList = super.getBeforeCountersign(requestVO.getId(), requestVO.getStatus().getId());
            if (CollectionUtil.isNotEmpty(requestFlowVOList)) {
                syncIsSigned(requestVO, processTransforParam.getFlowOption(), processTransforParam.getDescription());
                return;
            }
        }

        // 未加签，自己流程不是加签
        if (!extraNode) {

            // 修改当前节点信息
            SyncFlow syncFlow = SyncFlow.builder()
                    .requestId(requestVO.getId())
                    .flowId(requestFlowVO.getId())
                    .flowOptionValue(processTransforParam.getFlowOption())
                    .description(processTransforParam.getDescription())
                    .endTime(DateUtil.current())
                    .toUserId(userId)
                    .build();

            syncFlow(syncFlow);

            // 去向流程的流程配置
            List<ProcessConfigVO> processConfigVOList = getProcessConfigs(currentConfigList, requestFlowVO);

            // 如果大于1，则是会签
            if (processConfigVOList.size() > NumberConstant.ONE) {
                // 添加去向流程的逻辑在下面
            }else {

                ProcessConfigVO processConfigVO = getProcessConfig(processConfigVOList);

                // 验证条件
                Boolean doExpression = doExpression(processConfigVO.getQualifier(), processTransforParam.getAgree().getFlowConditions());

                if (doExpression) {

                    // 如果是非被加签人操作， 需要获取是否有加签
                    syncIsSigned(requestVO, NumberConstant.FOUR, null);

                }else {

                    // 默认流程
                    ProcessConfigVO defaultProcessConfigVO = getDefaultProcessConfig(currentConfigList, requestFlowVO);

                    syncFlow = SyncFlow.builder()
                            .requestId(requestVO.getId())
                            .flowOptionValue(NumberConstant.A_NEGATIVE)
                            .fromStatusId(defaultProcessConfigVO.getFromStatus().getId())
                            .toStatusId(defaultProcessConfigVO.getToStatus().getId())
                            .toRoleId(defaultProcessConfigVO.getToRoleId())
                            .configVersion(requestVO.getConfigVersion())
                            .toUserId(defaultProcessConfigVO.getToUserId())
                            .fromUserId(userId)
                            .build();

                    syncFlow(syncFlow);
                    return;
                }
            }
        }else {

            // 是加签，自己流程是加签

            SyncFlow syncFlow = SyncFlow.builder()
                    .requestId(requestVO.getId())
                    .flowId(requestFlowVO.getId())
                    .flowOptionValue(processTransforParam.getFlowOption())
                    .endTime(DateUtil.current())
                    .description(processTransforParam.getDescription())
                    .toUserId(userId)
                    .build();

            syncFlow(syncFlow);

        }

        // 判断当前流程是否完成流转
        if (!requestFlowService.existRequestFlowByFlowOptionAndRequestId(NumberConstant.A_NEGATIVE, requestVO.getId())) {

            // 当前流程的流转过程全部完成，

            // 如果是加签，需要获取当前节点前的未加签的节点
            List<RequestFlowVO> requestFlowVOList = requestFlowService.findRequestFlowRequestId(requestVO.getId());

            // 当前节点的上一个状态ID
            Long currentFromStatusId = requestFlowVO.getFromStatus().getId();

            // 判断当前流程是否是加签过程中
            if (ObjectUtil.equal(NumberConstant.SIX, requestFlowVO.getToStatus().getMark())) {

                RequestFlowVO lastRequestFlowVO = getLastStatusRequestFlow(requestFlowVOList, requestFlowVO.getFromUserId(),
                        currentFromStatusId, requestFlowVO.getId(), NumberConstant.SIX);

                SyncFlow syncFlow = SyncFlow.builder()
                        .requestId(requestVO.getId())
                        .flowOptionValue(NumberConstant.A_NEGATIVE)
                        .fromStatusId(requestVO.getStatus().getId())
                        .toStatusId(lastRequestFlowVO.getToStatus().getId())
                        .toRoleId(lastRequestFlowVO.getToRoleId())
                        .toUserId(lastRequestFlowVO.getToUserId())
                        .configVersion(requestVO.getConfigVersion())
                        .fromUserId(userId)
                        .build();

                syncFlow(syncFlow);

                super.updateRequestStatusIdById(requestVO.getId(), lastRequestFlowVO.getToStatus().getId());

            }else {

                List<ProcessConfigVO> processConfigVOList = super.findConfigByProcessIdAndFromStatusId(requestFlowVO.getProcess().getId(),
                        requestFlowVO.getToStatus().getId(), requestFlowVO.getConfigVersion(), userId, requestVO.getCreatedUser());

                if (ObjectUtil.equal(NumberConstant.FIVE, requestFlowVO.getToStatus().getMark())) {
                    RequestFlowVO lastRequestFlowVO = getLastStatusRequestFlow(requestFlowVOList, requestFlowVO.getFromUserId(),
                            currentFromStatusId, requestFlowVO.getId(), NumberConstant.FIVE);
                    processConfigVOList = super.findConfigByProcessIdAndFromStatusId(requestFlowVO.getProcess().getId(),
                            lastRequestFlowVO.getToStatus().getId(), lastRequestFlowVO.getConfigVersion(), userId, requestVO.getCreatedUser());
                }

                saveProcessFlow(processConfigVOList, requestVO, requestFlowVO, userId);
            }
        }

    }

    /**
     * 添加流转
     *
     * @param processConfigVOList 过程配置volist
     */
    private void saveProcessFlow(List<ProcessConfigVO> processConfigVOList, RequestVO requestVO,
                                 RequestFlowVO requestFlowVO, Long userId) {

        for (ProcessConfigVO nextProcessConfigVO : processConfigVOList) {

            Integer flowOptionValue = NumberConstant.A_NEGATIVE;
            Long endTime  = null;

            if (ObjectUtil.equal(NumberConstant.TWO, nextProcessConfigVO.getToStatus().getMark())
                    || ObjectUtil.equal(NumberConstant.FOUR, nextProcessConfigVO.getToStatus().getMark()) ) {
                flowOptionValue = NumberConstant.ONE;
                endTime = DateUtil.current();
            }

            SyncFlow syncFlow = SyncFlow.builder()
                    .requestId(requestVO.getId())
                    .flowOptionValue(flowOptionValue)
                    .fromStatusId(ObjectUtil.equal(NumberConstant.FIVE, requestFlowVO.getToStatus().getMark())
                            ? requestFlowVO.getToStatus().getId()
                            : nextProcessConfigVO.getFromStatus().getId())
                    .toStatusId(nextProcessConfigVO.getToStatus().getId())
                    .toRoleId(nextProcessConfigVO.getToRoleId())
                    .configVersion(requestVO.getConfigVersion())
                    .toUserId(nextProcessConfigVO.getToUserId())
                    .fromUserId(userId)
                    .endTime(endTime)
                    .build();

            syncFlow(syncFlow);

            super.updateRequestStatusIdById(requestVO.getId(), nextProcessConfigVO.getToStatus().getId());

        }

    }


    /**
     * 同步被加签操作
     * @param requestVO 请求VO
     */
    private void syncIsSigned(RequestVO requestVO, Integer flowOptionValue, @Nullable  String description) {

        if (!super.isSignatureWait()) {
            List<RequestFlowVO> requestFlowVOList = super.getBeforeCountersign(requestVO.getId(), requestVO.getStatus().getId());
            if (CollectionUtil.isNotEmpty(requestFlowVOList)) {
                for (RequestFlowVO requestFlowVO : requestFlowVOList) {

                    // 修改被加签节点信息
                    SyncFlow syncFlow = SyncFlow.builder()
                            .requestId(requestVO.getId())
                            .flowId(requestFlowVO.getId())
                            .endTime(DateUtil.current())
                            .description(description)
                            .flowOptionValue(flowOptionValue)
                            .build();

                    syncFlow(syncFlow);

                }
            }

        }
    }

    /**
     * 过滤流程配置
     * @param currentConfigList 流程配置集合
     * @param requestFlowVO 流转信息
     * @return {@link List<ProcessConfigVO>} 流程配置
     */
    private List<ProcessConfigVO> getProcessConfigs(List<ProcessConfigVO> currentConfigList,
                                             RequestFlowVO requestFlowVO) {

        List<ProcessConfigVO> processConfigVOList  = currentConfigList.stream()
                .filter(a -> ObjectUtil.equal(a.getProcess().getId(), requestFlowVO.getProcess().getId())
                        && ObjectUtil.equal(a.getFromStatus().getId(), requestFlowVO.getToStatus().getId()))
                .collect(Collectors.toList());

        if (CollectionUtil.isEmpty(processConfigVOList)) {
            log.error("Flow abnormal, The process configuration is not valid");
            throw new InteractionEngineException(ResponseEnum.THE_PROCESS_CONFIGURATION_INFORMATION_IS_NOT_VALID);
        }

        return processConfigVOList;
    }

    /**
     * 过滤流程配置
     * @param currentConfigList 流程配置集合
     * @return {@link ProcessConfigVO} 流程配置
     */
    private ProcessConfigVO getProcessConfig(List<ProcessConfigVO> currentConfigList) {

        ProcessConfigVO processConfigVO = currentConfigList.stream()
                .filter(a -> ObjectUtil.isNotNull(a.getQualifier())).findAny().orElse(null);

        if (ObjectUtil.isNull(processConfigVO)) {
            processConfigVO = currentConfigList.stream()
                    .filter(a -> ObjectUtil.isNull(a.getQualifier())).findAny().orElse(null);
        }

        if (ObjectUtil.isNull(processConfigVO)) {
            log.error("Flow abnormal, The process configuration is not valid");
            throw new InteractionEngineException(ResponseEnum.THE_PROCESS_CONFIGURATION_INFORMATION_IS_NOT_VALID);
        }

        return processConfigVO;
    }

    /**
     * 获取默认流程
     * @param currentConfigList 流程配置集合
     * @param requestFlowVO 流转信息
     * @return {@link ProcessConfigVO} 流程配置
     */
    private ProcessConfigVO getDefaultProcessConfig(List<ProcessConfigVO> currentConfigList,
                                             RequestFlowVO requestFlowVO) {

        ProcessConfigVO processConfigVO = currentConfigList.stream()
                .filter(a -> ObjectUtil.equal(a.getProcess().getId(), requestFlowVO.getProcess().getId())
                        && ObjectUtil.equal(a.getFromStatus().getId(), requestFlowVO.getToStatus().getId())
                        && ObjectUtil.isNull(a.getQualifier()))
                .findAny().orElse(null);

        if (ObjectUtil.isNull(processConfigVO)) {
            log.error("Flow abnormal, The process configuration is not valid");
            throw new InteractionEngineException(ResponseEnum.THE_PROCESS_CONFIGURATION_INFORMATION_IS_NOT_VALID);
        }

        return processConfigVO;
    }

    /**
     * 获取流转条件参数信息
     * @param flowCondition 流转条件参数信息
     * @return {@link Map<String, String>}  key: 字段名， value: 值
     */
    private Map<String, String> getFlowConditions(Object flowCondition) {

        if (ObjectUtil.isNotNull(flowCondition)) {
            Field[] fields = ReflectUtil.getFields(flowCondition.getClass());
            if (ArrayUtil.isNotEmpty(fields)) {
                Map<String, String> flowConditions = CollectionUtil.newHashMap();
                for (Field field : fields) {
                    String fieldName = field.getName();
                    try {
                        flowConditions.put(fieldName, Convert.toStr(ReflectUtil.getFieldValue(flowCondition, field)));
                    }catch (Exception e) {
                        log.error("getFlowConditions {}, {}",
                                String.format("field name  %s Value extraction anomaly", fieldName), e.getMessage());
                    }
                }
                return flowConditions;
            }
        }

        return null;
    }

    /**
     * 执行 条件
     * @param qualifierVO   限定符VO
     * @param flowConditionParam 流程状态参数
     * @return {@link Boolean} true: 通过，false: 失败
     */
    private Boolean doExpression(QualifierVO qualifierVO, Object flowConditionParam) {

        // 获取流转条件
        Map<String, String> flowConditions = getFlowConditions(flowConditionParam);

        // 限定条件, 没有条件则为空
        if (ObjectUtil.isNotNull(qualifierVO) && CollectionUtil.isNotEmpty(flowConditions)) {

            String script = qualifierVO.getScript();
            for (Map.Entry<String, String> entry : flowConditions.entrySet()) {
                script = StrUtil.replace(script, entry.getKey(), entry.getValue());
            }

            ExpressionParser parser = new SpelExpressionParser();
            try {
               return parser.parseExpression(script).getValue(boolean.class);
            }catch (Exception e) {
                log.error("Expression failed to run {}", e.getMessage());
            }
        }

        // 非正常则跳过验证条件
        return Boolean.TRUE;
    }


















}
