package com.xdcplus.interaction.service;

import com.xdcplus.interaction.common.pojo.dto.DictionaryDTO;
import com.xdcplus.interaction.common.pojo.entity.Dictionary;
import com.xdcplus.interaction.common.pojo.vo.DictionaryVO;
import com.xdcplus.mp.service.BaseService;

import java.util.List;

/**
 *  数据字典管理业务逻辑接口
 * @author Rong.Jia
 * @date 2019/02/28 15:31:22
 */
public interface DictionaryService extends BaseService<Dictionary, DictionaryVO, Dictionary> {

    /**
     *  增加数据字典信息
     * @param dictionaryDTO 数据字典信息
     * @author Rong.Jia
     * @date 2019/02/28 15:31:22
     */
    void saveDictionary(DictionaryDTO dictionaryDTO);

    /**
     *  批量增加数据字典信息
     * @param dictionaryList 数据字典信息集合
     * @author Rong.Jia
     * @date 2019/02/28 15:31:22
     */
    void batchSaveDictionary(List<DictionaryDTO> dictionaryList);

    /**
     *  查询所有数据字典
     * @date 2019/02/28 15:46:22
     * @author Rong.Jia
     * @return DictionaryVO
     */
    DictionaryVO findDictionaries();

    /**
     *  根据数据字典 数值，类别  查询信息
     * @param dictionaryClass 类别
     * @param numerical 数值
     * @author Rong.Jia
     * @date 2019/02/28 15:31:22
     * @return Dictionary
     */
    Dictionary findDataDictionaryByDictionaryClassAndNumerical(String dictionaryClass, Integer numerical);

    /**
     *  根据数据字典 含义，类别  查询信息
     * @param dictionaryClass  类别
     * @param meaning 含义
     * @return Dictionary
     */
    Dictionary findDictionaryByDictionaryClassAndMeaning(String dictionaryClass, String meaning);

}
