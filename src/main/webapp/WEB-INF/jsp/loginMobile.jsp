<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Login</title>
</head>
<script type="text/javascript">
$(document).ready(function() {
	$("input[type=submit]").click(function(e) {
	var name = $("#username").val();
	var email = $("#password").val();
	if (name == '' || email == '') {
	e.preventDefault();
	alert("Please Fill Required Fields");
	}
	});
	});
</script>
<style type="text/css">
.error{
	position:absolute;
 	top: 20%;
  	left: 50%;
  	-ms-transform: translate(-50%, -50%);
  	transform: translate(-50%, -50%);
   	color:red;
   
}
	.bg{
		background-image:url(bus_station.jpg);
		padding: 0%;padding-bottom:20%;background-repeat: no-repeat;background-attachment: fixed;
	}
	.cont .container-fluid{
		margin-top: 10%;
		width:60%;
	}
	@media screen and (max-width: 1024px){
		.bg{
			background-image:none;
		}
		body{
			background: url(bus_station.jpg) no-repeat center center fixed; 
  			-webkit-background-size: cover;
  			-moz-background-size: cover;
  			-o-background-size: cover;
  			background-size: cover;
		}
	}
	@media screen and (max-width: 450px) {
		.cont .container-fluid{
			margin-top:30%;
			width:80%;
			font-size: 18px;
		}
		.btn{
			font-size: 18px;
		}
  }
}
	</style>
<body>

<div class="bg">
<h1 align="center">Login here</h1>
<div class="cont">
<div class="container-fluid" style="align-self: center;background-color:#222c1f; padding: 1%;;border-radius: .25rem;opacity: 92%;background-size: cover; color: white">
<form:form class="form-horizontal" action="/autentifyLogin" modelAttribute="login">
<div class="form-control">
<label for="username">Username:</label>
<form:input type="text" path="username" placeholder="Enter username"/><br>
</div>
<div class="form-control">
<label for="password">Password:</label>
<form:input type="password" path="password" placeholder="Enter password"/><br>
</div>
<a href="/registerClient">Inregistrare client</a><br>
<button type="submit" class="btn btn-primary">Submit</button>
</form:form>
</div>
</div>
</div>
 <div class="error">
 <p>${errorMessage}</p>
 
 </div>
</body>
</html>