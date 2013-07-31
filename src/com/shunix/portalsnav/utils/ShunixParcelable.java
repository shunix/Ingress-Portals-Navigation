package com.shunix.portalsnav.utils;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class ShunixParcelable implements Parcelable {

	private List<BasicPortal> portals;
	
	public ShunixParcelable(List<BasicPortal> list) {
		portals = list;
	}
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeList(portals);
	}
	
	public static final Parcelable.Creator<ShunixParcelable> CREATOR = new Parcelable.Creator<ShunixParcelable>() {

		@Override
		public ShunixParcelable createFromParcel(Parcel source) {
			return new ShunixParcelable(source);
		}

		@Override
		public ShunixParcelable[] newArray(int size) {
			return new ShunixParcelable[size];
		}
	};
	
	public List<BasicPortal> getPortals() {
		return portals;
	}
	@SuppressWarnings("unchecked")
	public ShunixParcelable(Parcel in) {
		portals = in.readArrayList(List.class.getClassLoader());
	}

}
