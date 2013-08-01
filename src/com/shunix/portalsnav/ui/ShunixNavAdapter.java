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
import com.shunix.portalsnav.utils.SingleStep;

public class ShunixNavAdapter extends ArrayAdapter<SingleStep> {
	private LayoutInflater layoutInflater;
	private Context context;
	private int resourceId;
	public ShunixNavAdapter(Context context, int resource,
			List<SingleStep> objects) {
		super(context, resource, objects);
		this.context = context;
		this.resourceId = resource;
	}

	public ShunixNavAdapter(Context context, int resource,
			List<SingleStep> objects, LayoutInflater inflater) {
		this(context, resource, objects);
		this.layoutInflater = inflater;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		SingleStep portal = getItem(position);
		LinearLayout linearLayout = new LinearLayout(context);
		layoutInflater.inflate(resourceId, linearLayout, true);
		TextView direction = (TextView)linearLayout.findViewById(R.id.textView1);
		direction.setText(portal.direction);
		return linearLayout;
	}
}
