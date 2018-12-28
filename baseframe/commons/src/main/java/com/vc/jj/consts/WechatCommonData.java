package com.vc.jj.consts;


import java.io.IOException;
import java.util.Properties;

/**
 * @author: zm
 * @createDate: 2018/7/23$ 15:48$
 * @updateRemark: 修改内容
 * @description:
 */

public class WechatCommonData {

    public static final String APP_ID;
    public static final String SECRET;
    public static final String WECHAT_ACCESS_URL;
    public static final String WECHAT_REFRESH_URL;
    public static final String WECHAT_USERINFO_URL;
    public static final Properties CONFIG = new Properties();

    static {
        try { CONFIG.load(WechatCommonData.class.getClassLoader().getResourceAsStream("config/wechat.properties"));
            APP_ID = CONFIG.getProperty("appId");
            SECRET = CONFIG.getProperty("secret");
            WECHAT_ACCESS_URL = CONFIG.getProperty("WECHAT_ACCESS_URL");
            WECHAT_REFRESH_URL = CONFIG.getProperty("WECHAT_REFRESH_URL");
            WECHAT_USERINFO_URL = CONFIG.getProperty("WECHAT_USERINFO_URL");
        } catch (IOException e) {
            throw new RuntimeException("load wechat config error",e);
        }
    }
}
