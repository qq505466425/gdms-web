package com.oracle.gdms.service;

import com.oracle.gdms.entity.GoodsModel;
import com.oracle.gdms.entity.PageModel;

public interface GoodsService {
	/**
	 * 
	 * @param pageNumber	当前页码
	 * @param rows			每页记录
	 * @return
	 */
	PageModel<GoodsModel> findByPage(int pageNumber,int rows);

}
