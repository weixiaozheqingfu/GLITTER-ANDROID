package com.glitter.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.glitter.banch.Banch;
import com.glitter.banch.BanchImpl;
import com.glitter.base.BaseActivity;

public class HomeActivity extends BaseActivity {
	
	private Button btnBack;
	private Banch banch;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		btnBack = (Button)this.findViewById(R.id.home_btn_back);
		btnBack.setOnClickListener(this);
		banch = new BanchImpl();
		
	}
	
	@Override
	public void onClick(View v) {
		// 如果是"返回"按钮
		if(R.id.home_btn_back==v.getId()){
			banch.jumpActivity(this, LoginActivity.class);
		}
	}
}
