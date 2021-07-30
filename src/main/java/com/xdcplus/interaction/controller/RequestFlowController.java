package com.xdcplus.interaction.controller;

import com.xdcplus.interaction.common.pojo.vo.RequestFlowVO;
import com.xdcplus.interaction.common.pojo.vo.ResponseVO;
import com.xdcplus.interaction.common.utils.ProcessValidationUtils;
import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.interaction.common.pojo.dto.ProcessTransforDTO;
import com.xdcplus.interaction.common.validator.groupvlidator.ProcessTransforGroupValidator;
import com.xdcplus.interaction.service.RequestFlowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 流转意见表 前端控制器
 *
 * @author Rong.Jia
 * @date  2021-05-31
 */
@RestController
@Validated
@Slf4j
@Api(tags = "流转模块管理")
@RequestMapping("/requestFlow")
public class RequestFlowController extends AbstractController {

    @Autowired
    private RequestFlowService requestFlowService;

    @ApiOperation("流转操作")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO processTransfor(@RequestBody
                                          @Validated(ProcessTransforGroupValidator.class) ProcessTransforDTO processTransforDTO) {

        log.info("processTransfor {}", processTransforDTO.toString());

        ProcessValidationUtils.validationProcessTransfor(processTransforDTO);

        requestFlowService.processTransfor(processTransforDTO);

        return ResponseVO.success();
    }

    @GetMapping(value = "/{requestId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("根据表单ID查询流转信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "requestId", dataType = "Long", value = "表单ID", required = true),
    })
    public ResponseVO<List<RequestFlowVO>> findRequestFlowByRequestId(@PathVariable("requestId")
                                                 @NotNull(message = "表单ID不能为空")
                                                         Long requestId) {

        log.info("findRequestFlowByRequestId {}", requestId);

        return ResponseVO.success(requestFlowService.findRequestFlowRequestId(requestId));
    }










}
