package com.oracle.gdms.web.listener;

import java.net.URL;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.PropertyConfigurator;

@WebListener
public class AppListener implements ServletContextListener {


    public void contextDestroyed(ServletContextEvent arg0)  { 
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
    	URL url = AppListener.class.getClassLoader().getResource("config/log4j.properties");
    	//人工初始化log4j	告诉框架log4j在哪
    	PropertyConfigurator.configure(url);
    }
	
}
