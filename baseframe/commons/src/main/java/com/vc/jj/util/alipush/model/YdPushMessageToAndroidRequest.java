package com.vc.jj.util.alipush.model;

import com.aliyuncs.push.model.v20160801.PushMessageToAndroidRequest;

/**
 * 推送消息到android设备的字段扩展
 *
 * @author yuxinwei
 */
public class YdPushMessageToAndroidRequest extends PushMessageToAndroidRequest {

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
