package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import SQL.SQLconnector;
import user.User;

/**
 * Servlet implementation class connexionServlet
 */
@WebServlet("/connexionServlet")
public class connexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public connexionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String login = request.getParameter("login");
        String password = request.getParameter("password");
        SQLconnector sc = new SQLconnector();
        User user = sc.getUser(login, password);
        HttpSession session = request.getSession();
        
        if((user.getLogin()!=null) && (user.getPassword()!=null)) {
        	session.setAttribute("current_user", user);
			response.sendRedirect("pagesJSP/main.jsp");
        }
        else {
        	session.setAttribute("msg-err"," Login et/ou mot de passe incorrect/s");
			
			session.setAttribute("current_user",null);
			response.sendRedirect("error.jsp");
        }
        
	}

}
