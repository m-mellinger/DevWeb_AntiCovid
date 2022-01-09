package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import SQL.SQLconnector;
import user.User;

/**
 * Servlet implementation class declarerActiviteServlet
 */
@WebServlet("/declarerActiviteServlet")
public class declarerActiviteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public declarerActiviteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nom = request.getParameter("nom");
        String date = request.getParameter("date");
        String hDebut = request.getParameter("hDebut");
        String hFin = request.getParameter("hFin");
        String lieu = request.getParameter("lieu");
        HttpSession session = request.getSession();
        User user = (User) request.getSession().getAttribute("current_user");
        SQLconnector sc = new SQLconnector();
        
        if(!lieu.equals("") && !lieu.equals(null)) {
            String[] splitHDebut = hDebut.split(":");
            String[] splitHFin = hFin.split(":");
            if(Integer.parseInt(splitHDebut[0]) > Integer.parseInt(splitHFin[0])) {
            	session.setAttribute("msg-err","Erreur: heure de début supérieure à heure de fin.");
    			response.sendRedirect("pagesJSP/declarerActivite.jsp");
            }else if(Integer.parseInt(splitHDebut[0]) == Integer.parseInt(splitHFin[0])) {
                if(Integer.parseInt(splitHDebut[1]) > Integer.parseInt(splitHFin[1])) {
                	session.setAttribute("msg-err","Erreur: heure de début supérieure à heure de fin.");
        			response.sendRedirect("pagesJSP/declarerActivite.jsp");
                }else{
                    sc.createActivite(nom, date, hDebut, hFin, Integer.parseInt(lieu),user.getId());
                    session.setAttribute("msg-err",null);
                    response.sendRedirect("pagesJSP/activite.jsp");
                }
            }else {
                sc.createActivite(nom, date, hDebut, hFin, Integer.parseInt(lieu),user.getId());
                session.setAttribute("msg-err",null);
                response.sendRedirect("pagesJSP/activite.jsp");
            }
        }
        else {
        	session.setAttribute("msg-err","Veuillez choisir un lieu valide.");
			response.sendRedirect("pagesJSP/declarerActivite.jsp");
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
