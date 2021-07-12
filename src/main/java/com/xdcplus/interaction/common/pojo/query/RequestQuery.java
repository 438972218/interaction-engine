package com.xdcplus.interaction.common.pojo.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 *  表单 查询对象
 * @author Rong.Jia
 * @date 2021/06/02
 */
@Data
public class RequestQuery implements Serializable {

    private static final long serialVersionUID = 4902882783377028458L;

    /**
     * 表单ID
     */
    private Long id;

    /**
     *  流程ID
     */
    private Long processId;

    /**
     * 标题
     */
    private String title;

    /**
     *  表单ID集合
     */
    private Set<Long> ids;

    /**
     * 单号
     */
    private String oddNumber;


}
