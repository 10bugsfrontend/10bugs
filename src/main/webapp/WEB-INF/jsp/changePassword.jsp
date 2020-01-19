<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<c:choose>
 <c:when test="${tip=='client'}">
 <jsp:include page="meniuClient.jsp"></jsp:include>
 </c:when>
 <c:otherwise>
 <jsp:include page="meniuFirma.jsp"></jsp:include>
 </c:otherwise>
 </c:choose>
<meta charset="ISO-8859-1">
<style>
.error{
	position:absolute;
 	top: 20%;
  	left: 50%;
  	-ms-transform: translate(-50%, -50%);
  	transform: translate(-50%, -50%);
   	color:red
   
}
</style>
<title>Change Password</title>
</head>
<body>
<div class="container">
<form action="/secured/changePassword">
<table>

<tr>
<td>
  <div class="form-group">
  <label>Noua parola:</label>
  <input type="text"  class="form-control" id="parolaNoua" name="parolaNoua">
  </div>
  </td>
  </tr>
  <tr>
  <td>
  <div class="form-group">
  <label>Confirmare parola:</label>
  <input type="text" class="form-control"  id="cparola" name="cparola">
  </div>
  </td>
  </tr>
  <tr>
  <td>
  <button type="submit">Save</button>
  </td>
  </tr>
  </table>
 </form>
 </div>
 
 <div class="error">
 <p>${errorMessage}</p>
 
 </div>

</body>
</html>