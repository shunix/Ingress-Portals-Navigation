package com.shunix.portalsnav.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.orm.androrm.DatabaseAdapter;
import com.orm.androrm.Model;
import com.shunix.portalsnav.models.PortalsInfo;

public class DatabaseManager {
	private Context context;
	private DatabaseAdapter adapter;
	/**
	 * Calculate the distance between two points;
	 */
	private static final double EARTH_RADIUS = 6378.137;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	public static double getDistance(double lat1, double lng1, double lat2,
			double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}

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

	public Cursor getPortalsWithin(double lat, double lng, int dist) {
		SQLHelper sqlHelper = new SQLHelper(context, "Database");
		SQLiteDatabase database = sqlHelper.getReadableDatabase();
		database.beginTransaction();
		Cursor cur = database.query("PortalsInfo", new String[] { "portalName",
				"portalLat", "portalLng" },
				null, null, null,
				null, null);
		if(cur != null) {
			database.setTransactionSuccessful();
		}
		if(database.inTransaction()) {
			database.endTransaction();
		}
		while (cur.moveToNext()) {
			if (getDistance(lat, lng,
					cur.getDouble(cur.getColumnIndex("portalLat")),
					cur.getDouble(cur.getColumnIndex("portalLng"))) <= dist) {
				System.out.println(getDistance(lat, lng,
						cur.getDouble(cur.getColumnIndex("portalLat")),
						cur.getDouble(cur.getColumnIndex("portalLng"))));
				System.out.println(cur.getString(cur
						.getColumnIndex("portalName")));
			}
			// System.out.println(cur.getString(cur.getColumnIndex("portalLat")));
			// System.out.println(cur.getString(cur.getColumnIndex("portalLng")));
		}
		return cur;
	}

	public void endTransction() {
		adapter.commitTransaction();
		adapter.close();
	}
}
