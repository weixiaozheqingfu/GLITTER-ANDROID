package com.glitter.register.service;

import java.util.List;

import com.glitter.register.entity.User;

public interface RegisterService {

	/**
	 * ע�����û�����
	 * @param user �û�ʵ�����
	 * @return
	 */
	public long registerAdd(User user);
	
	/**
	 * �޸�����
	 * @param user �û�ʵ�����
	 * @return
	 */
	public long changePassword(User user);
	
	/**
	 * ע���û�
	 * @param user �û�ʵ�����
	 * @return
	 */
	public long destroy(User user);
	
	/**
	 * ���ݵ�¼���Ʋ����û��Ƿ����
	 * @param loginId
	 * @return
	 */
	public boolean findUserExistByLoginId(String loginId);
	
	/**
	 * ���ݵ�¼���Ʋ����û�
	 * @param loginId
	 * @return
	 */
	public User findUserByLoginId(String loginId);
	
	/**
	 * ��ȡ���е�¼�û��б�
	 * @return
	 */
	public List<User> findAllUsers();
	
}

