package com.vc.jj.Enum;

/**
 * @author: zm
 * @createDate: 2018/6/27$ 18:03$
 * @updateRemark: 修改内容
 * @description:
 */
public enum ResultEnum {
    // common errorCode
    OK(200, "OK"),

    SUCCESS(200, "SUCCESS"),

    DATA_ERROR(1001, "参数错误"),

    SYS_EORROR(1002,"系统内部错误"),
    // user errorCode
    USERNAMEANDPASSWORD_ISNULL(1010, "账号或密码不正确"),

    NEED_LOGIN(1011, "请重新登录"),

    // user token error
    TOKEN_OK(1020, "token 有效"),

    TOKEN_IS_EXPIRE(1021, "token过期"),

    TOKEN_IS_NOT(1091, "token不存在"),

    TOKEN_ERROR(1021, "token有误"),

    UNAUTHORIZED(1021, "未授权"),

    GRANT_ERROR(1021, "授权失败"),

    REFRESH_TOKEN_EXPIRE(1021, "非法的refreshToken"),

    REFRESH_TOKEN_IS_NULL(1021, "刷新token不能为空"),
    


    // validate code error
    VERIFICATION_CODE_IS_NULL(1031, "验证码不能为空"),

    VERIFICATION_CODE_HAS_EXPIRED(1031, "验证码已失效"),

    VERIFICATION_CODE_ERROR(1031, "验证码错误"),


    // bp error
    REACH_THE_UPPER_LIMIT(1040, "申请达到上限"),

    NAME_ISNULL(1041, "请填写公司名称"),

    PHONE_NUM_ISNULL(1041, "请填写手机号码"),

    USER_DISABLE(1041, "该用户被禁用"),

    TYPE_ISNULL(1042, "请填写类型"),

    // phone error
    ERROR_PHONE_NUM(1050, "非法手机号"),

    SEND_ERROR(1051, "短信发送失败"),

    PHONE_USED(1052, "手机号码已被注册"),

    PHONE_NEVER_USED(1053, "手机号码未注册"),


    USERNAME_ISNULL(400, "请填写员工账号"),

    PASSWORD_ISNULL(400, "请填写登录密码"),

    LOGIN_SUCCESS(200, "登录成功"),

    LOGOUT_SUCCESS(200, "退出成功"),

    ROLE_ADMIN_REMOVE_FAIL(800, "系统角色无法修改"),

    DATA_REMOVE(801, "数据不存在或被删除"),

    DISABLE_DATA(802, "数据被禁用,无法修改"),

    ADMIN_USER_REMOVE_FAIL(804, "系统角色无法修改"),

    ROLE_BIND_USER(1000, "存在用户绑定该角色,无法删除"),

    NOT_USER(804, "用户不存在"),

    FILE_IMPORT_ERROR(805, "文件导入失败,请按照模板要求导入"),

    FILE_SIZE_OUT_OF_RANGE(806, "文件大小超限,不得超过10M"),

    // http 异常
    DATA_TYPE_MISMATCH_ERROR(400, "参数类型不匹配"),

    DATA_MISSING_PARAMETER_ERROR(400, "参数缺失"),

    REQUEST_METHOD_NOT_SUPPORTED(400, "请求方法不正确异常"),

    HTTP_MESSAGE_NOT_READABLE(400, "消息不可读"),
    //漂流瓶模块
    THROW_BOTTLE_NUM(1101,"今天扔瓶子的机会已经用完了。"),
    PICK_BOTTLE_NUM(1102,"今天捡瓶子的机会已经用完了。"),
    PICK_NO_BOTTLE_(1103,"没有捡到瓶子，待会再试试吧。"),
    PICK_REPLY_BOTTLE_(1104,"该漂流瓶回复已到上限。"),
    BOTTLE_INVALID(1105,"该漂流瓶限制已到上限。"),
    
    //订单状态异常信息
    EXIT_OLD_ORDER(2001, "存在未完成订单"),
    NO_ORDER(2002, "不存在该订单"),
    NO_ORDER_TO_PAY(2003, "该订单无法支付"),
    
    //抽奖信息
    NO_PRIZE(2101, "谢谢参与"),

    //文章模块
    EXCLE_IMPORT_URL(1500,"https://ydplatform.oss-cn-hangzhou.aliyuncs.com/article-excel/%E9%A9%BF%E9%81%93%E5%B9%B3%E5%8F%B01.0%E5%AF%BC%E5%85%A5%E6%A8%A1%E6%9D%BF%281%29.xlsx");

    int code;

    String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
