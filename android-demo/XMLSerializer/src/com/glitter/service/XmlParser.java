package com.glitter.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

import com.glitter.domain.SmsInfo;

public class XmlParser {

	
	/**
	 * Xml�����л�
	 * @param in ������Xml�ļ�������
	 * @return
	 */
	public static List<SmsInfo> getSmsInfoListFromXml(InputStream in){
		
		List<SmsInfo> smsInfoList = null;
		SmsInfo smsInfo = null;
		XmlPullParser xmlPullParser = Xml.newPullParser();
		try {
			xmlPullParser.setInput(in, "utf-8");
			int type = xmlPullParser.getEventType();
			
			
			while(type!=XmlPullParser.END_DOCUMENT){
				// ������ʼ��ǩ��ǩ
				if(type==XmlPullParser.START_TAG){
					// ��������ǩ
					if("smss".equals(xmlPullParser.getName())){
						smsInfoList = new ArrayList<SmsInfo>();
					}else if("sms".equals(xmlPullParser.getName())){
						smsInfo = new SmsInfo();
						String id = xmlPullParser.getAttributeValue("", "id");
						smsInfo.setId(id);
					}else if("type".equals(xmlPullParser.getName())){
						smsInfo.setType(xmlPullParser.nextText());
					}else if("sender".equals(xmlPullParser.getName())){
						smsInfo.setSender(xmlPullParser.nextText());
					}else if("addressee".equals(xmlPullParser.getName())){
						smsInfo.setAddressee(xmlPullParser.nextText());
					}else if("content".equals(xmlPullParser.getName())){
						smsInfo.setContent(xmlPullParser.nextText());
					}
//					else if("actionTime".equals(xmlPullParser.getName())){
//						smsInfo.setActionTime(Date.valueOf(xmlPullParser.nextText()));
//					}
				}
				// ����������ǩ
				if(type==XmlPullParser.END_TAG){
					if("sms".equals(xmlPullParser.getName())){
						smsInfoList.add(smsInfo);
						smsInfo = null;
					}
				}
				// ָ������һ��ͬʱ������һ�еı�ǩ����
				type = xmlPullParser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return smsInfoList;
	}
}
