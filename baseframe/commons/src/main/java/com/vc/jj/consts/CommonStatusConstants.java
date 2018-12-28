package com.vc.jj.consts;

/**
 * @author: zm
 * @createDate: 2018/7/24$ 14:24$
 * @updateRemark: 修改内容
 * @description:
 */
public class CommonStatusConstants {

    public static final long DISABLE_STATUS = 0;
    public static final long DEFAULT_ENABLE_STATUS = 1;

    public static final long UN_DELETED_STATUS = 0;

    public static final long IS_DELETED = 1;

    public static final int CLICK_COUNT = 0;

    public static final int DOWNLOAD_COUNT = 1;

    public static final int REGISTERED_COUNT = 2;


    //短信发送类型
    /**
     * 注册
     */
    public static final long SMS_REGISTER_TYPE = 1;

    /**
     * 找回密码
     */
    public static final long SMS_RECOVER_PASSWORD_TYPE = 2;

    /**
     * 验证码
     */
    public static final long SMS_IDENTIFY_TYPE = 3;

    //BP申请状态

    /**
     * 审核中
     */
    public static final long BP_DEFAULT_APPLY_STATUS = 1;

    /**
     * 审核成功
     */
    public static final long BP_APPLY_SUCCESS_STATUS = 2;


    /**
     * 审核未通过
     */
    public static final long BP_APPLY_FAIL_STATUS = 0;

    /**
     * 取消申请
     */
    public static final long BP_APPLY_CANLE_STATUS = -1;

    public static final String BP_APPLY_AGREE_STATUS = "Y";
}
