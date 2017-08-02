<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Book deleted</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Book deleted</h1>
				<p>The book ${bookId} has been deleted!</p>
			</div>
		</div>
	</section>
	<section class="containter" style="padding:20dp">
		<div>
			<a href="/webstore/books" class="btn btn-primary btn-lg">Back to
				books</a>
		</div>
	</section>
</body>
</html>