package com.xdcplus.interaction.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *   工作流配置类
 * @author Rong.Jia
 * @date 2021/06/11
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "flowable")
public class FlowableConfig {

    /**
     * 设置是否等待前加签同意
     */
    private Boolean signatureWait;


}
