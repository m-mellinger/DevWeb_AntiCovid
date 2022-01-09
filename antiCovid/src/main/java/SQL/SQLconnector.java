package SQL;
import java.sql.*;

import user.User;

public class SQLconnector {
	public SQLconnector() { }

	
	public User getUser(String login, String password) {
		
		   User user = null;
		   
		   String rqString = "Select * from User where login='"+login+"' and password='"+password+"';";
		   ResultSet res = doRequest(rqString);
		   int i = 0;
		   try {
			   while(res.next()) {
				   if(i==0) {
					   user = new User();
					   user.setLogin(res.getString("login"));
					   user.setPassword(res.getString("password"));
					   user.setNom(res.getString("nom"));
					   user.setPrenom(res.getString("prenom"));
					   user.setRole(res.getString("role"));
					   user.setDate(res.getString("naissance"));
					   user.setId(res.getInt("id"));
					   user.setACovid(res.getInt("a_covid"));
					   user.setUrl(res.getString("url_photo"));
				   }
				   else {
					   i++;
					   arret("Plusieurs utilisateurs sont identiques...");
				   }

			   }
			} 
		   catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
		   return user;
	   }
	
	public User getUser(int id) {
		
		   User user = null;
		   
		   String rqString = "Select * from User where id='"+id+"';";
		   ResultSet res = doRequest(rqString);
		   int i = 0;
		   try {
			   while(res.next()) {
				   if(i==0) {
					   user = new User();
					   user.setLogin(res.getString("login"));
					   user.setPassword(res.getString("password"));
					   user.setNom(res.getString("nom"));
					   user.setPrenom(res.getString("prenom"));
					   user.setRole(res.getString("role"));
					   user.setDate(res.getString("naissance"));
					   user.setId(res.getInt("id"));
					   user.setACovid(res.getInt("a_covid"));
					   user.setUrl(res.getString("url_photo"));
				   }
				   else {
					   i++;
					   arret("Plusieurs utilisateurs sont identiques...");
				   }

			   }
			} 
		   catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
		   return user;
	   }
	   
	public void createUser(String login, String password, String nom, String prenom, String naissance, String url) {
		
		   Connection con = connect();
		   
		    try {
		    	Statement stmt = con.createStatement();
		    	String rqString = "INSERT INTO User (login,password,nom,prenom,naissance,role,a_covid, url_photo) VALUES ('"+
						   login+"','"+password+"','"+nom+"','"+prenom+"','"+naissance+"','basic_user','0','"+url+"')";
				stmt.executeUpdate(rqString);
			} 
		    catch (SQLException e) {
				e.printStackTrace();
			}
	   }
	
	public void uptadeUser(int id, String login, String password, String nom, String prenom, String naissance, String url) {
		
		   Connection con = connect();
		   
		    try {
		    	Statement stmt = con.createStatement();
		    	String rqString = "UPDATE User SET login='"+login+"', password='"+password+"', nom='"+nom+"', prenom='"+prenom+"', naissance='"+naissance+"', url_photo='"+url+"' WHERE id='"+id+"'";
				stmt.executeUpdate(rqString);
			} 
		    catch (SQLException e) {
				e.printStackTrace();
			}
	   }
	
	public void setCovid(int idUser, int covid) {
		
		   Connection con = connect();
		   
		    try {
		    	Statement stmt = con.createStatement();
		    	String rqString = "UPDATE User SET a_covid='"+covid+"' WHERE id='"+idUser+"'";
				stmt.executeUpdate(rqString);
			} 
		    catch (SQLException e) {
				e.printStackTrace();
			}
	   }
	
	public void createActivite(String nom, String date, String hDebut, String hFin, int id_lieu, int id_user) {
		
		   Connection con = connect();
		   
		    try {
		    	Statement stmt = con.createStatement();
		    	String rqString = "INSERT INTO Activite (nom,date,heure_debut,heure_fin,id_lieu,id_user) VALUES ('"+
						   nom+"','"+date+"','"+hDebut+"','"+hFin+"','"+id_lieu+"','"+id_user+"')";
				stmt.executeUpdate(rqString);
			} 
		    catch (SQLException e) {
				e.printStackTrace();
			}
	   }
	
	public void createLieu(String nom, String adresse, int codePostal) {
		
		   Connection con = connect();
		   
		    try {
		    	Statement stmt = con.createStatement();
		    	String rqString = "INSERT INTO Lieu (nom,adresse,code_postal) VALUES ('"+
						   nom+"','"+adresse+"','"+codePostal+"')";
				stmt.executeUpdate(rqString);
			} 
		    catch (SQLException e) {
				e.printStackTrace();
			}
	   }
	
	public void createNotif(int idUser, String message, int type) {
		
		   Connection con = connect();
		   
		    try {
		    	Statement stmt = con.createStatement();
		    	String rqString = "INSERT INTO Notification (id_user,message,type) VALUES ('"+
						   idUser+"','"+message+"','"+type+"')";
				stmt.executeUpdate(rqString);
			} 
		    catch (SQLException e) {
				e.printStackTrace();
			}
	   }
	
	public void createDemandeAmi(int idNotif,  int idUserSrc) {
		
		   Connection con = connect();
		   
		   try {
			   Statement stmt = con.createStatement();
			   String rqString = "INSERT INTO Demande_ami (id_notification,accepte,refuse,id_user_src) VALUES ('"+
					   idNotif+"','0','0','"+idUserSrc+"')";
			   stmt.executeUpdate(rqString);
		   } 
		   catch (SQLException e) {
			   e.printStackTrace();
		   }
	}
	
