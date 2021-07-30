package com.xdcplus.interaction.controller;


import com.xdcplus.interaction.common.pojo.vo.ResponseVO;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.interaction.common.pojo.dto.ProcessDTO;
import com.xdcplus.interaction.common.pojo.dto.ProcessFilterDTO;
import com.xdcplus.interaction.common.pojo.vo.ProcessVO;
import com.xdcplus.interaction.service.ProcessService;
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
 * 流程表 前端控制器
 *
 * @author Rong.Jia
 * @date  2021-05-31
 */
@Api(tags = "流程信息模块管理")
@RestController
@Validated
@Slf4j
@RequestMapping("/process")
public class ProcessController {

    @Autowired
    private ProcessService processService;

    @ApiOperation("新增流程")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveProcess(@RequestBody ProcessDTO processDTO) {

        log.info("saveProcess {}", processDTO.toString());

        processService.saveProcess(processDTO);

        return ResponseVO.success();

    }

    @ApiOperation("修改流程")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateProcess(@RequestBody ProcessDTO processDTO) {

        log.info("updateProcess {}", processDTO.toString());

        processService.updateProcess(processDTO);

        return ResponseVO.success();

    }

    @ApiOperation("删除流程")
    @DeleteMapping(value = "/{processId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "processId", dataType = "Long", value = "流程ID", required = true),
    })
    public ResponseVO deleteProcess(@PathVariable("processId")
                                          @NotNull(message = "流程ID不能为空") Long processId) {

        log.info("deleteProcess {}", processId);

        processService.deleteProcess(processId);

        return ResponseVO.success();

    }

    @ApiOperation("查询流程信息")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<ProcessVO>> findProcess(ProcessFilterDTO processFilterDTO) {

        log.info("findProcess {}", processFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(processFilterDTO);

        return ResponseVO.success(processService.findProcess(processFilterDTO));

    }

    @ApiOperation("查询单个流程信息")
    @GetMapping(value = "/{processId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "processId", dataType = "Long", value = "流程信息ID", required = true),
    })
    public ResponseVO<ProcessVO> findProcessById(@PathVariable("processId")
                                                             @NotNull(message = "流程信息ID 不能为空")
                                                                     Long processId) {

        log.info("findProcessById {}", processId);

        return ResponseVO.success(processService.findOne(processId));

    }

    @ApiOperation("验证流程信息是否存在")
    @GetMapping(value = "/existProcess/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "name", dataType = "String", value = "流程信息名称", required = true),
    })
    public ResponseVO<Boolean> validationExists(@PathVariable("name")
                                                    @NotBlank(message = "流程信息名称 不能为空")
                                                            String name) {

        log.info("validationExists {}", name);

        return ResponseVO.success(processService.validationExists(name));

    }


















}
