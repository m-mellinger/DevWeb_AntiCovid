package user;

public class User {
	private int id;
	private String nom;
	private String prenom;
	private String role;
	private String password;
	private String login;
	private String date;
	private boolean aCovid;
	private String url;
	
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
	
	public String getRole() {
		return this.role;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public String getUrl() {
		return this.url;
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
	
	public void setRole( String role ) {
		this.role = role;
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
	
	public void setUrl( String url ) {
		this.url = url;
	}
	
}
