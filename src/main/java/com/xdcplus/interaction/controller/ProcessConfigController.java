package com.xdcplus.interaction.controller;


import com.xdcplus.interaction.common.pojo.dto.ProcessConfigFilterDTO;
import com.xdcplus.interaction.common.pojo.vo.ProcessConfigInfoVO;
import com.xdcplus.interaction.common.pojo.dto.ProcessConfigDTO;
import com.xdcplus.interaction.common.pojo.vo.ResponseVO;
import com.xdcplus.interaction.service.ProcessConfigService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validation;
import java.util.List;

/**
 * 流程配置表 前端控制器
 *
 * @author Rong.Jia
 * @date 2021-05-31
 */
@RestController
@Validated
@Slf4j
@Api(tags = "流程配置模块管理")
@RequestMapping("/processConfig")
public class ProcessConfigController {

    @Autowired
    private ProcessConfigService processConfigService;

    @ApiOperation("添加流程配置")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveProcessConfig(@RequestBody @Validated ProcessConfigDTO processConfigDTO) {

        log.info("saveProcessConfig {}", processConfigDTO.toString());

        processConfigService.saveProcessConfig(processConfigDTO);

        return ResponseVO.success();
    }

    @ApiOperation("查询流程配置")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<List<ProcessConfigInfoVO>> findProcessConfig(ProcessConfigFilterDTO processConfigFilterDTO) {

        log.info("findProcessConfig  {} ", processConfigFilterDTO.toString());

        Validation.buildDefaultValidatorFactory().getValidator().validate(processConfigFilterDTO);

        return ResponseVO.success(processConfigService.findProcessConfig(processConfigFilterDTO.getProcessId(),
                processConfigFilterDTO.getVersion()));

    }













}
