package com.vc.jj.util;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * Created by F on 2017/5/15.
 */
public class SendSmsUtils {

    public static boolean isTest=false;
    public static String server="api.ucpaas.com";
    public static String sslIP= "0";
    public static int sslPort= 0;
    public static String version="2016-05-31";


    private static Logger logger=Logger.getLogger(SendSmsUtils.class);




    public static DefaultHttpClient getDefaultHttpClient(){
        DefaultHttpClient httpclient=null;
        if (isTest) {
            try {
                SSLHttpClient chc = new SSLHttpClient();
                httpclient = chc.registerSSL(sslIP,"TLS",sslPort,"https");
                HttpParams hParams=new BasicHttpParams();
                hParams.setParameter("https.protocols", "SSLv3,SSLv2Hello");
                httpclient.setParams(hParams);
            } catch (KeyManagementException e) {
                // TODO: handle exception
                logger.error(e);
            }catch (NoSuchAlgorithmException e) {
                // TODO: handle exception
                logger.error(e);
            }
        }else {
            httpclient=new DefaultHttpClient();
        }
        return httpclient;
    }


    public static String getSignature(String accountSid, String authToken,String timestamp,EncryptUtil encryptUtil) throws Exception{
        String sig = accountSid + authToken + timestamp;
        String signature = encryptUtil.md5Digest(sig);
        return signature;
    }


    public static StringBuffer getStringBuffer() {
        StringBuffer sb = new StringBuffer("https://");
        sb.append(server);
        return sb;
    }


    public static HttpResponse post(String cType,String accountSid,String authToken,String timestamp,String url,DefaultHttpClient httpclient,EncryptUtil encryptUtil,String body) throws Exception{
        HttpPost httppost = new HttpPost(url);
        httppost.setHeader("Accept", cType);
        httppost.setHeader("Content-Type", cType+";charset=utf-8");
        String src = accountSid + ":" + timestamp;
        String auth = encryptUtil.base64Encoder(src);
        httppost.setHeader("Authorization", auth);
        BasicHttpEntity requestBody = new BasicHttpEntity();
        requestBody.setContent(new ByteArrayInputStream(body.getBytes("UTF-8")));
        requestBody.setContentLength(body.getBytes("UTF-8").length);
        httppost.setEntity(requestBody);
        // 执行客户端请求
        HttpResponse response = httpclient.execute(httppost);
        return response;
    }

    /**-----------
     * 公共参数
     ------------*/
    private static String accountSid = "f3372231b06444caa4be5bdf3f8203cf";
    //   3c1a772cc27452901acfad04dd68df04
    private static String authToken = "5687ffe25c2a46e6a996070877271151";
    //  50f7a99263b56023cdf2ee8a7e476980

    /**
     * 发短信
     * */
    public static String templateSMS( String to,String param) {
        String templateId = "49985";
        String appId = "1e2ec82099964746919a2b78f644224d";

        // TODO Auto-generated method stub
        String result = "";
        DefaultHttpClient httpclient=getDefaultHttpClient();
        try {
            //MD5加密
            EncryptUtil encryptUtil = new EncryptUtil();
            // 构造请求URL内容
            String timestamp = DateUtil.dateToStr(new Date(),
                    DateUtil.DATE_TIME_NO_SLASH);// 时间戳
            String signature =getSignature(accountSid,authToken,timestamp,encryptUtil);
            String url = getStringBuffer().append("/").append(version)
                    .append("/Accounts/").append(accountSid)
                    .append("/Messages/templateSMS")
                    .append("?sig=").append(signature).toString();
            TemplateSMS templateSMS=new TemplateSMS();
            templateSMS.setAppId(appId);
            templateSMS.setTemplateId(templateId);
            templateSMS.setTo(to);
            templateSMS.setParam(param);
            Gson gson = new Gson();
            String body = gson.toJson(templateSMS);
            body="{\"templateSMS\":"+body+"}";
            logger.info(body);
            HttpResponse response=post("application/json",accountSid, authToken, timestamp, url, httpclient, encryptUtil, body);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, "UTF-8");
            }
            EntityUtils.consume(entity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            // 关闭连接
            httpclient.getConnectionManager().shutdown();
        }
        return result;
    }


    /**
     * 隐号通话
     *
     * */
    public static String allotNumber(SafetyCalls safetyCalls) {
        String result = "";
        DefaultHttpClient httpclient = getDefaultHttpClient();
        try {
            // MD5加密
            EncryptUtil encryptUtil = new EncryptUtil();
            // 构造请求URL内容
            String timestamp = DateUtil.dateToStr(new Date(), DateUtil.DATE_TIME_NO_SLASH);
            String signature = getSignature(accountSid, authToken, timestamp, encryptUtil);
            String url = getStringBuffer().append("/").append(version).append("/Accounts/").append(accountSid)
                    .append("/safetyCalls/allocNumber").append("?sig=").append(signature).toString();
            String body = "";
            if (safetyCalls != null) {
                Gson gson = new Gson();
                body = gson.toJson(safetyCalls);
            }
            HttpResponse response = post("application/json", accountSid, authToken, timestamp, url, httpclient,
                    encryptUtil, body);
            // 获取响应实体信息
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, "UTF-8");
            }
            EntityUtils.consume(entity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接
            httpclient.getConnectionManager().shutdown();
        }
        return result;
    }
}
