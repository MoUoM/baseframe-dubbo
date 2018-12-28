package com.vc.jj.consts;

/**
 * @author: zm
 * @createDate: 2018/7/23$ 19:56$
 * @updateRemark: 修改内容
 * @description:
 */
public class SysCacheConstant {

    public static final String USER_PHONE_PREFIX = "user_phone#";

    public static final String PHONE_VALIDATE_CODE_FIELD = "code#";

    public static final String PHONE_SEND_COUNT = "count#";

    public static final String LAST_SEND_TIME = "last_send_time#";

    public static final String REFRESH_TOKEN_KEY = "refresh_token_#";

    public static final String TOKEN_KEY = "token_#";

    public static final String USER_ID_KEY = "user_id#";

    public static final String USER_INFO_KEY = "user_info#";

    /**
     * token过期时间
     */
    public static final int ACCESS_TOKEN_EXPIRE_TIME = 2 * 60 * 60;

    /**
     * refreshtoken过期时间
     */
    public static final int REFRESH_EXPIRE_TIME = 15 * 24 * 60 * 60;

    /**
     * 验证码过期时间
     */
    public static final int VALIDATE_CODE_TIME = 5 * 60;

    /**
     * 发送验证码次数清空时间
     */
    public static final int SEND_COUNT_TIME = 24 * 60 * 60;

    public static final int SEND_MAX_COUNT = 100;

    public static final int SEND_INTERVAL_TIME = 60;
}
