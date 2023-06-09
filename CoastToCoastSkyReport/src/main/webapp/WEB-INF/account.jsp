<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="main.css">
<title>My Account View</title>
</head>
<body>
	<%@ include file="nav.jsp"%>
	<div style="min-height: 100vh" class="background-image">
		<div class="container-fluid">


			<c:choose>
				<c:when test="${loggedInUser.id == 1 }">
					<br>
					<h2>My Account View</h2>
					<br>

					<div class="row">
						<div class="col-lg-4">
							<div class="textbox">
								<h2>Account Details</h2>
							</div>
							<br> <img src="${loggedInUser.pictureUrl}"
								alt="sightingImage" width="300" height="200"> <br> <br>
							<div class="textbox">
								<h3>
									<c:out value="${loggedInUser.firstName }"></c:out>
									<c:out value="${loggedInUser.lastName }"></c:out>
								</h3>
								User Name:
								<c:out value="${loggedInUser.username }"></c:out>
								<br> Role:
								<c:out value="${loggedInUser.role }"></c:out>
								<br> About Me:
								<c:out value="${loggedInUser.aboutMe }"></c:out>
								</div>
								<br><div class ="textbox">
								<h2>Create Admin Account</h2>
								<form action="createAccount.do" method="post">
									<fieldset class="items add">
										<label id="one"> User Name: </label> <input type="text"
											name="username" required /> <br> <label id="two">
											Password: </label> <input type="text" name="password" required /> <br>
										<label id="three"> First Name: </label> <input type="text"
											name="firstName" /> <br> <label id="four"> Last
											Name: </label> <input type="text" name="lastName" required /> <br>
										<label id="five">Address: </label> <input type="text"
											name="address" /> <br> <label id="six">City: </label> <input
											type="text" name="city" /> <br> <label id="seven">ZipCode:
										</label> <input type="text" name="zipcode" /> <br> <label
											id="eight">State: </label> <input type="text" name="state" />
										<br> <label id="nine">Country: </label> <input
											type="text" name="country" /> <br> <label id="ten">About
											Me: </label> <input type="text" name="aboutMe" /> <br> <label
											id="eleven">Profile Picture: </label> <input type="text"
											name="profilePictureUrl" /> <br> <input type="hidden"
											name="enabled" value="1" /> <label id="twelve">User
											Role </label> <input type="text" name="role" />
									</fieldset>

									<input type="submit" value="Create Account" />
								</form>
							</div>
						</div>
						<div class="col-lg-1"></div>
						<div class="col-lg-7">
							<div class="textbox">
								<h2>Your Reported Sightings</h2>
							</div>
							<c:forEach var="sighting" items="${adminSightingList}">
								<br>
								<input type="hidden" name="id" value="${sighting.id }">
								<img src="${ sighting.pictureUrl}" alt="sightingImage"
									width="300" height="200">
								<br>
								<br>
								<div class="textbox">
									<h6>
										<a href="sightingById.do?id=${sighting.id}"
											class="link-danger">${sighting.title} </a>
									</h6>
									<ul class="list-unstyled">
										<li>${ sighting.sightingDate}</li>
										<li>${ sighting.description}</li>
									</ul>
								</div>
								<br>
								<a href="updateSighting.do?id=${sighting.id}"
									class="btn btn-dark btn-lg active" role="button">Edit
									Sighting Details</a>

								<a href="deleteSightingRouting.do?id=${sighting.id}"
									class="btn btn-dark btn-lg active" role="button">Delete
									Sightings</a>
								<br>
								<br>
							</c:forEach>


						</div>
					</div>
				</c:when>
				<c:when
					test="${not empty sessionScope.loggedInUser && sessionScope.loggedInUser.id ne 1}">
					<br>
					<h2>My Account View</h2>
					<br>
					<div class="row">
						<div class="col-lg-4">
							<div class="textbox">
								<h2>Account Details</h2>
							</div>
							<br> <img src="${loggedInUser.pictureUrl}"
								alt="sightingImage" width="300" height="200"> <br> <br>
							<div class="textbox">
								<h3>
									<c:out value="${loggedInUser.firstName }"></c:out>
									<c:out value="${loggedInUser.lastName }"></c:out>
								</h3>
								User Name:
								<c:out value="${loggedInUser.username }"></c:out>
								<br> Role:
								<c:out value="${loggedInUser.role }"></c:out>
								<br> About Me:
								<c:out value="${loggedInUser.aboutMe }"></c:out>
							</div>
						</div>
						<div class="col-lg-1"></div>
						<div class="col-lg-7">
							<div class="textbox">
								<h2>Your Reported Sightings</h2>
							</div>
							<c:forEach var="sighting" items="${sightingList}">
								<br>
								<input type="hidden" name="id" value="${sighting.id }">
								<img src="${ sighting.pictureUrl}" alt="sightingImage"
									width="300" height="200">
								<br>
								<br>
								<div class="textbox">
									<h6>
										<a href="sightingById.do?id=${sighting.id}"
											class="link-danger">${sighting.title} </a>
									</h6>
									<ul class="list-unstyled">
										<li>${ sighting.sightingDate}</li>
										<li>${ sighting.description}</li>
									</ul>
								</div>
								<br>
								<a href="updateSighting.do?id=${sighting.id}"
									class="btn btn-dark btn-lg active" role="button">Edit
									Sighting Details</a>

								<a href="deleteSightingRouting.do?id=${sighting.id}"
									class="btn btn-dark btn-lg active" role="button">Delete
									Sightings</a>
							</c:forEach>
						</div>
					</div>
				</c:when>

				<c:otherwise>
					<br>
					<h2>Not Logged In.</h2>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
		crossorigin="anonymous"></script>

</body>
</html>