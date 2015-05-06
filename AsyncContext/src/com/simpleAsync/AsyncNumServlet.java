package com.simpleAsync;

import java.io.IOException;
import java.util.List;

import javax.servlet.AsyncContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 负责接收请求的servlet，将接收的请求加入List中
 * Servlet implementation class AsyncNumServlet
 */
@WebServlet(urlPatterns={"/asyncNum.do"},
						asyncSupported=true)
public class AsyncNumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private List<AsyncContext> asyncs; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AsyncNumServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	@SuppressWarnings("unchecked")
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		//取得存储AsyncContext的List
		asyncs=(List<AsyncContext>)getServletContext().getAttribute("asyncs");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AsyncContext ctx=request.startAsync();//开始异步处理
		synchronized (asyncs) {
			asyncs.add(ctx);//加入维护AsyncContext的队列中。
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
