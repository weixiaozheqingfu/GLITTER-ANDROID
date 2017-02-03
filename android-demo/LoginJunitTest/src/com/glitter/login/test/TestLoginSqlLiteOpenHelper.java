package com.glitter.login.test;

import java.util.List;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.glitter.db.DbHelper;
import com.glitter.register.entity.User;
import com.glitter.register.service.RegisterService;
import com.glitter.register.service.impl.RegisterImplOne;
import com.glitter.register.service.impl.RegisterImplTwo;

public class TestLoginSqlLiteOpenHelper extends AndroidTestCase {
	
		public void testSqlLiteOperHelper(){
			DbHelper helper = new DbHelper(getContext());
			SQLiteDatabase dbWritable = helper.getWritableDatabase();
			System.out.println("当前数据库版本号:"+dbWritable.getVersion());
		}
	
		public void testRegisterAdd(){
			boolean result = false;
			
			User userOne = new User();
			userOne.setLoginId("zhangsan");
			userOne.setNickname("高山流水");
			userOne.setPassword("123456");
			userOne.setAccount(12000);
			
			User userTwo = new User();
			userTwo.setLoginId("lisi");
			userTwo.setNickname("微笑着轻拂");
			userTwo.setPassword("654321");
			userTwo.setAccount(15000);
			
//			RegisterService registerImplOne = new RegisterImplOne(getContext());
//			long resultOne = registerImplOne.registerAdd(userOne);
//			long resultTwo = registerImplOne.registerAdd(userTwo);
//			if(resultOne==1 && resultTwo == 1){
//				result = true;
//			}
			RegisterService registerImplTwo = new RegisterImplTwo(getContext());
			long resultOne = registerImplTwo.registerAdd(userOne);
			long resultTwo = registerImplTwo.registerAdd(userTwo);
			if(resultOne!=-1 && resultTwo!=-1){
				result = true;
			}
			AndroidTestCase.assertEquals(true,result);
		}
		public void testChangePassword(){
			
			User userOneChange = new User();
			userOneChange.setLoginId("zhangsan");
			userOneChange.setNickname("高山流水");
			userOneChange.setPassword("888888");
			userOneChange.setAccount(12000);
			
//			RegisterService registerImplOne = new RegisterImplOne(getContext());
//			long resultLong = registerImplOne.changePassword(userOneChange);
			RegisterService registerImplTwo = new RegisterImplTwo(getContext());
			long resultLong = registerImplTwo.changePassword(userOneChange);
			assertEquals(1,resultLong);

		}
		public void testDestroy(){
			User user = new User();
			user.setLoginId("wangwu");
//			RegisterService registerImplOne = new RegisterImplOne(getContext());
//			long resultLong = registerImplOne.destroy(user);
//			assertEquals(1,resultLong);
			RegisterService registerImplTwo = new RegisterImplTwo(getContext());
			long resultLong = registerImplTwo.destroy(user);
			boolean result = resultLong>0?true:false;
			AndroidTestCase.assertEquals(true, result);
			
		}
		
		public void testFindUserExistByLoginId(){
			User user = new User();
			user.setLoginId("lisi");
//			RegisterService registerImplOne = new RegisterImplOne(getContext());
//			boolean result =registerImplOne.findUserExistByLoginId(user.getLoginId());
//			AndroidTestCase.assertEquals(false, result);
			RegisterService registerImplTwo = new RegisterImplTwo(getContext());
			boolean result =registerImplTwo.findUserExistByLoginId(user.getLoginId());
			AndroidTestCase.assertEquals(true, result);
		}
		public void testFindUserByLoginId(){
			User userOne = new User();
			userOne.setLoginId("wangwu");
			userOne.setNickname("皓月清风");
			userOne.setPassword("123456");
			userOne.setAccount(12000);

			RegisterService registerImplTwo = new RegisterImplTwo(getContext());
			registerImplTwo.registerAdd(userOne);
			
			RegisterService registerImplOne = new RegisterImplOne(getContext());
			User userDb = registerImplOne.findUserByLoginId("wangwu");
//			User userDb = registerImplTwo.findUserByLoginId("wangwu");
			AndroidTestCase.assertEquals(true, userDb.getLoginId().equals("wangwu"));
			System.out.println(userDb.toString());
		}
		
		public void testFindAllUsers(){
			// 测试这个,就应该先增加一个,然后再查出来,写出预期
//			RegisterService registerImplOne = new RegisterImplOne(getContext());
//			List<User> userList = registerImplOne.findAllUsers();
			RegisterService registerImplTwo = new RegisterImplTwo(getContext());
			List<User> userList = registerImplTwo.findAllUsers();
			for(User user:userList){
				System.out.println(user.toString());
			}
		}
		// TODO 事物还没有写方法和测试用例
		
}
