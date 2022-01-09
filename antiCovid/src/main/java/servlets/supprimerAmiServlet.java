package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SQL.SQLconnector;
import user.User;

/**
 * Servlet implementation class supprimerAmiServlet
 */
@WebServlet("/supprimerAmiServlet")
public class supprimerAmiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public supprimerAmiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id_ami = request.getParameter("param");
		SQLconnector sc = new SQLconnector();
		User user = (User) request.getSession().getAttribute("current_user");
		sc.deleteAmi(user.getId(), Integer.parseInt(id_ami));
		// on notifie la personne qu'elle ne fait plus partie des amis de l'utilisateur
		String message = user.getPrenom()+" "+user.getNom()+" vous a retiré de ses amis.";
		sc.createNotif(Integer.parseInt(id_ami),message,2);
		response.sendRedirect("pagesJSP/amis.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
