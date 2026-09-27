<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Help</title>
  <%@ include file="head.jspf" %>
</head>
<body class="container py-4">
<%@ include file="nav.jspf" %>

<h1>Help page</h1>
<p>The data below came from a <code>ModelAndView</code> - data and view name in one object.</p>

<p>Name: <c:out value="${name}"/></p>
<p>Roll number: ${rollNumber}</p>
<p>Rendered at: ${time}</p>

<h2>Marks</h2>
<ul>
  <c:forEach var="mark" items="${marks}">
    <li>${mark} <c:if test="${mark >= 40}">(pass)</c:if></li>
  </c:forEach>
</ul>
</body>
</html>
