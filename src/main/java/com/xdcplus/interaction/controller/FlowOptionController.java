package com.xdcplus.interaction.controller;


import com.xdcplus.interaction.common.pojo.vo.ResponseVO;
import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.interaction.common.pojo.vo.FlowOptionVO;
import com.xdcplus.interaction.service.FlowOptionService;
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
 * 流程操作前端控制器
 *
 * @author Rong.Jia
 * @date  2021-05-31
 */
@Api(tags = "流程操作模块管理")
@RestController
@Validated
@Slf4j
@RequestMapping("/flowOption")
public class FlowOptionController extends AbstractController {

    @Autowired
    private FlowOptionService flowOptionService;

    @ApiOperation("查询流程操作")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<List<FlowOptionVO>> findFlowOption() {

        log.info("findFlowOption {}", System.currentTimeMillis());

        return ResponseVO.success(flowOptionService.findFlowOption());

    }





}
