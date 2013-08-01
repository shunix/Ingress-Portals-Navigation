package com.shunix.portalsnav.ui;

import java.util.ArrayList;
import java.util.List;

import com.shunix.portalsnav.R;
import com.shunix.portalsnav.utils.SingleStep;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;

public class ShunixHandler extends Handler {

	private List<SingleStep> list = new ArrayList<SingleStep>();
	private ListFragment fragment;
	
	public ShunixHandler(ListFragment fragment) {
		this.fragment = fragment;
	}
	
	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		Object[] steps = msg.getData().getParcelableArray("steps");
		for(int i = 0; i < steps.length; ++i) {
			list.add((SingleStep)steps[i]);
		}
		LayoutInflater layoutInflater = (LayoutInflater) fragment.getActivity()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		ShunixNavAdapter adapter = new ShunixNavAdapter(fragment.getActivity(), R.layout.nav_row_layout, list, layoutInflater);
		fragment.setListAdapter(adapter);
	}
}
