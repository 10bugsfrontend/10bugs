<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="meniuClient.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
 
<link rel="stylesheet" href="/style.css">
<script type="text/javascript" src="/popupPlata.js"></script>													 
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container" align="center">
        <table class=" table table-hover table-bordered ">
            <caption><h2 align="center">Lista curse disponibile dus</h2></caption>
            <thead>
           <tr>
            	<th> Traseu </th>
                <th>Plecare</th>
                <th>Sosire</th>
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
            <c:forEach var="cursa" items="${listaDus}">
                <tr>
                	<c:url var="salveazaRezervare" value="/secured/rezervaCursa">
                	<c:param name="id" value="${cursa.id}"/>
                	<c:param name="plecare" value="${Plecare}"/>
                	<c:param name="sosire" value="${Sosire}"/>
                	<c:param name="data" value="${dataCautarii}"/>
                	</c:url>
                	<td><c:out value="${cursa.traseu.statieInceput}"/> - <c:out value="${cursa.traseu.statieSosire}" /> </td>
                    <td><c:out value="${Plecare}" /></td>
                    <td><c:out value="${Sosire}" /></td>
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
 						<c:when test="${tip=='client'}">
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
  
        <table class=" table table-hover table-bordered ">
            <caption><h2 align="center">Lista curse disponibile intors</h2></caption>
            <thead>
            <tr>
            	<th> Traseu </th>
                <th>Plecare</th>
                <th>Sosire</th>
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
            <c:forEach var="cursa" items="${listaIntors}">
                <tr>
                	<c:url var="salveazaRezervare" value="/secured/rezervaCursa">
                	<c:param name="id" value="${cursa.id}"/>
                	<c:param name="plecare" value="${Plecare}"/>
                	<c:param name="sosire" value="${Sosire}"/>
                	<c:param name="data" value="${dataCautarii}"/>
                	</c:url>
                	<td><c:out value="${cursa.traseu.statieInceput}"/> - <c:out value="${cursa.traseu.statieSosire}" /> </td>
                    <td><c:out value="${Plecare}" /></td>
                    <td><c:out value="${Sosire}" /></td>
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
 						<c:when test="${tip=='client'}">
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
    </div>	
</body>
</html>