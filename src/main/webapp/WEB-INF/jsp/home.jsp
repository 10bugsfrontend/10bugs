<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<title>Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css" />
<script>
	$(document).ready(
			function() {
				var date_input = $('input[name="date"]'); //our date input has the name "date"
				var container = $('.bootstrap-iso form').length > 0 ? $(
						'.bootstrap-iso form').parent() : "body";
				date_input.datepicker({
					format : 'yyyy-mm-dd',
					container : container,
					todayHighlight : true,
					autoclose : true,
				});
			});
	function validateDate() {
		var currentDate = new Date();
		var val = document.getElementById("date").value;
		var compDate = new Date(val);
		if (compDate < currentDate) {
			document.getElementById("date").value = "";
			window.alert("Nu se pot efectua rezervari in trecut");
		}
	}
</script>
<style type="text/css">
.bg {
	background: url(bus_station.jpg);
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-size: cover;
	min-width: 100%;
	min-height: 100%;
	width: 100px;
	height: auto;
	position: absolute;
	top: 0;
	left: 0;
	margin: auto;
	overflow: hidden;
}

.cont .container-fluid {
	margin-top: 10%;
	width: 60%;
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
</style>
</head>
<body style="height: 100%">
	<div class="bg">
		<jsp:include page="meniuClient.jsp" />
		<div class="cont">
			<div class="container-fluid"
				style="align-self: center; background-color: #222c1f; padding: 1%;; border-radius: .25rem; opacity: 92%; background-size: cover; color: white">
				<form action="/secured/listareCurseDupaCriterii">
					<div class="form-group">
						<label class="control-label" for="date">Data</label> <input
							class="form-control" id="date" name="date"
							placeholder="YYYY-MM-DD" type="date" onchange="validateDate()" />
					</div>
					<div class="form-group">
						<label for="text">Statie plecare:</label> <input type="text"
							class="form-control" id="plecare" name="plecare">
					</div>
					<div class="form-group">
						<label for="text">Statie sosire:</label> <input type="text"
							class="form-control" id="destinatie" name="destinatie">
					</div>
					<div class="form-group form-check">
						<label class="form-check-label"> <input
							class="form-check-input" type="checkbox" id="dus-intors"
							value="dusintors" name="ruta"> Dus-Intors
						</label>
					</div>
					<div class="form-group form-check" style="display: none;">
						<label class="form-check-label"> <input
							class="form-check-input" type="checkbox" id="dus" value="on"
							name="ruta" checked> Dus-Intors
						</label>
					</div>
					<button type="submit" class="btn btn-primary">
						<span class="glyphicon glyphicon-search"></span> Cauta
					</button>
				</form>
				<div class="error">
					<p>${errorMessage}</p>
				</div>
				<div class="errorDate">
					<p>${errorMessageDate}</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>