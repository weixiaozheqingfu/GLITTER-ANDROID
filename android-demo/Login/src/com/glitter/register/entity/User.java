package com.glitter.register.entity;

public class User {
	// ������ˮ��
	private int id;
	// ��¼�˺�
	private String loginId;
	// �ǳ�
	private String nickname;
	// ����
	private String password;
	// �˻����
	private double account;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getAccount() {
		return account;
	}
	public void setAccount(double account) {
		this.account = account;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", loginId=" + loginId + ", nickname="
				+ nickname + ", password=" + password + ", account=" + account
				+ "]";
	}

	
}
