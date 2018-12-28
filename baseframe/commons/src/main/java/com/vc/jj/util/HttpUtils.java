package com.vc.jj.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.common.base.Strings;

import net.sf.json.JSONObject;


public class HttpUtils {

    static HttpClient httpclient;
    static HttpPost httpPost;
    static HttpPut httpPut;

    static {
        // 创建HttpClient实例
        httpclient = new DefaultHttpClient();
        // 创建Get方法实例
    }


    public static ResultDto doGet(String allConfigUrl,String token) {
        BufferedReader in = null;
        StringBuffer result = null;
        ResultDto resultDto = new ResultDto();
        try {
            // url请求中如果有中文，要在接收方用相应字符转码
            URI uri = new URI(allConfigUrl);
            URL url = uri.toURL();
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept-Charset", "utf-8");
            connection.setRequestProperty("contentType", "utf-8");
            if (!Strings.isNullOrEmpty(token)){
                connection.setRequestProperty("Authorization",token);
            }
            connection.connect();
            int code = ((HttpURLConnection)connection).getResponseCode();
            resultDto.setCode(code);
            result = new StringBuffer();
            // 读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            resultDto.setData(result);
            return resultDto;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return resultDto;
    }

    public static ResultDto doPost(String url,JSONObject params,String token){
        ResultDto resultDto = new ResultDto();
        try {
            httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type", "application/json");
            if (!Strings.isNullOrEmpty(token)){
                httpPost.setHeader("Authorization",token);
            }
            httpPost.setEntity(new StringEntity(params.toString(), "UTF-8"));
            HttpResponse response = null;
            response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            int code = response.getStatusLine().getStatusCode();
            resultDto.setCode(code);
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                resultDto.setData(result);
                return resultDto;
            }
            resultDto.setCode(ErrorCode.C11016);
            resultDto.setMsg(ErrorCode.E11016);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultDto;
    }

    public static ResultDto doPut(String url,JSONObject params,String token){
        ResultDto resultDto = new ResultDto();
        try {
            httpPut = new HttpPut(url);
            if (!Strings.isNullOrEmpty(token)){
                httpPut.setHeader("Authorization",token);
            }
            httpPut.setEntity(new StringEntity(params.toString(), "UTF-8"));
            HttpResponse response = null;
            response = httpclient.execute(httpPut);
            HttpEntity entity = response.getEntity();
            int code = response.getStatusLine().getStatusCode();
            resultDto.setCode(code);
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                resultDto.setData(result);
                return resultDto;
            }
            resultDto.setCode(ErrorCode.C11016);
            resultDto.setMsg(ErrorCode.E11016);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultDto;
    }


}
