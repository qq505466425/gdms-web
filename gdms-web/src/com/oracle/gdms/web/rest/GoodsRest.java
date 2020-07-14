package com.oracle.gdms.web.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.oracle.gdms.entity.Model;
import com.oracle.gdms.entity.ResponseEntity;
import com.oracle.gdms.service.GoodsService;
import com.oracle.gdms.service.impl.GoodsServiceImpl;

@Path("/goods")
public class GoodsRest {

	@Path("/push")
	@POST
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public ResponseEntity pushGoods(Model goods) {
		System.out.println("商品名=  "+goods.getGoods().getName());
		GoodsService service = new GoodsServiceImpl();
		int count = service.add(goods.getGoods());
		if (count >0) {
			System.out.println("插入成功");
		}
		ResponseEntity resp = new ResponseEntity();
		resp.setCode(0);
		resp.setMessage("这波啊,这波是肉蛋葱鸡");

		return resp;
	}
}
