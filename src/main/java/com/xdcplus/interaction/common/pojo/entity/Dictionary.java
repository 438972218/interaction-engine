package com.xdcplus.interaction.common.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *  数据字典实体类
 * @date 2019/12/04 20:45:22
 * @author Rong.Jia
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dictionary implements Serializable {

    private static final long serialVersionUID = -5984667219090137939L;

    /**
     *  主键
     */
    private Long id;

    /**
     *  字典类别中文含义
     */
    private String dictionaryChinese;

    /**
     *  字典类别
     */
    private String dictionaryClass;

    /**
     * 含义
     */
    private String meaning;

    /**
     * 数值
     */
    private Integer numerical;

}