package com.shunix.portalsnav.utils;

import android.os.Parcel;
import android.os.Parcelable;


public class BasicPortal implements Parcelable {
	
	private String portalName;
	private String portalLat;
	private String portalLng;
	
	public static final Parcelable.Creator<BasicPortal> CREATOR = new Parcelable.Creator<BasicPortal>() {

		@Override
		public BasicPortal createFromParcel(Parcel source) {
			return new BasicPortal(source);
		}

		@Override
		public BasicPortal[] newArray(int size) {
			return new BasicPortal[size];
		}
		
	};
	
	public BasicPortal (String arg0, String arg1, String arg2) {
		portalName = arg0;
		portalLat = arg1;
		portalLng = arg2;
	}
	public BasicPortal(Parcel in) {
		portalName = in.readString();
		portalLat = in.readString();
		portalLng = in.readString();
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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(portalName);
		dest.writeString(portalLat);
		dest.writeString(portalLng);
	}
}
