

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AsyncServlet
 */
//告诉容器，此servlet支持异步处理
@WebServlet(name="AsyncServlet",urlPatterns={"/async.do"},
						asyncSupported=true)
public class AsyncServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ExecutorService executorService=Executors.newFixedThreadPool(10);//返回一个线程池
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AsyncServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html,charset=utf-8");
		//开始异步处理，释放请求线程
		AsyncContext ctx=request.startAsync();//对于每个请求，servlet都会获得其AsyncContext
		//创建AsyncRequest，调度线程
		//让必须长时间处理的请求，在这些有限线程中完成，而不用每次请求都占用容器分配的
		//线程
		executorService.submit(new AsyncRequest(ctx));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	public void destory(){
		executorService.shutdown();//关闭线程池
	}
}
