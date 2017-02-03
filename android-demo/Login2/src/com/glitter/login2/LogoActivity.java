package com.glitter.login2;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

import android.widget.Button;
import android.widget.ProgressBar;

public class LogoActivity extends Activity {
	@SuppressWarnings("unused")
	private ProgressBar progressBar;
	private Button backButton;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ȥ������
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.logo);

		progressBar = (ProgressBar) findViewById(R.id.pgBar);
		backButton = (Button) findViewById(R.id.btn_back);

		Intent intent = new Intent(this,WelcomeActivity.class);
		LogoActivity.this.startActivity(intent);

		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}