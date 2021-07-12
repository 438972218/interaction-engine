package com.xdcplus.interaction;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 项目启动类
 * @author Rong.Jia
 * @date 2021/05/31 10:26
 */
@EnableTransactionManagement
@MapperScan("com.xdcplus.interaction.mapper")
@EnableAsync
@EnableCaching
@EnableScheduling
@SpringBootApplication
public class InteractionEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(InteractionEngineApplication.class, args);
    }

}
