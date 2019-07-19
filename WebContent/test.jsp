<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page 
import ="java.text.SimpleDateFormat"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy");
String format_time1 = format1.format (System.currentTimeMillis());
%>

<%=format_time1 %>
</body>
</html>