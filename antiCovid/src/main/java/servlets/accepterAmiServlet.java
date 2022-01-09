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
 * Servlet implementation class accepterAmiServlet
 */
@WebServlet("/accepterAmiServlet")
public class accepterAmiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public accepterAmiServlet() {
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
				String message = userDst.getPrenom()+" "+userDst.getNom()+" a accepté votre demande d ami.";
				sc.createNotif(demande.getInt("id_user_src"),message,2);
				// on notifie la personne qui a accepté la demande
				User userSrc = sc.getUser(demande.getInt("id_user_src"));
				String message2 = "Vous avez accepté la demande d ami de "+userSrc.getPrenom()+" "+userSrc.getNom()+".";
				sc.createNotif(userDst.getId(),message2,2);
				sc.deleteDemandeAmi(Integer.parseInt(id_notification));
				sc.createAmi(userSrc.getId(), userDst.getId());
				sc.deleteNotif(Integer.parseInt(id_notification));
				// on vérifie si l'un des utilisateurs est positif au Covid et on envoie une notif si c'est le cas
				if(userDst.getACovid()) {
					if(!userSrc.getACovid()) {
						String mCasContact = userDst.getPrenom()+" "+userDst.getNom()+" est positif au Covid. Veuillez vous faire tester si vous l avez vu récemment.";
						sc.createNotif(userSrc.getId(),mCasContact,2);
					}
				}
				else if(userSrc.getACovid()){
					if(!userDst.getACovid()) {
						String mCasContact = userSrc.getPrenom()+" "+userSrc.getNom()+" est positif au Covid. Veuillez vous faire tester si vous l avez vu récemment.";
						sc.createNotif(userDst.getId(),mCasContact,2);
					}
				}
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
