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
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

<title>Book</title>
</head>
<body>
	<nav
		class="navbar navbar-toggleable-md navbar-inverse fixed-top bg-inverse">
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
	<section class="container">
		<div class="row">
			<div class="col-md-5"
				style="padding: 10px 10px 10px 10px; background-color: #f3ffec; border-style: dotted; border-width: 1px">
				<div class="panel panel-success">
					<div class="panel-heading">
						<h3>
							<span style="margin-bottom: 10px;">ID: ${book.id}</span>
						</h3>
					</div>
					<div class="panel-body" style="padding: 30px 30px 30px 30px;">
						<h3>
							<span style="margin-bottom: 10px;"><strong>Book
									title:</strong> </span>${book.title}
						</h3>
						<h3>
							<span style="margin-bottom: 30px;"><strong>Wrote
									by</strong></span>: ${book.authors}
						</h3>

						<script>
							function goBack() {
								window.history.back();
							}
						</script>
					</div>
				</div>
				<p>

					<button onclick="goBack()" class="btn btn-primary">
						<span class="glyphicon-hand-left glyphicon"></span> Go back
					</button>
				</p>
			</div>
		</div>
	</section>
</body>
</html>
