<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="meniuClient.jsp"/>
<meta charset="ISO-8859-1">
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
        rel="stylesheet">
<title>Informatii Client</title>
</head>
<body>
<div class="center">
	<form:form action="/secured/saveClient" modelAttribute="client">
	
		<table align="center" width="60%">
			<tr>
				<td><b>Email:</b></td>
				<td><form:input path="email" readonly="true" /></td>
			</tr>
			<tr>
				<td><b>Nume:</b></td>
				<td><form:input path="name" readonly="true"/></td>
			</tr>
			<tr>
				<td><b>Numar de telefon:</b></td>
				<td><form:input path="phoneNumber" type="text" maxlength="10" minlength="10" readonly="true" /></td>
			</tr>
			<tr>
				<td><b>Username:</b></td>
				<td><form:input path="username" readonly="true" /></td>
			</tr>
			<tr>
				<td><b>Password:</b></td>
				<td><form:input type="password" path="password" readonly="true"/></td>
			</tr>
		</table>
		
	</form:form>
</div>
</body>
</html>