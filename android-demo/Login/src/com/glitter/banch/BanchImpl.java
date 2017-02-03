package com.glitter.banch;

import android.content.Context;
import android.content.Intent;

public class BanchImpl implements Banch {

	@Override
	public void jumpActivity(Context currentContext, Class<?> targetClass) {
		Intent intent = new Intent(currentContext,targetClass);
		currentContext.startActivity(intent);
	}
	
}
