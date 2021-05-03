<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
    <%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="/css/main.css" />
    <title>Home Page</title>
</head>
<body>
 <div class="navbar">
 	<h1 class="titles"><a href="/home">Sporting events</a></h1>
    <ul class="navbarmenu">
        <li class="main"><a href="/dashboard">Home</a></li>
        <li class="main"><a href="/event/new">New</a></li>
        <li><a href="/logout">Logout</a></li>
    </ul>
</div>
<div class="container">
	<h1>Welcome <c:out value="${user.firstName}"/></h1>
	<p>Today in <%= (new java.util.Date()).toLocaleString() %> and you have <c:out value="${user.scheduledEvents.size()}"/>
	today.</p>
	<div class= "container">
		<table class="table table-dark table-hover">
				<tr>
					<th>Event Name</th>
					<th>Location</th>
					<th>Members</th>
					<th>Date</th>
				</tr>
				<tbody>
					<c:when test="${event.attendees.contains(user)}">
					<c:out value="${event}" var ="event">
					<tr>
						<td><a href= "/event/${event.index}"></a>${event.name}</td>
						<td>${event.location}</td>
						<td>${event.attendees.size()}/${event.capacity}</td>
						<td>${event.eventdate}</td>							
					</tr>
					</c:out>
					</c:when>
				</tbody>
		</table>
	</div>
	<div class="container">
	<p>Here a list of other events you may join.</p>
	<table class="table table-dark table-hover">
				<tr>
					<th>Event Name</th>
					<th>Location</th>
					<th>Members</th>
					<th>Date</th>
					<th>Creator</th>
					<th>Action</th>
				</tr>
				<tbody>
					<c:out value="${event}" var ="event">
					<tr>
						<td><a href= "/event/${event.index}"></a>${event.name}</td>
						<td>${event.location}</td>
						<td>${event.attendees.size()}/${event.capacity}</td>
						<td>${event.eventdate}</td>
						<td>${event.eventCreator}</td>
						<td>
							<c:choose>
							<c:when test="${event.attendees.contains(user)}">
							<a href="/event/remove/${user.id}">Remove</a>
							</c:when>
							<c:when test="${event.attendees} = ${event.capacity}">
							Full
							</c:when>
							<c:when test="${event.attendees.size()} < ${event.capacity}">
							<a href="/event/add/${user.id}">Join</a>
							</c:when>
							</c:choose>							
					</tr>
					</c:out>
				</tbody>
		</table>
	</div>
</div>
</body>
</html>