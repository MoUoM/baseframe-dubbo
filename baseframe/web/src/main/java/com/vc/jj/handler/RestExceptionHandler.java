package com.vc.jj.handler;

import com.mysql.jdbc.log.LogUtils;
import com.vc.jj.Enum.ResultEnum;
import com.vc.jj.dto.ResultBean;
import com.vc.jj.dto.YdResultDTO;
import com.vc.jj.util.LogUtil;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author :         XLY
 * @createDate :     2018/8/25 13:40
 * @description :
 */
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(TypeMismatchException.class)
    public ResultBean typeMismatchException(TypeMismatchException ex) {
        String classAndMethod = LogUtils.findCallingClassAndMethod(ex);
        LogUtil.error(classAndMethod, ex.getMessage());
        return createResult(String.valueOf(ResultEnum.DATA_TYPE_MISMATCH_ERROR.getCode()),
                ResultEnum.DATA_TYPE_MISMATCH_ERROR.getMsg());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultBean httpMessageNotReadableException(HttpMessageNotReadableException ex) {
        String classAndMethod = LogUtils.findCallingClassAndMethod(ex);
        LogUtil.error(classAndMethod, ex.getMessage());
        return createResult(String.valueOf(ResultEnum.HTTP_MESSAGE_NOT_READABLE.getCode()),
                ResultEnum.HTTP_MESSAGE_NOT_READABLE.getMsg());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResultBean missingServletRequestParameterException(
            MissingServletRequestParameterException ex) {
        String classAndMethod = LogUtils.findCallingClassAndMethod(ex);
        LogUtil.error(classAndMethod, ex.getMessage());
        return createResult(String.valueOf(ResultEnum.DATA_MISSING_PARAMETER_ERROR.getCode()),
                ResultEnum.DATA_MISSING_PARAMETER_ERROR.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public ResultBean exception(MissingServletRequestParameterException ex) {
        String classAndMethod = LogUtils.findCallingClassAndMethod(ex);
        LogUtil.error(classAndMethod, ex.getMessage());
        return createResult(String.valueOf(ResultEnum.SYS_EORROR.getCode()),
                ResultEnum.SYS_EORROR.getMsg());
    }

    private ResultBean createResult(String errorCode, String errorMsg) {

        YdResultDTO ydResultDTO = new YdResultDTO();
        ydResultDTO.setFlag(false);
        ydResultDTO.setStatusCode(errorCode);
        ydResultDTO.setErrorMessage(errorMsg);
        System.out.println(ydResultDTO.getErrorMessage());
        return ResultBean.build4ResultDTO(ydResultDTO);
    }

}
