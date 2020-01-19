<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="meniuFirma.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Formular Traseu</title>
<style>
body {
	background: url(bus_station.jpg) no-repeat center center fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
}
.container {
	position: absolute;
	top: 20%;
	left: 25%;
	width: 50%;
	color: black;
	background-color: white;
}
</style>
</head>
<body>
<div class="container">
<form:form action="/secured/saveTraseu" modelAttribute="traseu">

	<c:forEach items="${traseu.statii}" varStatus="i">
	<div class="form-group">
	<br>
		<label>Oras</label>
		<form:input path="statii[${i.index}].oras" type="text" label="Oras" class="form-control"/> <br>
	</div>
	<div class="form-group">
		<label>Urmatoarea Statie</label>
		<form:input path="statii[${i.index}].urmStatie" type="text" label="Urmatoarea Statie" class="form-control"/> <br>
	</div>
	<div class="form-group">
		<label>Km urm statie</label>
		<form:input path="statii[${i.index}].km_urmStatie" type="number" label="Km pana la urmatoarea statie" class="form-control"/><br>
	</div>
    </c:forEach>
    <br>
	
    <button type="submit" class="btn btn-primary">Salvare</button>
</form:form>
</div>
</body>
</html>