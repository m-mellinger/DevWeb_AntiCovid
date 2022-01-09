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
 * Servlet implementation class supprimerUserServlet
 */
@WebServlet("/supprimerUserServlet")
public class supprimerUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public supprimerUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String id_user = request.getParameter("param");
		SQLconnector sc = new SQLconnector();
		User user = sc.getUser(Integer.parseInt(id_user));
		User currentUser = (User) request.getSession().getAttribute("current_user");
		sc.deleteUser(user.getId());
		ResultSet res = sc.doRequest("SELECT * FROM user where id='"+currentUser.getId()+"'");
		try {
			if(res.next()) {
				// l'admin a supprimer un utilisateur (pas lui-meme)
				response.sendRedirect("pagesJSP/admin.jsp");
			}
			else {
				// l'utilisateur a supprimer son propre compte
				session.setAttribute("current_user", null);
				response.sendRedirect("pagesJSP/main.jsp");
			}
		} catch (SQLException | IOException e) {
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

}
