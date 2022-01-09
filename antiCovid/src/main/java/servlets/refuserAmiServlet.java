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
import user.User;

/**
 * Servlet implementation class refuserAmiServlet
 */
@WebServlet("/refuserAmiServlet")
public class refuserAmiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public refuserAmiServlet() {
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
		User userDst = (User) request.getSession().getAttribute("current_user");
		ResultSet demande = sc.doRequest("SELECT * FROM demande_ami where id_notification='"+id_notification+"'");
		try {
			while(demande.next()) {
				// on notifie la personne qui a fait la demande
				String message = userDst.getPrenom()+" "+userDst.getNom()+" a refuse votre demande d ami.";
				sc.createNotif(demande.getInt("id_user_src"),message,2);
				// on notifie la personne qui a refusé la demande
				User userSrc = sc.getUser(demande.getInt("id_user_src"));
				String message2 = "Vous avez refuse la demande d ami de "+userSrc.getPrenom()+" "+userSrc.getNom()+".";
				sc.createNotif(userDst.getId(),message2,2);
				sc.deleteDemandeAmi(Integer.parseInt(id_notification));
				sc.deleteNotif(Integer.parseInt(id_notification));
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
