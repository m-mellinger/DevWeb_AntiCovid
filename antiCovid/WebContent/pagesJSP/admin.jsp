<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.User" %>
<%@ page import="SQL.SQLconnector" %>
<%@ page import="java.sql.*" %>
<%
	User user = (User) request.getSession().getAttribute("current_user");
	if(user == null || !user.getRole().equals("admin")) {
		response.sendRedirect("main.jsp");
	}
%>
<!DOCTYPE html>
<head>

  	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
 

    <title>Gérer le site</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/jumbotron/">

    <!-- Bootstrap core CSS -->
    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="jumbotron.css" rel="stylesheet">

</head>
<body>
 <nav class="navbar navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="main.jsp">
      <img src="../images/logo.png" alt="" width="40" height="40" class="d-inline-block align-text-top">
      AntiCovid
    </a>
    <div class="d-flex">
    	<%
			if(user != null) {
				out.println("<a href='compte.jsp'><img alt='Compte' src='"+user.getUrl()+"' width='30' height='30'></a>");
				out.println("<a class='btn btn-outline-danger mx-4' href='../deconnexionServlet'>DECONNEXION</a>");
			}else{
				out.println("<a class='btn btn-outline-danger mx-2' href='inscription.jsp'>INSCRIPTION</a>");
				
				out.println("<a class='btn btn-danger mx-2' href='connexion.jsp'>CONNEXION</a>");
			}
		%>
    </div>
  </div>
</nav>

    <main role="main">

	<div class="container">
	<div class="row">
      <div class="col-md-12">
      	<br>
		<nav aria-label="breadcrumb">
 			<ol class="breadcrumb">
    			<li class="breadcrumb-item"><a href="main.jsp">Home</a></li>
    			<li class="breadcrumb-item active" aria-current="page">Gérer le site</li>
 		 	</ol>
		</nav>
		</div>
	 </div>
	 <br>
	 <div>
	 	<a class='btn btn-outline-danger mx-2' onclick='afficheUsers()'>Utilisateurs</a>
	 	<a class='btn btn-outline-danger mx-2' onclick='afficheActivites()'>Activités</a>
	 	<a class='btn btn-outline-danger mx-2' onclick='afficheLieux()'>Lieux</a>
	 </div>
	 <br>
	 <script type="text/javascript">
     	  	function afficheUsers(){
     	  		document.getElementById("users").hidden = false;
     	  		document.getElementById("activites").hidden = true;
     	  		document.getElementById("lieux").hidden = true;
     	  	}
     	  	function afficheActivites(){
     	  		document.getElementById("activites").hidden = false;
     	  		document.getElementById("users").hidden = true;
     	  		document.getElementById("lieux").hidden = true;
     	  	}
     	  	function afficheLieux(){
     	  		document.getElementById("lieux").hidden = false;
     	  		document.getElementById("users").hidden = true;
     	  		document.getElementById("activites").hidden = true;
     	  	}
     	  	</script>
     <div id="users" hidden>
		<h2>Les utilisateurs présents sur le site :</h2>
			<% 
				SQLconnector sc = new SQLconnector();
				sc = new SQLconnector();
				ResultSet users = sc.doRequest("SELECT * FROM user");
				while(users.next()) {
					out.println("<div class='p-2 mb-2 border border-danger'>");
					out.println("<div class='row'>");
					out.println("<div class='col-md-10'>");
					out.println(users.getString("nom") + " " + users.getString("prenom")+"<br> login: "+users.getString("login")+"<br>");
					User a = sc.getUser(users.getInt("id"));
					if(a.getACovid()){
						out.println("<div class='text-danger'>Positif</div>");
					}
					else{
						out.println("<div class='text-success'>Pas positif</div>");
					}
					out.println("</div><div class='col-md-2'><a class='btn btn-outline-danger' href='../supprimerUserServlet?param="+users.getInt("id")+"'>Supprimer</a>");
					out.println("</div>");
					out.println("</div>");
					out.println("</div>");
				}
			%>

     </div>
     <div id="activites" hidden>
		<h2>Les activités déclarées sur le site :</h2>
			<% 
				ResultSet activites = sc.doRequest("SELECT * FROM activite");
				while(activites.next()) {
					out.println("<div class='p-2 mb-2 border border-danger'>");
					out.println("<div class='row'>");
					out.println("<div class='col-md-10'>");
					out.println(activites.getString("nom") + "<br> Date: " + activites.getString("date")+"<br> Heure de debut: "+activites.getString("heure_debut")+"<br> Heure de fin: "+activites.getString("heure_fin"));
					ResultSet lieu = sc.doRequest("SELECT * FROM lieu where id='"+activites.getInt("id_lieu")+"'");
					while(lieu.next()) {
						out.println("<br>Lieu: "+lieu.getString("nom"));
					}
					out.println("</div><div class='col-md-2'><a class='btn btn-outline-danger' href='../supprimerActiviteServlet?param="+activites.getInt("id")+"'>Supprimer</a>");
					out.println("</div>");
					out.println("</div>");
					out.println("</div>");
				}
			%>

     </div>
     <div id="lieux" hidden>
		<h2>Les lieux identifiés sur le site :</h2>
			<% 
				ResultSet lieux = sc.doRequest("SELECT * FROM lieu");
				while(lieux.next()) {
					out.println("<div class='p-2 mb-2 border border-danger'>");
					out.println("<div class='row'>");
					out.println("<div class='col-md-10'>");
					out.println(lieux.getString("nom") + "<br> Adresse: " + lieux.getString("adresse")+"<br> Code postal: "+lieux.getInt("code_postal"));
					out.println("</div><div class='col-md-2'><a class='btn btn-outline-danger' href='../supprimerLieuServlet?param="+lieux.getInt("id")+"'>Supprimer</a>");
					out.println("</div>");
					out.println("</div>");
					out.println("</div>");
				}
			%>

     </div>
    </div>
    </main>

   

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="../../assets/js/vendor/popper.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>