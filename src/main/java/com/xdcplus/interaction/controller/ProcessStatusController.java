package com.xdcplus.interaction.controller;


import com.xdcplus.interaction.common.pojo.entity.ProcessStatus;
import com.xdcplus.interaction.common.pojo.vo.ProcessVO;
import com.xdcplus.interaction.common.pojo.vo.ResponseVO;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.interaction.common.pojo.dto.ProcessStatusDTO;
import com.xdcplus.interaction.common.pojo.dto.ProcessStatusFilterDTO;
import com.xdcplus.interaction.common.pojo.vo.ProcessStatusVO;
import com.xdcplus.interaction.service.ProcessStatusService;
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
 * 流程状态表 前端控制器
 *
 * @author Rong.Jia
 * @date  2021-05-31
 */
@RestController
@Validated
@Slf4j
@Api(tags = "流程状态模块管理")
@RequestMapping("/processStatus")
public class ProcessStatusController {

    @Autowired
    private ProcessStatusService processStatusService;

    @ApiOperation("新增流程状态")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveProcessStatus(@RequestBody ProcessStatusDTO processStatusDTO) {

        log.info("saveProcessStatus {}", processStatusDTO.toString());

        processStatusService.saveProcessStatus(processStatusDTO);

        return ResponseVO.success();

    }

    @ApiOperation("修改流程状态")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateProcessStatus(@RequestBody ProcessStatusDTO processStatusDTO) {

        log.info("updateProcessStatus {}", processStatusDTO.toString());

        processStatusService.updateProcessStatus(processStatusDTO);

        return ResponseVO.success();

    }

    @ApiOperation("删除流程状态")
    @DeleteMapping(value = "/{processStatusId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "processStatusId", dataType = "Long", value = "流程状态ID", required = true),
    })
    public ResponseVO deleteProcessStatus(@PathVariable("processStatusId")
                                         @NotNull(message = "流程状态ID不能为空") Long processStatusId) {

        log.info("deleteProcessStatus {}", processStatusId);

        processStatusService.deleteProcessStatus(processStatusId);

        return ResponseVO.success();

    }

    @ApiOperation("查询流程状态")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<ProcessStatusVO>> findProcessStatus(ProcessStatusFilterDTO processStatusFilterDTO) {

        log.info("findProcessStatus {}", processStatusFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(processStatusFilterDTO);

        return ResponseVO.success(processStatusService.findProcessStatus(processStatusFilterDTO));

    }

    @ApiOperation("查询单个流程状态信息")
    @GetMapping(value = "/{processStatusId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "processStatusId", dataType = "Long", value = "流程状态信息ID", required = true),
    })
    public ResponseVO<ProcessStatusVO> findProcessStatusById(@PathVariable("processStatusId")
                                                 @NotNull(message = "流程状态信息ID 不能为空")
                                                         Long processStatusId) {

        log.info("findProcessStatusById {}", processStatusId);

        return ResponseVO.success(processStatusService.findOne(processStatusId));

    }

    @ApiOperation("验证流程状态信息是否存在")
    @GetMapping(value = "/existProcessStatus/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "name", dataType = "String", value = "流程状态信息名称", required = true),
    })
    public ResponseVO<Boolean> validationExists(@PathVariable("name")
                                                @NotBlank(message = "流程状态信息名称 不能为空")
                                                        String name) {

        log.info("validationExists {}", name);

        return ResponseVO.success(processStatusService.validationExists(name));

    }

















}
