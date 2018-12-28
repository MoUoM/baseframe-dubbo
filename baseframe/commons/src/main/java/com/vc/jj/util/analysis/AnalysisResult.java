package com.vc.jj.util.analysis;

import lombok.Data;

import java.util.List;


/**
 *
 * User: wangpeng
 * Date: 2018-08-06
 */

@Data
public class AnalysisResult<T> {

    private Boolean isSuccess = false;
    private String errorInfo; //错误信息
    private String errorValue; //错误值
    List<T> list; //转换成功后的集合

}
