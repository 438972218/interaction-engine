package com.xdcplus.interaction.controller;


import com.xdcplus.interaction.common.pojo.vo.ResponseVO;
import com.xdcplus.mp.controller.AbstractController;
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
public class ProcessController extends AbstractController {

    @Autowired
    private ProcessService processService;

    @ApiOperation("新增流程")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveProcess(@RequestBody ProcessDTO processDTO) {

        log.info("saveProcess {}", processDTO.toString());

        processDTO.setCreatedUser(getAccount());
        processService.saveProcess(processDTO);

        return ResponseVO.success();

    }

    @ApiOperation("修改流程")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateProcess(@RequestBody ProcessDTO processDTO) {

        log.info("updateProcess {}", processDTO.toString());

        processDTO.setUpdatedUser(getAccount());
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

    @ApiOperation("查询流程")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<ProcessVO>> findProcess(ProcessFilterDTO processFilterDTO) {

        log.info("findProcess {}", processFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(processFilterDTO);

        return ResponseVO.success(processService.findProcess(processFilterDTO));

    }






















}
