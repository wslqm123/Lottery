package com.github.lottery.net;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.github.lottery.ConstantValue;
import com.github.lottery.GlobalParams;

public class HttpClientUtil {
	private HttpClient client;
	
	private HttpPost post;
	private HttpGet get;

	public HttpClientUtil(){
		client = new DefaultHttpClient();
		if (StringUtils.isNotBlank(GlobalParams.PROXY)) {
			//设置代理信息
			HttpHost host = new HttpHost(GlobalParams.PROXY,GlobalParams.PORT);
			client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, host);
		}
	}
	
	public InputStream sendXml(String uri,String xml){
		post = new HttpPost();
		
		try {
			StringEntity entity = new StringEntity(xml, ConstantValue.ENCONDING);
			post.setEntity(entity);
			HttpResponse response = client.execute(post);
			
			//200
			if (response.getStatusLine().getStatusCode()==200) {
				return response.getEntity().getContent();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
