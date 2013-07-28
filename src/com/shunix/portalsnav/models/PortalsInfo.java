package com.shunix.portalsnav.models;

import android.content.Context;

import com.orm.androrm.Model;
import com.orm.androrm.QuerySet;
import com.orm.androrm.field.CharField;
import com.orm.androrm.field.DoubleField;

public class PortalsInfo extends Model {
	protected CharField portalName;
	protected DoubleField portalLat;
	protected DoubleField portalLng;
	
	public PortalsInfo() {
		portalName = new CharField(100);
		portalLat = new DoubleField();
		portalLng = new DoubleField();
	}
	
	public void setPortalName(String name) {
		portalName.set(name);
	}
	
	public void setPortalLat(double lat) {
		portalLat.set(lat);
	}
	
	public void setPortalLng(double lng) {
		portalLng.set(lng);
	}
	
	public String getPortalName() {
		return portalName.get();
	}
	
	public double getPortalLat() {
		return portalLat.get();
	}
	
	public double getPortalLng() {
		return portalLng.get();
	}
	
	public static final QuerySet<PortalsInfo> objects(Context context) {
        return objects(context, PortalsInfo.class);
    }
}
