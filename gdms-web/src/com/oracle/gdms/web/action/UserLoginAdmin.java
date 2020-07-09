package com.oracle.gdms.web.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oracle.gdms.entity.UserModel;
import com.oracle.gdms.service.UserService;
import com.oracle.gdms.service.impl.AreaServiceImpl;
import com.oracle.gdms.service.impl.UserServiceImpl;
import com.oracle.gdms.util.GDMSUtil;

@WebServlet("/admin/user/login.action")
public class UserLoginAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 将所有提交过来的参数，接收以后封装成一个UserModel实体
		String account = request.getParameter("name");
		String password = request.getParameter("password");
		String code = request.getParameter("code");
		// 拿到服务器的session中正确的验证码
		HttpSession session = request.getSession();// 先获取当前会话
		// 强制转型一般在父类和子类中
		String yzm = (String) session.getAttribute("code");// 从会话中获取正确的验证码
		//用过的验证码无效,销毁掉
		session.removeAttribute("code");
		
		// 忽略大小写进行比较
		if (yzm == null || !yzm.equalsIgnoreCase(code)) {
			request.setAttribute("login_msg", "验证码无效");
			// 执行转发
			request.getRequestDispatcher("../../index.jsp").forward(request, response);
			return;
		}

//		 System.out.println(account);
//		 System.out.println(password);
		 
		UserModel user = new UserModel();
		user.setAccount(account);
		try {
			user.setLogpwd(GDMSUtil.getMD5(password.getBytes()));
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// 传给业务逻辑层的对象

		UserService userservice = new UserServiceImpl();
		//调用登录方法,返回一个user对象
		UserModel loginUser = userservice.login(user);
		
		if (loginUser !=null) {
			//登录成功就把用户对象存进会话
			session.setAttribute("loginUser", loginUser);
			//重定向页面到admit/main.jsp
			response.sendRedirect("../main.jsp");
			
			
		}else {
			request.setAttribute("login_msg", "用户名或密码无效");
			// 执行转发
			request.getRequestDispatcher("../../index.jsp").forward(request, response);
		}
//		int count = userservice.add(user);
//		System.out.println(count > 0 ? "ok" : "no");

	}
}
