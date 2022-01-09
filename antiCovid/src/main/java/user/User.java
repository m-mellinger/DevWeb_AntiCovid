package user;

import java.sql.Date;

public class User {
	private int id;
	private String nom;
	private String prenom;
	private String rang;
	private String password;
	private String login;
	private String date;
	private boolean aCovid;
	
	public int getId() {
		return this.id;
	}
	
	public String getNom() {
		return this.nom;
	}

	public String getPrenom() {
		return this.prenom;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getLogin() {
		return this.login;
	}
	
	public String getRang() {
		return this.rang;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public Boolean getACovid() {
		return this.aCovid;
	}
	
	public void setId( int id ) {
		this.id = id;
	}
	
	public void setNom( String nom ) {
		this.nom = nom;
	}

	public void setPrenom( String prenom ) {
		this.prenom = prenom;
	}
	
	public void setPassword( String password ) {
		this.password = password;
	}
	
	public void setLogin( String login ) {
		this.login = login;
	}
	
	public void setRang( String rang ) {
		this.rang = rang;
	}
	
	public void setDate( String date ) {
		this.date = date;
	}
	
	public void setACovid( int covid ) {
		if(covid==1) {
			this.aCovid = true;
		}
		else {
			this.aCovid = false;
		}
	}
}
