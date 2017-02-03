package com.glitter.sendmessages.test;

import junit.framework.Assert;
import android.test.AndroidTestCase;

import com.glitter.sendmessages.service.CalcService;

public class TestCalcService extends AndroidTestCase {
	
	/**
	 * add方法的测试方法
	 * 把异常抛给测试框架
	 * @throws Exception
	 */
	public void testAdd() throws Exception{
		CalcService calcService = new CalcService();
		int result = calcService.add(1, 2);
		Assert.assertEquals(3, result);
	}
}
