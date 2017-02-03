package com.glitter.register.service.impl;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.glitter.db.DbHelper;
import com.glitter.register.entity.User;
import com.glitter.register.service.RegisterService;

public class RegisterImplTwo implements RegisterService {
	private SQLiteDatabase dbWritable;
	private SQLiteDatabase dbReadable;
	
	public RegisterImplTwo(Context context){
		DbHelper dbHelper = new DbHelper(context);
		dbWritable = dbHelper.getWritableDatabase();
		dbReadable = dbHelper.getReadableDatabase();
	}
	
	@Override
	public long registerAdd(User user) {
		long result = 0;
		ContentValues contentValues = new ContentValues();
		contentValues.put("login_id", user.getLoginId());
		contentValues.put("nickname", user.getNickname());
		contentValues.put("password", user.getPassword());
		try {
			result = dbWritable.insert("sys_user", null, contentValues);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public long changePassword(User user) {
		int result =0;
		ContentValues contentValues = new ContentValues();
		contentValues.put("password", user.getPassword());
		try {
			result = dbWritable.update("sys_user", contentValues, "where login_id=?", new String[]{user.getLoginId()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public long destroy(User user) {
		int result =0;
		try {
			result = dbWritable.delete("sys_user", "login_id=?", new String[]{user.getLoginId()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean findUserExistByLoginId(String loginId) {
		boolean result = false;
		Cursor cursor = null;
		try {
			cursor = dbReadable.query("sys_user", new String[]{"id","login_id","nickname","password","account"}, "login_id=?", new String[]{loginId}, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		result = cursor.moveToNext();
		return result;
	}
	
	@Override
	public User findUserByLoginId(String loginId) {
		User user = null;
		Cursor cursor = null;
		try {
			cursor = dbReadable.query("sys_user", new String[]{"id","login_id","nickname","password","account"}, "login_id=?", new String[]{loginId}, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(cursor.moveToNext()){
			user = new User();
			user.setId(cursor.getInt(cursor.getColumnIndex("id")));
			user.setLoginId(cursor.getString(cursor.getColumnIndex("login_id")));
			user.setNickname(cursor.getString(cursor.getColumnIndex("nickname")));
			user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
			user.setAccount(cursor.getDouble(cursor.getColumnIndex("account")));
		}
		return user;
	}

	@Override
	public List<User> findAllUsers() {
		List<User> userList = new ArrayList<User>();
		User user = null;
		Cursor cursor = null;
		try {
			cursor = dbReadable.query("sys_user", new String[]{"id","login_id","nickname","password","account"}, null, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		while(cursor.moveToNext()){
			user = new User();
			user.setId(cursor.getInt(cursor.getColumnIndex("id")));
			user.setLoginId(cursor.getString(cursor.getColumnIndex("login_id")));
			user.setNickname(cursor.getString(cursor.getColumnIndex("nickname")));
			user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
			user.setAccount(cursor.getDouble(cursor.getColumnIndex("account")));
			userList.add(user);
		}
		return userList;
	}
	

}
