package com.xdcplus.interaction.controller;


import com.xdcplus.interaction.common.pojo.dto.HandleMattersFilterDTO;
import com.xdcplus.interaction.common.pojo.vo.ResponseVO;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.interaction.common.pojo.dto.RequestConfigDTO;
import com.xdcplus.interaction.common.pojo.dto.RequestDTO;
import com.xdcplus.interaction.common.pojo.dto.RequestFilterDTO;
import com.xdcplus.interaction.common.pojo.vo.RequestVO;
import com.xdcplus.interaction.service.RequestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validation;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
public class RequestController {

    @Autowired
    private RequestService requestService;

    @ApiOperation("查询表单信息")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<RequestVO>> findRequest(RequestFilterDTO requestFilterDTO) {

        log.info("findRequest {}", requestFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(requestFilterDTO);

        return ResponseVO.success(requestService.findRequest(requestFilterDTO));

    }

    @ApiOperation("新增表单")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<RequestVO> saveRequest(@RequestBody RequestDTO requestDTO) {

        log.info("saveRequest {}", requestDTO.toString());

        return ResponseVO.success(requestService.saveRequest(requestDTO));

    }

    @PatchMapping(value = "/config", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("修改表单流程配置")
    public ResponseVO updateRequestConfig(@RequestBody RequestConfigDTO requestConfigDTO) {

        log.info("updateRequestConfig {}" , requestConfigDTO.toString());

        requestService.updateProcessIdAndVersionById(requestConfigDTO);

        return ResponseVO.success();
    }

    @GetMapping(value = "/{requestId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("查询单个表单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "requestId", dataType = "Long", value = "表单ID", required = true),
    })
    public ResponseVO<RequestVO> findRequestById(@PathVariable("requestId")
                                                             @NotNull(message = "表单ID不能为空")
                                                             Long requestId) {

        log.info("findRequestById {}", requestId);

        return ResponseVO.success(requestService.findOne(requestId));
    }

    @ApiOperation("验证表单是否存在")
    @GetMapping(value = "/existRequest/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "title", dataType = "String", value = "表单标题", required = true),
    })
    public ResponseVO<Boolean> validationExists(@PathVariable("title")
                                                @NotBlank(message = "表单标题 不能为空")
                                                        String title) {

        log.info("validationExists {}", title);

        return ResponseVO.success(requestService.validationExists(title));

    }

    @GetMapping(value = "/handleMatters", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("表单处理事项")
    public ResponseVO<PageVO<RequestVO>> handleMatters(HandleMattersFilterDTO handleMattersFilterDTO) {

        log.info("handleMatters {}", handleMattersFilterDTO.toString());

        Validation.buildDefaultValidatorFactory().getValidator().validate(handleMattersFilterDTO);

        return ResponseVO.success(requestService.handleMatters(handleMattersFilterDTO));
    }
















}
