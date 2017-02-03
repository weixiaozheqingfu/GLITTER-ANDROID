package com.glitter.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

import com.glitter.domain.SmsInfo;

public class XmlParser {

	
	/**
	 * Xml反序列化
	 * @param in 待解析Xml文件输入流
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
				// 解析开始标签标签
				if(type==XmlPullParser.START_TAG){
					// 解析根标签
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
				// 解析结束标签
				if(type==XmlPullParser.END_TAG){
					if("sms".equals(xmlPullParser.getName())){
						smsInfoList.add(smsInfo);
						smsInfo = null;
					}
				}
				// 指针下移一行同时返回下一行的标签类型
				type = xmlPullParser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return smsInfoList;
	}
}
