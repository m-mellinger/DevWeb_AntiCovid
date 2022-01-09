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
 

    <title>Notifications</title>

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
      		<div class="col-md-12">
      			<br>
				<nav aria-label="breadcrumb">
 					<ol class="breadcrumb">
    					<li class="breadcrumb-item"><a href="main.jsp">Home</a></li>
    					<li class="breadcrumb-item active" aria-current="page">Notifications</li>
 		 			</ol>
			</nav>
			</div>
	 	</div>
	 <br>
				<h2>Vos notifications :</h2>
					<% 
						SQLconnector sc = new SQLconnector();
					 	sc = new SQLconnector();
						ResultSet notifs = sc.doRequest("SELECT * FROM notification WHERE id_user = '" + user.getId() + "' ORDER BY id DESC");
						while(notifs.next()) {
							out.println("<div class='p-2 mb-2 border border-danger'>");
							out.println("<div class='row'>");
							out.println("<div class='col-md-11'>");
							out.println(notifs.getString("message"));
							if(notifs.getInt("type") == 1){
								ResultSet demande = sc.doRequest("SELECT * FROM demande_ami WHERE id_notification = '" + notifs.getInt("id") + "'");
								while(demande.next()) {
									if((demande.getInt("accepte") == 0) && (demande.getInt("refuse") == 0)){
										session.setAttribute("current_user", user);
										out.println("<a class='btn btn-outline-danger mx-2' href='../refuserAmiServlet?param="+demande.getInt("id_notification")+"'>REFUSER</a>");
										out.println("<a class='btn btn-danger mx-2' href='../accepterAmiServlet?param="+demande.getInt("id_notification")+"'>ACCEPTER</a>");
									}
								}
							}
							out.println("</div><div class='col-md-1'><a class='btn btn-outline-danger' href='../supprimerNotifServlet?param="+notifs.getInt("id")+"'>X</a>");
							out.println("</div>");
							out.println("</div>");
							out.println("</div>");
						}
						
					%>

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