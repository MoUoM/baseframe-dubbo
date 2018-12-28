package com.vc.jj.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;


/**
 *
 *
 * @Description:
 * @author: wang
 * @date 2018/7/14
 */
@Data
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ResultBean<T> {
    private boolean status = true;
    private String errCode;
    private String info;
    private T result;

    private ResultBean() {
    }

    public static <T> ResultBean build(String errCode, String info, T result){
        ResultBean<T> resultBean = new ResultBean<T>();
        resultBean.setErrCode(errCode);
        resultBean.setInfo(info);
        resultBean.setResult(result);
        return resultBean;
    }

    public static <T> ResultBean build4ResultDTO(YdResultDTO<T> resultDTO){
        ResultBean<T> resultBean = new ResultBean<T>();
        if(!resultDTO.isFlag())
            resultBean.setStatus(false);
        else
            resultBean.setStatus(true);

        if(null != resultDTO.getStatusCode())
            resultBean.setErrCode(resultDTO.getStatusCode());

        if(null != resultDTO.getErrorMessage())
            resultBean.setInfo(resultDTO.getErrorMessage());

        if(null != resultDTO.getData())
            resultBean.setResult(resultDTO.getData());

        return resultBean;
    }

   /* public static ResultBean build(OrderModuleMessageEnum orderModuleMessageEnum){
        ResultBean resultBean = new ResultBean();
        resultBean.setSuccess(false);
        resultBean.setErrCode(orderModuleMessageEnum.getCode());
        resultBean.setResult(null);
        resultBean.setInfo(orderModuleMessageEnum.getMsg());
        return resultBean;
    }

    public static ResultBean build(CommonMessageEnum commonMessageEnum){
        return build( commonMessageEnum,false);
    }
    public static ResultBean build(CommonMessageEnum commonMessageEnum,Boolean success){
        ResultBean resultBean = new ResultBean();
        resultBean.setSuccess(success);
        resultBean.setResult(null);
        resultBean.setInfo(commonMessageEnum.getMsg());
        resultBean.setErrCode(commonMessageEnum.getCode());
        return resultBean;
    }


    public static ResultBean build(RpModuleMessageEnum rpModuleMessageEnum) {
        ResultBean resultBean = new ResultBean();
        resultBean.setSuccess(false);
        resultBean.setInfo(rpModuleMessageEnum.getMsg());
        resultBean.setErrCode(rpModuleMessageEnum.getCode());
        resultBean.setResult(null);
        return resultBean;
    }*/

    public static ResultBean build(ErrorMessages errorMessages) {
        ResultBean resultBean = new ResultBean();
        resultBean.setStatus(false);
        resultBean.setInfo(errorMessages.getErrMessage());
        resultBean.setErrCode(errorMessages.getErrCode());
        resultBean.setResult(null);
        return resultBean;
    }

    public static <T> ResultBean buildResultBean(boolean status,String errorCode,
    		String errorMassage,T result){
        ResultBean<T> resultBean = new ResultBean<T>();
        resultBean.setStatus(status);
        resultBean.setErrCode(errorCode);
        resultBean.setInfo(errorMassage);
        resultBean.setResult(result);
        return resultBean;
    }
    
    public static <T> ResultBean buildResultBean(String errorCode,T result){
        ResultBean<T> resultBean = new ResultBean<T>();
        resultBean.setErrCode(errorCode);
        resultBean.setInfo("success");
        resultBean.setResult(result);
        return resultBean;
    }
  /*  public static ResultBean build(OverDueModuleMessageEnum overDueModuleMessageEnum) {
        ResultBean resultBean = new ResultBean();
        resultBean.setSuccess(false);
        resultBean.setInfo(overDueModuleMessageEnum.getDesc());
        resultBean.setErrCode(overDueModuleMessageEnum.getCode());
        resultBean.setResult(null);
        return resultBean;
    }*/

   /* public static ResultBean build(PmsModuleMessageEnum userNameIsEmpty) {
        ResultBean resultBean = new ResultBean();
        resultBean.setSuccess(false);
        resultBean.setInfo(userNameIsEmpty.getMsg());
        resultBean.setErrCode(userNameIsEmpty.getCode());
        resultBean.setResult(null);
        return resultBean;
    }
*/
    public static ResultBean buildError(String errCode, String info){
        ResultBean resultBean = new ResultBean();
        resultBean.setStatus(false);
        resultBean.setErrCode(errCode);
        resultBean.setResult(null);
        resultBean.setInfo(info);
        return resultBean;
    }
  /*  public static ResultBean build(ErrorMsg errorMsg){
        ResultBean resultBean = new ResultBean();
        resultBean.setSuccess(false);
        resultBean.setErrCode(errorMsg.getErrCode());
        resultBean.setResult(null);
        resultBean.setInfo(errorMsg.getErrMessage());
        return resultBean;
    }*/

    public static <T> ResultBean querySucc(T data){
        if(null == data){
            return ResultBean.build("0","暂无数据",data);
        }
        return ResultBean.build("1","查询成功",data);
    }

    public static <T> ResultBean operSucc(T data){
        return ResultBean.build("1000","操作成功",data);
    }

    public static ResultBean operFail(){
        return ResultBean.build("405","操作失败",null);
    }

    public static ResultBean operFail(String code,String errorMessage){
        return ResultBean.build(code,errorMessage,null);
    }
    /**
     * 服务器内部错误
     * @param msg
     * @return
     */
    public static ResultBean exception(String msg){
        return ResultBean.build("500",msg,null);
    }

}
