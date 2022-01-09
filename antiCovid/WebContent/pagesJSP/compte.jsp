<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.User" %>
<%@ page import="SQL.SQLconnector" %>
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
 

    <title>profil</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/jumbotron/">

    <!-- Bootstrap core CSS -->
    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">

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
     	<div class="row">
     		<div class="col-md-1">
     		</div>
     	  <div class="col-md-6">
     			<h2>Votre profil : </h2>
     			<hr>
     			<form method="post" action="../modifierProfilServlet">
     	  		<div class="col">
     	  			 <div class="form-group">
     	  			 	<%
     	  			 		out.println("<img class='rounded mx-auto d-block' width='128' height='128' src='"+user.getUrl()+"'>");
     	  				%>
  						<label for="urlPhoto" class="form-label">Votre photo de profil:</label>
  						<input class='form-control' type='url' name='urlPhoto' id='urlPhoto' value='<%out.println(user.getUrl());%>'>
					 </div>
				 	 <div class="form">
				   		 <label for="login">Login :</label>
				   		 <% 
				   			out.println("<input type='text' class='form-control' id='login' name='login' value='"+user.getLogin()+"' pattern='[^\s]{3,20}' required>");
				   		 %>
				 	 </div>
					<div class="form-group">
				   		 <label for="password">Mot de passe :</label>
				   		 <%
				   			out.println("<input type='password' class='form-control' id='password' name='password' value='"+user.getPassword()+"' pattern='(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}' required>");
				   		%>
					</div>
					<div class="form-group">
				 	 	 <label for="password">Confirmer le mot de passe :</label>
				 	 	<%
				 	 		out.println("<input type='password' class='form-control' id='passwordConf' name='passwordConf' value='"+user.getPassword()+"' required>");
				 	 	%>
					</div>
     			</div>
     			<div class="col">
     				<div class="form-group">
				    	<label for="nom">Nom :</label>
				    	<%
				    		out.println("<input type='text' class='form-control' id='nom' name='nom' value='"+user.getNom()+"' pattern='[a-zA-Z]{2,20}' required>");
				    	%>
					</div>
					<div class="form-group">
				    	<label for="prenom">Prénom :</label>
				    	<%
				    		out.println("<input type='text' class='form-control' id='prenom' name='prenom' value='"+user.getPrenom()+"' pattern='[a-zA-Z]{2,20}' required>");
				    	%>
					</div>
					<div class="form-group">
						<label for="birth">Date de naissance:</label>
						<%
							out.println("<input type='date' class='form-control' id='birth' name='birth' value='"+user.getDate()+"' min='1850-01-01' max='2022-01-09' required>");
						%>
					</div>
					<div class="row g-2">
					<div class="col-sm">
				  	<label for="mdpActuel">Mot de passe actuel :</label>
				    <input type="password" class="form-control" id="mdpActuel" name="mdpActuel" pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}" required>
				    </div>
				    <div class="col-sm">
				    <p> &ensp; </p>
				  	<button type="submit" class="btn btn-danger">Valider les modifications</button>
				  	</div>
				  	</div>
     			</div>
     			</form>
     	  </div>
          <div class="col-md-5">
     	  </div>
     	</div>
     </div>

	<hr>
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