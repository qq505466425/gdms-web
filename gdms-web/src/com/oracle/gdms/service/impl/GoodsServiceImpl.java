package com.oracle.gdms.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.oracle.gdms.dao.GoodsDao;
import com.oracle.gdms.entity.GoodsModel;
import com.oracle.gdms.entity.PageModel;
import com.oracle.gdms.service.GoodsService;
import com.oracle.gdms.util.GDMSUtil;

public class GoodsServiceImpl extends BaseService implements GoodsService {

	@Override
	public PageModel<GoodsModel> findByPage(int pageNumber, int rows) {
		PageModel<GoodsModel> obj = new PageModel<GoodsModel>();
		obj.setCurrent(pageNumber);
		try {
			session = GDMSUtil.getSesstion();
			GoodsDao dao = session.getMapper(GoodsDao.class);
			// 查询总记录行数
			int count = dao.findCount();
			// 计算总页数
			int total = count % rows == 0 ? count / rows : count / rows + 1;
			obj.setTotal(total);
			// 计算起始位置
			int offset = (pageNumber - 1) * rows;
			Map<String,Integer> map = new HashMap<>();
			map.put("offset", offset);
			map.put("rows", rows);
			obj.setData(dao.findByPage(map));// 查数据集

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			free();
		}

		return obj;
	}
	
	public static void main(String[] args) {
		GoodsService ser = new GoodsServiceImpl();
		PageModel<GoodsModel> p = ser.findByPage(1, 2);
		System.out.println(p.getTotal());
		for (GoodsModel m : p.getData()) {
			System.out.println("id="+m.getGoodsid()+"	name="+m.getName());
		}
	
	}
}
