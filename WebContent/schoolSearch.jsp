<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Api.SchoolFinder"%>
<%@ page import="Api.Item"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
request.setCharacterEncoding("UTF-8");
String uploadPath = request.getRealPath("upload");
String schoolNm =(String) request.getParameter("schoolNm");
SchoolFinder sf = new SchoolFinder();
ArrayList<Item> list = new ArrayList<Item>();
list = sf.getItemList(schoolNm);
//list = sf.OpenCSVSearch(schoolNm,uploadPath);
int count = 1;
if(list.isEmpty()){
    out.println("<script language='javascript'>");
     out.println("alert('일치하는 검색 결과가 없습니다. 정확한 이름으로 검색해주세요.');");
     out.println("href=history.back();");
     out.println("</script>");
     return;
}
%>

	<table>
		<thead>
			<tr>
				<th>학교ID</th>
				<th>학교 주소</th>
				<th>학교 명</th>
			</tr>
		</thead>
		<tbody>
			<%
   for (Item item : list){
   %>

			<tr>

				<td id="sch_number<%=count %>" name="sch_number<%=count %>"><%=item.getSchoolId()%></td>
				<td><%=item.getRdnmadr()%></td>
				<td id="sch_name<%=count %>" name="sch_name<%=count %>"><%=schoolNm%></td>
				<td>
					<form action="/PeerSys/page_tea/ClassManagement/addClass.jsp"
						method="post" id="form" role="form">
						<input type="hidden" name="sch_region"
							value="<%= item.getRdnmadr() %>"> <input type="hidden"
							name="sch_name" value="<%= schoolNm %>"> <input
							type="hidden" name="sch_number" value="<%= item.getSchoolId() %>">
						<input type="submit" class="btn btn-mod btn-circle btn-medium"
							value="선택">
					</form>
				</td>

			</tr>

			<%
   count++;}
   %>
		</tbody>
	</table>

</body>
</html>