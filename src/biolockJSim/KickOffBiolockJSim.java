package biolockJSim;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// for example http://127.0.0.1:8080/ServletExamples/servlets/servlet/KickOffBiolockJSim
public class KickOffBiolockJSim extends HttpServlet
{

	AtomicInteger idCounter = new AtomicInteger(0);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		out.write("{jobID : " + idCounter.getAndIncrement() + "}\n");
		
		out.flush();  out.close();
	}
}
