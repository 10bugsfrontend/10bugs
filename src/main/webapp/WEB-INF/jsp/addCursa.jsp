<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="meniuFirma.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
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
<meta charset="ISO-8859-1">
<title>Adauga cursa</title>
</head>
<body>
	<c:url var="saveCursa" value="/secured/saveCursa">
		<c:param name="trasId" value="${traseuId}" />
	</c:url>
	<div class="container">
		<form:form action="${saveCursa}" modelAttribute="cursa">
			<div class="form-group">
				<label>Frecventa(separate prin virgula)</label>
				<form:input path="frecventa" type="text" class="form-control" />
			</div>
			<div class="form-group">
				<label>Pret</label>
				<form:input path="pret" type="number" class="form-control" />
			</div>
			<div class="form-group">
				<label>Ora plecare</label>
				<form:input path="ora_de_plecare" type="time" class="form-control" />

			</div>
			<div class="form-group">
				<label>Ora de sosire</label>
				<form:input path="ora_de_sosire" type="time" class="form-control" />

				<div class="form-group">
					<label>Capacitate</label>
					<form:input path="capacitate" type="number" class="form-control" />
				</div>
				<div class="form-group">
					<label>Facilitati</label>
					<form:input path="facilitati" type="text" class="form-control" />
				</div>
				<button type="submit" class="btn btn-primary">Salvare</button>
			</div>
		</form:form>
	</div>
</body>
</html>