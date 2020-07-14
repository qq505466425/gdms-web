package com.oracle.gdms.service;

import com.oracle.gdms.entity.UserModel;
/**
 * 此类是用户相关功能接口
 * @author QQ,505466425
 *2020-7-6
 */
public interface UserService {
	/**
	 * 新用户注册业务
	 * @param user
	 * @return 
	 */
	int add(UserModel user);

	UserModel login(UserModel user);
	/**
	 * 检查手机号是否重复
	 * @param mobile
	 * @return 
	 */
	boolean hasmobile(String mobile);
}
