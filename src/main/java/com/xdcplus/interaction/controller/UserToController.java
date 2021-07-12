package com.xdcplus.interaction.controller;

import com.xdcplus.interaction.common.pojo.vo.ResponseVO;
import com.xdcplus.interaction.common.pojo.vo.UserToVO;
import com.xdcplus.interaction.service.UserToService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户去向控制器
 *
 * @author Rong.Jia
 * @date 2021/07/01
 */
@RestController
@Validated
@Slf4j
@Api(tags = "用户去向信息模块管理")
@RequestMapping("/userTo")
public class UserToController {

    @Autowired
    private UserToService userToService;

    @ApiOperation("查询用户去向信息")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<List<UserToVO>> findUserTo() {

        log.info("findUserTo {}", System.currentTimeMillis());

        return ResponseVO.success(userToService.findUserTo());

    }








}
