package com.rateboard.weather.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.catalina.connector.Request;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ApiService {

	@Async
	public static String executeTask(String api) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(api);
		CloseableHttpResponse response;
		String result = null;
		try {
			response = httpclient.execute(httpGet);
			System.out.println(response.getStatusLine());
			HttpEntity entity = response.getEntity();
			// do something useful with the response body
			// and ensure it is fully consumed
			result = EntityUtils.toString(entity);
			EntityUtils.consume(entity);
			response.close();
			httpclient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
}
