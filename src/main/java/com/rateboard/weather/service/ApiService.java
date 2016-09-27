package com.rateboard.weather.service;

import java.util.concurrent.CountDownLatch;

import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ApiService {
	
	@Async
	public void executeTask(){
		AsyncHttpClient client = new DefaultAsyncHttpClient();
		client.prepareGet("http://api.wunderground.com/api/76d7b6a0e22e37bb/forecast10day/q/CA/San_Francisco.json")
				.execute(new AsyncCompletionHandler<Response>() {

					@Override
					public Response onCompleted(Response arg0) throws Exception {
						// TODO Auto-generated method stub
						System.out.println(arg0.getResponseBody());
						return arg0;
					}
					
					@Override
					public void onThrowable(Throwable t) {
						super.onThrowable(t);
					}
				});
	}
}
