<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous"> 
 <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>  
 <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css">
<link rel="stylesheet" href="/style.css">
<script type="text/javascript" src="/rating.js"></script>
 <meta name="viewport" content="width=device-width, initial-scale=1">
<title>Rating</title>

</head>
<style>
	@media screen and (max-width: 450px) {
		.container{
			margin-top:30%;
			width:80%;
			font-size: 18px;
		}
		.btn{
			font-size: 18px;
		}
  }
</style>
<body>

<div class="container"> 
    <h3>Rate</h3>
    <c:url var="saveRate" value="http://localhost:9999/secured/sendRating">
    <c:param name="rezId" value="${rezId}"/>
    </c:url>
 <form:form action="${saveRate}" modelAttribute="rating">
  
    <div class="pt-3">
      <form:input id="input-2" name="input-2" path="scor" class="rating rating-loading" data-min="0" data-max="5" data-step="1" value="2" data-size="md"/>
    </div>
    <button type="submit" id="submit">Trimite</button>
</form:form>
  </div>
</body>
</html>