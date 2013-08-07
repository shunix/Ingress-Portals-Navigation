package com.shunix.portalsnav.fragments;

import com.shunix.portalsnav.R;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
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
	private SharedPreferences sharedPreferences;
	private SharedPreferences.Editor editor;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.choose_area, container, false);
		beijingCheckBox = (CheckBox) view.findViewById(R.id.checkBox1);
		chengduCheckBox = (CheckBox) view.findViewById(R.id.checkBox2);
		chongqingCheckBox = (CheckBox) view.findViewById(R.id.checkBox3);
		guangzhouCheckBox = (CheckBox) view.findViewById(R.id.checkBox4);
		nanjingCheckBox = (CheckBox) view.findViewById(R.id.checkBox5);
		shanghaiCheckBox = (CheckBox) view.findViewById(R.id.checkBox6);
		wuhanCheckBox = (CheckBox) view.findViewById(R.id.checkBox7);
		xianCheckBox = (CheckBox) view.findViewById(R.id.checkBox8);
		beijingCheckBox.setOnCheckedChangeListener(beijingListener);
		chengduCheckBox.setOnCheckedChangeListener(chengduListener);
		chongqingCheckBox.setOnCheckedChangeListener(chongqingListener);
		guangzhouCheckBox.setOnCheckedChangeListener(guangzhouListener);
		nanjingCheckBox.setOnCheckedChangeListener(nanjingListener);
		shanghaiCheckBox.setOnCheckedChangeListener(shanghaiListener);
		wuhanCheckBox.setOnCheckedChangeListener(wuhanListener);
		xianCheckBox.setOnCheckedChangeListener(xianListener);
		return view;
	}

	OnCheckedChangeListener beijingListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			editor.putBoolean("Beijing", isChecked);
			editor.commit();
		}
	};

	OnCheckedChangeListener chengduListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			editor.putBoolean("Chengdu", isChecked);
			editor.commit();
		}
	};
	
	OnCheckedChangeListener chongqingListener = new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			editor.putBoolean("Chongqing", isChecked);
			editor.commit();
		}
	};

	OnCheckedChangeListener guangzhouListener = new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			editor.putBoolean("Guangzhou", isChecked);
			editor.commit();
		}
	};
	
	OnCheckedChangeListener nanjingListener = new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			editor.putBoolean("Nanjing", isChecked);
			editor.commit();
		}
	};
	
	OnCheckedChangeListener shanghaiListener = new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			editor.putBoolean("Shanghai", isChecked);
			editor.commit();
		}
	};
	
	OnCheckedChangeListener wuhanListener = new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			editor.putBoolean("Wuhan", isChecked);
			editor.commit();
		}
	};
	
	OnCheckedChangeListener xianListener = new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			editor.putBoolean("Xian", isChecked);
			editor.commit();
		}
	};
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sharedPreferences = getActivity().getSharedPreferences("PortalsNav", 0);
		editor = sharedPreferences.edit();
		Toast.makeText(
				getActivity(),
				"Retrieving the data of each area takes about one minute, "
						+ "so just choose the area you're in, or the waiting time will be intolerable, "
						+ "you can change the area at anytime in the app's menu",
				Toast.LENGTH_LONG).show();

	}

}
