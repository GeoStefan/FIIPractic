<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FIIMDb - insert movie</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/site.css">

<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>


<body>
	<jsp:useBean id="movieBean" class="eu.ubis.fiimdb.controller.MovieBean" scope="request"></jsp:useBean>

	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<div class="navbar-brand">
					<a href="#"> Java Awesome Training Logo &copy; FII Practic 2017
					</a>
					<div class="nav navbar-nav navbar-right">
						<div class="dropdown">
							<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown"><%=request.getRemoteUser() %>
  							<span class="caret"></span></button>
							<ul class="dropdown-menu" >
								<li>
									<form action="<%=response.encodeURL("UserServlet?action=logout") %>"  method="post">
		                    				<button type="submit" class="btn btn-default center-block">Logout</button>
		                				</form>	
								</li>
							</ul>
						</div>
					</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="movies">Home</a></li>
					<li class="active"><a href="movie-insert.jsp">Insert movie</a></li>
				</ul>
			</div>
	</nav>

	<div class="container">
		<fieldset>
			<legend>Movie details</legend>

			<form method="post" action="InsertServlet">

				<div class="form-group">
					<label for="name">Name:</label> 
					<input type="text" name="name" class="form-control" id="name">
				</div>
				
				<div class="form-group">
					<label for="rating">Rating:</label> 
					<input type="number" name="rating" class="form-control" id="rating" value="0">
				</div>
				
				<div class="form-group">
					<label for="length">Length:</label> 
					<input type="number" name="length" class="form-control" id="length" value="0">
				</div>
				
				<div class="form-group">
					<label for="casting">Casting:</label> 
					<input type="text" name="casting" class="form-control" id="casting">
				</div>
				
				<div class="form-group">
					<label for="director">Director:</label> 
					<input type="text" name="director" class="form-control" id="director">
				</div>
				
				<div class="form-group">
					<label for="description">Description:</label> 
					<textarea name="description" id="description" class="form-control rows="10" cols="50"></textarea>
				</div>
				
				<div class="form-group">
					<label for="writer">Writer:</label> 
					<input type="text" name="writer" class="form-control" id="writer">
				</div>
				
				<div class="form-group">
					<label for="writer">Genres:</label> 
					<input type="text" name="genres" class="form-control" id="genres">
				</div>
				
				<button type="submit" class="btn btn-primary">Insert</button>
			</form>

		</fieldset>
	</div>


</body>
</html>
