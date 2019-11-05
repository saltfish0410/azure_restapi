package azure;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gargoylesoftware.htmlunit.javascript.host.URLSearchParams;

/**
 * Servlet implementation class testservlet
 */
@WebServlet(description = "test header", urlPatterns = { "/testservlet" })
public class testservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public testservlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    	
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	System.out.println("testservlet");
    	System.out.println("----------------------------------------------------------------------------------------------");
    	System.out.println("----------------------------------------------------------------------------------------------");
    	Enumeration e = req.getHeaderNames();
		while(e.hasMoreElements()){
			String name=(String) e.nextElement();
			String value2=req.getHeader(name);
			System.out.println(name+":"+value2);
			

		}
		System.out.println("----------------------------------------------------------------------------------------------");

		BufferedReader s1=req.getReader();
	    String str, wholeStr = "";
	    while((str = s1.readLine()) != null){
	        wholeStr += str;
	}
	System.out.println(wholeStr);
	System.out.println("----------------------------------------------------------------------------------------------");
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
