<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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


<style type="text/css">
.cont .container-fluid {
	align-self: center;
	background-color: #2C2E2C;
	margin-top: 2%;
	width: 70%;
	color: white;
	padding: 1%;
	border-radius: .25rem;
	opacity: 95%;
	background-size: cover;
	font-family: "Proxima Nova", "Helvetica Neue", Helvetica, Arial,
		sans-serif;
	font-size: 2rem;
}

.point {
	display: inline-block;
	margin-right: 10px;
}

span {
	font-family: "Proxima Nova", "Helvetica Neue", Helvetica, Arial,
		sans-serif;
	font-size: 2rem;
	font-weight: 10;
	margin-right: 10px;
}

body {
	background: url(station2.jpg) no-repeat center center fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
	height: 100%;
	width: 100%;
	object-fit: contain;
}

.pagetitle {
	text-align: center;
	color: #2C2E2C;
	font-family: "Proxima Nova", "Helvetica Neue", Helvetica, Arial,
		sans-serif;
}
</style>
<title>Istoric rezervari</title>
</head>
<body>
	<div class="pagetitle">

		<h1>Istoric rezervari</h1>
	</div>
	<c:choose>
		<c:when test="${fn:length(listaRezervari)> 0}">
			<c:forEach var="rez" items="${listaRezervari}">
				<div class="cont">
					<div class="container-fluid">
						Client:
						<c:out value="${rez.client.name}" />
						<br> Firma:
						<c:out value="${rez.cursa.firma.name}" />
						<br> Data:
						<fmt:formatDate type="date" value="${rez.data}" />
						<br>
						<div class="point">

							<c:out value="${rez.plecare}" />
							<br>
							<fmt:formatDate type="time" pattern="hh:mm"
								value="${rez.cursa.ora_de_plecare}" />
						</div>
						<span>&#8594;</span>
						<div class="point">
							<c:out value="${rez.sosire}" />
							<br>
							<fmt:formatDate type="time" pattern="hh:mm"
								value="${rez.cursa.ora_de_sosire}" />
						</div>
						<br> Pret:
						<c:out value="${rez.cursa.pret}" />
						<br> Status:
						<c:out value="${rez.status}" />
						<br>
						<c:choose>
							<c:when test="${tip=='client'}">

								<c:if test="${rez.status == 'COMPLETED' && rez.getIsRated() == false}">

									<button type="button" class="btn btn-primary" id="button"
										onclick="mypopup('http://localhost:9999/secured/rating?id='+${rez.id});">Rate</button>

								</c:if>
								<c:if
												test="${rez.status.name() == 'COMPLETED' && rez.getIsRated() == true }">
												<td>
													<button type="button" class="btn btn-primary"
														id="buttonrated" disabled>Rated</button>
												</td>
											</c:if></td>

							</c:when>
							<c:otherwise>

								<c:if test="${rez.status == 'PENDING' }">
									<a href="/secured/overdue?id=<c:out value='${rez.id}' />">Overdue
									</a>
									<a href="/secured/completed?id=<c:out value='${rez.id}' />">Completed</a>
								</c:if>

							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>

			<div class="pagetitle">

				<h1>Nu exista rezervari</h1>
			</div>
		</c:otherwise>
	</c:choose>


</body>
</html>