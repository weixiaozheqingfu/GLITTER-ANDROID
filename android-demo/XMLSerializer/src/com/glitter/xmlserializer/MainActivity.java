package com.glitter.xmlserializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.xmlpull.v1.XmlSerializer;

import android.os.Bundle;
import android.os.Environment;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.glitter.base.BaseActivity;
import com.glitter.domain.SmsInfo;
import com.glitter.service.XmlParser;

public class MainActivity extends BaseActivity {
	
	private Button btnSerializer;
	private Button btnParser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnSerializer = (Button)this.findViewById(R.id.btn_xml_serializer);
		btnParser = (Button)this.findViewById(R.id.btn_xml_pullParser);
		btnSerializer.setOnClickListener(this);
		btnParser.setOnClickListener(this);
		
	}
	
	
	@Override
	public void onClick(View v) {
		if(v!=null){
			// XML���л���
			if(R.id.btn_xml_serializer == v.getId()){
				List<SmsInfo> smsInfoList = new ArrayList<SmsInfo>();
				
				SmsInfo smsInfo = new SmsInfo();
				smsInfo.setType("0");
				smsInfo.setSender("����");
				smsInfo.setAddressee("����");
				smsInfo.setContent("���,���Զ��ű���");
				smsInfo.setActionTime(new Date());
				
				SmsInfo smsInfo2 = new SmsInfo();
				smsInfo2.setType("0");
				smsInfo2.setSender("����");
				smsInfo2.setAddressee("����");
				smsInfo2.setContent("���,���Զ��ű���ͨ��");
				smsInfo2.setActionTime(new Date());
				
				smsInfoList.add(smsInfo);
				smsInfoList.add(smsInfo2);
				
				try {
					XmlSerializer xmlSerializer = Xml.newSerializer();
					File file = new File(Environment.getExternalStorageDirectory(),"backSms.xml");
					FileOutputStream fos = new FileOutputStream(file);
					xmlSerializer.setOutput(fos, "utf-8");
					
					xmlSerializer.startDocument("utf-8", true);
					xmlSerializer.startTag(null, "smss");
					// ѭ�����ż���,��ÿһ���������ݶ���xml���л����ݵ�һ��xml�ļ���
					for(int i=0;i<smsInfoList.size();i++){
						SmsInfo s = smsInfoList.get(i);
						xmlSerializer.startTag(null, "sms");
						xmlSerializer.attribute(null, "id",String.valueOf(i));
						xmlSerializer.startTag(null, "type");
						xmlSerializer.text(s.getType());
						xmlSerializer.endTag(null, "type");
						xmlSerializer.startTag(null, "sender");
						xmlSerializer.text(s.getSender());
						xmlSerializer.endTag(null, "sender");
						xmlSerializer.startTag(null, "addressee");
						xmlSerializer.text(s.getAddressee());
						xmlSerializer.endTag(null, "addressee");
						xmlSerializer.startTag(null, "content");
						xmlSerializer.text(s.getContent());
						xmlSerializer.endTag(null, "content");
						xmlSerializer.startTag(null, "actionTime");
						xmlSerializer.text(s.getActionTime().toString());
						xmlSerializer.endTag(null, "actionTime");
						xmlSerializer.endTag(null, "sms");
					}
					xmlSerializer.endTag(null, "smss");
					xmlSerializer.endDocument();
					fos.close();
					Toast.makeText(this, "���ݶ��ųɹ�", Toast.LENGTH_SHORT).show();
				} catch (Exception e) {
					e.printStackTrace();
					Toast.makeText(this, "���ݶ���ʧ��", Toast.LENGTH_SHORT).show();
				}
			}	
			
			// XML�����л���
			if(R.id.btn_xml_pullParser==v.getId()){
				File file = new File(Environment.getExternalStorageDirectory(),"backSms.xml");
				try {
					FileInputStream in = new FileInputStream(file);
					List<SmsInfo> smsInfoList = XmlParser.getSmsInfoListFromXml(in);
					System.out.println(smsInfoList.get(0).getContent());
					
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
