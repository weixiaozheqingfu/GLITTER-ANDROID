package com.glitter.domain;

import java.util.Date;

public class SmsInfo {
	// ��ˮ��
	private String id;
	// �������� �ռ���/������
	private String type;
	// ������
	private String sender;
	// �ռ���
	private String addressee;
	// ��������
	private String content;
	// ���ŷ��ͻ����ʱ��
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
