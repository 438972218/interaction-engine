package com.xdcplus.interaction.service;

import com.xdcplus.interaction.InteractionEngineApplicationTests;
import com.xdcplus.interaction.common.pojo.dto.QualifierDTO;
import com.xdcplus.interaction.common.pojo.dto.QualifierFilterDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 限定符业务层接口测试
 *
 * @author Rong.Jia
 * @date 2021/06/02
 */
class QualifierServiceTest extends InteractionEngineApplicationTests {

    @Autowired
    private QualifierService qualifierService;

    @Test
    void saveQualifier() {

        QualifierDTO qualifierDTO = new QualifierDTO();
        qualifierDTO.setName("金额条件");
        qualifierDTO.setScript("age > 10000");
        System.out.println(qualifierService.saveQualifier(qualifierDTO));

    }

    @Test
    void updateQualifier() {
        QualifierDTO qualifierDTO = new QualifierDTO();
        qualifierDTO.setName("测试12");
        qualifierDTO.setScript("2321321");
        qualifierDTO.setId(1399902338051395586L);
        System.out.println(qualifierService.updateQualifier(qualifierDTO));
    }

    @Test
    void deleteQualifier() {
        System.out.println(qualifierService.deleteQualifier(1399902338051395586L));
    }

    @Test
    void findQualifier() {

        QualifierFilterDTO qualifierFilterDTO = new QualifierFilterDTO();
        qualifierFilterDTO.setCurrentPage(1);
        qualifierFilterDTO.setPageSize(20);

        System.out.println(qualifierService.findQualifier(qualifierFilterDTO));


    }
}