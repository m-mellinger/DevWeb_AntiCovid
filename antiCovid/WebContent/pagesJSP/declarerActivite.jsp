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
 

    <title>antiCovid</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/jumbotron/">

    <!-- Bootstrap core CSS -->
    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="jumbotron.css" rel="stylesheet">
    
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDNKwXT3ffAoqhAnG1pViBXHXc8KsywcPE&callback=initAutocomplete&libraries=places&v=weekly" async ></script>
    <script>
    	function initialize() {
    	  var input = document.getElementById('adresse');
    	  new google.maps.places.Autocomplete(input);
    	}

    	google.maps.event.addDomListener(window, 'load', initialize);
    </script>

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
    			<li class="breadcrumb-item"><a href="activite.jsp">Activités</a></li>
    			<li class="breadcrumb-item active" aria-current="page">Déclarer une activité</li>
 		 	</ol>
 		 </div>
		</nav>
		</div>
		<div class="row">
       <% 
       	String msgErr = (String) request.getSession().getAttribute("msg-err"); 
       	if(msgErr!=null){
    	   	out.println("<div class='container'>");
    	   		out.println("<div class='row'>");
    	   			out.println("<div class='col-md-12'>");
    	   				out.println("<div class='alert alert-danger' role='alert'>");
    	   					out.println(msgErr);
    	   				out.println("</div>");
    	   			out.println("</div>");
    	   		out.println("</div>");
    	   	out.println("</div>");
       	   }
       %>
       </div>
       <br>
     	<div class="row">
     	  <div class="col-md-6">
     			<h2>Déclarer une activité : </h2>
     			<hr>
     			<form method="post" action="../declarerActiviteServlet">
     			
				  <div class="form-group">
				    <label for="nom">Titre :</label>
				    <input type="text" class="form-control" id="nom" name="nom" placeholder="Entrer le titre de l'activité" required pattern="[^\s]{3,50}">
				  </div>
				  <div class="form-group">
				    <label for="nom">Date :</label>
				    <input type="date" class="form-control" id="date" name="date" placeholder="Entrer la date de l'activité" min="1850-01-01" max="2022-01-20" required>
				  </div>
				  <div class="form-group">
				    <label for="nom">Heure de début :</label>
				    <input type="time" class="form-control" id="hDebut" name="hDebut" min="00:01" max="00:00" required>
				  </div>
				  <div class="form-group">
				    <label for="nom">Heure de fin :</label>
				    <input type="time" class="form-control" id="hFin" name="hFin" min="00:01" max="00:00" required>
				  </div>
				  <div class="form-group">
				  	Lieu :
				    <select class="form-control" id="lieu" name="lieu" required>
				    	<%
				    			SQLconnector sc = new SQLconnector();
								ResultSet res = sc.doRequest("SELECT * FROM lieu ORDER BY id DESC");
								out.println("<option value=''>&ensp;&ensp;&ensp;</option>");
								try{
									while(res.next()) {
										out.println("<option value=" + res.getInt("id") + ">" + res.getString("nom") + "/ adresse: "+ res.getString("adresse") + ", "+res.getInt("code_postal")+"</option>");
								}
								} catch (SQLException e){
									e.printStackTrace();
								}
						%>
				    </select>
				  </div>

				  <button type="submit" class="btn btn-danger">Déclarer l'activité</button>
				</form>
     	  </div>
     	  <div class="col-md-6">
     	  	<a class='btn btn-outline-danger mx-2 my-2' onclick='afficheFormulaire()'>Ajouter un lieu</a>
     	  	<script type="text/javascript">
     	  	function afficheFormulaire(){
     	  		document.getElementById("addLieu").hidden = false;
     	  	}
     	  	</script>
     	  	<form method="post" action="../ajouterLieuServlet" id="addLieu" hidden>
     	  		<div class="form-group">
				    <label for="nom">Nom :</label>
				    <input type="text" class="form-control" id="nom" name="nom" placeholder="Entrer le nom du lieu" pattern="[^\s]{3,50}" required>
				</div>
				<div class="form-group">
				    <label for="adresse">Adresse :</label>
				    <input type="text" class="form-control" id="adresse" name="adresse" placeholder="Entrer l'adresse du lieu" pattern="\w+(\s\w+){2,}"  required>
				</div>
				<div class="form-group">
				    <label for="adresse">Code postal :</label>
				    <input type="text" class="form-control" id="cPostal" name="cPostal" placeholder="Entrer le code postal de l'adresse" pattern="[0-9]{5}" required>
				</div>
				<button type="submit" class="btn btn-danger">Ajouter</button>
     	  	</form>
     	  </div>
         <hr>
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