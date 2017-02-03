package com.glitter.sendmessages.service;

import android.util.Log;

public class CalcService {

	private String tag = "CalcService";
	
	public int add(int x,int y){
		
		Log.v(tag,"x="+x);
		Log.i(tag,"y="+y);
		int result = x + y;
		Log.w(tag,"result="+result);
		Log.e(tag,"result="+result);
		
		return x + y;
	}
	
}
