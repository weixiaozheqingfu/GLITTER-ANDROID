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
		// ��ȡ"����"��ť����
		Button btn_dail = (Button)this.findViewById(R.id.btn_dail);
		// ע��"����"��ť����ĵ���¼�
		// ����Ϊ"���Žӿ�"��ʵ����ʵ��,����ͨ���ӿ�����ɵ���¼���ͳһ��������,���ھ��巽���ڲ���ʵ��������������졣��
		// ����ǽӿڵķǳ��õ�һ�ι淶Ӧ������,�ӿھ����ƶ����ù淶,ʵ��ͳһ���á�
		btn_dail.setOnClickListener(new MyOnclickListener());
		
	}
	
	
	private class MyOnclickListener implements OnClickListener{

		// ��ť���ʱ���õķ���
		@Override
		public void onClick(View v) {
			EditText et_number = (EditText) MainActivity.this.findViewById(R.id.et_number);
			System.out.println("���ԣ�"+et_number.getText());
			// ��ͼ ����
			Intent intent = new Intent();
			// ��ͼ��绰
			intent.setAction(Intent.ACTION_CALL);
			// ������ͼ��绰�����ݶ�λ
			intent.setData(Uri.parse("tel:"+et_number.getText().toString()));
			
			MainActivity.this.startActivity(intent);
			
		}
	}
}

