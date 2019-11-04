package azure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microsoft.aad.adal4j.AuthenticationContext;
import com.microsoft.aad.adal4j.AuthenticationResult;
import com.microsoft.aad.adal4j.ClientCredential;



@WebServlet(description = "this is a demo", urlPatterns = { "/token_servlet" })
public class token_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public  String token=getAccessToken("5b7637fb-c8fc-4380-9101-1cce8924f18a","cfe42308-6f2c-401d-ab95-089f1fd6a19f","etgfA4TS=WbJ4hu3wKqzQWcK_mZ=Bs-6");

       
    /**
     * @see HttpServlet#HttpServlet()
     */
	


    public token_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("token :");
		System.out.println(token);
		System.out.println("response json :");
		String subscriptlist;
		try {
			subscriptlist = send_get("https://management.chinacloudapi.cn/subscriptions/2f5b7a0b-c0e6-4fbd-9131-f18dcc533fd7"
					+ "/providers/Microsoft.Compute/virtualMachines?api-version=2019-03-01");
			System.out.println(subscriptlist);
			response.getWriter().write(subscriptlist);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private String send_get(String rest_url) throws Exception {
		URL url = new URL(rest_url);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestProperty("Authorization", "Bearer "+token);
		conn.setRequestProperty("Content-Type","application/json");
		conn.connect();
		InputStream input = conn.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		
		String reString="";
		String  result="";
		while ((reString=reader.readLine())!=null) {
			result=reString;
			
		}
		return result;
	}

	
	
	
	private String getAccessToken(String zuhuid,String appid,String password) {
	    AuthenticationContext context = null;
	    AuthenticationResult result = null;
	    ExecutorService service = null;
	    service = Executors.newFixedThreadPool(1);

	    try {
	        Object tentantId;
			context = new AuthenticationContext(
	            String.format("%s/%s", 
	            "https://login.chinacloudapi.cn", zuhuid),
	            true, 
	            service);
	        String clientSecret;
			String clientId;
			ClientCredential cred = new ClientCredential(
	            appid,password);
	        java.util.concurrent.Future<AuthenticationResult> future =   
	            context.acquireToken(
			    "https://management.chinacloudapi.cn/", cred,
			    null);
	        result = future.get();
	    } catch (MalformedURLException e) {
	        e.printStackTrace();
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    } catch (ExecutionException e) {
	        e.printStackTrace();
	    } finally {
	        service.shutdown();
	    }
	    return result.getAccessToken();
	}

}
