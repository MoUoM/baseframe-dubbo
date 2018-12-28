package com.vc.jj.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum StatusCodeEmuns {
    STATUS_200("200","成功"),
    STATUS_500("400","业务数据不正常"),
    STATUS_403("403","参数异常"),
    STATUS_405("405","无权操作"),
    ERR_STATUS("1","非正常状态"),
    ;
    private @Getter
    String statusCode  ;

    private @Getter
    final String statusMessage;
}
