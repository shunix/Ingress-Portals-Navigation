package com.shunix.portalsnav.ui;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shunix.portalsnav.R;
import com.shunix.portalsnav.utils.BasicPortal;

public class ShunixAdapter extends ArrayAdapter<BasicPortal> {
	private LayoutInflater layoutInflater;
	private Context context;
	private int resourceId;
	public ShunixAdapter(Context context, int resource,
			List<BasicPortal> objects) {
		super(context, resource, objects);
		this.context = context;
		this.resourceId = resource;
	}

	public ShunixAdapter(Context context, int resource,
			List<BasicPortal> objects, LayoutInflater inflater) {
		this(context, resource, objects);
		this.layoutInflater = inflater;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		BasicPortal portal = getItem(position);
		LinearLayout linearLayout = new LinearLayout(context);
		layoutInflater.inflate(resourceId, linearLayout, true);
		TextView name = (TextView)linearLayout.findViewById(R.id.textView1);
		TextView lat = (TextView)linearLayout.findViewById(R.id.textView2);
		TextView lng = (TextView)linearLayout.findViewById(R.id.textView3);
		name.setText(portal.getPortalName());
		lat.setText(portal.getPortalLat());
		lng.setText(portal.getPortalLng());
		return linearLayout;
	}
}
