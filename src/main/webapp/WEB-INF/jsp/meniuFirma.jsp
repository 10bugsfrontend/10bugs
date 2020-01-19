<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<style type="text/css">/* The sidepanel menu */
.sidepanel {
	height: 100%; /* Specify a height */
	width: 0; /* 0 width - change this with JavaScript */
	position: fixed; /* Stay in place */
	z-index: 1; /* Stay on top */
	top: 0;
	left: 0;
	background-color: #111; /* Black*/
	overflow-x: hidden; /* Disable horizontal scroll */
	padding-top: 60px; /* Place content 60px from the top */
	transition: 0.5s;
	/* 0.5 second transition effect to slide in the sidepanel */
	overflow-x: hidden; /* Disable horizontal scroll */
}

/* The sidepanel links */
.sidepanel a {
	padding: 8px 8px 8px 32px;
	text-decoration: none;
	font-size: 25px;
	color: #818181;
	display: block;
	transition: 0.3s;
}
.logo{
height:50px;
width:100px;
}

/* When you mouse over the navigation links, change their color */
.sidepanel a:hover {
	color: #f1f1f1;
}

/* Position and style the close button (top right corner) */
.sidepanel .closebtn {
	position: absolute;
	top: 0;
	right: 25px;
	font-size: 36px;
	margin-left: 50px;
}

/* Style the button that is used to open the sidepanel */
.openbtn {
	font-size: 20px;
	cursor: pointer;
	background-color: #111;
	color: white;
	padding: 10px 15px;
	border: none;
	display: none;
}

.navbar {
	margin-bottom: 0;
	border-radius: 0;
}

.openbtn:hover {
	background-color: #444;
}

@media screen and (max-height: 450px) {
	.sidepanel {
		padding-top: 15px;
	}
	.sidepanel a {
		font-size: 18px;
	}
	.openbtn {
		height: 100%;
	}
}

@media screen and (max-width: 450px) {
	.navbar {
		display: none;
	}
	.openbtn {
		display: inline;
	}
}
</style>
<script type="text/javascript">
	/* Set the width of the sidebar to 250px (show it) */
	function openNav() {
		document.getElementById("mySidepanel").style.width = "300px";
	}

	/* Set the width of the sidebar to 0 (hide it) */
	function closeNav() {
		document.getElementById("mySidepanel").style.width = "0";
	}
</script>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header"></div>
			<ul class="nav navbar-nav">
				<li><img src="autobugs.jpg" class="logo"></li>
				<li class="active"><a href="/secured/welcome-firma">Home</a></li>
				<li><a href="/secured/listaRezervari">Lista rezervari</a></li>
				<li><a href="/secured/alegeNrStatii">Adauga o cursa </a></li>
				<li><a href="/secured/allCurse">Toate cursele</a></li>
				<li><a href="/secured/reservationHistory">Istoric rezervari</a></li>
				<li><a href="/secured/showInfo">Modifica cont</a></li>
				<li><a href="/secured/showFactura">Factura curenta</a></li>
				<li><a href="/logout">Logout</a></li>
			</ul>
		</div>
	</nav>
	<div id="mySidepanel" class="sidepanel">
		<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
		<a href="/secured/welcome-firma">Home</a> <a
			href="/secured/listaRezervari">Lista rezervari</a> <a
			href="/secured/alegeNrStatii">Adauga o cursa</a> <a
			href="/secured/allCurse">Toate cursele</a> <a
			href="/secured/reservationHistory">Istoric rezervari</a> <a
			href="/secured/showInfo">Modfica cont</a> <a
			href="/secured/showFactura">Factura curenta</a> <a href="/logout">Logout</a>
	</div>

	<button class="openbtn" onclick="openNav()">&#9776; Menu</button>
</body>
</html>