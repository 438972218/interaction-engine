package com.xdcplus.interaction.factory;

import com.xdcplus.interaction.InteractionEngineApplicationTests;
import com.xdcplus.interaction.factory.oddrule.OddAlgorithmFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 单号算法工厂类测试类
 *
 * @author Rong.Jia
 * @date 2021/07/21
 */
public class OddAlgorithmFactoryTest extends InteractionEngineApplicationTests {

    @Autowired
    private OddAlgorithmFactory algorithmFactory;

    @Test
    void algorithmProcessor() {

        String numbers = algorithmFactory.algorithmProcessor(1L, 1L, "QJ", 1);
        System.out.println(numbers);

        String numbers1 = algorithmFactory.algorithmProcessor(2L, 1L, "BX", 2);
        System.out.println(numbers1);

        String numbers2 = algorithmFactory.algorithmProcessor(3L, 2L, "FJ", 3);
        System.out.println(numbers2);

        String numbers3 = algorithmFactory.algorithmProcessor(4L, 2L, "XJ", 4);
        System.out.println(numbers3);

    }

}
