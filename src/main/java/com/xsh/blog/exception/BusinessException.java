package com.xsh.blog.exception;

/**
 * @Author:xieshuang
 * @Description: 自定义异常
 * @Date:Create in 20:55 2018/3/21
 * @Modified By :
 */
public class BusinessException extends RuntimeException {

    public BusinessException(){}

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }
}