	public void createAmi(int id_user1,  int id_user2) {
		
		   Connection con = connect();
		   
		   try {
			   Statement stmt = con.createStatement();
			   String rqString = "INSERT INTO Amis (id_user1,id_user2) VALUES ('"+
					   id_user1+"','"+id_user2+"')";
			   stmt.executeUpdate(rqString);
		   } 
		   catch (SQLException e) {
			   e.printStackTrace();
		   }
	}
	
	public void deleteDemandeAmi(int idNotif) {
		
		   Connection con = connect();
		   
		   try {
			   Statement stmt = con.createStatement();
			   String rqString = "DELETE FROM Demande_ami WHERE id_notification = '"+idNotif+"'";
			   stmt.executeUpdate(rqString);
		   } 
		   catch (SQLException e) {
			   e.printStackTrace();
		   }
	}
	
	public void deleteNotif(int idNotif) {
		
		   Connection con = connect();
		   
		   try {
			   Statement stmt = con.createStatement();
			   String rqString = "DELETE FROM Notification WHERE id = '"+idNotif+"'";
			   stmt.executeUpdate(rqString);
		   } 
		   catch (SQLException e) {
			   e.printStackTrace();
		   }
	}
	
	public void deleteAmi(int idUser1, int idUser2) {
		
		   Connection con = connect();
		   
		   try {
			   Statement stmt2 = con.createStatement();
			   String rqString2 = "DELETE FROM Amis WHERE (id_user1 = '"+idUser1+"' AND id_user2 = '"+idUser2+"') OR (id_user1 = '"+idUser2+"' AND id_user2 = '"+idUser1+"')";
			   stmt2.executeUpdate(rqString2);
		   } 
		   catch (SQLException e) {
			   e.printStackTrace();
		   }
	}
	
	public void deleteUser(int idUser) {
		
		   Connection con = connect();
		   
		   try {
			   Statement stmt = con.createStatement();
			   // on le supprime des utilisateurs
			   String rqString = "DELETE FROM User WHERE id = '"+idUser+"'";
			   stmt.executeUpdate(rqString);
			   // on le supprime des amis
			   rqString = "DELETE FROM Amis WHERE id_user1 = '"+idUser+"' OR id_user2 = '"+idUser+"'";
			   stmt.execute(rqString);
			   // on supprime ses activites
			   rqString = "DELETE FROM Activite WHERE id_user = '"+idUser+"'";
			   stmt.execute(rqString);
			   // on supprime ses notifications
			   ResultSet notifs = doRequest("SELECT * FROM notification where id_user='"+idUser+"'");
			   while(notifs.next()) {
				   // on supprime les demandes d'ami qu'il a effectué
				   rqString = "DELETE FROM Demande_ami WHERE id_notification = '"+notifs.getInt("id")+"'";
				   stmt.execute(rqString);
				// on supprime les demandes d'ami qu'il a recu
				   rqString = "DELETE FROM Demande_ami WHERE id_user_src = '"+idUser+"'";
				   stmt.execute(rqString);
			   }
			   rqString = "DELETE FROM Notification WHERE id_user = '"+idUser+"'";
			   stmt.execute(rqString);
			   
			   
		   } 
		   catch (SQLException e) {
			   e.printStackTrace();
		   }
	}
	
	public void deleteActivite(int idActivite) {
		
		   Connection con = connect();
		   
		   try {
			   Statement stmt = con.createStatement();
			   String rqString = "DELETE FROM Activite WHERE id = '"+idActivite+"'";
			   stmt.executeUpdate(rqString);
		   } 
		   catch (SQLException e) {
			   e.printStackTrace();
		   }
	}
	
	public void deleteLieu(int idLieu) {
		
		   Connection con = connect();
		   
		   try {
			   Statement stmt = con.createStatement();
			   // on supprime les activités qui se sont déroulées dans ce lieu
			   String rqString = "DELETE FROM Activite WHERE id_lieu = '"+idLieu+"'";
			   stmt.executeUpdate(rqString);
			   // on supprime le lieu
			   rqString = "DELETE FROM Lieu WHERE id = '"+idLieu+"'";
			   stmt.executeUpdate(rqString);
		   } 
		   catch (SQLException e) {
			   e.printStackTrace();
		   }
	}
	   
	   
	   public  ResultSet doRequest(String sql_string) {
		   ResultSet results = null;
		   Connection con = connect();
		   try {
			   Statement stmt = con.createStatement();
			   results = stmt.executeQuery(sql_string);
			} catch (SQLException e) {
			   e.printStackTrace();
			}
		   
		   return results;
	   }
	   
	 
	   public Connection connect() {
		   
		   Connection con = null;
		   
		   try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		   affiche("connexion a la base de données");
		   
		   try {
		         con = DriverManager.getConnection("jdbc:mysql://localhost/antiCovid", "root", "");
		         affiche("connexion réussie");
		   } 
		   catch (SQLException e) {
		         arret("Connection à  la base de donnéees impossible");
		         System.out.print(e);
		   }
		   
		   return con;
	   }
	   
	   
	   
	   private static void affiche(String message) {
		      System.out.println(message);
	   }

	   
	   
	   private static void arret(String message) {
		      System.err.println(message);
		      System.exit(99);
	   }

}
