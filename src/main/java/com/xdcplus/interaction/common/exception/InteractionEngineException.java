package com.xdcplus.interaction.common.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xdcplus.interaction.common.enums.ResponseEnum;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 *  数据格式返回统一
 * @author Rong.Jia
 * @date 2019/4/2
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("返回对象")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InteractionEngineException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 8174656999073214077L;

    /**
     *  异常code 码
     */
    private Integer code;

    /**
     * 异常详细信息
     */
    private String message;

    public InteractionEngineException(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public InteractionEngineException(ResponseEnum responseEnum) {
        super(responseEnum.getMessage());
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
    }

    public InteractionEngineException(ResponseEnum responseEnum, String message) {
        super(message);
        this.code = responseEnum.getCode();
        this.message = message;
    }

    public InteractionEngineException(Integer code, String message, Throwable t) {
        super(message, t);
        this.code = code;
        this.message = message;
    }

    public InteractionEngineException(ResponseEnum responseEnum, Throwable t) {
        super(responseEnum.getMessage(), t);
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
    }

}
