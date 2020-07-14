package com.oracle.gdms.web.listener;

import java.net.URL;
import java.util.ResourceBundle;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.PropertyConfigurator;

@WebListener
public class AppListener implements ServletContextListener {

	private static ResourceBundle rb;	//资源绑定的对象
	
	public static String getString(String key) {//为私有的属性提供一个公共的方法访问
		return rb.getString(key);
	}

    public void contextDestroyed(ServletContextEvent arg0)  { 
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
    	
    	//读取一下application.properties文件
    	
    	rb= ResourceBundle.getBundle("config/application");//将application.properties与rb绑定

    	URL url = AppListener.class.getClassLoader().getResource(rb.getString("log4jpath"));
    	//人工初始化log4j	告诉框架log4j在哪
    	PropertyConfigurator.configure(url);
    	
    	//下面将应用程序需要的参数,全放进全局环境中
    	String  href = "http://"+rb.getString("host")+":"+
    	rb.getString("port")+"/"+rb.getString("context")+"/";
    	
    	arg0.getServletContext().setAttribute("href", href);//在全局内存环境中绑定一个对象
    	
    	
    }
	
}
