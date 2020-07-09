package com.oracle.gdms.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSONObject;
import com.oracle.gdms.entity.ResponseEntity;
import com.oracle.gdms.util.GDMSUtil;


@WebServlet("/admin/user")
public class UserAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FileItemFactory fileItemFactory = new DiskFileItemFactory();
		// TODO Auto-generated method stub
		// 解析请求中传过来的附件对象
		ServletFileUpload sfu = new ServletFileUpload(fileItemFactory);

		sfu.setFileSizeMax(1024000);// 制定文件最大尺寸为1mb
		// 制定保存路径
		String path = this.getServletContext().getRealPath("/images");// 取得当前程序下images的绝对路径
		path += "/upload";
//		System.out.println("path=" + path);

		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();// 如果这个文件夹不存在，就创建它
		}
		try {
			// 开始解析附件
			List<DiskFileItem> list = sfu.parseRequest(request);
			for (DiskFileItem item : list) {
				if (!item.isFormField()) {// 如果item对象不是表单项，就把它当文件处理
					String fileName = GDMSUtil.generic(24);// 生成一个随机的24位的文件名
					int i = item.getName().lastIndexOf(".");// 找到文件名中过最后一个.的位置
					fileName += item.getName().substring(i);// 从这个位置开始，截取后面的内容作为新的文件的后缀名

					File file = new File(path + "/" + fileName);// 生成新文件对象
					
					item.write(file);	//把源文件内容写入到新文件中
					
					ResponseEntity entity = new ResponseEntity();
					entity.setCode(0);
					entity.setMsg(fileName);
					entity.setData("<img width='200' height='200' src='images/upload/"+ fileName+"'>");
					JSONObject j = new JSONObject();
					j.put("entity", entity);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().print(j.toJSONString());	//把上传的结果以一个JSON对象返回
				}
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print("上传失败");	//把上传成功的新文件名返回
		}
	
	}
//	public static void main(String[] args) {
//		//构造一个JSON对象
//		JSONObject json = new JSONObject();
//		json.put("name", "促恒");
//		json.put("sex", "男");
//		String str = json.toJSONString();
//		System.out.println(str);
//		
//	}
}
