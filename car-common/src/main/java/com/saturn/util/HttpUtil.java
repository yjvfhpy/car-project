package com.saturn.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
	public static String request(String method, String url, Map<String, String> heard, Map<String, Object> params){
        url = url.replaceAll(" ", "%20");
        CloseableHttpClient client = HttpClients.createDefault();
		
		try {
			HttpUriRequest request = null;
			URIBuilder uriBuilder = new URIBuilder(url);
			if(params != null && params.size()>0){	
				Iterator<String> heardKeys = params.keySet().iterator();
				String key;
				Object value;
				while(heardKeys.hasNext()){
					key = heardKeys.next();
					value = params.get(key);
					uriBuilder.setParameter(key, value.toString());
				}
			}
			
			if(method.equalsIgnoreCase("get")){
				request = new HttpGet(uriBuilder.build());
			}else if(method.equalsIgnoreCase("post")){
				request = new HttpPost(uriBuilder.build());
			}
			if( heard!=null && heard.size()>0){
				Iterator<String> heardKeys = heard.keySet().iterator();
				String key;
				String value;
				while(heardKeys.hasNext()){
					key = heardKeys.next();
					value = heard.get(key);
					request.addHeader(key, value);
				}
			}
			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity);
			return result;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (ConnectTimeoutException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static String get(String url, Map<String, String> heard, Map<String, Object> params){

	       url = url.replaceAll(" ", "%20");
	       CloseableHttpClient client = HttpClients.createDefault();
			try {
				URIBuilder uriBuilder = new URIBuilder(url);
				if(params != null && params.size()>0){	
					Iterator<String> heardKeys = params.keySet().iterator();
					String key;
					Object value;
					while(heardKeys.hasNext()){
						key = heardKeys.next();
						value = params.get(key);
						uriBuilder.setParameter(key, value.toString());
					}
				}
				
				HttpGet request  = new HttpGet(uriBuilder.build());
				if( heard!=null && heard.size()>0){
					Iterator<String> heardKeys = heard.keySet().iterator();
					String key;
					String value;
					while(heardKeys.hasNext()){
						key = heardKeys.next();
						value = heard.get(key);
						request.addHeader(key, value);
					}
				}
//				if(params != null && params.size()>0){
//					Iterator<String> heardKeys = params.keySet().iterator();
//					String key;
//					Object value;
//					url += "?";
//					while(heardKeys.hasNext()){
//						key = heardKeys.next();
//						value = params.get(key);
//						url += (key+"="+value+"&");
//					}
//					url = url.substring(0,url.lastIndexOf("&"));
//					request.setURI(new URI(url));
//				}
				HttpResponse response = client.execute(request);
				HttpEntity entity = response.getEntity();
				String result = EntityUtils.toString(entity);
				return result;
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (ConnectTimeoutException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					client.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}
	
	public static String post(String url, Map<String, String> heard, Map<String, Object> params, String postBody){

	       url = url.replaceAll(" ", "%20");
	       CloseableHttpClient client = HttpClients.createDefault();
			try {
				URIBuilder uriBuilder = new URIBuilder(url);
				if(params != null && params.size()>0){	
					Iterator<String> heardKeys = params.keySet().iterator();
					String key;
					Object value;
					while(heardKeys.hasNext()){
						key = heardKeys.next();
						value = params.get(key);
						uriBuilder.setParameter(key, value.toString());
					}
				}
				HttpPost request = new HttpPost(uriBuilder.build());
				if( heard!=null && heard.size()>0){
					Iterator<String> heardKeys = heard.keySet().iterator();
					String key;
					String value;
					while(heardKeys.hasNext()){
						key = heardKeys.next();
						value = heard.get(key);
						request.addHeader(key, value);
					}
				}
				
				if(postBody !=null){
					request.setEntity(new StringEntity(postBody, "utf-8"));
				}
				
//				System.out.println(request.getURI());
				HttpResponse response = client.execute(request);
				HttpEntity entity = response.getEntity();
				String result = EntityUtils.toString(entity);
				return result;
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (ConnectTimeoutException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					client.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}
	
	public static void asynGet(){
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
		try {
		    // Start the client
		    httpclient.start();

		    // Execute request
//		    final HttpGet request1 = new HttpGet("http://www.apache.org/");
//		    Future<HttpResponse> future = httpclient.execute(request1, null);
//		    Thread.sleep(3000);
//		    System.out.println("done");
		    // and wait until a response is received
//		    HttpResponse response1 = future.get();
//		    System.out.println(request1.getRequestLine() + "->" + response1.getStatusLine());

		    // One most likely would want to use a callback for operation result
		    final CountDownLatch latch1 = new CountDownLatch(1);
		    final HttpGet request2 = new HttpGet("http://localhost/sdk/user/test?a=sfafdfsdfafdaf");
		    httpclient.execute(request2, new FutureCallback<HttpResponse>() {

		        public void completed(final HttpResponse response2) {
		            latch1.countDown();
		            System.out.println(request2.getRequestLine() + "->" + response2.getStatusLine());
		        }

		        public void failed(final Exception ex) {
		            latch1.countDown();
		            System.out.println(request2.getRequestLine() + "->" + ex);
		        }

		        public void cancelled() {
		            latch1.countDown();
		            System.out.println(request2.getRequestLine() + " cancelled");
		        }

		    });
		    latch1.await();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		    try {
				httpclient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args){
		System.out.println("===============================");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("uuid", "123");
//		params.put("name", "中问");
		System.out.println(request("get","http://www.baidu.com", null, params));
//		System.out.println(post("http://www.baidu.com", null, params, null));
	}

}
