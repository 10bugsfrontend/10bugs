<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
 <html>
 <head>
 	<title>Register</title>
 <meta charset="ISO-8859-1">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
 <style type="text/css">
.text-box{
    border-radius: 1rem;
    margin-top : 1rem;
}
.button-submit{
    width: 50%;
    border-radius: 1rem;
    margin-top : 1rem
}
.body-background {
    background-image: linear-gradient(to right, #4facfe 0%, #00f2fe 100%);
}
.main-label{
    margin: 1rem;
}
.container-fluid{
	margin-top : 4rem;
    padding : 3rem;
    background: white;
    border-radius: 20px;
    box-shadow: rgba(0, 0, 0, 0.1) 0px 0.5rem 1rem 0px;
	align-self: center;
	text-align: center;
	width: 50%;
 }
 @media screen and (max-width: 450px) {
		.container-fluid{
			width:90%;
		}
}
 </style>
 </head>
<body class="body-background">
    <div class="container">
            <div class="container-fluid">
            <form:form action="/saveClient" modelAttribute="client">
                <div class="d-flex justify-content-center mx-auto main-label" >
                    <h2>Register</h2>
                </div>
                <div class="form-group">
                    <form:input type="email" class="form-control text-box" path="email" aria-describedby="email" placeholder="Enter email"/>
                </div>
                <div class="form-group">
                    <form:input type="text" path="name" class="form-control text-box" id="name" placeholder="Enter name"/>
                </div>
                <div class="form-group">
                    <form:input type="text" path="phoneNumber" class="form-control text-box" id="phone" maxlength="10" minlength="10" placeholder="Phone number"/>
                </div>
                <div class="form-group">
                    <form:input type="text" path="username" class="form-control text-box" id="user" placeholder="Enter username"/>
                </div>
                <div class="form-group">
                    <form:input type="password" path="password" class="form-control text-box" id="password" placeholder="Password"/>
                </div>
                <div class="form-group justify-content-center d-flex">
                    <button type="submit" class="btn btn-primary button-submit">Inregistrare</button>
                </div>
            </form:form>
        </div>
    </div>
</body>
 </html>