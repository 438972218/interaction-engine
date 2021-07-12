package com.xdcplus.interaction.common.pojo.query;

import lombok.Data;

import java.io.Serializable;

/**
 * 单号规则查询对象
 * @author Rong.Jia
 * @date 2021/05/31
 */
@Data
public class OddRuleQuery implements Serializable {

    private static final long serialVersionUID = 5022102616622760953L;

    /**
     * 名称
     */
    private String name;

    /**
     * 前缀
     */
    private String prefix;


}
