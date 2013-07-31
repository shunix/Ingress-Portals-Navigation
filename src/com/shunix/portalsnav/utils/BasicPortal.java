package com.shunix.portalsnav.utils;

public class BasicPortal {
	private String portalName;
	private String portalLat;
	private String portalLng;
	
	public BasicPortal(String arg0, String arg1, String arg2) {
		portalName = arg0;
		portalLat = arg1;
		portalLng = arg2;
	}
	
	public String getPortalName() {
		return portalName;
	}
	
	public String getPortalLat() {
		return portalLat;
	}
	
	public String getPortalLng() {
		return portalLng;
	}
}
