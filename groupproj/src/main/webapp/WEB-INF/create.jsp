<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create an Event</title>
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
<h3>Create a New Event</h3>
	<form:form action="/event/new" method="post" modelAttribute="event">
    <p>
        <form:label path="name">Name:</form:label>
        <form:errors path="name"/>
        <form:input path="name"/>
    </p>
    <p>
        <form:label path="description">Description:</form:label>
        <form:errors path="description"/>
        <form:textarea path="description"/>
    </p>
    <p>
        <form:label path="location">Location:</form:label>
        <form:errors path="location"/>
        <form:input path="location"/>
    </p>
    <p>
        <form:label path="eventDate">Date</form:label>
        <form:errors path="eventDate"/>     
        <form:input type="date" path="eventDate"/>
    </p>    
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>