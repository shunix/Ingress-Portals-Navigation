package com.shunix.portalsnav.utils;

import java.util.ArrayList;
import java.util.List;

import com.orm.androrm.DatabaseAdapter;
import com.orm.androrm.Model;
import com.shunix.portalsnav.models.PortalsInfo;

import android.content.Context;

public class DatabaseManager {
	private Context context;
	private DatabaseAdapter adapter;
	private int dbId;
	public DatabaseManager(Context context, String dbName, int id) {
		this.context = context;
		this.dbId = id;
		adapter = DatabaseAdapter.getInstance(context);
		DatabaseAdapter.setDatabaseName(dbName);
		List<Class<? extends Model>> models = new ArrayList<Class<? extends Model>>();
		models.add(PortalsInfo.class);
		adapter.setModels(models);
	}
	
	public void savePortalsInfo(String name, double lat, double lng) {
		PortalsInfo portalsInfo = new PortalsInfo();
		portalsInfo.setPortalName(name);
		portalsInfo.setPortalLat(lat);
		portalsInfo.setPortalLng(lng);
		portalsInfo.save(context, dbId);
	}
	
	public void endTransction() {
		adapter.commitTransaction();
	}
	
	public void beginTransction() {
		adapter.beginTransaction();;
	}
}
