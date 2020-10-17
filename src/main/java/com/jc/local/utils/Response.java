package com.jc.local.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "REST API 响应报文数据结构")
@Data
public class Response<T> implements Serializable {
    @ApiModelProperty(value = "响应码 0:成功 -1:失败")
    private int code;

    @ApiModelProperty(value = "提示消息", allowEmptyValue = true)
    private String message;

    @ApiModelProperty(value = "响应数据", allowEmptyValue = true)
    private T data;

    private Response() {
    }

    private Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Response<T> failure(String message) {
        return new Response<T>(-1, message, null);
    }

    public static <T> Response<T> success(T data) {
        return new Response<T>(0, "success", data);
    }
}
