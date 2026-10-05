<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Home</title>
  <%@ include file="head.jspf" %>
</head>
<body class="container py-4">
<%@ include file="nav.jspf" %>

<h1>Home page</h1>
<p>Rendered by <code>HomeController.home()</code> for the URL <code>/home</code>.</p>

<%-- EL: ${name} reads the "name" value that the controller put into the Model --%>
<p>Name: <c:out value="${name}"/></p>
<p>ID: ${id}</p>

<h2>Friends</h2>
<ul>
  <%-- a JSTL loop (JSTL = ready-made JSP tags) over the "friends" list from the Model --%>
  <c:forEach var="friend" items="${friends}">
    <li><c:out value="${friend}"/></li>
  </c:forEach>
</ul>

<%--
  The OLD way: scriptlets (Java code written inside the page). Avoid it:
  it mixes logic with HTML, and needs casts everywhere:
    <% String name = (String) request.getAttribute("name"); %>
    <h1>Name is <%= name %></h1>
    <% for (String s : (List<String>) request.getAttribute("friends")) { %> <li><%= s %></li> <% } %>
--%>
</body>
</html>
