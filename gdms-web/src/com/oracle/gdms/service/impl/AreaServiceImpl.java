package com.oracle.gdms.service.impl;

import java.util.List;

import com.oracle.gdms.dao.AreaDao;
import com.oracle.gdms.entity.AreaModel;
import com.oracle.gdms.service.AreaService;
import com.oracle.gdms.util.GDMSUtil;

public class AreaServiceImpl extends BaseService implements AreaService {


	@Override
	public List<AreaModel> findAllProv(int pid) {
		try {
			// TODO 获取地区
			session = GDMSUtil.getSesstion();
			AreaDao dao = session.getMapper(AreaDao.class);
			AreaModel area = new AreaModel();
			area.setParentid(pid);
			return dao.findAllProv(area);
		} catch (Exception e) {// 假如程序出现异常
			e.printStackTrace();// 输出异常信息
			session.rollback();// 回滚事务
		} finally {// 在return之前一定要执行关闭连接
			free();
		}
		return null;
	}

	@Override
	public AreaModel findNameById(int areaid) {
		try {
			// TODO 获取地区
			session = GDMSUtil.getSesstion();
			AreaDao dao = session.getMapper(AreaDao.class);
			return dao.findNameById(areaid);
		} catch (Exception e) {// 假如程序出现异常
			e.printStackTrace();// 输出异常信息
			session.rollback();// 回滚事务
		} finally {// 在return之前一定要执行关闭连接
			free();
		}
		return null;
	}

}
