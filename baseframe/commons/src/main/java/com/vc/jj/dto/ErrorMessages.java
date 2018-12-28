package com.vc.jj.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统错误信息
 */
@AllArgsConstructor
public enum ErrorMessages {

    FAILURE("操作失败","FAILURE"),
    INSERT_FAILURE("增加失败","INSERT_FAILURE"),
    UPDATE_FAILURE("更改失败","UPDATE_FAILURE"),
    SELECT_FAILURE("查询失败","SELECT_FAILURE"),
    DELETE_FAILURE("删除失败","DELETE_FAILURE"),
    CHANGE_FAILURE("转换失败","CHANGE_FAILURE"),
    VALIDATE_FAILURE("验证失败","VALIDATE_FAILURE"),
    SELECT_NULL("查询为空","SELECT_NULL"),
    NOT_REPEAT_OPERATE("请不要重复操作","NOT_REPEAT_OPERATE"),
    OPERATE_FAILURE_AND_PLEASE_REFRESH("操作失败,请刷新后重试","OPERATE_FAILURE_AND_REFRESH"),
    PARAM_IS_EMPTY("参数为空","PARAM_IS_EMPTY"),
    PARAM_LOST("参数缺失","PARAM_LOST"),
    RECORD_IS_NOT_EXIST("·token记录不存在","RECORD_IS_NOT_EXIST"),
    CACHE_SAVE_ERROR("缓存保存失败","CACHE_SAVE_ERROR"),
    CACHE_READ_ERROR("缓存读取失败","CACHE_READ_ERROR"),
    CACHE_DELETE_ERROR("缓存删除失败","CACHE_DELETE_ERROR"),

    /**********************用户相关start****************************/
    LOGIN_NAME_OR_PSW_MISTAKE("登录名或密码错误","LOGIN_NAME_OR_PSW_MISTAKE"),
    LOGINNAME_DISABLED("已禁用，请联系管理员","LOGINNAME_DISABLED"),
    LOGIN_USER_LOGIN_ERROR("登录失败","LOGIN_USER_LOGIN_ERROR"),
    LOGIN_USER_CACHE_ERROR("用户缓存设置失败","LOGIN_USER_CACHE_ERROR"),
    LOGIN_USER_LOGOUT_ERROR("登出失败","LOGIN_USER_LOGOUT_ERROR"),
    REGISTER_USER_REGISTER_ERROR("注册失败","REGISTER_USER_REGISTER_ERROR"),
    REGISTER_USER_EXISTS_ERROR("该手机号已被注册，请直接登录","REGISTER_USER_EXISTS_ERROR"),
    USER_NOT_EXISTS_ERROR("用户不存在","USER_NOT_EXISTS_ERROR"),
    REMEMBER_PASSWD_REMEMBER_ERROR("找回密码失败","REMEMBER_PASSWD_REMEMBER_ERROR"),
    VERIFICATION_CODE_FAILURE("验证码失效","VERIFICATION_CODE_FAILURE"),
    VERIFICATION_CODE_IS_EMPTY("验证码为空","VERIFICATION_CODE_IS_EMPTY"),
    VERIFICATION_CODE_ERROR("验证码错误","VERIFICATION_CODE_ERROR"),
    ORIGINAL_PASSWORD_INPUT_ERROR("原始密码输入错误","ORIGINAL_PASSWORD_INPUT_ERROR"),
    NOT_AUTHORIZED("身份验证失败","401"),
    LOGINNAME_NUMBER_EXIST("账号已存在","LOGINNAME_NUMBER_EXIST"),
    PHONE_NO_IS_EMPTY("手机号码不能为空","PHONE_NO_IS_EMPTY"),
    PHONE_NO_FORMAT_ERROR("手机号码格式错误","PHONE_NO_FORMAT_ERROR"),
    PASSWORD_IS_EMPTY("密码不能为空","PASSWORD_IS_EMPTY"),
    CONFIRM_PASSWORD_IS_EMPTY("确认密码不能为空","CONFIRM_PASSWORD_IS_EMPTY"),
    TWO_PASSWORD_IS_DIFF("两次密码不一致","TWO_PASSWORD_IS_DIFF"),
    USER_NOT_EXIST("用户不存在","USER_NOT_EXIST"),
    LOGIN_FAILURE("登录失败","LOGIN_FAILURE"),
    LOGIN_FAILURE_BY_USERNAME_OR_PASS_ERROR("用户名或密码不正确","LOGIN_FAILURE_BY_USERNAME_OR_PASS_ERROR"),
    USER_NAME_IS_EMPTY("用户名不能为空","USER_NAME_IS_EMPTY"),
    USER_PASS_IS_EMPTY("密码不能为空","USER_PASS_IS_EMPTY"),
    USER__IS_LOCK_NOTDELETE("用户已锁定不能被删除","408"),

    /**********************用户相关end****************************/
    ;

    private @Getter
    final String errMessage;

    private @Getter
    final String errCode;


}
