package com.xdcplus.interaction.controller;


import com.xdcplus.interaction.common.pojo.dto.ProcessConfigFilterDTO;
import com.xdcplus.interaction.common.pojo.dto.ProcessConfigInfoFilterDTO;
import com.xdcplus.interaction.common.pojo.vo.ProcessConfigInfoVO;
import com.xdcplus.interaction.common.pojo.dto.ProcessConfigDTO;
import com.xdcplus.interaction.common.pojo.vo.ProcessConfigVO;
import com.xdcplus.interaction.common.pojo.vo.ResponseVO;
import com.xdcplus.interaction.service.ProcessConfigService;
import com.xdcplus.tool.pojo.dto.PageDTO;
import com.xdcplus.tool.pojo.vo.PageVO;
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

    @ApiOperation("查询流程配置信息(配置模式)")
    @GetMapping(value = "/chart", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<List<ProcessConfigInfoVO>> findProcessConfig(ProcessConfigInfoFilterDTO processConfigInfoFilterDTO) {

        log.info("findProcessConfig  {} ", processConfigInfoFilterDTO.toString());

        Validation.buildDefaultValidatorFactory().getValidator().validate(processConfigInfoFilterDTO);

        return ResponseVO.success(processConfigService.findProcessConfig(processConfigInfoFilterDTO.getProcessId(),
                processConfigInfoFilterDTO.getVersion()));

    }

    @ApiOperation("查询流程配置信息")
    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<ProcessConfigVO>> findProcessConfigFilter(ProcessConfigFilterDTO processConfigFilterDTO) {

        log.info("findProcessConfig {}", processConfigFilterDTO.toString());

        return ResponseVO.success(processConfigService.findProcessConfig(processConfigFilterDTO));

    }











}
