package com.lzcc.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.lzcc.util.DbHelper;

/**
 * 服务器的监听器类
 * 
 *
 */
public class ApplicationListener implements ServletContextListener{

	// 服务停止时被调用
	@Override
	public void contextDestroyed(ServletContextEvent contextEvent) {
		System.out.println("服务器停止了...");
		// 关闭连接对象
		DbHelper.closeResource();
	}

	// 服务启动时被调用
	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
		System.out.println("服务器启动了...");
		// 初始化连接对象
		DbHelper.initConnection();
	}
}
