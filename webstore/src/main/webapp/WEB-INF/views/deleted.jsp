<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Book deleted</title>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<ul class="nav navbar-nav">
				<li class="active"><a href="/webstore">Home</a></li>
				<sec:authorize access="isAnonymous()">
					<li><a href="login">Login</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li><a href="logout">Log out</a></li>
				</sec:authorize>

			</ul>
		</div>
	</nav>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Book deleted</h1>
				<p>The book '${title}' has been deleted!</p>
			</div>
		</div>
	</section>
	<section class="containter" style="padding: 20px">
		<div>
			<a href="/webstore/books" class="btn btn-primary btn-lg">Back to
				books</a>
		</div>
	</section>
</body>
</html>