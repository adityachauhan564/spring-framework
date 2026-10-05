<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Sign up</title>
  <%@ include file="head.jspf" %>
</head>
<body class="container py-4">
<%@ include file="nav.jspf" %>

<h1 class="text-center">Registration form</h1>

<%--
  Spring's form tags are connected to the "form" value in the Model (a SignupForm):
    path="email"      -> gives the input its name and its current value (kept after an error)
    <form:errors .../> -> shows the validation message for that field, if there is one
--%>
<form:form modelAttribute="form" action="processform" method="post" class="mx-auto" style="max-width: 32rem">
  <div class="mb-3">
    <label for="email" class="form-label">Email address</label>
    <form:input path="email" type="email" class="form-control" placeholder="Enter email"/>
    <form:errors path="email" cssClass="text-danger small"/>
  </div>
  <div class="mb-3">
    <label for="userName" class="form-label">User name</label>
    <form:input path="userName" class="form-control" placeholder="Enter user name"/>
    <form:errors path="userName" cssClass="text-danger small"/>
  </div>
  <div class="mb-3">
    <label for="password" class="form-label">Password</label>
    <%-- form:password never shows the typed password again, even after an error --%>
    <form:password path="password" class="form-control" placeholder="At least 8 characters"/>
    <form:errors path="password" cssClass="text-danger small"/>
  </div>
  <button type="submit" class="btn btn-success">Sign up</button>
</form:form>
</body>
</html>
