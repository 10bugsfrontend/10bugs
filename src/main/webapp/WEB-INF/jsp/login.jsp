<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
.error{
	position:absolute;
 	top: 40%;
  	left: 50%;
  	-ms-transform: translate(-50%, -50%);
  	transform: translate(-50%, -50%);
   	color:red;
   
}

body {
	display:block;
	margin:0;
	padding:0;
	background: url("login-bkg.jpeg") center fixed;
	background-size: cover;
	font-size: sans-serif;
	color:white;
}

.loginbox {
	width: 320px;
	height: 420px;
	background: #000;
	color: #fff;
	top: 50%;
	left: 50%;
	position: absolute;
	transform: translate(-50%,-50%);
	box-sizing: border-box;
	padding: 70px 30px;
}

.avatar {
	width: 100px;
	height: 100px;
	position: absolute;
	top: -50px;
	left: calc(50% - 50px);
	border-radius: 50%;
	
}

h1 {
	color: #ee7922;
	margin: 0;
	padding: 0  0 20px;
	text-align: center;
}

.loginbox label {
	color: #ee7922;
	font-size: 16px;
	margin: 0;
	padding: 0;
	font-weight: bold;
}

.loginbox input {
	width: 100%;
	margin-bottom: 20px;	
}

.loginbox input[type="text"], input[type="password"]{
	border: none;
	border-bottom: 1px solid #fff;
	background: transparent;
	outline: none;
	height:40px;
	color: #fff;
	font-size: 16px;
}

.loginbox input[type="submit"]{
	border: none;
	outline: none;
	height: 40px;
	background: #ee7922;
	color: #fff;
	font-size: 18px;
}

.loginbox input[type="submit"]:hover {
	cursor: pointer;
	background: #ee7922;
	opacity: 50%;
	color: #000;
}

.loginbox a {
	text-decoration: none;
	font-size: 16px;
	line-height: 20px;
	color: #fff;
	
}

.loginbox a:hover {
 	color: #ee7922;
}
#button {
	color: #fff;
	background-color: #ee7922;
	border-color: #ee7922;
}

#button:hover {
	background: #ee7922;
	opacity: 50%;
	color: #fff;
}
</style>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

</head>
<body>
	<div class="container text-center loginbox" style="width: 30%; height: auto">
	<img src="10Bugs.png" class="avatar">
		<h1>Login here</h1>
		<form:form class="form-horizontal" action="/autentifyLogin" modelAttribute="login">
			<label for="text" class="control-label col-md-3">Username</label>
			<form:input type="text" path="username" placeholder="Enter username"/>
			<label for ="password" class="control-label col-md-3">Password</label>
			<form:input type="password" path="password" placeholder="Enter password"/>
			<input class="btn btn-primary" type="submit" value="Login" /> <a
				class="btn btn-primary" id="button" style="margin-right: 30px"
				href="/registerFirma">Inregistrare firma</a> <a
				class="btn btn-primary" id="button" href="/registerClient">Inregistrare
				client</a>
		</form:form>
 </div>
 <div class="error">
 <p>${errorMessage}</p>
 
 </div>
	
	
</body>
</html>
