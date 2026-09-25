<%@ page import="java.util.*" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>about page </title>
</head>
<body>
<h1>Hello, Welcome to Adi's Help Page</h1>
<h2>............................</h2>

 <!--  String name=(String)request.getAttribute("name");

Integer rollnumber=(Integer)request.getAttribute("rollnumber");

LocalDateTime time=(LocalDateTime)request.getAttribute("time");
//to avoid NullPointer Exception-->
<h1>Hello my name is ${name} <%-- <%=name %> --%></h1>

<h1>Hello my RollNumber is ${rollnumber } <%-- <%=rollnumber %> --%></h1>

<h1>Date And Time is ${time} <%-- <%=time%> --%></h1>
<hr>
<c:forEach  var="item" items="${marks}"> 

<h1>${item}</h1>

</c:forEach>

<h2>End </h2>
</body>
</html>