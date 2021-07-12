package com.xdcplus.interaction.controller;


import com.xdcplus.interaction.common.pojo.dto.ProcessConfigTemplateDTO;
import com.xdcplus.interaction.common.pojo.dto.ProcessConfigTemplateFilterDTO;
import com.xdcplus.interaction.common.pojo.dto.ProcessTemplateConfigDTO;
import com.xdcplus.interaction.common.pojo.vo.ProcessConfigTemplateInfoVO;
import com.xdcplus.interaction.common.pojo.vo.ProcessConfigTemplateVO;
import com.xdcplus.interaction.common.pojo.vo.ResponseVO;
import com.xdcplus.interaction.service.ProcessConfigTemplateService;
import com.xdcplus.tool.pojo.vo.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * 流程配置线模板 前端控制器
 *
 * @author Rong.Jia
 * @date  2021-06-22
 */
@Validated
@Slf4j
@Controller
@RequestMapping("/template")
@Api(tags = "流程模板信息和模板配置模块管理")
public class ProcessConfigTemplateController {

    @Autowired
    private ProcessConfigTemplateService processConfigTemplateService;

    @ApiOperation("添加模板信息")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveTemplate(@RequestBody ProcessConfigTemplateDTO configTemplateDTO) {

        log.info("saveTemplate {}", configTemplateDTO.toString());

        processConfigTemplateService.saveTemplate(configTemplateDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改模板信息")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateTemplate(@RequestBody ProcessConfigTemplateDTO configTemplateDTO) {

        log.info("updateTemplate {}", configTemplateDTO.toString());

        processConfigTemplateService.updateTemplate(configTemplateDTO);

        return ResponseVO.success();
    }


    @ApiOperation("删除模板信息")
    @DeleteMapping(value = "/{templateId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "templateId", dataType = "Long", value = "模板信息ID", required = true),
    })
    public ResponseVO deleteTemplate(@PathVariable("templateId")
                                         @NotNull(message = "模板信息ID 不能为空") Long templateId) {

        log.info("deleteTemplate {}", templateId);

        processConfigTemplateService.deleteTemplate(templateId);

        return ResponseVO.success();
    }

    @ApiOperation("查询模板信息")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<ProcessConfigTemplateVO>> findTemplate(ProcessConfigTemplateFilterDTO filterDTO) {

        log.info("findTemplate {}", filterDTO.toString());

        PageVO<ProcessConfigTemplateVO> pageVO = processConfigTemplateService.findTemplate(filterDTO);

        return ResponseVO.success(pageVO);
    }

    @ApiOperation("添加模板配置")
    @PostMapping(value = "/config", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveTemplateConfig(@RequestBody ProcessTemplateConfigDTO configTemplateDTO) {

        log.info("saveTemplateConfig {}", configTemplateDTO.toString());

        processConfigTemplateService.saveTemplateConfig(configTemplateDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改模板配置信息")
    @PutMapping(value = "/config", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateTemplateConfig(@RequestBody ProcessTemplateConfigDTO configTemplateDTO) {

        log.info("updateTemplateConfig {}", configTemplateDTO.toString());

        processConfigTemplateService.updateTemplateConfig(configTemplateDTO);

        return ResponseVO.success();
    }


    @ApiOperation("删除模板配置信息")
    @DeleteMapping(value = "/config/{templateId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "templateId", dataType = "Long", value = "模板信息ID", required = true),
    })
    public ResponseVO deleteTemplateConfig(@PathVariable("templateId")
                                     @NotNull(message = "模板信息ID 不能为空") Long templateId) {

        log.info("deleteTemplateConfig {}", templateId);

        processConfigTemplateService.deleteTemplateConfig(templateId);

        return ResponseVO.success();
    }

    @ApiOperation("查询模板配置信息")
    @GetMapping(value = "/config/{templateId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "templateId", dataType = "Long", value = "模板信息ID", required = true),
    })
    public ResponseVO<ProcessConfigTemplateInfoVO> findTemplateConfig(@PathVariable("templateId")
                                                                              @NotNull(message = "模板信息ID 不能为空") Long templateId) {

        log.info("findTemplateConfig {}", templateId);

        ProcessConfigTemplateInfoVO processConfigTemplateInfoVO = processConfigTemplateService.findTemplateConfig(templateId);

        return ResponseVO.success(processConfigTemplateInfoVO);
    }















}
