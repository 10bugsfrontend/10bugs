<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/style.css">
<script type="text/javascript" src="/popupPlata.js"></script>
<style>
.table {
	background-color: white;
}
body {
	background: url(station2.jpg) no-repeat center center fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
	max-height: 100%;
	max-width: 100%;
	margin:auto;
	display:block;
}
</style>
 <c:choose>
 <c:when test="${tip=='client'}">
 <jsp:include page="meniuClient.jsp"></jsp:include>
 </c:when>
 <c:otherwise>
 <jsp:include page="meniuFirma.jsp"></jsp:include>
 </c:otherwise>
 </c:choose>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<c:choose>
	<c:when test="${fn:length(listaCurse)> 0}">
    <div class="container" align="center">
        <table class=" table table-hover table-bordered ">
            <caption><h2 align="center" style="color:white">Lista curse disponibile</h2></caption>
            <thead>
            <tr>
            	<th> Traseu </th>
                <th>Ora plecare</th>
                <th>Ora sosire</th>
                <th>Pret(lei)</th>
                <th>Frecventa</th>
                <th>Locuri ocupate</th>
                <th>Firma</th>
                <th>Rating</th>
                <th>Capacitate</th>
                <th>Facilitati</th>
                <th></th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="cursa" items="${listaCurse}">
                <tr>
                	<c:url var="salveazaRezervare" value="/secured/rezervaCursa">
                	<c:param name="id" value="${cursa.id}"/>
                	<c:param name="plecare" value="${Plecare}"/>
                	<c:param name="sosire" value="${Sosire}"/>
                	<c:param name="data" value="${dataCautarii}"/>
                	</c:url>
                	<td><c:out value="${cursa.traseu.statieInceput}"/> - <c:out value="${cursa.traseu.statieSosire}" /> </td>
                    <td><fmt:formatDate type = "time" timeStyle="short" value="${cursa.ora_de_plecare}" /></td>
                    <td><fmt:formatDate type = "time" timeStyle="short" value="${cursa.ora_de_sosire}" /></td>
                    <td><c:out value="${cursa.pret}" /></td>
                    <td><c:out value="${cursa.frecventa}" /></td>
                    <td><c:out value="${cursa.ocupate}" /></td>
                     <td><c:out value="${cursa.firma.name}" /></td>
                     <td><fmt:formatNumber type="number" maxFractionDigits="1" value="${cursa.firma.rating.scor}" /></td>
                    <td><c:out value="${cursa.capacitate}" /></td>
                    <td><c:out value="${cursa.facilitati}" /></td>
                     <c:choose>
 						<c:when test="${tip=='client' && cursa.ocupate < cursa.capacitate}">
 							<td>
 							<div class="popup" onclick="myFunction()">
                    	<a  class="btn btn-primary" id="rezbutton" href="${salveazaRezervare}">Rezerva</a>
                    	<span class="popuptext" id="myPopup">Cursa a fost rezervata cu succes!</span>
                    	</div>
                    		</td>
						 </c:when>
 					<c:otherwise>
 							<td>
 							
 							<a class="btn btn-primary" id="rezbutton" href="/secured/listaRezervariPtCursa?id=<c:out value='${cursa.id}' />">Rezervari</a>
 							
 							</td>
 					</c:otherwise>
					 </c:choose>
                    
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>	
    </c:when>
		<c:otherwise>
		
		<h2 align="center">Nu exista curse</h2>
		</c:otherwise>
		</c:choose>
  
</body>
</html>