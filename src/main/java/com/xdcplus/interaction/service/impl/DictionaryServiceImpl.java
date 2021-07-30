package com.xdcplus.interaction.service.impl;

import cn.hutool.core.lang.Assert;
import com.xdcplus.interaction.common.enums.ResponseEnum;
import com.xdcplus.interaction.common.pojo.dto.DictionaryDTO;
import com.xdcplus.interaction.common.pojo.entity.Dictionary;
import com.xdcplus.interaction.common.pojo.vo.DictionaryVO;
import com.xdcplus.interaction.mapper.DictionaryMapper;
import com.xdcplus.interaction.service.DictionaryService;
import com.xdcplus.mp.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *  数据字典管理业务逻辑接口实现类
 * @author Rong.Jia
 * @date 2019/02/28 15:31:22
 */
@Slf4j
@Service
public class DictionaryServiceImpl
        extends BaseServiceImpl<Dictionary, DictionaryVO, Dictionary, DictionaryMapper>
        implements DictionaryService {

    @Autowired
    private DictionaryMapper dictionaryMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveDictionary(DictionaryDTO dictionaryDTO) {

        Dictionary dataDictionary = new Dictionary();
        dataDictionary.setDictionaryChinese(dictionaryDTO.getDictionaryChinese());
        dataDictionary.setDictionaryClass(dictionaryDTO.getDictionaryClass());
        dataDictionary.setMeaning(dictionaryDTO.getMeaning());
        dataDictionary.setNumerical(dictionaryDTO.getNumerical());

        this.save(dataDictionary);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void batchSaveDictionary(List<DictionaryDTO> dictionaryList) {

        List<Dictionary> dataDictionaryList = new ArrayList<>();

        for (DictionaryDTO dataDictionaryDTO : dictionaryList) {

            Dictionary dataDictionary = new Dictionary();
            dataDictionary.setDictionaryChinese(dataDictionaryDTO.getDictionaryChinese());
            dataDictionary.setDictionaryClass(dataDictionaryDTO.getDictionaryClass());
            dataDictionary.setMeaning(dataDictionaryDTO.getMeaning());
            dataDictionary.setNumerical(dataDictionaryDTO.getNumerical());

            dataDictionaryList.add(dataDictionary);

        }

        dictionaryMapper.insertBatch(dataDictionaryList);
    }

    @Override
    public Dictionary findDataDictionaryByDictionaryClassAndNumerical(String dictionaryClass, Integer numerical) {

        Assert.notBlank(dictionaryClass, ResponseEnum.DATA_DICTIONARY_CATEGORY_NOT_NULL.getMessage());
        Assert.notNull(numerical, ResponseEnum.DATA_DICTIONARY_NUMERICAL_NOT_NULL.getMessage());

        return dictionaryMapper.findDictionaryByDictionaryClassAndNumerical(dictionaryClass, numerical);
    }

    @Override
    public DictionaryVO findDictionaries() {

        List<Dictionary> dataDictionaryList = dictionaryMapper.findAll();

        Map<String, List<DictionaryVO>> collect = this.objectConversion(dataDictionaryList)
                .stream().collect(Collectors.groupingBy(DictionaryVO::getDictionaryClass));

        DictionaryVO dataDictionaryVO  = new DictionaryVO();
        dataDictionaryVO.setDataDictionaries(collect);

        return dataDictionaryVO;

    }

    @Override
    public Dictionary findDictionaryByDictionaryClassAndMeaning(String dictionaryClass, String meaning) {

        Assert.notBlank(dictionaryClass, ResponseEnum.DATA_DICTIONARY_CATEGORY_NOT_NULL.getMessage());
        Assert.notBlank(meaning, ResponseEnum.DATA_DICTIONARY_MEANING_NOT_NULL.getMessage());

        return dictionaryMapper.findDictionaryByDictionaryClassAndMeaning(dictionaryClass, meaning);
    }


}
