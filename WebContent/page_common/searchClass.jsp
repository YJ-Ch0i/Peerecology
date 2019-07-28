<%@page import="User.UserDTO.TeacherDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Service.SchoolService"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="School.SchoolDTO.SchoolDTO"%>
<!DOCTYPE html>
<html>
<head>
<title>Rhythm &mdash; One & Multi Page Creative Theme</title>
<meta name="description" content="">
<meta name="keywords" content="">
<meta charset="utf-8">
<meta name="author" content="Roman Kirichik">
<!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

<!-- Favicons -->
<link rel="shortcut icon" href="/PeerSys/style/images/favicon.png">

<!-- CSS -->
<link rel="stylesheet" href="/PeerSys/style/css/bootstrap.min.css">
<link rel="stylesheet" href="/PeerSys/style/css/style.css">
<link rel="stylesheet" href="/PeerSys/style/css/style-responsive.css">
<link rel="stylesheet" href="/PeerSys/style/css/animate.min.css">
<link rel="stylesheet" href="/PeerSys/style/css/vertical-rhythm.min.css">
<link rel="stylesheet" href="/PeerSys/style/css/owl.carousel.css">
<link rel="stylesheet" href="/PeerSys/style/css/magnific-popup.css">

</head>
<body class="appear-animate">

	<!-- Page Loader -->
	<div class="page-loader">
		<div class="loader">Loading...</div>
	</div>
	<!-- End Page Loader -->
	<!-- Navigation panel -->
	<%@include file="../../pageInclude/Header.jsp"%>
	<!-- End Navigation panel -->

	<section class="page-section">
		<div class="container relative">
		<h3 style="text-align: center;">본인의 반을 선택해주세요.</h3><br><br>
			<div class="works-filter font-alt align-center">
				<% boolean isElementalSchool = (boolean) request.getAttribute("isElementalSchool"); %>

				<a href="#" class="filter active" data-filter="*">모든 학년</a> <a
					href="#firstGrade" class="filter" data-filter=".firstGrade">1학년
					학급</a> <a href="#secondGrade" class="filter" data-filter=".secondGrade">2학년
					학급</a> <a href="#thirdGrade" class="filter" data-filter=".thirdGrade">3학년
					학급</a>
				<% if(isElementalSchool) {%>

				<a href="#4thGrade" class="filter" data-filter=".4thGrade">4학년
					학급</a> <a href="#5thGrade" class="filter" data-filter=".5thGrade">5학년
					학급</a> <a href="#6thGrade" class="filter" data-filter=".6thGrade">6학년
					학급</a>

				<%} %>
			</div>
			<!-- End Works Filter -->

			<!-- Works Grid -->
			<ul
				class="works-grid work-grid-5 clearfix font-alt hover-white hide-titles"
				id="work-grid">
				<%
                    ArrayList<TeacherDTO> list = (ArrayList<TeacherDTO>) request.getAttribute("list");
                    SchoolService service = SchoolService.getInstance();
        			%>

				<% for(int i=0; i<list.size(); i++){ %>
				
				<% if(list.get(i).getGrade() == 1) {%>
				<li class="work-item mix firstGrade">
				<%} %> 
				<% if(list.get(i).getGrade() == 2) {%>
				
				<li class="work-item mix secondGrade">
					<%} %> <% if(list.get(i).getGrade() == 3) {%>
				
				<li class="work-item mix thirdGrade">
					<%} %> <% if(list.get(i).getGrade() == 4) {%>
				
				<li class="work-item mix 4thGrade">
					<%} %> <% if(list.get(i).getGrade() == 5) {%>
				
				<li class="work-item mix 5thGrade">
					<%} %> <% if(list.get(i).getGrade() == 6) {%>
				
				<li class="work-item mix 6thGrade">
					<%} %> 
				<%if(!request.getAttribute("adminSearching").equals("admin")){%><form
						action="/PeerSys/studentSearch.st" method="post">
						<%}else{ %>
						<form action="/PeerSys/surveyStart.sv" method="post">
							<% }%>
							<input type="hidden" name="tea_id" value="<%= list.get(i).getTID()%>">
							<input type="hidden" name="grade" value="<%= list.get(i).getGrade() %>"> 
							<input type="hidden" name="grd_num" value="<%= list.get(i).getClasses()%>">
							<input type="hidden" name="sch_code" value="<%= request.getAttribute("sch_code") %>">
							<input type="hidden" name="sch_name" value="<%= request.getAttribute("sch_name") %>"> 
							<input
								type="submit" value="<%= list.get(i).getGrade()%>학년 <%=list.get(i).getClasses()%>반"
								style="margin: 0% 0% 0% 15%"
								class="btn btn-mod btn-circle btn-small">
						</form>
				</li>

				<%} %>
			</ul>

			<div class="row"></div>
			<!-- End Row -->

		</div>
	</section>
	<!-- End Section -->


	<!-- Footer -->
	<%@ include file="../pageInclude/Footer.jsp"%>
	<!-- End Footer -->

	</div>
	<!-- End Page Wrap -->


	<!-- JS -->
	<script type="text/javascript" src="/PeerSys/style/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/jquery.easing.1.3.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/SmoothScroll.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/jquery.scrollTo.min.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/jquery.localScroll.min.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/jquery.viewport.mini.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/jquery.countTo.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/jquery.appear.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/jquery.sticky.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/jquery.parallax-1.1.3.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/jquery.fitvids.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/owl.carousel.min.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/isotope.pkgd.min.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/imagesloaded.pkgd.min.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/jquery.magnific-popup.min.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/wow.min.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/masonry.pkgd.min.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/jquery.simple-text-rotator.min.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/all.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/contact-form.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/jquery.ajaxchimp.min.js"></script>
	<!--[if lt IE 10]><script type="text/javascript" src="js/placeholder.js"></script><![endif]-->

</body>
</html>

