package com.xdcplus.interaction.service;

import com.alibaba.fastjson.JSON;
import com.xdcplus.interaction.InteractionEngineApplicationTests;
import com.xdcplus.interaction.common.pojo.vo.UserToVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 用户去向信息业务层接口测试
 *
 * @author Rong.Jia
 * @date 2021/07/01
 */
class UserToServiceTest extends InteractionEngineApplicationTests {

    @Autowired
    private UserToService userToService;

    @Test
    void findUserTo() {

        List<UserToVO> userTo = userToService.findUserTo();

        System.out.println(JSON.toJSONString(userTo));

    }
}