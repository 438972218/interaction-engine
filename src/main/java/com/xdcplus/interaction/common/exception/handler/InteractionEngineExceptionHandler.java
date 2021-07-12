package com.xdcplus.interaction.common.exception.handler;

import com.xdcplus.interaction.common.exception.InteractionEngineException;
import com.xdcplus.interaction.common.pojo.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *  异常控制处理器
 * @author Rong.Jia
 * @date 2019/4/3
 */
@Slf4j
@RestControllerAdvice
public class InteractionEngineExceptionHandler {

    /**
     *  捕获自定义异常，并返回异常数据
     * @author Rong.Jia
     * @date 2019/4/3 8:46
     */
    @ExceptionHandler(value = InteractionEngineException.class)
    public ResponseVO interactionEngineExceptionHandler(InteractionEngineException e){

        log.error("interactionEngineExceptionHandler  {}", e.getMessage());

        return ResponseVO.error(e.getCode(), e.getMessage());

    }


}
