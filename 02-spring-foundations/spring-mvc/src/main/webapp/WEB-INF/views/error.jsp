<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Error ${status}</title>
  <%@ include file="head.jspf" %>
</head>
<body class="container py-4">
<%@ include file="nav.jspf" %>

<%-- chosen by PageExceptionHandler; the HTTP status code is set by @ResponseStatus --%>
<h1 class="text-danger">Error ${status}</h1>
<p><c:out value="${message}"/></p>
<p><a href="<c:url value='/'/>">Back to the home page</a></p>
</body>
</html>
