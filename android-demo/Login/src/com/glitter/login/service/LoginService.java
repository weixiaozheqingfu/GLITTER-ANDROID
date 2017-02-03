package com.glitter.login.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

public class LoginService {

	private static BufferedReader br;
	
	/**
	 * �����û�������
	 * 
	 * @param context �����Ķ���
	 * @param userName �û���
	 * @param password ����
	 * @return boolean true:�����û�������ɹ�;false�������û�������ʧ�ܡ� 
	 */
	@SuppressWarnings("static-access")
	public static boolean saveLoginInfo(Context context, String userName,String password,boolean isRemember,boolean automaticLogin) {

		boolean result = false;
		try {
			// �õ�SharedPreferencesʵ����������������Ӧ�������ļ�
			SharedPreferences sp = context.getSharedPreferences("login",context.MODE_PRIVATE);
			// �õ�sp�ı༭��
			Editor editor = sp.edit();
			editor.putString("userName",userName);
			editor.putString("password",password);
			editor.putBoolean("isRemember",isRemember);
			editor.putBoolean("automaticLogin",automaticLogin);
			editor.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * �����û�������
	 * 
	 * @param context �����Ķ���
	 * @param userName �û���
	 * @param password ����
	 * @return boolean true:�����û�������ɹ�;false�������û�������ʧ�ܡ� 
	 */
	@Deprecated 
	public static boolean saveUserInfo(Context context, String userName,
			String password) {

		boolean result = false;
		try {
			File file = new File(context.getCacheDir(), "login.txt");
			FileOutputStream fos = new FileOutputStream(file);
			fos.write((userName + "##" + password).getBytes());
			fos.close();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * ��ȡ�����е��û�������
	 * 
	 * @param context �����Ķ���
	 * @return Map<String,String> key contains(userName:�û���;userPassword:����)
	 */
	@Deprecated 
	public static Map<String, String> getUserInfo(Context context) {
		Map<String, String> userInfoMap = null;
		File file = new File(context.getCacheDir(), "login.txt");
		try {
			FileInputStream fis = new FileInputStream(file);
			br = new BufferedReader(new InputStreamReader(fis));
			String userInfoStr = br.readLine();
			if(!TextUtils.isEmpty(userInfoStr)){
				String[] userInfoArray = userInfoStr.split("##");
				if(userInfoArray!=null && userInfoArray.length==2){
					userInfoMap = new HashMap<String,String>();
					userInfoMap.put("userName", userInfoArray[0]);
					userInfoMap.put("userPassword", userInfoArray[1]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userInfoMap;
	}
}
