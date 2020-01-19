<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="meniuClient.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
  <title>Pagina de rezervare</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<div class="container">
<form action="/secured/listaCurseDupaCriterii" class="form-inline">
  <div class="form-group">
  <label class="sr-only" >Plecare</label>
  <input type="text"  class="form-control" id="plecare" placeholder="Plecare" name="Plecare">
  </div>
  <div class="form-group">
  <label class="sr-only"  >Destinatie</label>
  <input type="text" class="form-control"  id="destinatie" placeholder="Destinatie" name="Destinatie">
  </div>
   <div class="form-group">
  <label class="sr-only"  >Data</label>
  <input type="date" class="form-control"  id="date"  name="date">
  </div>
 <div class="custom-control custom-radio">
  <input type="radio" class="custom-control-input" id="dus" name="ruta" >
  <label class="custom-control-label" >Dus</label>
  <br>
  <input type="radio" class="custom-control-input" id="dus-intors" name="ruta">
  <label class="custom-control-label" >Dus-Intors</label>

</div>
  <button type="submit" class="btn btn-primary">
    <span class="glyphicon glyphicon-search"></span> Cauta
  </button>
</form> 
</div>
<body>

</body>
</html>