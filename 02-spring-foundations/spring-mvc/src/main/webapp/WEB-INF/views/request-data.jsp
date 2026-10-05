<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Request data</title>
  <%@ include file="head.jspf" %>
</head>
<body class="container py-4">
<%@ include file="nav.jspf" %>

<h1>Reading request data</h1>
<p>Read with: <code><c:out value="${source}"/></code></p>
<%-- c:out escapes HTML (shows < and > as plain text instead of running them).
     The value comes from the URL, so printing it raw with ${value} would let anyone
     put a <script> tag into the page (this attack is called XSS). Try /greet?name=<b>bold</b> --%>
<p class="fs-4"><c:out value="${value}"/></p>
</body>
</html>
