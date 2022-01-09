package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import SQL.SQLconnector;
import user.User;

/**
 * Servlet implementation class inscriptionServlet
 */
@WebServlet("/inscriptionServlet")
public class inscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public inscriptionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("BeanServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String passwordConf = request.getParameter("passwordConf");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String date = request.getParameter("birth");
		
		HttpSession session = request.getSession();
		SQLconnector sc = new SQLconnector();
		
		if(password.equals(passwordConf)) {
			
			sc.createUser(login, password, nom, prenom, date);
			User user = sc.getUser(login,password);
			session.setAttribute("current_user", user);
			response.sendRedirect("/pagesJSP/main.jsp");
		}
		else {
			
			session.setAttribute("msg-err"," Ce login est déjà utilisé.");
			
			session.setAttribute("current_user",null);
			response.sendRedirect("error.jsp");
		}
		
		
	}


}
