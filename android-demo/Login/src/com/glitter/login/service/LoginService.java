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
	 * 保存用户名密码
	 * 
	 * @param context 上下文对象
	 * @param userName 用户名
	 * @param password 密码
	 * @return boolean true:保存用户名密码成功;false：保存用户名密码失败。 
	 */
	@SuppressWarnings("static-access")
	public static boolean saveLoginInfo(Context context, String userName,String password,boolean isRemember,boolean automaticLogin) {

		boolean result = false;
		try {
			// 得到SharedPreferences实例对象用来声明对应的配置文件
			SharedPreferences sp = context.getSharedPreferences("login",context.MODE_PRIVATE);
			// 得到sp的编辑器
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
	 * 保存用户名密码
	 * 
	 * @param context 上下文对象
	 * @param userName 用户名
	 * @param password 密码
	 * @return boolean true:保存用户名密码成功;false：保存用户名密码失败。 
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
	 * 获取缓存中的用户名密码
	 * 
	 * @param context 上下文对象
	 * @return Map<String,String> key contains(userName:用户名;userPassword:密码)
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
