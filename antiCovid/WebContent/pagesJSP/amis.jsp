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
 

    <title>Amis</title>

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
    			<li class="breadcrumb-item active" aria-current="page">Amis</li>
 		 	</ol>
		</nav>
		</div>
	 </div>
	 <br>
	 <div class="row">
	<div class="col-md-7">
					<h2>Vos amis :</h2>
					<% 
						SQLconnector sc = new SQLconnector();
						ResultSet amis = sc.doRequest("SELECT * FROM amis WHERE id_user1 = '" + user.getId() + "' OR id_user2 = '" + user.getId()+"'");
							while(amis.next()) {
								ResultSet ami;
								out.println("<div class='p-2 mb-2 border border-danger'>");
								out.println("<div class='row'>");
								out.println("<div class='col-md-9'>");
								if(amis.getInt("id_user1") == user.getId()){
									ami = sc.doRequest("SELECT * FROM user WHERE id = '" + amis.getInt("id_user2") + "'");
								}
								else{
									ami = sc.doRequest("SELECT * FROM user WHERE id = '" + amis.getInt("id_user1") + "'");
								}
								while(ami.next()) {
									User a = sc.getUser(ami.getInt("id"));
									out.println("<img alt='Compte' src='"+a.getUrl()+"' width='30' height='30'>");
									out.println(ami.getString("nom") + " " + ami.getString("prenom") );
									if(a.getACovid()){
										out.println("<div class='text-danger'>Positif</div>");
									}
									else{
										out.println("<div class='text-success'>Pas positif</div>");
									}
									out.println("</div><div class='col-md-3'><a class='btn btn-outline-danger' href='../supprimerAmiServlet?param="+ami.getInt("id")+"'>Supprimer</a>");
								}
								out.println("</div>");
								out.println("</div>");
								out.println("</div>");
								out.println("<br>");
							}
					%>
	</div>
	<div class="col-md-5">
		<form method="post" action="../demanderEnAmiServlet">
     	<div class="row">
				<div class="form-group">
				<div class="col-md-11">
				<label for="selectUser">Rechercher un utilisateur: </label>
				    <select id="selectUser" name="selectUser" class="form-control" required/>
				    	<option value="">&ensp;&ensp;&ensp;</option>
				    	<% 
				    		ResultSet users = sc.doRequest("SELECT * FROM user WHERE NOT id = '" + user.getId() + "'");
				    		while(users.next()){
				    			ResultSet res = sc.doRequest("SELECT * FROM amis WHERE (id_user1 = '" + user.getId() + "' AND id_user2='"+users.getInt("id")+"') OR (id_user1 = '" + users.getInt("id") + "' AND id_user2='"+user.getId()+"')");
				    			if(!res.next()){
				    				out.println("<option value='"+users.getInt("id")+"'>"+users.getString("prenom")+" "+users.getString("nom")+"</option>");
				    			}
				    		}
				    		%>	
				    </select>
				</div>
				</div>
				<div class="col-md-1">
				<br>
				<button type="submit" class="btn btn-danger" id="addAmi">Demander en ami</button>
				</div>
		</div>
     </form>
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