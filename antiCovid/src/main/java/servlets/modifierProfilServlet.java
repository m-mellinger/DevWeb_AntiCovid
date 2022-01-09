package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import SQL.SQLconnector;
import user.User;

/**
 * Servlet implementation class modifierProfilServlet
 */
@WebServlet("/modifierProfilServlet")
public class modifierProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modifierProfilServlet() {
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
		String mdpActuel = request.getParameter("mdpActuel");
		String url = request.getParameter("urlPhoto");
		System.out.println(url);
		if(url==null || url.equals("")) {
			url = "https://cdn-icons-png.flaticon.com/512/64/64572.png";
		}
		User user = (User) request.getSession().getAttribute("current_user");
		
		HttpSession session = request.getSession();
		SQLconnector sc = new SQLconnector();
		
		if(mdpActuel.equals(user.getPassword())) {
			if(password.equals(passwordConf)) {
				ResultSet res = sc.doRequest("SELECT * FROM user where login='"+login+"'");
				try {
					if(!res.next() || user.getLogin().equals(login)) {
						sc.uptadeUser(user.getId(), login, password, nom, prenom, date, url);
						User nUser = sc.getUser(login,password);
						session.setAttribute("current_user", nUser);
						session.setAttribute("msg-err",null);
						response.sendRedirect("pagesJSP/compte.jsp");
					}
					else {
						session.setAttribute("msg-err","Ce login est déjà utilisé.");
						response.sendRedirect("pagesJSP/compte.jsp");
					}
				} catch (SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else {
				session.setAttribute("msg-err","Le mot de passe et la confirmation de mot de passe sont différents.");
				response.sendRedirect("pagesJSP/compte.jsp");
			}
		}
		else {
			session.setAttribute("msg-err","Champ mot de passe actuel incorrect.");
			response.sendRedirect("pagesJSP/compte.jsp");
		}
		
	}

}
