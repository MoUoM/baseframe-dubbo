package com.vc.jj.util.alipush.model;

import com.aliyuncs.push.model.v20160801.PushNoticeToAndroidRequest;

/**
 * 推送通知到android设备的字段扩展
 *
 * @author yuxinwei
 */
public class YdPushNoticeToAndroidRequest extends PushNoticeToAndroidRequest {

    private String expireTime;
    private Boolean storeOffline;

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public Boolean getStoreOffline() {
        return storeOffline;
    }

    public void setStoreOffline(Boolean storeOffline) {
        this.storeOffline = storeOffline;
    }
}
