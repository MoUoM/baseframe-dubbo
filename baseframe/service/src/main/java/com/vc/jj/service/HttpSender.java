package com.vc.jj.service;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLDecoder;


/**
 *  重构 第三方短信
 * */
public class HttpSender {

    public static String send(String uri, String account, String pswd, String mobiles, String content, boolean needstatus, String product, String extno) throws Exception {
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod();

        try {
            URI base = new URI(uri, false);
            method.setURI(new URI(base, "HttpSendSM", false));
            method.setQueryString(new NameValuePair[]{new NameValuePair("account", account), new NameValuePair("pswd", pswd), new NameValuePair("mobile", mobiles), new NameValuePair("needstatus", String.valueOf(needstatus)), new NameValuePair("msg", content), new NameValuePair("product", product), new NameValuePair("extno", extno)});
            int result = client.executeMethod(method);
            if (result != 200) {
                throw new Exception("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
            } else {
                InputStream in = method.getResponseBodyAsStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                boolean var15 = false;

                int len;
                while((len = in.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }

                String var17 = URLDecoder.decode(baos.toString(), "UTF-8");
                return var17;
            }
        } finally {
            method.releaseConnection();
        }
    }

    public static String batchSend(String uri, String account, String pswd, String mobiles, String content, boolean needstatus, String product, String extno) throws Exception {
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod();

        try {
            URI base = new URI(uri, false);
            method.setURI(new URI(base, "HttpBatchSendSM", false));
            method.setQueryString(new NameValuePair[]{new NameValuePair("account", account), new NameValuePair("pswd", pswd), new NameValuePair("mobile", mobiles), new NameValuePair("needstatus", String.valueOf(needstatus)), new NameValuePair("msg", content), new NameValuePair("product", product), new NameValuePair("extno", extno)});
            int result = client.executeMethod(method);
            if (result != 200) {
                throw new Exception("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
            } else {
                InputStream in = method.getResponseBodyAsStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                boolean var15 = false;

                int len;
                while((len = in.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }

                String var17 = URLDecoder.decode(baos.toString(), "UTF-8");
                return var17;
            }
        } finally {
            method.releaseConnection();
        }
    }

}
