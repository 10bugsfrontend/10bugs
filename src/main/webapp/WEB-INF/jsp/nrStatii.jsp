<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>nrStatii</title>
<meta charset="utf-8">
<style>
.container {
	color: white;
	top: 50%;
	left: 80%;
	position: fixed;
	transform: translate(-50%, -50%);
}

body {
	background: url(bus_station.jpg) no-repeat center center fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
}
</style>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="meniuFirma.jsp"></jsp:include>
	<div class="container">
		<form action="/secured/addTraseu" class="form-inline">
			<div class="form-group">
				<label for="text">Alege numar statii:</label> <br> <input
					type="text" class="form-control" id="nrStatii" placeholder="0"
					name="nrStatii">
			</div>
			<br>
			<button style="margin-top: 1%;" type="submit" class="btn btn-primary">
				Salveaza</button>
		</form>
	</div>
</body>
</html>