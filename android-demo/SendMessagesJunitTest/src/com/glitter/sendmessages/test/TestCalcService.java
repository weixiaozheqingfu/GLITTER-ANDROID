package com.glitter.sendmessages.test;

import junit.framework.Assert;
import android.test.AndroidTestCase;

import com.glitter.sendmessages.service.CalcService;

public class TestCalcService extends AndroidTestCase {
	
	/**
	 * add�����Ĳ��Է���
	 * ���쳣�׸����Կ��
	 * @throws Exception
	 */
	public void testAdd() throws Exception{
		CalcService calcService = new CalcService();
		int result = calcService.add(1, 2);
		Assert.assertEquals(3, result);
	}
}
