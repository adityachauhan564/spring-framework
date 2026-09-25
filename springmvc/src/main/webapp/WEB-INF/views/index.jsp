<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
</head>
<body>
<h1>This is Adi's Home Page</h1>
<h1>Called by home Controller</h1>
<h1>URL : /home</h1>

<%  
String name=(String)request.getAttribute("name");
Integer id=(Integer)request.getAttribute("id");
List<String> friends=(List<String>) request.getAttribute("f");

%>


<h1>Name is <%=name%></h1>
<h1>ID is <%=id%></h1>
<%
for(String s: friends){
	%>
	<h1><%=s%></h1>
	
	<% 
}
%>
</body>
</html>