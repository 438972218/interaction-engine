package com.xdcplus.interaction.factory.user;

import com.xdcplus.interaction.service.HttpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * 用户去向处理器-公共方法
 * @author Rong.Jia
 * @date 2021/07/19
 */
@Slf4j
public abstract class BaseUserDestinationProcessor implements UserDestinationProcessor {

    @Autowired
    HttpService httpService;














}
