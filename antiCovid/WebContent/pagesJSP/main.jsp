<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.User" %>
<%@ page import="SQL.SQLconnector" %>
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

    <!-- Custom styles for this template -->
    <link href="jumbotron.css" rel="stylesheet">

</head>
<body>
 <nav class="navbar navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">
      <img src="../images/logo.png" alt="" width="40" height="40" class="d-inline-block align-text-top">
      AntiCovid
    </a>
    <div class="d-flex">
    	<%
    		User user = (User) request.getSession().getAttribute("current_user");
			if(user != null) {
				out.println("<a href='compte.jsp'>Votre compte</a>");
				out.println("<a class='btn btn-outline-danger' href='../deconnexionServlet'>DECONNEXION</a>");
			}else{
				out.println("<a class='btn btn-outline-danger mx-2' href='inscription.jsp'>INSCRIPTION</a>");
				
				out.println("<a class='btn btn-danger mx-2' href='connexion.jsp'>CONNEXION</a>");
			}
		%>
    </div>
  </div>
</nav>

    <main role="main">

      <div class="jumbotron bg-danger">
        <div class="container">
          <h1 class="display-3">Bienvenue sur AntiCovid!</h1>
          <p>Soyez au courant des dernières nouvelles concernant le Covid-19 et votre entourage.</p>
        </div>
      </div>
      
       <% if(request.getAttribute("msg-err") != null ){
    	   	out.print("<div class=\"container\">");
    	   		out.print("<div class=\"row\">");
    	   			out.print("<div class=\"col-md-12\">");
    	   				out.print("<div class=\"alert alert-danger\" role=\"alert\">");
    	   					out.print(request.getAttribute("msg-err"));
    	   				out.print("</div>");
    	   			out.print("</div>");
    	   		out.print("</div>");
    	   	out.print("</div>");
       	   }
       %>
       



     <div class="container">
     	<div class="row">
     	  <div class="col-md-6">
     			<h2>Connexion : </h2>
     			<hr>
     			<form method="post" action="connexionServlet">
     			
				  <div class="form-group">
				    <label for="login">Login :</label>
				    <input type="text" class="form-control" id="login" name="login" placeholder="Entrer votre login">
				  </div>
				
				<div class="form-group">
				    <label for="password">Mot de passe :</label>
				    <input type="password" class="form-control" id="password" name="password" placeholder="Entrer votre mot de passe">
				</div>

				  <button type="submit" class="btn btn-primary">Login</button>
				  <button type="reset" class="btn btn-danger"> Reset </button>
				</form>
     	  </div>
     	  <div class="col-md-6">
     			<h2>Inscription : </h2>
     			<hr>
     			<form method="post" action="../inscriptionServlet">
     			
				  <div class="form-group">
				    <label for="login">Login :</label>
				    <input type="text" class="form-control" id="login" name="login" placeholder="Entrer votre login" pattern="[^\s]{3,20}">
				  </div>
				
				<div class="form-group">
				    <label for="password">Mot de passe :</label>
				    <input type="password" class="form-control" id="password" name="password" placeholder="Entrer votre mot de passe" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}">
				</div>
				<div class="form-group">
				    <label for="password">Confirmer le mot de passe :</label>
				    <input type="password" class="form-control" id="passwordConf" name="passwordConf" placeholder="Confirmer votre mot de passe">
				</div>
				<div class="form-group">
				    <label for="nom">Nom :</label>
				    <input type="text" class="form-control" id="nom" name="nom" placeholder="Entrer vote nom" pattern="[a-zA-Z]{2,20}">
				</div>
				<div class="form-group">
				    <label for="prenom">Prénom :</label>
				    <input type="text" class="form-control" id="prenom" name="prenom" placeholder="Entrer votre prénom" pattern="[a-zA-Z]{2,20}">
				</div>
				<div class="form-group">
					<label for="birth">Date de naissance:</label>
					<input type="date" class="form-control" id="birth" name="birth" value="1970-01-01" min="1850-01-01" max="2022-01-09">
				</div>
				  <button type="submit" class="btn btn-primary">Register</button>
				  <button type="reset" class="btn btn-danger"> Reset </button>
				</form>
     	  </div>
         <hr>
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