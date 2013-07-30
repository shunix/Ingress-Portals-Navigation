package com.shunix.portalsnav.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;

public class DrivingHandler implements AsyncInterface {
	private HttpGet httpGet;
	private String result = null;
	private Activity context;
	private String url;
	private ProgressDialog dialog = null;
	private final String URL_STUB = "http://maps.googleapis.com/maps/api/directions/json?";
	private List<SingleStep> list;

	public DrivingHandler(Activity context, double lat1, double lng1,
			double lat2, double lng2) {
		this.context = context;
		this.url = URL_STUB + "origin=" + String.valueOf(lat1) + ","
				+ String.valueOf(lng1) + "&destination=" + String.valueOf(lat2)
				+ "," + String.valueOf(lng2) + "&mode=driving&sensor=false";
		list = new ArrayList<SingleStep>();
		httpGet = new HttpGet(this.url);
	}

	@Override
	public void beginWork() {
		try {
			context.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					dialog = ProgressDialog.show(context, "Requesting",
							"Wait for a moment", true);
				}
			});
			DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
			HttpResponse response = defaultHttpClient.execute(httpGet);
			result = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void endWork() {
		try {
			System.out.println("Enter endWork");
			JSONObject jsonObject = new JSONObject(result);
			String status = jsonObject.getString("status");
			System.out.println(status);
			System.out.println(status.equalsIgnoreCase("OK"));
			if (status.equalsIgnoreCase("OK")) {
				context.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						dialog.dismiss();
					}
				});
				return;
			}
			JSONArray routes = jsonObject.getJSONArray("routes");
			JSONArray legs = routes.getJSONObject(0).getJSONArray("legs");
			JSONObject overall = legs.getJSONObject(0);
			SingleStep step = new SingleStep();
			step.distance = overall.getJSONObject("distance").getString("text");
			step.duration = overall.getJSONObject("dration").getString("text");
			step.startLocation.lat = overall.getJSONObject("start_location")
					.getString("lat");
			step.startLocation.lng = overall.getJSONObject("start_location")
					.getString("lng");
			step.endLocation.lat = overall.getJSONObject("end_location")
					.getString("lat");
			step.endLocation.lng = overall.getJSONObject("end_location")
					.getString("lng");
			list.add(step);
			JSONArray stepsArray = legs.getJSONObject(0).getJSONArray("steps");
			for (int i = 0; i < stepsArray.length(); ++i) {
				overall = stepsArray.getJSONObject(i);
				step = new SingleStep();
				step.distance = overall.getJSONObject("distance").getString(
						"text");
				step.duration = overall.getJSONObject("dration").getString(
						"text");
				step.startLocation.lat = overall
						.getJSONObject("start_location").getString("lat");
				step.startLocation.lng = overall
						.getJSONObject("start_location").getString("lng");
				step.endLocation.lat = overall.getJSONObject("end_location")
						.getString("lat");
				step.endLocation.lng = overall.getJSONObject("end_location")
						.getString("lng");
				step.direction = overall.getString("html_instructions");
				list.add(step);
			}
			context.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					dialog.dismiss();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	
	public List<SingleStep> getList() {
		AsyncHelper asyncHelper = new AsyncHelper(this);
		asyncHelper.AsyncWorkBegin();
		asyncHelper.AsyncWorkEnd();
		return list;
	}
}
