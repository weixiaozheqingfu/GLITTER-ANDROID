package com.glitter.register.service.impl;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.glitter.db.DbHelper;
import com.glitter.register.entity.User;
import com.glitter.register.service.RegisterService;

public class RegisterImplOne implements RegisterService {
	private SQLiteDatabase dbWritable;
	private SQLiteDatabase dbReadable;
	
	public RegisterImplOne(Context context){
		DbHelper dbHelper = new DbHelper(context);
		dbWritable = dbHelper.getWritableDatabase();
		dbReadable = dbHelper.getReadableDatabase();
	}
	
	@Override
	public long registerAdd(User user) {
		int result = 0;
		try{
			dbWritable.execSQL("insert into sys_user(login_id,nickname,password) values(?,?,?)",
			           new Object[]{user.getLoginId(),user.getNickname(),user.getPassword()});	
			result = 1;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public long changePassword(User user) {
		int result = 0;
		try {
			dbWritable.execSQL("update sys_user set password=? where login_id=?", new Object[]{user.getPassword(),user.getLoginId()});
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public long destroy(User user) {
		int result =0;
		try {
			dbWritable.execSQL("delete from sys_user where login_id = ?", new Object[]{user.getLoginId()});
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public boolean findUserExistByLoginId(String loginId) {
		boolean result = false;
		Cursor cursor=dbReadable.rawQuery("select id,login_id,nickname,password,account from sys_user where login_id=?",new String[]{loginId});
		result = cursor.moveToNext();
		return result;
	}

	@Override
	public User findUserByLoginId(String loginId) {
		User user = null;
		Cursor cursor=dbReadable.rawQuery("select id,login_id,nickname,password,account from sys_user where login_id=?",new String[]{loginId});
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
		Cursor cursor=dbReadable.rawQuery("select id,login_id,nickname,password,account from sys_user ", null);
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
