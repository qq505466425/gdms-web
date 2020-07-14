package com.oracle.gdms.service;

import com.oracle.gdms.entity.GoodsEntity;
import com.oracle.gdms.entity.GoodsModel;
import com.oracle.gdms.entity.PageModel;

public interface GoodsService {
	/**
	 * 分页显示商品数据
	 * @param pageNumber	当前页码
	 * @param rows			每页记录个数
	 * @return
	 */
	PageModel<GoodsModel> findByPage(int pageNumber,int rows);
	/**
	 * 推送指定ID的商品
	 * @param goodsid
	 * @return	成功消息为空字符串,失败为消息内容
	 */
	String pushGoods(int goodsid);
	/**
	 * 增加一条商品记录
	 * @param goods
	 * @return 受影响行数
	 */
	int add(GoodsEntity goods);
						
}
