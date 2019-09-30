<%@page import="School.SchoolDAO.SchoolDAO"%>
<%@page import="Service.SchoolService"%>
<%@page import="School.SchoolDTO.SchoolDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Api.SchoolFinder"%>
<%@ page import="Api.Item"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학교 검색</title>
<link rel="stylesheet" href="/PeerSys/style/css/bootstrap.min.css">
<link rel="stylesheet" href="/PeerSys/style/css/style.css?version=1">
<link rel="stylesheet" href="/PeerSys/style/css/style-responsive.css">
<link rel="stylesheet" href="/PeerSys/style/css/vertical-rhythm.min.css">
<link rel="stylesheet" href="/PeerSys/style/css/owl.carousel.css">
<link rel="stylesheet" href="/PeerSys/style/css/magnific-popup.css">
</head>
<body class="appear-animate">
	<%
request.setCharacterEncoding("UTF-8");
String uploadPath = request.getRealPath("upload");
String schoolNm =(String) request.getParameter("schoolNm");
SchoolFinder sf = new SchoolFinder();
ArrayList<Item> list = new ArrayList<Item>();

//api를 사용하는 schoolList 객체
//list = sf.getItemList(schoolNm);

//csv를 사용하는 schoolList 객체
//list = sf.OpenCSVSearch(schoolNm,uploadPath);

//DB를 사용하는 schoolList 객체
list = SchoolDAO.getInstance().getSchoolList(schoolNm);

int count = 1;
if(list.isEmpty()){
    out.println("<script language='javascript'>");
     out.println("alert('일치하는 검색 결과가 없습니다. 정확한 이름으로 검색해주세요.');");
     out.println("href=history.back();");
     out.println("</script>");
     return;
}
%>

	<!-- Page Loader -->
	<div class="page-loader">
		<div class="loader">Loading...</div>
	</div>
	<!-- End Page Loader -->
	<!-- Navigation panel -->
	<%@include file="../../pageInclude/Header.jsp"%>
	<!-- End Navigation panel -->
	<section class=""page-section">
		<div class="container relative">
			<h3 style="text-align: center;">본인이 소속하신 학교를 선택해주세요.</h3><br><br>
			<table style="text-align:center; margin:auto" class="table table-striped">
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
					<td id="sch_name<%=count %>" name="sch_name<%=count %>"><%=item.getSchoolname()%></td>
					<td>
						<form action="/PeerSys/page_tea/ClassManagement/addClass.jsp"
							method="post" id="form" role="form">
							<input type="hidden" name="sch_region"
								value="<%= item.getRdnmadr() %>"> <input type="hidden"
								name="sch_name" value="<%= schoolNm %>"> <input
								type="hidden" name="sch_number" value="<%= item.getSchoolId() %>">
							<input type="submit" class="btn btn-mod btn-circle btn-small"
								value="선택">
						</form>
					</td>
	
				</tr>

				<%
			   count++;}
			   %>
			</tbody>
		</table>
		</div>
	</section>
	<script type="text/javascript"
		src="/PeerSys/style/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript"
		src="/PeerSys/style/js/jquery.easing.1.3.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/SmoothScroll.js"></script>
	<script type="text/javascript"
		src="/PeerSys/style/js/jquery.scrollTo.min.js"></script>
	<script type="text/javascript"
		src="/PeerSys/style/js/jquery.localScroll.min.js"></script>
	<script type="text/javascript"
		src="/PeerSys/style/js/jquery.viewport.mini.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/jquery.countTo.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/jquery.appear.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/jquery.sticky.js"></script>
	<script type="text/javascript"
		src="/PeerSys/style/js/jquery.parallax-1.1.3.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/jquery.fitvids.js"></script>
	<script type="text/javascript"
		src="/PeerSys/style/js/owl.carousel.min.js"></script>
	<script type="text/javascript"
		src="/PeerSys/style/js/isotope.pkgd.min.js"></script>
	<script type="text/javascript"
		src="/PeerSys/style/js/imagesloaded.pkgd.min.js"></script>
	<script type="text/javascript"
		src="/PeerSys/style/js/jquery.magnific-popup.min.js"></script>
	<!-- Replace test API Key "AIzaSyAZsDkJFLS0b59q7cmW0EprwfcfUA8d9dg" with your own one below 
        **** You can get API Key here - https://developers.google.com/maps/documentation/javascript/get-api-key -->
	<script type="text/javascript" src="/PeerSys/style/js/wow.min.js"></script>
	<script type="text/javascript"
		src="/PeerSys/style/js/masonry.pkgd.min.js"></script>
	<script type="text/javascript"
		src="/PeerSys/style/js/jquery.simple-text-rotator.min.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/all.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/contact-form.js"></script>
	<script type="text/javascript"
		src="/PeerSys/style/js/jquery.ajaxchimp.min.js"></script>
</body>
</html>