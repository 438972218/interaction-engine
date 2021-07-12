package com.xdcplus.interaction.controller;


import com.xdcplus.interaction.common.pojo.vo.ResponseVO;
import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.interaction.common.pojo.dto.RequestConfigDTO;
import com.xdcplus.interaction.common.pojo.dto.RequestDTO;
import com.xdcplus.interaction.common.pojo.dto.RequestFilterDTO;
import com.xdcplus.interaction.common.pojo.vo.RequestVO;
import com.xdcplus.interaction.service.RequestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validation;

/**
 * 流程表单 前端控制器
 *
 * @author Rong.Jia
 * @date  2021-05-31
 */
@RestController
@Validated
@Slf4j
@RequestMapping("/request")
@Api(tags = "流程表单模块管理")
public class RequestController extends AbstractController {

    @Autowired
    private RequestService requestService;

    @ApiOperation("查询表单")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<RequestVO>> findRequest(RequestFilterDTO requestFilterDTO) {

        log.info("findRequest {}", requestFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(requestFilterDTO);

        return ResponseVO.success(requestService.findRequest(requestFilterDTO));

    }

    @ApiOperation("新增表单")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveRequest(@RequestBody RequestDTO requestDTO) {

        log.info("saveReques {}", requestDTO.toString());

        requestDTO.setCreatedUser(getAccount());

        return ResponseVO.success(requestService.saveRequest(requestDTO));

    }

    @PatchMapping(value = "/config", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("修改表单流程配置")
    public ResponseVO updateRequestConfig(@RequestBody RequestConfigDTO requestConfigDTO) {

        log.info("updateRequestConfig {}" , requestConfigDTO.toString());

        requestService.updateProcessIdAndVersionById(requestConfigDTO);

        return ResponseVO.success();
    }









}
