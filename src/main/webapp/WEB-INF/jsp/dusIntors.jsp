<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/style.css">
<script type="text/javascript" src="/popupPlata.js"></script>	
 <c:choose>
 <c:when test="${tip=='client'}">
 <jsp:include page="meniuClient.jsp"></jsp:include>
 </c:when>
 <c:otherwise>
 <jsp:include page="meniuFirma.jsp"></jsp:include>
 </c:otherwise>
 </c:choose>
  <meta charset="ISO-8859-1">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
 <style type="text/css">
 	.cont .container-fluid{
 		align-self: center;
 		background-color:#2C2E2C;
 		margin-top: 2%;
		width:70%;
		color:white;
		padding: 1%;border-radius: .25rem;opacity: 95%;background-size: cover;
		font-family: "Proxima Nova", "Helvetica Neue", Helvetica, Arial, sans-serif;
 		font-size: 2rem;
 	}
 	.point{
 		display: inline-block;
 		margin-right: 10px;
 	}
 	span{
 		font-family: "Proxima Nova", "Helvetica Neue", Helvetica, Arial, sans-serif;
 		font-size: 5.25rem;
 		font-weight: 700;
 		margin-right: 10px;
 	}
 	body{
			background: url(station2.jpg) no-repeat center center fixed; 
  			-webkit-background-size: cover;
  			-moz-background-size: cover;
  			-o-background-size: cover;
  			background-size: cover;
		}
	.pagetitle{
		text-align: center;
		color: #2C2E2C;
		font-family: "Proxima Nova", "Helvetica Neue", Helvetica, Arial, sans-serif;
	}
 </style>
</head>
<body>
    <div class="pagetitle">
	<h1>Curse disponibile dus:</h1></div>
            <c:forEach var="cursa" items="${listaDus}">
            <c:url var="salveazaRezervare" value="/secured/rezervaCursa">
                	<c:param name="id" value="${cursa.id}"/>
                	<c:param name="plecare" value="${Plecare}"/>
                	<c:param name="sosire" value="${Sosire}"/>
                	<c:param name="data" value="${dataCautarii}"/>
            </c:url>
        <div class="cont">
        <div class="container-fluid">
        Traseu: <c:out value="${cursa.traseu.statieInceput}"/> - <c:out value="${cursa.traseu.statieSosire}" /><br>
			<div class="point">
				<c:out value="${Plecare}" /><br><fmt:formatDate type = "time" pattern="hh:mm" value="${cursa.ora_de_plecare}" />
			</div>
			<span>&#8594;</span>
			<div class="point">
				<c:out value="${Sosire}" /><br><fmt:formatDate type = "time" pattern="hh:mm" value="${cursa.ora_de_sosire}" />
			</div>
			<br>
		Pret:<c:out value="${cursa.pret}" /><br>
		Firma:<c:out value="${cursa.firma.name}" /><br>
		Capacitate:<c:out value="${cursa.capacitate}" /><br>
		Locuri ocupate:<c:out value="${cursa.ocupate}" /><br>
		Rating:<c:out value="${cursa.firma.rating.scor}" /><br>
		Facilitati:<c:out value="${cursa.facilitati}" /><br>
		 <c:choose>
 						<c:when test="${tip=='client'}">
 							<td>
                    	<a href="${salveazaRezervare}">Rezerva</a>
                    		</td>
						 </c:when>
 					<c:otherwise>
 							<td>
 							<a href="/secured/listaRezervariPtCursa?id=<c:out value='${cursa.id}' />">Rezervari</a>
 							</td>
 					</c:otherwise>
					 </c:choose>
	</div>
	</div>
    </c:forEach>
    <div class="pagetitle">
	<h1>Curse disponibile intors:</h1></div>
            <c:forEach var="cursa" items="${listaIntors}">
            <c:url var="salveazaRezervare" value="/secured/rezervaCursa">
                	<c:param name="id" value="${cursa.id}"/>
                	<c:param name="plecare" value="${Plecare}"/>
                	<c:param name="sosire" value="${Sosire}"/>
                	<c:param name="data" value="${dataCautarii}"/>
            </c:url>
        <div class="cont">
        <div class="container-fluid">
        Traseu: <c:out value="${cursa.traseu.statieInceput}"/> - <c:out value="${cursa.traseu.statieSosire}" /><br>
			<div class="point">
				<c:out value="${Plecare}" /><br><fmt:formatDate type = "time" pattern="hh:mm" value="${cursa.ora_de_plecare}" />
			</div>
			<span>&#8594;</span>
			<div class="point">
				<c:out value="${Sosire}" /><br><fmt:formatDate type = "time" pattern="hh:mm" value="${cursa.ora_de_sosire}" />
			</div>
			<br>
		Pret:<c:out value="${cursa.pret}" /><br>
		Firma:<c:out value="${cursa.firma.name}" /><br>
		Capacitate:<c:out value="${cursa.capacitate}" /><br>
		Locuri ocupate:<c:out value="${cursa.ocupate}" /><br>
		Rating:<fmt:formatNumber type="number" maxFractionDigits="1" value="${cursa.firma.rating.scor}" /><br>
		Facilitati:<c:out value="${cursa.facilitati}" /><br>
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
	</div>
	</div>
    </c:forEach>        
</body>
</html>