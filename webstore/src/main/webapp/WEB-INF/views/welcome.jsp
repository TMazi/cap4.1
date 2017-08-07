<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Hello</title>
</head>
<body>
	<nav class="navbar navbar-toggleable-md navbar-inverse fixed-top bg-inverse">
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
				<h1>${greeting}</h1>
				<p>${info}</p>
			</div>
		</div>
	</section>
	<div class="containter" style="Padding: 30px">
		<div class="row">
			<div class="col-sm-6 col-md-4 " style="padding-bottom: 15px">
				<div class="thumbnail">
					<div class="caption">
						<h3>Books</h3>
						<p>Display all books</p>
						<p>
							<a href="/webstore/books" class="btn btn-default"> <span
								class="glyphicon-info-sign glyphicon" /></span> Show all books
							</a>
						</p>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-4 " style="padding-bottom: 15px">
				<div class="thumbnail">
					<div class="caption">
						<h3>Add book</h3>
						<p>Create new book</p>
						<p>
							<a href="/webstore/books/add" class="btn btn-default"> <span
								class="glyphicon-info-sign glyphicon" /></span> Add book
							</a>
						</p>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-4 " style="padding-bottom: 15px">
				<div class="thumbnail">
					<div class="caption">
						<h3>Find Book</h3>
						<p>Find specified book</p>
						<p>
							<a href="/webstore/search" class="btn btn-default"> <span
								class="glyphicon-info-sign glyphicon" /></span> Find Book
							</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
