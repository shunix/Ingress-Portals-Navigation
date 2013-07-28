package com.shunix.portalsnav.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.orm.androrm.DatabaseAdapter;
import com.orm.androrm.Model;
import com.shunix.portalsnav.models.PortalsInfo;

public class DatabaseManager {
	private Context context;
	private DatabaseAdapter adapter;
	public DatabaseManager(Context context, String dbName) {
		this.context = context;
		DatabaseAdapter.setDatabaseName(dbName);
		adapter = DatabaseAdapter.getInstance(context);
		List<Class<? extends Model>> models = new ArrayList<Class<? extends Model>>();
		models.add(PortalsInfo.class);
		adapter.setModels(models);
		adapter.beginTransaction();
	}
	
	public void savePortalsInfo(String name, double lat, double lng) {
		PortalsInfo portalsInfo = new PortalsInfo();
		portalsInfo.setPortalName(name);
		portalsInfo.setPortalLat(lat);
		portalsInfo.setPortalLng(lng);
		portalsInfo.save(context);
	}
	
	public void endTransction() {
		adapter.commitTransaction();
	}
}
