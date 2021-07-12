package com.xdcplus.interaction.controller;

import com.xdcplus.interaction.common.pojo.vo.DictionaryVO;
import com.xdcplus.interaction.common.pojo.vo.ResponseVO;
import com.xdcplus.interaction.service.DictionaryService;
import com.xdcplus.mp.controller.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 字典管理 Controller, 对接页面
 * @author Rong.Jia
 * @date 2020/04/21 19:50
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/system/")
@Api(value = "字典管理", tags = "字典管理")
public class DictionaryController extends AbstractController {

    @Autowired
    private DictionaryService dictionaryService;

    @ApiOperation("查询数据字典信息")
    @GetMapping(value = "dictionary", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<DictionaryVO> findDictionaries(){

        log.debug("findDictionaries {}", System.currentTimeMillis());

        DictionaryVO dictionaryVO = dictionaryService.findDictionaries();

        return ResponseVO.success(dictionaryVO);

    }


}
