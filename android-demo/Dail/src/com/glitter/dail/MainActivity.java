package com.glitter.dail;

import com.example.dail.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 获取"拨号"按钮对象
		Button btn_dail = (Button)this.findViewById(R.id.btn_dail);
		// 注册"拨号"按钮对象的点击事件
		// 参数为"拨号接口"的实现类实例,这样通过接口来完成点击事件的统一方法调用,至于具体方法内部的实现因人因需求而异。、
		// 这就是接口的非常好的一次规范应用体验,接口就是制定调用规范,实现统一调用。
		btn_dail.setOnClickListener(new MyOnclickListener());
		
	}
	
	
	private class MyOnclickListener implements OnClickListener{

		// 按钮点击时调用的方法
		@Override
		public void onClick(View v) {
			EditText et_number = (EditText) MainActivity.this.findViewById(R.id.et_number);
			System.out.println("调试："+et_number.getText());
			// 意图 对象
			Intent intent = new Intent();
			// 意图打电话
			intent.setAction(Intent.ACTION_CALL);
			// 设置意图打电话的数据定位
			intent.setData(Uri.parse("tel:"+et_number.getText().toString()));
			
			MainActivity.this.startActivity(intent);
			
		}
	}
}

