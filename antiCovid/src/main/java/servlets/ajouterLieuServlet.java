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

/**
 * Servlet implementation class ajouterLieuServlet
 */
@WebServlet("/ajouterLieuServlet")
public class ajouterLieuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ajouterLieuServlet() {
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
		String nom = request.getParameter("nom");
		String adresse = request.getParameter("adresse");
		String cPostal = request.getParameter("cPostal");
		
		HttpSession session = request.getSession();
		SQLconnector sc = new SQLconnector();
		ResultSet res = sc.doRequest("SELECT * FROM lieu where code_postal='"+cPostal+"'");
		try {
			int i=0;
			boolean stop = false;
			while(res.next() && !stop) {
				i++;
				if(res.getString("adresse").equalsIgnoreCase(adresse)) {
					session.setAttribute("msg-err"," Création du lieu impossible: ce lieu existe déjà.");
				}
				else {
					sc.createLieu(nom, adresse, Integer.parseInt(cPostal));
					session.setAttribute("msg-err",null);
					stop = true;
				}
			}
			if(i==0) {
				sc.createLieu(nom, adresse, Integer.parseInt(cPostal));
				session.setAttribute("msg-err",null);
				response.sendRedirect("pagesJSP/declarerActivite.jsp");
			}
			else {
				response.sendRedirect("pagesJSP/declarerActivite.jsp");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
