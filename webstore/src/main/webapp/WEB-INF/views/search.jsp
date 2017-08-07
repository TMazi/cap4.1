<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Find Book</title>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<ul class="nav navbar-nav">
				<li class="active"><a href="/webstore">Home</a></li>
				<sec:authorize access="isAnonymous()">
					<li><a href="<c:url value="/login" />">Login</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li><a href="<c:url value="/logout" />">Log out</a></li>
				</sec:authorize>

			</ul>
		</div>
	</nav>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h2>Enter search criteria and press find button</h2>
			</div>
		</div>
	</section>
	<form action="/webstore/books/" method="GET">
		<div class="container">
			<div class="row" style="padding-bottom: 25px">
				<div class="col-lg-4">
					<div class="input-group input-group-lg">
						<span class="input-group-addon"><i
							class="glyphicon glyphicon-user"></i></span> <input id="author"
							type="text" name="author" class="form-control"
							placeholder="Enter book author">
					</div>
				</div>
				<div class="col-lg-4">
					<div class="input-group input-group-lg">
						<span class="input-group-addon"><i
							class="glyphicon glyphicon-book"></i> </span> <input id="title"
							type="text" name="title" class="form-control"
							placeholder="Enter book title">
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-2">
					<input type="submit" class="btn-primary btn-lg" title="Find"></input>
				</div>
			</div>
		</div>
	</form>
</body>
</html>