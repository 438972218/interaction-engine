package com.xdcplus.interaction.mapper;

import com.xdcplus.interaction.common.pojo.entity.Dictionary;
import com.xdcplus.mp.mapper.IBaseMapper;

import java.util.List;

/**
 *  数据字典持久层接口
 * @date 2019/12/03 14:22:22
 * @author Rong.Jia
 */
public interface DictionaryMapper extends IBaseMapper<Dictionary> {

    /**
     *  根据主键删除 字典
     * @param id 主键id
     * @date 2019/12/05 08:57:22
     * @author Rong.Jia
     * @return 0：失败。1：成功
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  新增字典
     * @param dictionary 字典信息
     * @date 2019/12/05 08:57:22
     * @author Rong.Jia
     * @return 0：失败。1：成功
     */
    @Override
    int insert(Dictionary dictionary);

    /**
     *  根据主键id 查询字典
     * @param id 主键id
     * @date 2019/12/05 08:57:22
     * @author Rong.Jia
     * @return Dictionary 字典信息
     */
    Dictionary selectByPrimaryKey(Long id);

    /**
     *  修改字典信息
     * @param dictionary 字典信息
     * @date 2019/12/05 08:57:22
     * @author Rong.Jia
     * @return 0：失败。1：成功
     */
    int updateByPrimaryKeySelective(Dictionary dictionary);

    /**
     *  修改字典信息
     * @param dictionary 字典信息
     * @date 2019/12/05 08:57:22
     * @author Rong.Jia
     * @return 0：失败。1：成功
     */
    int updateByPrimaryKey(Dictionary dictionary);

    /**
     *  根据数据字典 数值，类别  查询信息
     * @param dictionaryClass 类别
     * @param numerical 数值
     * @author Rong.Jia
     * @date 2019/02/28 15:31:22
     * @return Dictionary
     */
    Dictionary findDictionaryByDictionaryClassAndNumerical(String dictionaryClass, Integer numerical);

    /**
     *  查询所有字典信息
     * @author Rong.Jia
     * @date 2019/02/28 15:31:22
     * @return List<Dictionary> 字典信息集合
     */
    List<Dictionary> findAll();

    /**
     *  根据数据字典 含义，类别  查询信息
     * @param dictionaryClass  类别
     * @param meaning 含义
     * @return Dictionary
     */
    Dictionary findDictionaryByDictionaryClassAndMeaning(String dictionaryClass, String meaning);

    /**
     *  新增字典信息
     * @param dictionaryList 字典信息集合
     * @date 2019/12/05 08:57:22
     * @author Rong.Jia
     * @return 0：失败。>1：成功
     */
    int insertBatch(List<Dictionary> dictionaryList);
}