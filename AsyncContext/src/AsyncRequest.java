

import java.io.PrintWriter;

import javax.servlet.AsyncContext;

public class AsyncRequest implements Runnable {
	private AsyncContext ctx;
	//构建时接受AsyncContext
	public AsyncRequest(AsyncContext ctx){
		this.ctx=ctx;
	}
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try{
			Thread.sleep(10000);//模拟冗长请求
			PrintWriter out=ctx.getResponse().getWriter();
			out.println("抱歉就等了~");
			ctx.complete();	
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
}
