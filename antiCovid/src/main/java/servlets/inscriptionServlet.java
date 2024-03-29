package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		String url = request.getParameter("urlPhoto");
		if(url==null || url.equals("")) {
			url = "https://cdn-icons-png.flaticon.com/512/64/64572.png";
		}
		HttpSession session = request.getSession();
		SQLconnector sc = new SQLconnector();
        ResultSet res = sc.doRequest("SELECT * FROM user where login='"+login+"'");
		try {
			if(!res.next()) {
				if(password.equals(passwordConf)) {
					sc.createUser(login, password, nom, prenom, date, url);
					User user = sc.getUser(login,password);
					session.setAttribute("current_user", user);
					session.setAttribute("msg-err",null);
					response.sendRedirect("pagesJSP/main.jsp");
				}
				else {
					
					session.setAttribute("msg-err","Le mot de passe et la confirmation de mot de passe sont diff�rents.");
					session.setAttribute("current_user",null);
					response.sendRedirect("pagesJSP/inscription.jsp");
				}
			}
			else {
				session.setAttribute("msg-err","Ce login existe d�j�.");
				session.setAttribute("current_user",null);
				response.sendRedirect("pagesJSP/inscription.jsp");
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


}
