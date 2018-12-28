package com.vc.jj.consts;

/**
 * @author: zm
 * @createDate: 2018/7/23$ 19:04$
 * @updateRemark: 修改内容
 * @description: aliyun 短信常量
 */
public class AliyunSmsConstants {


    //产品名称:云通信短信API产品,开发者无需替换
    public static final String PRODUCT = "Dysmsapi";
    //产品域名,开发者无需替换
    public static final String DOMAIN = "dysmsapi.aliyuncs.com";

    /**
     * 非法手机号返回码
     */
    public static final String ERRORPHONE_CODE = "isv.MOBILE_NUMBER_ILLEGAL";

    public static final String ACCESSKEY_ID = "LTAIjGk7cDhTL4bI";

    public static final String ACCESSKEY_SECRET = "JY78oTRC6hv5W9B8JJw7WBDugTcbLE";

    /**
     * 短信验证默认模板id
     */
    public static final String VALIDATE_TEMPLATE_CODE = "SMS_140005314";

    /**
     * 短信绑定手机模板id
     */
    public static final String BIND_PHONE_VALIDATE_TEMPLATE_CODE = "SMS_143719688";

    /**
     * 短信登录模板id
     */
    public static final String LOGIN_VALIDATE_TEMPLATE_CODE = "SMS_143714767";

    /**
     * 短信注册模板id
     */
    public static final String REGISTERED_VALIDATE_TEMPLATE_CODE = "SMS_143719690";

    /**
     * TODO 找回密码
     */
    public static final String PASSWORD_VALIDATE_TEMPLATE_CODE = "SMS_143719690";

    /**
     * 业务停机
     */
    public static final String OUT_OF_SERVICE_CODE = "isv.OUT_OF_SERVICE";

    /**
     * 登录
     */
    public static final int LOGIN_TYPE = 0;

    /**
     * 登录
     */
    public static final int BIND_PHONE_TYPE = 1;

    /**
     * 注册
     */
    public static final int REGISTERED_TYPE = 2;

    /**
     * 找回验证码
     */
    public static final int PASSWORD_TYPE = 3;
}
