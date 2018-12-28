package com.vc.jj.util;

/**
 * 杭州新苗网络科技有限公司
 *
 * @author meihf
 * @create 2017/5/18
 * @description
 */
public class HxConfig {

    public static String  clientId = "YXA6arQZgDulEeeroLPshOpvsA";

    public static String clientSecret = "YXA6Q-hHGqAuFz9Y0hvBn8irFmH8AG4";

    public static String appName = "yyyy";

    public static String orgName = "1116170518115334";

    /*获取token用于调用其他接口*/
    public static String getTokenUrl = "http://a1.easemob.com/"+orgName+"/"+appName+"/token";

    /*创建用户*/
    public static String createUserUrl = "http://a1.easemob.com/"+orgName+"/"+appName+"/users";

    /*获取聊天记录*/
    public static String findChatMsgUrl = "http://a1.easemob.com/"+orgName+"/"+appName+"/chatmessages";

    public static String prefixToken = "Bearer ";

}
