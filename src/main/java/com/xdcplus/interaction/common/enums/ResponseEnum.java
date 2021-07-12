package com.xdcplus.interaction.common.enums;

/**
 *  数据信息状态枚举类
 * @author Rong.Jia
 * @date 2019/4/2
 */
public enum ResponseEnum {

    /**
     *  枚举类code 开头使用规则：
     *  0: 成功；
     *  -1: 失败；
     *  1：参数不正确
     *  5200：数据字典
     *  5300：组织模块
     *  5400: 单号规则模块
     */

    // 成功
    SUCCESS(0,"成功"),

    // 失败
    ERROR(-1, "失败"),

    // 1000：公共
    THE_ID_CANNOT_BE_EMPTY(1004, "id 不能为空"),
    DATA_QUOTE(1006, "数据被引用，无法执行操作"),

    //  5200：数据字典
    DATA_DICTIONARY_LIST_NULL(5201,"数据字典列表为空"),
    DATA_DICTIONARY_CATEGORY_NOT_NULL(5202,"数据字典的类别不能为空"),
    DATA_DICTIONARY_NUMERICAL_NOT_NULL(5203,"数据字典的数值不能为空"),
    DATA_DICTIONARY_MEANING_NOT_NULL(5204,"数据字典的含义不能为空"),

    // 5400：单号规则模块
    THE_ORDER_NUMBER_RULE_ALREADY_EXISTS(5401, "单号规则已存在"),
    THE_ORDER_NUMBER_RULE_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5402, "单号规则不存在, 或者已删除"),
    THE_ORDER_NUMBER_RULE_CANNOT_BE_MODIFIED(5403, "单号规则不能修改"),

    // 5600：流程相关模块
    THE_PROCESS_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5601, "流程信息不存在, 或者已删除"),
    THE_PROCESS_ALREADY_EXISTS(5602, "流程信息已存在"),
    THE_FLOW_OPTION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5603, "流操作不存在, 或者已删除"),
    THE_FLOW_OPTION_ALREADY_EXISTS(5604, "流操作信息已存在"),
    THE_PROCESS_STATUS_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5605, "流程状态信息不存在, 或者已删除"),
    THE_PROCESS_STATUS_ALREADY_EXISTS(5606, "流程状态信息已存在"),
    THE_QUALIFIER_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5607, "流程规则信息不存在, 或者已删除"),
    THE_QUALIFIER_ALREADY_EXISTS(5608, "流程规则信息已存在"),
    THE_REQUEST_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5609, "表单信息不存在, 或者已删除"),
    THE_REQUEST_ALREADY_EXISTS(5610, "表单信息已存在"),
    FORM_DUPLICATE_ASSOCIATION(5611, "表单重复关联"),
    THE_PROCESS_CONFIGURATION_INFORMATION_IS_EMPTY(5613, "流程配置信息为空"),
    THE_PROCESS_CONFIGURATION_INFORMATION_IS_NOT_VALID(5614, "流程配置信息不合法"),
    THE_PROCESS_CONFIGURATION_NOT_EXIST_OR_HAS_BEEN_DELETED(5616, "流程配置信息不存在, 或者已删除"),
    A_VALID_PROCESS_CONFIGURATION_WAS_NOT_FOUND(5618, "未找到有效的流程配置, 请先添加流程配置"),
    FLOW_ABNORMAL_CURRENT_NODE_CANNOT_BE_FOUND(5619, "流转异常，找不到当前节点"),
    THE_FLOW_INFORMATION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5621, "流转信息不存在, 或者已删除"),
    ABNORMAL_FLOW_SPECIFIED_RETURN_PERSON_DOES_NOT_EXIST(5623, "流转异常，指定退回人不存在"),
    THE_FLOW_HAS_ENDED_AND_CANNOT_BE_CONTINUED(5624, "流转已结束，不可继续"),
    ABNORMAL_FLOW_SIGNATURE_STATUS_DOES_NOT_EXIST(5625, "流转异常，加签状态不存在"),
    A_LIST_CAN_ONLY_BE_CANCELLED_BY_THE_CREATOR(5626, "单子只允许创建人取消"),
    PROCESS_CONFIGURATION_VERSIONS_ARE_DUPLICATED(5627, "流程配置版本重复"),
    THE_PROCESS_CONFIGURATION_TEMPLATE_ALREADY_EXISTS(5628, "流程配置模板已存在"),
    THE_PROCESS_CONFIGURATION_TEMPLATE_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5629, "流程配置模板不存在或已删除"),
    MISSING_START_NODE(5630, "缺少开始节点"),
    MISSING_END_NODE(5630, "缺少结束节点"),

    // 5700： 表达式模块
    THE_EXPRESSION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5701, "表达式不存在, 或者已删除"),
    THE_EXPRESSION_ALREADY_EXISTS(5702, "表达式已存在"),














































    ;

    private Integer code;
    private String message;

    ResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
