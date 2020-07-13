package com.oracle.gdms.web.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.oracle.gdms.entity.GoodsModel;
import com.oracle.gdms.entity.ResponseEntity;
import com.oracle.gdms.service.GoodsService;
import com.oracle.gdms.service.impl.GoodsServiceImpl;

/**
 * Servlet implementation class GoodsPush
 */
@WebServlet("/admin/goods/push.php")
public class GoodsPush extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json;charset=UTF-8");// 响应的结果类型
		ResponseEntity ent = new ResponseEntity(); // 准备一个响应结果对象向客户端返回
		JSONObject json = new JSONObject(); // JSON对象
		PrintWriter out = response.getWriter(); // 向客户端输出的流对象

		String gid = request.getParameter("goodsid");// 从请求中得到要推送的商品ID
		if (gid == null) {
			ent.setCode(1001);
			ent.setMessage("商品ID缺失");
		} else {
			GoodsService service = new GoodsServiceImpl();
			int goodsid = Integer.parseInt(gid);
			String msg = service.pushGoods(goodsid);
			
		}

		json.put("data", ent);

		out.print(json.toJSONString());// 把json结果输出到客户端
	}
}
