<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="meniuClient.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<title>Rezervare noua</title>
</head>
<body>
 <div class="container">
<form action="/secured/saveRezervare">
<input type="hidden" id="cursId" name="cursId" value="${cursaId}"/>
<input type="hidden" name="Plecare" value="${plecare}"/>
<input type="hidden" name="Sosire" value="${sosire}"/>
<div class="form-group">
<label>Name</label>
<input id="name" name="name" type="text" class="form-control"/> 
</div>
<div class="form-group">
<label>Nr telefon</label>
<input name="phoneNumber" type="text" maxlength="10" minlength="10" class="form-control"/> <br>
</div>
<div class="form-group">
<label>Email</label>
<input name="email" type="text" class="form-control"/> <br>
</div>
 <div class="form-group">
<label>Data</label>
<input type="date" name="data" class="form-control"/> 
</div>

<button type="submit" class="btn btn-primary">Salvare</button>
</form>
</div> 

</body>
</html>