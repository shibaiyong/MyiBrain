package com.uec.ibrain.ibrain.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Consts;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.io.IOException;
import java.nio.charset.CodingErrorAction;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: httpclient管理 </p> 
 * <p>Author:jlchen/陈金梁</p>
 */
public class HttpManager {
	private static final Log log = LogFactory.getLog(HttpManager.class);
	private static PoolingHttpClientConnectionManager cm = null;
	private static RequestConfig rc = null;
	private static CloseableHttpClient httpclient = null;
	//超时时间
	private static int timeout = 1000*10;

	public static void init() {
		try {
			if(null == cm) {
				cm = new PoolingHttpClientConnectionManager();
				
				ConnectionConfig connectionConfig = ConnectionConfig.custom()
			            .setMalformedInputAction(CodingErrorAction.IGNORE)
			            .setUnmappableInputAction(CodingErrorAction.IGNORE)
			            .setCharset(Consts.UTF_8)
			            .build();
				
				cm.setDefaultConnectionConfig(connectionConfig);
				
				cm.setMaxTotal(200);//连接池最大连接数
				cm.setDefaultMaxPerRoute(50);//根据连接到的主机对MaxTotal的一个细分，限制实际使用数量
				
				SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(timeout).build();
				cm.setDefaultSocketConfig(socketConfig);
				
				rc = RequestConfig.custom()
							.setConnectionRequestTimeout(timeout)
							.setConnectTimeout(timeout)
							.setSocketTimeout(timeout)
//				            .setCookieSpec(CookieSpecs.BEST_MATCH)
//				            .setExpectContinueEnabled(true)
//				            .setStaleConnectionCheckEnabled(true)
//				            .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
//				            .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
				            .build();
				httpclient = HttpClients.custom()
						.setConnectionManager(cm)
						.setDefaultRequestConfig(rc)
						.build();
			}
		}catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	public static void getStats() {
		log.info("now client pool "+cm.getTotalStats().toString());
	}
	
	public static CloseableHttpClient getHttpClient() {
		init();
		return httpclient;
	}
	
	public static void close() {
		log.info("PoolingHttpClientConnectionManager close");
		try {
			if(null != httpclient) {
				httpclient.close();
			}
			if(null != cm) {
				cm.close();
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}
}
