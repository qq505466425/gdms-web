package com.oracle.gdms.web.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.oracle.gdms.entity.ResponseEntity;

@Path("/goods")
public class GoodsRest {
	
	@Path("/push")
	@POST
	public String pushGoods(String jsonstr) {
		
		System.out.println("hello world!	ppp==="+jsonstr);
		
		
		return "推送成功";
		
	}
	@Path("/test")
	@POST
	public String aaa(ResponseEntity r) {
		System.out.println("r.code"+r.getCode());
		System.out.println("r.msg"+r.getMsg());
		return "ok";
	}
}
