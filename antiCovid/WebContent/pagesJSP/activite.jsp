<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.User" %>
<%@ page import="SQL.SQLconnector" %>
<%@ page import="java.sql.*" %>
<%
	User user = (User) request.getSession().getAttribute("current_user");
	if(user == null) {
		response.sendRedirect("main.jsp");
	}
%>
<!DOCTYPE html>
<head>

  	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
 

    <title>Activités</title>

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
    			<li class="breadcrumb-item active" aria-current="page">Activités</li>
 		 	</ol>
 		 </div>
		</nav>
		</div>
		<br>
		<div class="row">
			<div class="col-md-9">
				<h2>Vos activités :</h2>
					<% 
						SQLconnector sc = new SQLconnector();
						ResultSet activites = sc.doRequest("SELECT * FROM activite WHERE id_user = '" + user.getId() + "'");
							while(activites.next()) {
								out.println("<div class='p-2 mb-2 border border-danger'>");
								out.println("<div class='row'>");
								out.println("<div class='col-md-9'>");
								out.println(
									     activites.getString("nom") + "<br>" +
									     "Date: "+activites.getString("date") + "<br>" +
									     "Heure de début: "+activites.getString("heure_debut") + "<br>" +
									     "Heure de fin: "+activites.getString("heure_fin")+"<br>");
								ResultSet lieux = sc.doRequest("SELECT * FROM lieu WHERE id = '" + activites.getString("id_lieu") + "';");
								while(lieux.next()) {
									out.println("Lieu: "+lieux.getString("nom") +
											"</div><div class='col-md-3'><a class='btn btn-outline-danger' href='../supprimerActiviteServlet?param="+activites.getInt("id")+"'>Supprimer</a>"
											);
								}
								out.println("</div>");
								out.println("</div>");
								out.println("</div>");
								out.println("<br>");
							}
					%>
			</div>
			<div class="col-md-3">
				<a class="btn btn-danger mx-2" role="button" href="declarerActivite.jsp">Déclarer une activité</a>
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