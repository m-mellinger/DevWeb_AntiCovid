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
 * Servlet implementation class annulerPositifServlet
 */
@WebServlet("/annulerPositifServlet")
public class annulerPositifServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public annulerPositifServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		SQLconnector sc = new SQLconnector();
		HttpSession session = request.getSession();
		User user = (User) request.getSession().getAttribute("current_user");
		// devient negatif covid
		sc.setCovid(user.getId(), 0);
		user.setACovid(0);
		session.setAttribute("current_user", user);
		response.sendRedirect("pagesJSP/main.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
