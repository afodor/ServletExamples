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


	 public static String VALID_METHODS = "DELETE, HEAD, GET, OPTIONS, POST, PUT";
	AtomicInteger idCounter = new AtomicInteger(0);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		resp.setContentType("text/html");
		
		resp.addHeader("Access-Control-Allow-Origin", "*");
		
		int runID = idCounter.getAndIncrement() ;
		BiolockJSim bSim = new BiolockJSim(runID, 100);
		bSim.startUpdates();
		BiolockJSim.runMap.put(runID, bSim);
		
		PrintWriter out = resp.getWriter();
		
		out.write("{jobID : " + runID + "}\n");
		out.flush();  out.close();
	}
}
