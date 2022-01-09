package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SQL.SQLconnector;

/**
 * Servlet implementation class supprimerNotifServlet
 */
@WebServlet("/supprimerNotifServlet")
public class supprimerNotifServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public supprimerNotifServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id_notification = request.getParameter("param");
		SQLconnector sc = new SQLconnector();
		sc.deleteNotif(Integer.parseInt(id_notification));
		// on supprime la demande d'ami liée à la notification si il y en a une
		ResultSet demande = sc.doRequest("SELECT * FROM demande_ami where id_notification='"+id_notification+"'");
		try {
			while(demande.next()) {
				sc.deleteDemandeAmi(Integer.parseInt(id_notification));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("pagesJSP/notifs.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
