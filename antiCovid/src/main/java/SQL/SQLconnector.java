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
					   user.setRang(res.getString("role"));
					   user.setDate(res.getString("naissance"));
					   user.setId(res.getInt("id"));
					   user.setACovid(res.getInt("a_covid"));
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
	   
	public void createUser(String login, String password, String nom, String prenom, String naissance) {
		
		   Connection con = connect();
		   
		    try {
		    	Statement stmt = con.createStatement();
		    	String rqString = "INSERT INTO User (login,password,nom,prenom,naissance,role,a_covid) VALUES ('"+
						   login+"','"+password+"','"+nom+"','"+prenom+"','"+naissance+"','basic_user','0')";
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
