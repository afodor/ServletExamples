package biolockJSim;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// for example
//http://127.0.0.1:8080/ServletExamples/servlets/servlet/GetStatusReport?JOB_ID=0
public class GetStatusReport extends HttpServlet
{
	public static final String JOB_ID = "JOB_ID";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		int id = Integer.parseInt(req.getParameter(JOB_ID));
		
		BiolockJSim bSim = BiolockJSim.runMap.get(id);
		
		PrintWriter out = resp.getWriter();
		out.write("{");
		
		for(int x=0; x < bSim.getNumNodes(); x++)
		{
			BiolockJSimNode bNode = bSim.getNode(x);
			
			out.write("{\n");
			
			out.write("nodeName : " + bNode.getName() + ", \n");

			out.write("job Name : " + bNode.getName() + ", \n");
			
			out.write("progress : " + bNode.getProgress() + " \n");
			
			out.write("}\n");
			
			if( x +1 < bSim.getNumNodes())
				out.write(",\n");
		}
		
		out.write("}\n");
		out.flush();  out.close();
	}
}
