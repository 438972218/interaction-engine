package com.xdcplus.interaction.controller;


import com.xdcplus.interaction.common.pojo.vo.ResponseVO;
import com.xdcplus.mp.controller.AbstractController;
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
@Api(tags = "流程状态 模块管理")
@RequestMapping("/processStatus")
public class ProcessStatusController  extends AbstractController {

    @Autowired
    private ProcessStatusService processStatusService;

    @ApiOperation("新增流程状态")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveProcessStatus(@RequestBody ProcessStatusDTO processStatusDTO) {

        log.info("saveProcessStatus {}", processStatusDTO.toString());

        processStatusDTO.setCreatedUser(getAccount());
        processStatusService.saveProcessStatus(processStatusDTO);

        return ResponseVO.success();

    }

    @ApiOperation("修改流程状态")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateProcessStatus(@RequestBody ProcessStatusDTO processStatusDTO) {

        log.info("updateProcessStatus {}", processStatusDTO.toString());

        processStatusDTO.setUpdatedUser(getAccount());
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



















}
