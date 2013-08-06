package com.shunix.portalsnav.fragments;

import com.shunix.portalsnav.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

public class ChooseArea extends Fragment {

	private CheckBox beijingCheckBox;
	private CheckBox chengduCheckBox;
	private CheckBox chongqingCheckBox;
	private CheckBox guangzhouCheckBox;
	private CheckBox nanjingCheckBox;
	private CheckBox shanghaiCheckBox;
	private CheckBox wuhanCheckBox;
	private CheckBox xianCheckBox;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.choose_area, container, false);
		beijingCheckBox = (CheckBox)view.findViewById(R.id.checkBox1);
		chengduCheckBox = (CheckBox)view.findViewById(R.id.checkBox2);
		chongqingCheckBox = (CheckBox)view.findViewById(R.id.checkBox3);
		guangzhouCheckBox = (CheckBox)view.findViewById(R.id.checkBox4);
		nanjingCheckBox = (CheckBox)view.findViewById(R.id.checkBox5);
		shanghaiCheckBox = (CheckBox)view.findViewById(R.id.checkBox6);
		wuhanCheckBox = (CheckBox)view.findViewById(R.id.checkBox7);
		xianCheckBox = (CheckBox)view.findViewById(R.id.checkBox8);
		
		return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Toast.makeText(
				getActivity(),
				"Retrieving the data of each area takes about one minute, " +
				"so just choose the area you're in, or the waiting time will be intolerable, " +
				"you can change the area at anytime in the app's menu",
				Toast.LENGTH_LONG).show();
		
	}

}
