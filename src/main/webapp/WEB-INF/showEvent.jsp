<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
    <%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Event Details</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="/css/main.css" />
</head>
<body>
	 <div class="navbar">
 	<h1 class="titles"><a href="/home">Sporting events</a></h1>
    <ul class="navbarmenu">
        <li class="main"><a href="/dashboard">Home</a></li>
        <li class="main"><a href="/events/new">New</a></li>
        <li><a href="/logout">Logout</a></li>
    </ul>
</div>
	<p>Event Date: Title: <c:out value="${event.name}"/>, Date: <c:out value="${event.eventDate}"/></p>
	<p>Information: Location <c:out value="${event.location}"/>, <c:out value="${event.eventCreator}"/></p>
	<p>Users: <c:out value="${event.attendees.size()}/${event.capacity}"/></p>
</body>
</html>