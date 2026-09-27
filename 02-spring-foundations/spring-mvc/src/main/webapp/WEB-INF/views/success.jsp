<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Welcome</title>
  <%@ include file="head.jspf" %>
</head>
<body class="container py-4">
<%@ include file="nav.jspf" %>

<%-- "user" arrived as a flash attribute across the redirect (Post/Redirect/Get) --%>
<h1>Welcome, <c:out value="${user.userName}"/>!</h1>
<p>Your email address is <c:out value="${user.email}"/>.</p>
<%-- The old page printed the password here. Never show or store a plain password:
     it was hashed with BCrypt before it reached the database. --%>
<p>Your password was stored as a BCrypt hash - not even this app can read it back.</p>
<p>Refresh this page: it redirects to the form instead of registering you twice.</p>
<p><a href="<c:url value='/users/${user.id}'/>">View your profile</a></p>
</body>
</html>
