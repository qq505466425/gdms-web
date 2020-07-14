package com.oracle.gdms.service.impl;

import com.oracle.gdms.dao.UserDao;
import com.oracle.gdms.entity.UserModel;
import com.oracle.gdms.service.UserService;
import com.oracle.gdms.util.GDMSUtil;

public class UserServiceImpl extends BaseService implements UserService {
	private UserDao userdao;

	@Override
	public int add(UserModel user) {
		try {
			// TODO 增加一个新用户的业务
			session = GDMSUtil.getSesstion();
			userdao = session.getMapper(UserDao.class);
			int c = userdao.add(user);
			session.commit();
			return c;
		} catch (Exception e) {// 假如程序出现异常
			e.printStackTrace();// 输出异常信息
			session.rollback();// 回滚事务
		} finally {// 在return之前一定要执行关闭连接
			free();
		}
		return 0;
	}

	@Override
	public UserModel login(UserModel user) {
		try {
			// TODO 增加一个新用户的业务
			session = GDMSUtil.getSesstion();
			userdao = session.getMapper(UserDao.class);
			return userdao.login(user);
		} catch (Exception e) {// 假如程序出现异常
			e.printStackTrace();// 输出异常信息
			session.rollback();// 回滚事务
		} finally {// 在return之前一定要执行关闭连接
			free();
		}
		return null;
	}

	@Override
	public boolean hasmobile(String mobile) {
		try {
			// TODO 增加一个新用户的业务
			session = GDMSUtil.getSesstion();
			userdao = session.getMapper(UserDao.class);
			int c = userdao.hasMobile(mobile);
			return c > 0;
		} catch (Exception e) {// 假如程序出现异常
			e.printStackTrace();// 输出异常信息
			session.rollback();// 回滚事务
		} finally {// 在return之前一定要执行关闭连接
			free();
		}
		return false;
	}

}
