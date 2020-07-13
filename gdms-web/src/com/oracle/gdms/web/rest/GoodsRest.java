package com.oracle.gdms.web.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.oracle.gdms.entity.Model;
import com.oracle.gdms.entity.ResponseEntity;

@Path("/goods")
public class GoodsRest {

	@Path("/push")
	@POST
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public ResponseEntity pushGoods(Model goods) {
		System.out.println(goods.getGoods().getName());
		ResponseEntity resp = new ResponseEntity();
		resp.setCode(9527);
		resp.setMessage("别推了,推锤子呢");

		return resp;
	}
}
