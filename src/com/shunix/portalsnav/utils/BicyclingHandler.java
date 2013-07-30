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

public class BicyclingHandler implements AsyncInterface{
	private HttpGet httpGet;
	private String result = null;
	private Activity context;
	private String url;
	private ProgressDialog dialog = null;
	private final String URL_STUB = "http://maps.googleapis.com/maps/api/directions/json?";
	private List<SingleStep> list;

	public BicyclingHandler(Activity context, double lat1, double lng1,
			double lat2, double lng2) {
		this.context = context;
		this.url = URL_STUB + "origin=" + String.valueOf(lat1) + ","
				+ String.valueOf(lng1) + "&destination=" + String.valueOf(lat2)
				+ "," + String.valueOf(lng2) + "&mode=bicycling&sensor=false";
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
			JSONObject jsonObject = new JSONObject(result);
			String status = jsonObject.getString("status");
			if (!status.equalsIgnoreCase("OK")) {
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
			System.out.println(step.distance);
			step.duration = overall.getJSONObject("duration").getString("text");
			System.out.println(step.duration);
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
				JSONObject object = stepsArray.getJSONObject(i);
				SingleStep everyStep = new SingleStep();
				everyStep.distance = object.getJSONObject("distance").getString(
						"text");
				System.out.println(step.distance);
				everyStep.duration = object.getJSONObject("duration").getString(
						"text");
				System.out.println(step.duration);
				everyStep.startLocation.lat = object
						.getJSONObject("start_location").getString("lat");
				everyStep.startLocation.lng = object
						.getJSONObject("start_location").getString("lng");
				everyStep.endLocation.lat = object.getJSONObject("end_location")
						.getString("lat");
				everyStep.endLocation.lng = object.getJSONObject("end_location")
						.getString("lng");
				everyStep.direction = object.getString("html_instructions");
				list.add(everyStep);
			}
			context.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					dialog.dismiss();
				}
			});
			//TODO: Do your work here.
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	
	public void processData() {
		AsyncHelper asyncHelper = new AsyncHelper(this);
		asyncHelper.AsyncWorkBegin();
		asyncHelper.AsyncWorkEnd();
	}
}
