package com.xdcplus.interaction.common.pojo.query;

import com.xdcplus.interaction.common.pojo.dto.RequestFilterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

/**
 *  表单 查询对象
 * @author Rong.Jia
 * @date 2021/06/02
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RequestQuery extends RequestFilterDTO implements Serializable {

    private static final long serialVersionUID = 4902882783377028458L;

    /**
     *  表单ID集合
     */
    private Set<Long> ids;


}
