package com.glitter.sendmessages;

import java.util.List;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	private EditText sendmessages_et_input_phone;
	private EditText sendmessages_et_input_messages;
	private Button sendmessages_btn_send;
	Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		sendmessages_et_input_phone = (EditText)this.findViewById(R.id.sendmessages_et_input_phone);
		sendmessages_et_input_messages = (EditText)this.findViewById(R.id.sendmessages_et_input_messages);
		sendmessages_btn_send = (Button)this.findViewById(R.id.sendmessages_btn_send);
		sendmessages_btn_send.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(v!=null){
			int id = v.getId();
			if (id == R.id.sendmessages_btn_send) {
				String phone = sendmessages_et_input_phone.getText().toString().trim();
				String message = sendmessages_et_input_messages.getText().toString();
				if(TextUtils.isEmpty(phone)){
					 Toast.makeText(this,"�������ռ��˺��룡",Toast.LENGTH_SHORT).show();
					 return;
				}
				// ����ϵͳ������
//				Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+phone));          
//	            intent.putExtra("sms_body", message);          
//	            startActivity(intent);
				
				// ���ö��Žӿڷ�����
				// �����صķ���״̬ 
				String SENT_SMS_ACTION = "SENT_SMS_ACTION";
				Intent sentIntent = new Intent(SENT_SMS_ACTION);
				PendingIntent sendIntent= PendingIntent.getBroadcast(this,0,sentIntent,0);
				this.registerReceiver(new BroadcastReceiver() {
				    @Override
				    public void onReceive(Context _context, Intent _intent) {
				        switch (getResultCode()) {
				        case Activity.RESULT_OK:
				        	Toast.makeText(MainActivity.this,"���ŷ��ͳɹ�",Toast.LENGTH_SHORT).show();
				        break;
				        case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
				        break;
				        case SmsManager.RESULT_ERROR_RADIO_OFF:
				        break;
				        case SmsManager.RESULT_ERROR_NULL_PDU:
				        break;
				        }
				    }
				}, new IntentFilter(SENT_SMS_ACTION));
				
				//�����صĽ���״̬ 
				String DELIVERED_SMS_ACTION = "DELIVERED_SMS_ACTION";
				Intent deliverIntent = new Intent(DELIVERED_SMS_ACTION);
				PendingIntent backIntent= PendingIntent.getBroadcast(this,0,deliverIntent, 0);
				this.registerReceiver(new BroadcastReceiver() {
				   @Override
				   public void onReceive(Context _context, Intent _intent) {
				       Toast.makeText(MainActivity.this,"�������Ѿ��ɹ�����", Toast.LENGTH_SHORT).show();
				   }
				}, new IntentFilter(DELIVERED_SMS_ACTION));
				
				SmsManager smsManager = SmsManager.getDefault();
				// ��ֶ�������,�ֻ������г�������
				List<String> divideContents = smsManager.divideMessage(message);
				if(divideContents!=null && divideContents.size()>0){
					for(String content:divideContents){
						smsManager.sendTextMessage(phone, null, content, sendIntent, backIntent);
					}
				}
			}
		}
	}
	
	
	
}
