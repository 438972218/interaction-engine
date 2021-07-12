package com.xdcplus.interaction.controller;

import com.xdcplus.interaction.common.pojo.vo.ResponseVO;
import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.interaction.common.pojo.dto.ProcessTransforDTO;
import com.xdcplus.interaction.common.validator.groupvlidator.ProcessTransforAgreeGroupValidator;
import com.xdcplus.interaction.common.validator.groupvlidator.ProcessTransforCountersignGroupValidator;
import com.xdcplus.interaction.common.validator.groupvlidator.ProcessTransforGroupValidator;
import com.xdcplus.interaction.common.validator.groupvlidator.ProcessTransforSendBackGroupValidator;
import com.xdcplus.interaction.service.RequestFlowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Validation;
import javax.validation.Validator;

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

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Integer flowOption = processTransforDTO.getFlowOption();
        switch (flowOption) {
            case 1:
                validator.validate(processTransforDTO, ProcessTransforAgreeGroupValidator.class);
                break;
            case 2:
                validator.validate(processTransforDTO, ProcessTransforSendBackGroupValidator.class);
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
            case 6:
                validator.validate(processTransforDTO, ProcessTransforCountersignGroupValidator.class);
                break;
            default:
                break;
        }

        requestFlowService.processTransfor(processTransforDTO);

        return ResponseVO.success();
    }













}
