package com.xdcplus.interaction.common.pojo.query;

import com.xdcplus.interaction.common.pojo.dto.HandleMattersFilterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

/**
 * 表单办理事项 查询对象
 *
 * @author Rong.Jia
 * @date 2021/07/21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HandleMattersQuery extends HandleMattersFilterDTO implements Serializable {

    private static final long serialVersionUID = 5690818242784740744L;

    /**
     *  表单ID
     */
    private Set<Long> requestIds;












}
