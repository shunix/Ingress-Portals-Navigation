package com.shunix.portalsnav.utils;

import android.os.Parcel;
import android.os.Parcelable;

public class SingleStep implements Parcelable{
	public String distance = "";
	public String duration = "";
	public Location startLocation = new Location();
	public Location endLocation = new Location();
	public String direction = "";
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(distance);
		dest.writeString(duration);
		dest.writeString(direction);
		dest.writeParcelable(startLocation, flags);
		dest.writeParcelable(endLocation, flags);
	}
	public SingleStep() {
		
	}
	public static final Parcelable.Creator<SingleStep> CREATOR = new Parcelable.Creator<SingleStep>() {

		@Override
		public SingleStep createFromParcel(Parcel source) {
			return new SingleStep(source);
		}

		@Override
		public SingleStep[] newArray(int size) {
			return new SingleStep[size];
		}
	};
	public SingleStep(Parcel source) {
		distance = source.readString();
		duration = source.readString();
		direction = source.readString();
		startLocation = source.readParcelable(Location.class.getClassLoader());
		endLocation = source.readParcelable(Location.class.getClassLoader());
	}
}

class Location implements Parcelable{
	public final static Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() {

		@Override
		public Location createFromParcel(Parcel source) {
			return new Location(source);
		}

		@Override
		public Location[] newArray(int size) {
			return new Location[size];
		}
		
	};
	
	public Location(Parcel source) {
		lat = source.readString();
		lng = source.readString();
	}
	
	public Location() {
		
	}
	public String lat = "";
	public String lng = "";

	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(lat);
		dest.writeString(lng);
	}
}