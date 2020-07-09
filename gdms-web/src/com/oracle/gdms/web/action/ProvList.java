package com.oracle.gdms.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.oracle.gdms.entity.AreaModel;
import com.oracle.gdms.service.AreaService;
import com.oracle.gdms.service.impl.AreaServiceImpl;

/**
 * Servlet implementation class ProvList
 */
@WebServlet("/admin/area/provlist")
public class ProvList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//接收parentid 如果是0就不用
		String pid = request.getParameter("parentid");
		
		int id = Integer.parseInt(pid);
		
		//向客户端返回一个JSON对象
		response.setContentType("application/json;charset=UTF-8");
		
		AreaService areaservice = new AreaServiceImpl();
		
		List<AreaModel> list = areaservice.findAllProv(id);//查出所有省份的列表
		
		JSONObject j = new JSONObject();
		j.put("data", list);
		
		response.getWriter().print(j.toJSONString());//把json数据输出到客户端
		
	}

}
