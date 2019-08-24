<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String stu_id = (String) session.getAttribute("stu_id");
	if(stu_id == null){
%>
<script>
alert("잘못된 접근입니다.");
location.href="/PeerSys/index.jsp";
</script>

<% return; } %>