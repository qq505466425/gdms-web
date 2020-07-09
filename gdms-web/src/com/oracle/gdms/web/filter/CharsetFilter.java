package com.oracle.gdms.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class CharsetFilter
 */
@WebFilter("/admin/*")
public class CharsetFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();//当前请求的目标资源
		System.out.println("当前请求"+uri);
		req.setCharacterEncoding("UTF-8");
		
		chain.doFilter(request, response);	//请求和响应放行,调用后台资源结束,就回到这一行,返回了.不是再执行一次dofilter方法
		//如果删了就被拦截了
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
