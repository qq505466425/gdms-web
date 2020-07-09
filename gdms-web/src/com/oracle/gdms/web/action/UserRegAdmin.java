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

@WebServlet("/admin/user/reg.action")
public class UserRegAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 将所有提交过来的参数，接收以后封装成一个UserModel实体
		String account = request.getParameter("name");
		String password = request.getParameter("password");
		String realname = request.getParameter("realname");
		String idnumber = request.getParameter("Idnumber");
		String photo = request.getParameter("slogo");
		String place = request.getParameter("prov");
		String city = request.getParameter("city");
		int pid = Integer.parseInt(place);
		String pname = new AreaServiceImpl().findNameById(pid).getName();// 查询出省份名称
		int cid = Integer.parseInt(city);
		String cname = new AreaServiceImpl().findNameById(cid).getName();// 查询出城区名称

		place = pname + "-" + cname;// 拼接

		String code = request.getParameter("code");
		// 拿到服务器的session中正确的验证码
		HttpSession session = request.getSession();// 先获取当前会话
		// 强制转型一般在父类和子类中
		String yzm = (String) session.getAttribute("code");// 从会话中获取正确的验证码
		// 用过的验证码无效,销毁掉
		session.removeAttribute("code");
		// 忽略大小写进行比较
		if (yzm == null || !yzm.equalsIgnoreCase(code)) {
			request.setAttribute("err_msg", "验证码无效");
			// 执行转发
			request.getRequestDispatcher("../../index.jsp").forward(request, response);
			return;
		}

		// System.out.println(account);
		// System.out.println(logpwd);
		// System.out.println(realname);
		// System.out.println(idnumber);
		// System.out.println(place);
		// System.out.println(photo);
		// System.out.println(code);
		UserModel user = new UserModel();
		user.setAccount(account);
		try {
			user.setLogpwd(GDMSUtil.getMD5(password.getBytes()));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		user.setIdnumber(idnumber);
		user.setPhoto(photo);
		user.setPlace(place);
		user.setRealname(realname);

		// 取出性别

		char c = idnumber.charAt(idnumber.length() - 2);// 取出倒数第二位数
		int i = Integer.parseInt(String.valueOf(c));
		String sex = i % 2 == 0 ? "女" : "男";
		user.setRowflag("oraU" + GDMSUtil.generic(20));
		user.setRoleid(2);
		user.setSex(sex);
		// 取出生日
		String s = idnumber.substring(6, 14);// 取子串包含6不包含14
		// 把字符串类型的s转为Timestamp类型的birthday
		// SimpleDateFormat sdf = new SimpleDateFormat();// 初始化格式化类
		try {
			int yy = Integer.parseInt(s.substring(0, 4));// 取得年
			int mm = Integer.parseInt(s.substring(4, 6));// 取得月
			int dd = Integer.parseInt(s.substring(6));// 取得日
			Date d = new Date(yy - 1900, mm - 1, dd);// 解析字符串为date类型
			Timestamp birthday = new Timestamp(d.getTime());
			Timestamp regtime = GDMSUtil.now();
			user.setBirthday(birthday);
			user.setRegtime(regtime);
			user.setLastlogin(regtime);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		user.setStatus(false);

		// 传给业务逻辑层的对象
		UserService userservice = new UserServiceImpl();
		int count = userservice.add(user);
		if (count > 0) {
			request.setAttribute("reg_msg", "注册成功,请登录");
			// 执行转发
		} else {
			request.setAttribute("err_msg", "注册失败,请大侠重新来过");
		}
		request.getRequestDispatcher("../../index.jsp").forward(request, response);
	}
}
