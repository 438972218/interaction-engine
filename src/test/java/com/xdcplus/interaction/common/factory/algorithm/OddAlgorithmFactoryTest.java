package com.xdcplus.interaction.common.factory.algorithm;

import com.xdcplus.interaction.InteractionEngineApplicationTests;
import com.xdcplus.interaction.common.pojo.entity.OddRule;
import com.xdcplus.interaction.factory.oddrule.OddAlgorithmFactory;
import com.xdcplus.interaction.service.OddRuleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class OddAlgorithmFactoryTest extends InteractionEngineApplicationTests {

    @Autowired
    private OddAlgorithmFactory oddAlgorithmFactory;

    @Autowired
    private OddRuleService oddRuleService;

    @Test
    public void algorithmProcessorTest() {

        String processor = oddAlgorithmFactory.algorithmProcessor(null, null, "TD", 1);
        System.out.println(processor);
    }

    @Test
    public void algorithmProcessorTest1() {

        OddRule oddRule = oddRuleService.getById(1);

        String processor = oddAlgorithmFactory.algorithmProcessor(oddRule.getId(), oddRule.getAutoNumber(), oddRule.getPrefix(), oddRule.getAlgorithm());
        System.out.println(processor);
    }

    @Test
    public void algorithmProcessorTest2() {

        OddRule oddRule = oddRuleService.getById(2);

        String processor = oddAlgorithmFactory.algorithmProcessor(oddRule.getId(), oddRule.getAutoNumber(), oddRule.getPrefix(), oddRule.getAlgorithm());
        System.out.println(processor);
    }





}
