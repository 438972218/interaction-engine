package com.xdcplus.interaction.perm;

import com.xdcplus.interaction.InteractionEngineApplicationTests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseUtilsTest extends InteractionEngineApplicationTests {

    @Test
    void getSysUserByUserIdOrUserName() {

        Long aLong = ResponseUtils.getSysUserByUserIdOrUserName(null, "admin");
        System.out.println(aLong);
    }
}