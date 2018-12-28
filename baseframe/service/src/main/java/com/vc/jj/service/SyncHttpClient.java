package com.vc.jj.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
@Lazy
public class SyncHttpClient {
	private static final Logger LOG = LoggerFactory.getLogger(SyncHttpClient.class);
	private static int REQUEST_TIMEOUT = 30000;
	private static int CONNECT_TIMEOUT = 23000;

	@PostConstruct
	private void init() {
	}

	private static URI buildURI(String host, String path, boolean isSecured, Map<String, String> parameters) throws URISyntaxException {
		URIBuilder ub = new URIBuilder();
		if (isSecured) {
			ub.setScheme("https");
		} else {
			ub.setScheme("http");
		}
		ub.setHost(host);
		ub.setPath(path);
		if (null != parameters) {
			for (String key : parameters.keySet()) {
				ub.addParameter(key, parameters.get(key));
			}
		}
		return ub.build();
	}

	/**
	 * HTTPS 信任所有
	 * @return
	 */
	public static CloseableHttpClient createSSLClientDefault(){
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(
					null, new TrustStrategy() {
						// 信任所有
						public boolean isTrusted(X509Certificate[] chain,
								String authType) throws CertificateException {
							return true;
						}
					}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
					sslContext);
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		return  HttpClients.createDefault();
	}
	
	public String postWx(String host, String path, Map<String, String> parameters, boolean isSecured) {
		CloseableHttpResponse response = null;
		CloseableHttpClient httpClient = createSSLClientDefault();
		String result = "";
		try {
			URIBuilder ub = new URIBuilder();
			if(isSecured){
				ub.setScheme("https");
			} else{
				ub.setScheme("http");
			}
			ub.setHost(host);
			ub.setPath(path);
			
			URI uri = ub.build();

			HttpPost httppost = new HttpPost(uri);
			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(REQUEST_TIMEOUT).setConnectTimeout(CONNECT_TIMEOUT).build();
			httppost.setConfig(requestConfig);
			httppost.addHeader("Content-Type", "text/xml");
			StringBuilder params = new StringBuilder("<xml>");
			if (null != parameters) {
				for (String key : parameters.keySet()) {
					params.append("<").append(key).append(">").append(parameters.get(key)).append("</").append(key).append(">");
				}
			}
			params.append("</xml>");
//			System.out.println("发送的参数: " + params.toString());
			StringEntity stringEntity = new StringEntity(params.toString(), "UTF-8");
			httppost.setEntity(stringEntity);
			response = httpClient.execute(httppost);
			
			HttpEntity entity = response.getEntity();
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 200) {
				result = EntityUtils.toString(entity, "UTF-8");
//				System.out.println("Post request return: " + result);
			}
		} catch (RuntimeException ex2) {
			throw ex2;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			if (null != response) {
				try {
					response.close();
				} catch (Exception ex2) {

				}
			}
		}
		return result;
	}

}
