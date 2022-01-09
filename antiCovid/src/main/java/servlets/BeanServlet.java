package servlets;

import user.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BeanServlet
 */
@WebServlet("/BeanServlet")
public class BeanServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BeanServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");	
		
		HttpSession session = request.getSession();
		
		User current_user = (User) session.getAttribute("current_user");
		
		if(current_user == null) {
			
			request.getRequestDispatcher( "/pagesJSP/main.jsp" ).forward( request, response );
		}
		else{
			if(current_user.getRang().trim().equals("basic_user")) {
				request.getRequestDispatcher( "/pagesJSP/logged.jsp" ).forward( request, response );
			}
			else {
				if(current_user.getRang().trim().equals("admin")) {
					request.getRequestDispatcher( "/pagesJSP/admin.jsp" ).forward( request, response );
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


}
