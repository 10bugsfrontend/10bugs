<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<c:if test="${tip == 'client' }">
<jsp:include page="meniuClient.jsp"/>
</c:if>
<meta charset="ISO-8859-1">
<style>
input[type="submit"]{
	border: none;
	outline: none;
	padding: 5px 20px 5px 20px;
	background: #ee7922;
	color: #000;
	font-size: 16px;
}

input[type="submit"]:hover {
	cursor: pointer;
	background: #ee7922;
	opacity: 50%;
	color: #fff;
}

.center {
	color: white;
	top: 50%;
	left: 50%;
	position: absolute;
	transform: translate(-50%,-50%);
}

body {
background: url(bus_station_night.jpg) no-repeat center center fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
	max-height: 100%;
	max-width: 100%;
	margin: auto;
	display: block;	
	background-color: #191970;
}

.error{
	position:absolute;
 	top: 20%;
  	left: 50%;
  	-ms-transform: translate(-50%, -50%);
  	transform: translate(-50%, -50%);
   	color:red
   
}
label{
	color: white;
	background-color: #337ab7;
	
}
</style>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"> 
<title>Inregistrare client</title>
</head>
<body>
<div class="center">
		<form:form action="/saveClient" modelAttribute="client">

			<div class="form-group">
	   
				<label>Email:</label>
				<form:input path="email" type="text" class="form-control" />
			</div>

			<div class="form-group">
				<label>Nume:</label>
				<form:input path="name" type="text" class="form-control" />
			</div>

			<div class="form-group">
				<label>Numar telefon:</label>
				<form:input path="phoneNumber" type="text" maxLength="10"
					minLength="10" class="form-control" />
			</div>

			<div class="form-group">
				<label>Username:</label>
				<form:input path="username" type="text" class="form-control" />
			</div>

			<div class="form-group">
				<label>Password:</label>
				<form:input path="password" type="password" class="form-control" />
			</div>

	   
			 
			<button type="submit" class="btn btn-primary">Salvare</button>


  
		</form:form>
	</div>
<div class="error">
 <p>${errorMessage}</p>
 
 </div>
</body>
</html>