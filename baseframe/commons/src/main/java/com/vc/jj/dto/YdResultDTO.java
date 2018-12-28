package com.vc.jj.dto;


import lombok.Data;

import java.io.Serializable;

/**
 * Project Name: kc-financialservice
 * Package Name: cn.kingcar.financialservice.api.dto
 * Function: 通用返回结果集
 * user: San Date: 2018/2/9
 */
public class YdResultDTO<T> implements Serializable{

    private static final long serialVersionUID = 3462911482601685693L;

    private boolean flag = false;

    private T  data;

    private Object result;

    /**
     * 用于分页返回总数
     */
    private String errorMessage;

    private String statusCode;

    private T  total;

    /**
     * 调用成功，直接返回
     * @param data
     * @param <T>
     * @return
     * @author: Lai
     */
    public static <T> YdResultDTO<T> operSucc(T data){
        YdResultDTO<T> resultDTO = new YdResultDTO<>();
        resultDTO.setFlag(true);
        resultDTO.setData(data);
        return resultDTO;
    }

    /**
     * 调用失败，返回错误信息
     * @param errorMessage
     * @param <T>
     * @return
     */
    public static <T> YdResultDTO<T> operFail(String errorMessage){
        YdResultDTO<T> resultDTO = new YdResultDTO<>();
        resultDTO.setFlag(false);
        resultDTO.setErrorMessage(errorMessage);
        return resultDTO;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public T getTotal() {
        return total;
    }

    public void setTotal(T total) {
        this.total = total;
    }
}
