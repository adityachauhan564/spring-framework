<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>User</title>
  <%@ include file="head.jspf" %>
</head>
<body class="container py-4">
<%@ include file="nav.jspf" %>

<h1><c:out value="${user.userName}"/></h1>
<p>id: ${user.id}</p>
<p>email: <c:out value="${user.email}"/></p>
</body>
</html>
