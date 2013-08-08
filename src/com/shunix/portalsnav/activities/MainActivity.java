package com.shunix.portalsnav.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.WindowManager.LayoutParams;

import com.shunix.portalsnav.R;
import com.shunix.portalsnav.fragments.StartFragment;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.container_layout);
		getSupportFragmentManager().beginTransaction()
				.add(R.id.container, new StartFragment()).commit();
	}

	@Override
	protected void onResume() {
		super.onResume();
		//Android System will manager the wakelock automatically.
		getWindow().addFlags(LayoutParams.FLAG_KEEP_SCREEN_ON);
	}
}
