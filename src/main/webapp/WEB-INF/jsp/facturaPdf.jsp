<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="meniuFirma.jsp"></jsp:include>
<meta charset="ISO-8859-1">
<title>Factura firma</title>
</head>
<style>
html, body { height: 100%; }
</style>
<body>
<object data="${pageContext.request.contextPath}/raportFacturi.pdf" 
type="application/pdf" width="100%" height="100%">
<a href="${pageContext.request.contextPath}/raportFacturi.pdf">Download file.pdf</a>
</object>
</body>
</html>