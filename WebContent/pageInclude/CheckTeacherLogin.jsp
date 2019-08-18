<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
if(session.getAttribute("tea_id") == null) { %>
<script>
	alert("로그인을 해주시기 바랍니다.");
	location.href="/PeerSys/teacherIndex.jsp";
	</script>
<%	
return; }else if(session.getAttribute("admin_id")!=null || session.getAttribute("stu_id")!=null){%>
<script>
	alert("선생님만 사용할수 있는 페이지입니다.");
	location.href="/PeerSys/teacherIndex.jsp";
	</script>
<%
return; }
%>