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
 * Servlet implementation class demanderEnAmiServlet
 */
@WebServlet("/demanderEnAmiServlet")
public class demanderEnAmiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public demanderEnAmiServlet() {
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
		String id = request.getParameter("selectUser");
		User user = (User) request.getSession().getAttribute("current_user");
		if(user != null) {
		
			HttpSession session = request.getSession();
			SQLconnector sc = new SQLconnector();
			if(id!=null && id!="") {
				ResultSet res = sc.doRequest("SELECT * FROM user where id='"+id+"'");
				try {
					while(res.next()) {
						String message = user.getPrenom()+" "+user.getNom()+" vous demande en ami.";
						ResultSet notifExist = sc.doRequest("SELECT * FROM notification where id_user='"+id+"' AND message='"+message+"' AND type='1'");
						if(!notifExist.next()) {
							sc.createNotif(Integer.parseInt(id), message,1);
							ResultSet res2 = sc.doRequest("SELECT * FROM notification where id_user='"+id+"' AND message='"+message+"' AND type='1'");
							while(res2.next()) {
								sc.createDemandeAmi(res2.getInt("id"),user.getId());
							}
						}
						else {
							session.setAttribute("msg-err","Cet utilisateur n'a pas encore répondu à votre demande.");
							response.sendRedirect("pagesJSP/amis.jsp");
						}
					}
				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.sendRedirect("pagesJSP/amis.jsp");
			}
			else {
			
				session.setAttribute("msg-err"," Veuillez remplir le champs.");
				response.sendRedirect("pagesJSP/amis.jsp");
			}
		}
	}

}
