package com.glitter.domain;

import java.util.Date;

public class SmsInfo {
	// 流水号
	private String id;
	// 短信类型 收件箱/发件箱
	private String type;
	// 发件人
	private String sender;
	// 收件人
	private String addressee;
	// 短信内容
	private String content;
	// 短信发送或接收时间
	private Date actionTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getAddressee() {
		return addressee;
	}
	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getActionTime() {
		return actionTime;
	}
	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}
	
	
}
