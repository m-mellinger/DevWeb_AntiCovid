package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import SQL.SQLconnector;
import user.User;

/**
 * Servlet implementation class declarerPositifServlet
 */
@WebServlet("/declarerPositifServlet")
public class declarerPositifServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public declarerPositifServlet() {
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
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        String dateActuelle = dtf.format(LocalDateTime.now());
		// devient positif covid
		sc.setCovid(user.getId(), 1);
		// on notifie les amis
		ResultSet amis = sc.doRequest("SELECT * FROM amis WHERE id_user1 = '" +user.getId()+"' OR id_user2 = '" +user.getId()+"'");
		try {
			while(amis.next()) {
				if(amis.getInt("id_user1")==user.getId()) {
					User ami = sc.getUser(amis.getInt("id_user2"));
					// on verifie si il n'a pas deja le covid
					if(!ami.getACovid()) {
						String m = user.getPrenom()+" "+user.getNom()+" est positif au Covid. Veuillez vous faire tester si vous l avez vu récemment.";
						sc.createNotif(ami.getId(), m, 2);
					}
				}
				else {
					User ami = sc.getUser(amis.getInt("id_user1"));
					// on verifie si il n'a pas deja le covid
					if(!ami.getACovid()) {
						String m = user.getPrenom()+" "+user.getNom()+" est positif au Covid. Veuillez vous faire tester si vous l avez vu récemment.";
						sc.createNotif(ami.getId(), m, 2);
					}
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// on notifie les utilisateurs qui ont déclaré une activité à la meme date, meme lieu et meme plage horraire que l'utilisateur positif
		ResultSet activites = sc.doRequest("SELECT * FROM activite WHERE id_user = '" +user.getId()+"'");
		try {
			Date dateActu = new SimpleDateFormat("yyyy-mm-dd").parse(dateActuelle);
			while(activites.next()) {
				Date dateActivite = new SimpleDateFormat("yyyy-mm-dd").parse(activites.getString("date"));
				int dif = (int) (dateActivite.getTime() - dateActu.getTime()) / (1000 * 60 * 60 * 24);
				System.out.println(dif);
				if(dif>=-15 && dif<=15) {
					System.out.println("test1");
					ResultSet activiteMemeLieu = sc.doRequest("SELECT * FROM activite WHERE id_lieu = '" +activites.getInt("id_lieu")+"'");
					while(activiteMemeLieu.next()) {
						if(activiteMemeLieu.getInt("id_user") != user.getId()) {
							// meme date
							if(activiteMemeLieu.getString("date").equals(activites.getString("date"))) {
								int debutActivite = Integer.parseInt(activites.getString("heure_debut").replace(":", ""));
						        int finActivite = Integer.parseInt(activites.getString("heure_fin").replace(":", ""));
						        int debutActiviteMemeLieu = Integer.parseInt(activiteMemeLieu.getString("heure_debut").replace(":", ""));
						        int finActiviteMemeLieu = Integer.parseInt(activiteMemeLieu.getString("heure_fin").replace(":", ""));
						        boolean horraireCommun = false;
						        if(debutActiviteMemeLieu >= debutActivite && debutActiviteMemeLieu <= finActivite) {
						            horraireCommun =  true;
						        }

						        if(finActiviteMemeLieu >= debutActivite && finActiviteMemeLieu <= finActivite) {
						        	horraireCommun =  true;
						        }
						        // meme plage horraire
						        if(horraireCommun) {
						        	String message = "Vous avez été au contact d une personne positive au Covid lors de votre activité "+activiteMemeLieu.getString("nom")+" le "+activiteMemeLieu.getString("date")+". Veuillez vous faire tester.";
						        	sc.createNotif(activiteMemeLieu.getInt("id_user"), message, 2);
						        }
							}
							
					       
						}
					}
				}
				
			}
		} catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User nUser = sc.getUser(user.getId());
		session.setAttribute("current_user", nUser);
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
