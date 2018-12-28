package com.vc.jj.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author zm
 * @create 2018-06-19 9:22
 * @description  统一异常处理器
 **/
//@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String CONTENT_TYPE = "Content-Type";

    private static final String CONTENT_TYPE_VALUE = "application/json; charset=UTF-8";


    /**
     * @param ex      参数缺失的异常
     * @param headers 请求头
     * @param status  Http状态
     * @param request Http请求
     * @return 异常产生时的自定义返回
     */
    @Override
    protected ResponseEntity handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String parameterName = ex.getParameterName();
        String parameterType = ex.getParameterType();
        String message = String.format("参数 %s(%s) 传入有误", parameterName, parameterType);
        return ResponseEntity.ok(message);
    }


    /**
     * @param ex      请求方法不正确异常
     * @param headers 请求头
     * @param status  Http状态码
     * @param request Http请求
     * @return 异常时候自定义提示
     */
    @Override
    protected ResponseEntity handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex
            , HttpHeaders headers
            , HttpStatus status
            , WebRequest request) {
        StringBuilder sb = new StringBuilder();

        for (HttpMethod method : ex.getSupportedHttpMethods()) {
            sb.append(method.name());
            sb.append(" ");
        }
        String message = String.format("仅支持%s类型的请求！", sb.toString());
        return ResponseEntity.ok(message);
    }

    /**
     * @param ex      参数校验失败的异常
     * @param headers 请求头
     * @param status  Http状态码
     * @param request Http请求
     * @return 异常时候自定义提示
     */
    @Override
    protected ResponseEntity handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex
            , HttpHeaders headers
            , HttpStatus status
            , WebRequest request) {
        String errorMsg = "请求参数格式校验失败，请重新填写";
        return ResponseEntity.ok(errorMsg);
    }

    /**
     * @param ex      参数转换异常
     * @param headers 请求头
     * @param status  Http状态码
     * @param request Http请求
     * @return 异常时候自定义提示
     */
    @Override
    public ResponseEntity handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex
            , HttpHeaders headers
            , HttpStatus status
            , WebRequest request) {
        String errorMsg = "请求参数格式不正确";
        headers.add(CONTENT_TYPE, CONTENT_TYPE_VALUE);
        return new ResponseEntity(errorMsg, headers, status);
    }


    /**
     * @param ex  异常信息
     * @param req 请求信息
     * @return 其他服务器异常的处理
     */
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity handleOtherExceptions(final Exception ex, final WebRequest req) {
        return ResponseEntity.ok("server error");
    }
}
