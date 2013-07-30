package com.shunix.portalsnav.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;

public final class NavigationUtils {
	private static final String URL_STUB = "http://maps.googleapis.com/maps/api/directions/json?";

	public static List<SingleStep> getDrivingSteps(Activity activity,
			double lat1, double lng1, double lat2, double lng2) {
		List<SingleStep> list = new ArrayList<SingleStep>();
		try {
			String url = URL_STUB + "origin=" + String.valueOf(lat1) + ","
					+ String.valueOf(lng1) + "&destination="
					+ String.valueOf(lat2) + "," + String.valueOf(lng2)
					+ "&mode=driving&sensor=false";
			HttpHandler handler = new HttpHandler(url, activity);
			String reString = handler.getResult();
			System.out.println(reString);
			JSONObject jsonObject = new JSONObject(handler.getResult());
			
			String status = jsonObject.getString("status");
			if(status != "OK") {
				return list;
			}
			JSONArray routes = jsonObject.getJSONArray("routes");
			JSONArray legs = routes.getJSONObject(0).getJSONArray("legs");
			JSONObject overall = legs.getJSONObject(0);
			SingleStep step = new SingleStep();
			step.distance = overall.getJSONObject("distance").getString("text");
			step.duration = overall.getJSONObject("dration").getString("text");
			step.startLocation.lat = overall.getJSONObject("start_location").getString("lat");
			step.startLocation.lng = overall.getJSONObject("start_location").getString("lng");
			step.endLocation.lat = overall.getJSONObject("end_location").getString("lat");
			step.endLocation.lng = overall.getJSONObject("end_location").getString("lng");
			list.add(step);
			JSONArray stepsArray = legs.getJSONObject(0).getJSONArray("steps");
			for(int i = 0; i < stepsArray.length(); ++i) {
				overall = stepsArray.getJSONObject(i);
				step = new SingleStep();
				step.distance = overall.getJSONObject("distance").getString("text");
				step.duration = overall.getJSONObject("dration").getString("text");
				step.startLocation.lat = overall.getJSONObject("start_location").getString("lat");
				step.startLocation.lng = overall.getJSONObject("start_location").getString("lng");
				step.endLocation.lat = overall.getJSONObject("end_location").getString("lat");
				step.endLocation.lng = overall.getJSONObject("end_location").getString("lng");
				step.direction = overall.getString("html_instructions");
				list.add(step);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public static List<SingleStep> getWalkingSteps(Activity activity) {
		List<SingleStep> list = new ArrayList<SingleStep>();
		return list;
	}

	public static List<SingleStep> getBicyclingSteps(Activity activity) {
		List<SingleStep> list = new ArrayList<SingleStep>();
		return list;
	}

	public static List<SingleStep> getTransitSteps(Activity activity) {
		List<SingleStep> list = new ArrayList<SingleStep>();
		return list;
	}
}
