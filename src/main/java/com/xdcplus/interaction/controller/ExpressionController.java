package com.xdcplus.interaction.controller;

import com.xdcplus.interaction.common.pojo.vo.ResponseVO;
import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.interaction.common.pojo.vo.ExpressionVO;
import com.xdcplus.interaction.service.ExpressionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 表达式标识表 前端控制器
 *
 * @author Rong.Jia
 * @date  2021-05-31
 */
@RestController
@Validated
@Slf4j
@Api(tags = "表达式标识管理")
@RequestMapping("/expression")
public class ExpressionController extends AbstractController {

    @Autowired
    private ExpressionService expressionService;

    @ApiOperation("查询表达式列表")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<List<ExpressionVO>> findExpression() {

        log.info("findExpression {}", System.currentTimeMillis());

        return ResponseVO.success(expressionService.findExpression());

    }

    @ApiOperation("查询单个表达式信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "expressionId", dataType = "Long", value = "表达式主键ID", required = true),
    })
    @GetMapping(value = "/{expressionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<ExpressionVO> findExpressionById(@PathVariable("expressionId")
                                                                         @NotNull(message = "表达式主键ID 不能为空")
                                                                         Long expressionId) {

        log.info("findExpressionById {}", expressionId);

        return ResponseVO.success(expressionService.findOne(expressionId));

    }

}
