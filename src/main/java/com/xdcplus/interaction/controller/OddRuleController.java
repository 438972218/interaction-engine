package com.xdcplus.interaction.controller;

import com.xdcplus.interaction.common.pojo.vo.ResponseVO;
import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.interaction.common.pojo.dto.OddRuleDTO;
import com.xdcplus.interaction.common.pojo.dto.OddRuleFilterDTO;
import com.xdcplus.interaction.common.pojo.vo.OddRuleVO;
import com.xdcplus.interaction.service.OddRuleService;
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
 * 单号规则 前端控制器
 *
 * @author Rong.Jia
 * @date  2021-05-31
 */
@Slf4j
@RestController
@Validated
@RequestMapping("/oddRule")
@Api(tags = "单号规则管理")
public class OddRuleController extends AbstractController {

    @Autowired
    private OddRuleService oddRuleService;

    @ApiOperation("新增单号规则")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveOddRule(@RequestBody OddRuleDTO oddRuleDTO) {

        log.info("saveOddRule {}", oddRuleDTO.toString());

        oddRuleDTO.setCreatedUser(getAccount());
        oddRuleService.saveOddRule(oddRuleDTO);

        return ResponseVO.success();

    }

    @ApiOperation("修改单号规则")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateOddRule(@RequestBody OddRuleDTO oddRuleDTO) {

        log.info("updateOddRule {}", oddRuleDTO.toString());

        oddRuleDTO.setUpdatedUser(getAccount());
        oddRuleService.updateOddRule(oddRuleDTO);

        return ResponseVO.success();

    }

    @ApiOperation("删除单号规则")
    @DeleteMapping(value = "/{ruleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "ruleId", dataType = "Long", value = "单号规则ID", required = true),
    })
    public ResponseVO deleteOddRule(@PathVariable("ruleId") @NotNull(message = "单号规则ID不能为空") Long ruleId) {

        log.info("deleteOddRule {}", ruleId);

        oddRuleService.deleteOddRule(ruleId);

        return ResponseVO.success();

    }

    @ApiOperation("查询单号规则")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<OddRuleVO>> findOddRule(OddRuleFilterDTO oddRuleFilterDTO) {

        log.info("findOddRule {}", oddRuleFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(oddRuleFilterDTO);

        return ResponseVO.success(oddRuleService.findOddRule(oddRuleFilterDTO));

    }













}
