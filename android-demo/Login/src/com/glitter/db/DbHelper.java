package com.glitter.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
	private static String dbName;
	private static CursorFactory cursorFactory;
	private static int dbVersion;
	
	static{
		// TODO Ӧ�ô������ļ���ȡ
		dbName = "glitter.db";
		cursorFactory = null;
		dbVersion = 1;
	}

	// ���Ƽ�ʹ��
	public DbHelper(Context context) {		
		super(context,dbName, cursorFactory, dbVersion);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		System.out.println("���ݿ�ִ��onCreate����......");
		db.execSQL(
				   "create table sys_user(" +
					   "id integer primary key autoincrement," +
				       "login_id varchar2(20)," +
				       "nickname nvarchar2(20)," +
				       "password varchar2(20)," +
				       "account double" +
			       ");"
			       );
//		db.execSQL(
//			       "create table sys_account(" +
//					   "user_id integer primary key autoincrement," +
//				       "login_id varchar2(20)," +
//				       "password varchar2(20)" +
//			       ");"
//			       );
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		System.out.println("���ݿ�ִ��onUpgrade����......");
	}

}
