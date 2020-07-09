package com.oracle.gdms.service.impl;

import org.apache.ibatis.session.SqlSession;
/**
 * 会话
 * @author ll
 *
 */
public abstract class BaseService {
	
	protected SqlSession session;
	
	protected void free() {
		if (session != null) {
			session.close();// 关闭连接
		}
	}
}
