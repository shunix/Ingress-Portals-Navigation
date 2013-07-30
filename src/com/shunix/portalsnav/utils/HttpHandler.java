package com.shunix.portalsnav.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.ProgressDialog;

public class HttpHandler implements AsyncInterface{
	private String url;
	private HttpGet httpGet;
	private String result = "Fuck";
	private Activity context;
	private ProgressDialog dialog = null;
	public HttpHandler(String url, Activity context) {
		this.url = url;
		System.out.println(url);
		httpGet = new HttpGet(this.url);
		this.context = context;
	}
	@Override
	public void beginWork() {
		HttpResponse response = null;
		try {
			context.runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					dialog = ProgressDialog.show(context, "Requesting", "Wait for a moment", true);
				}
			});
			DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
			response = defaultHttpClient.execute(httpGet);
			result = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void endWork() {
		context.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				dialog.dismiss();
			}
		});
		return;
	}
	
	public String getResult() {
		AsyncHelper asyncHelper = new AsyncHelper(this);
		asyncHelper.AsyncWorkBegin();
		asyncHelper.AsyncWorkEnd();
		while(result == null) {
			result = "";
		}
		
		return result;
	}
	
}
