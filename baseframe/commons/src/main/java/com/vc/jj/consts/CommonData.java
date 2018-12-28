package com.vc.jj.consts;

import java.io.IOException;
import java.util.Properties;

/**
 * @author :         XLY
 * @createDate :     2018/8/29 16:42
 * @description :
 */
public class CommonData {

    public static final String DOWNLOAD_URL;
    public static final Properties CONFIG = new Properties();

    static {
        try { CONFIG.load(WechatCommonData.class.getClassLoader().getResourceAsStream("common-data.properties"));
            DOWNLOAD_URL = CONFIG.getProperty("downloadUrl");
        } catch (IOException e) {
            throw new RuntimeException("load downloadUrl config error",e);
        }
    }
}
