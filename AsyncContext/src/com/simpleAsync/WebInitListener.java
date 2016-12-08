package com.simpleAsync;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.AsyncContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 模拟应用程序不定期产生最新数据
 * Application Lifecycle Listener implementation class WebInitListener
 *
 */
@WebListener
public class WebInitListener implements ServletContextListener {
	//将所有异步请求的AsyncContext存储在list中
	private List<AsyncContext> asyncs=new ArrayList<AsyncContext>();
    /**
     * Default constructor. 
     */
    public WebInitListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce) {
        // TODO Auto-generated method stub
    	sce.getServletContext().setAttribute("asyncs", asyncs);
    	new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO 自动生成的方法存根
				while(true){
					try{
						//模拟不定时随机产生数字后，逐一对客户端进行响应。
						Thread.sleep((int)(Math.random())*10000);
						double num=Math.random()*10;
						synchronized (asyncs) {
							for(AsyncContext ctx : asyncs){
								ctx.getResponse().getWriter().println(num);
								ctx.complete();//完成请求
							}
							asyncs.clear();//将列表中的元素清空
						}
					}catch(Exception e){
						throw new RuntimeException(e);
					}
				}
			}
		} ).start();
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce) {
        // TODO Auto-generated method stub
    }
	
}
