<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script>
function mypopup(url) {
    mywindow = window.open(url, "Title",
        "location=0,status=1,scrollbars=1,resizable=1,menubar=0,toolbar=no,width=900,height=600");
    mywindow.moveTo(0, 0);
    mywindow.focus();
}
$(function f(){
        $('#button').click(function() {
            document.getElementById("button").disabled = true;
			
        });
});

</script>
<c:choose>
	<c:when test="${tip=='client'}">
		<jsp:include page="meniuClient.jsp"></jsp:include>
	</c:when>
	<c:otherwise>
		<jsp:include page="meniuFirma.jsp"></jsp:include>
	</c:otherwise>
</c:choose>
<meta charset="ISO-8859-1">
<title>Istoric rezervari</title>
</head>
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
	margin: auto;
	display: block;
}
</style>
<body>
	<c:choose>
		<c:when test="${fn:length(listaRezervari)> 0}">
			<div class="container" align="center">

				<table class=" table table-hover table-bordered ">
					<caption>
						<h2 align="center">Lista rezervari</h2>
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
							<th></th>
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
								<td><c:out value="${rez.cursa.traseu.statieInceput}" /> -
									<c:out value="${rez.cursa.traseu.statieSosire}" /></td>
								<td><fmt:formatDate type="date" value="${rez.data}" /> <fmt:formatDate
										type="time" timeStyle="short" value="${rez.cursa.ora_de_plecare}" /></td>
								<td><fmt:formatDate type="date" value="${rez.data}" /> <fmt:formatDate
										type="time" timeStyle="short" value="${rez.cursa.ora_de_sosire}" /></td>
								<td><c:out value="${rez.cursa.pret}" /></td>
								<td><c:out value="${rez.status}" /></td>
								<c:choose>
									<c:when test="${tip=='client'}">
										<td><c:if
												test="${rez.status.name() == 'COMPLETED' && rez.getIsRated() == false}">

												<td>
													<button type="button" class="btn btn-primary" id="button"
														onclick="mypopup('http://localhost:9999/secured/rating?id='+${rez.id})">Rate</button>
												</td>
											</c:if> <c:if
												test="${rez.status.name() == 'COMPLETED' && rez.getIsRated() == true }">
												<td>
													<button type="button" class="btn btn-primary"
														id="buttonrated" disabled>Rated</button>
												</td>
											</c:if></td>
									</c:when>
									<c:otherwise>
										<td><c:if test="${rez.status == 'PENDING' }">
												<a href="/secured/overdue?id=<c:out value='${rez.id}' />">Overdue
												</a>
												<a href="/secured/completed?id=<c:out value='${rez.id}' />">Completed</a>
											</c:if></td>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:when>
		<c:otherwise>

			<h2 align="center">Nu exista rezervari</h2>
		</c:otherwise>
	</c:choose>
</body>
</html>