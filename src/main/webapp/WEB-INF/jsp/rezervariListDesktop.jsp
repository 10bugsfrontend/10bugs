<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/secured/style.css">
<script type="text/javascript" src="/secured/popupPlata.js"></script>
<c:choose>
	<c:when test="${tip=='client'}">
		<jsp:include page="meniuClient.jsp"></jsp:include>
	</c:when>
	<c:otherwise>
		<jsp:include page="meniuFirma.jsp"></jsp:include>
	</c:otherwise>
</c:choose>
<title>Lista rezervari</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<style type="text/css">
.table {
	background-color: white;
}

.bg {
	background-image: url(bus_station.jpg);
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-size: cover;
	min-height:100%;
	min-width:100%;
	width:100px;
	height:auto;
	position:fixed;
}
@media screen and (max-width: 1024px) {
	.bg {
		background-image: none;
	}
	body {
		background: url(bus_station.jpg) no-repeat center center fixed;
		-webkit-background-size: cover;
		-moz-background-size: cover;
		-o-background-size: cover;
		background-size: cover;
	}
}

@media screen and (max-width: 450px) {
	.cont .container-fluid {
		margin-top: 30%;
		width: 80%;
		font-size: 18px;
	}
	.btn {
		font-size: 18px;
	}
}
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
<body>
<div class="bg">								   
	<div class="container" align="center">
		<c:if test="${tip != 'client' && idCursa != null}">
		
		<c:url var="rezervariCursa" value="/secured/listaRezervariPtCursa">
		<c:param name="id" value="${idCursa}"/>
		</c:url>
			<form action="${rezervariCursa}">
				<input type="hidden" name="id" value="${idCursa}" /> <input
					id="date" name="date" placeholder="YYYY-MM-DD" type="date" />
				<button type="submit" class="btn btn-primary">
				<span class="glyphicon glyphicon-search"></span> Cauta
				</button>
			</form>
	</c:if>
	<c:choose>
	<c:when test="${fn:length(listaRezervari)> 0}">
		<table class=" table table-hover table-bordered ">
			<caption>
				<h2 align="center" style="color:white">Lista rezervari</h2>
			</caption>
		
			<thead>
				<tr>
					<c:choose>
						<c:when test="${tip=='client'}">
							<th>Firma</th>
						</c:when>
						<c:otherwise>
							<th>Nume</th>
							<th>Nr telefon</th>
							<th>Email</th>
						</c:otherwise>
					</c:choose>
					<th>Plecare</th>
					<th>Sosire</th>
					<th>Traseu</th>
					<th>Ora plecare</th>
					<th>Ora sosire</th>
					<th>Pret(lei)</th>
					<th>Status</th>
					<th>Platit</th>
					<th>&nbsp;</th>
					<th>&nbsp;</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="rez" items="${listaRezervari}">
					<tr>
						<c:choose>
							<c:when test="${tip=='client'}">
								<td><c:out value="${rez.cursa.firma.name}" /></td>
							</c:when>
							<c:otherwise>
								<td><c:out value="${rez.client.name}" /></td>
								<td><c:out value="${rez.client.phoneNumber}" /></td>
								<td><c:out value="${rez.client.email}" /></td>
							</c:otherwise>
						</c:choose>
						<td><c:out value="${rez.plecare}" /></td>
						<td><c:out value="${rez.sosire}" /></td>
						<td><c:out value="${rez.cursa.traseu.statieInceput}" /> - <c:out value="${rez.cursa.traseu.statieSosire}" /> </td>
						<td><fmt:formatDate type="date" value="${rez.data}" /> <fmt:formatDate
								type="time" timeStyle="short" value="${rez.cursa.ora_de_plecare}" /></td>
						<td><fmt:formatDate type="date" value="${rez.data}" /> <fmt:formatDate
								type="time" timeStyle="short" value="${rez.cursa.ora_de_sosire}" /></td>
						<td><c:out value="${rez.cursa.pret}" /></td>
						<td><c:out value="${rez.status}" /></td>
						<td><c:out value="${rez.isPaid()}" /></td>
						<c:choose>
							<c:when test="${tip=='client' && rez.status.name() == 'ACTIVE'}">
								<td>
									<div class="cancelpopup" onclick="cancelFunction()">
										<a class="btn btn-primary" id="button"
											href="/secured/cancelReservation?id=<c:out value='${rez.id}' />">Anulare</a>
										<span class="canceltext" id="anulare">Cursa a fost
											anulata cu succes!</span>
									</div>
								</td>
								<c:choose>
										<c:when test="${rez.status.name() == 'ACTIVE' && rez.isPaid() == false}">
										<td><div class="popup" onclick="myFunction()">
												<a class="btn btn-primary" id="paybutton" href="/secured/plata?id=<c:out value='${rez.id}'/>">Plateste!</a>
												<span class="popuptext" id="myPopup">Plata a fost
													facuta cu succes!</span>
											</div></td>
									</c:when>
										<c:when test="${rez.isPaid() == true }">
										<td>
											<button type="button" class="btn btn-primary"
												id="buttonrated" disabled>Platit</button>
										</td>
									</c:when>
									<c:otherwise>
										<td>&nbsp;</td>
									</c:otherwise>
								</c:choose>
							</c:when>

							<c:otherwise>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</c:otherwise>
						</c:choose>
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</c:when>
		<c:otherwise>
		
		<h2 align="center" style="color: white">Nu exista rezervari</h2>
		</c:otherwise>
		</c:choose>
	</div>
	</div>

</body>
</html>