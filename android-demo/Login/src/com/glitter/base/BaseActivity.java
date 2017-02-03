package com.glitter.base;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;

public class BaseActivity extends Activity implements OnClickListener,CompoundButton.OnCheckedChangeListener {

	@Override
	public void onClick(View v) {}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {}

}
