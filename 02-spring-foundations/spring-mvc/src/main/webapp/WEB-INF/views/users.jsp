<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Users</title>
  <%@ include file="head.jspf" %>
</head>
<body class="container py-4">
<%@ include file="nav.jspf" %>

<h1>Registered users</h1>
<c:choose>
  <c:when test="${empty users}">
    <p>No users yet - <a href="<c:url value='/contact'/>">sign up</a> first.</p>
  </c:when>
  <c:otherwise>
    <table class="table">
      <tr><th>id</th><th>user name</th><th>email</th></tr>
      <c:forEach var="u" items="${users}">
        <tr>
          <td><a href="<c:url value='/users/${u.id}'/>">${u.id}</a></td>
          <td><c:out value="${u.userName}"/></td>
          <td><c:out value="${u.email}"/></td>
        </tr>
      </c:forEach>
    </table>
  </c:otherwise>
</c:choose>
</body>
</html>
