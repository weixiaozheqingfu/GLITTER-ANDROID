package com.glitter.register.service;

import java.util.List;

import com.glitter.register.entity.User;

public interface RegisterService {

	/**
	 * 注册新用户方法
	 * @param user 用户实体对象
	 * @return
	 */
	public long registerAdd(User user);
	
	/**
	 * 修改密码
	 * @param user 用户实体对象
	 * @return
	 */
	public long changePassword(User user);
	
	/**
	 * 注销用户
	 * @param user 用户实体对象
	 * @return
	 */
	public long destroy(User user);
	
	/**
	 * 根据登录名称查找用户是否存在
	 * @param loginId
	 * @return
	 */
	public boolean findUserExistByLoginId(String loginId);
	
	/**
	 * 根据登录名称查找用户
	 * @param loginId
	 * @return
	 */
	public User findUserByLoginId(String loginId);
	
	/**
	 * 获取所有登录用户列表
	 * @return
	 */
	public List<User> findAllUsers();
	
}

