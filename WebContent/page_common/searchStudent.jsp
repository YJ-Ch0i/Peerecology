<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Service.StudentService"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="User.UserDTO.StudentDTO"%>
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
<link rel="shortcut icon" href="/Survey/style/images/favicon.png">

<!-- CSS -->
<link rel="stylesheet" href="/Survey/style/css/bootstrap.min.css">
<link rel="stylesheet" href="/Survey/style/css/style.css">
<link rel="stylesheet" href="/Survey/style/css/style-responsive.css">
<link rel="stylesheet" href="/Survey/style/css/animate.min.css">
<link rel="stylesheet" href="/Survey/style/css/vertical-rhythm.min.css">
<link rel="stylesheet" href="/Survey/style/css/owl.carousel.css">
<link rel="stylesheet" href="/Survey/style/css/magnific-popup.css">

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
			<h3 style="text-align: center;">자신의 번호를 선택해주세요.</h3>
			<%
                    ArrayList<StudentDTO> list = (ArrayList<StudentDTO>) request.getAttribute("list");
        	%>
			<ul
				class="works-grid work-grid-5 clearfix font-alt hover-white hide-titles"
				id="work-grid">

				<% for(int i=0; i<list.size(); i++){
                    %>
				<li class="work-item mix all">
					<form action="/Survey/studentLogin.st" method="post">
						<input type="hidden" name="stu_id"
							value="<%=list.get(i).getStu_id() %>"> <input
							type="hidden" name="grade_class"
							value="<%= request.getAttribute("grade_class") %>"> <input
							type="hidden" name="sch_name"
							value="<%= request.getAttribute("sch_name") %>"> <input
							type="hidden" name="stu_name"
							value="<%= list.get(i).getStu_name() %>"> <input
							type="hidden" name="class_id"
							value="<%= list.get(i).getClass_id() %>"> <input
							type="hidden" name="stu_num"
							value="<%= list.get(i).getStu_num() %>"> <input
							type="submit" value="<%= list.get(i).getStu_num() %>"
							style="margin: 10% 10% 10% 15%"
							class="btn btn-mod btn-circle btn-small">
					</form>
				</li>
				<%} %>

			</ul>
			<!-- Row -->


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
	<script type="text/javascript"
		src="/Survey/style/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript"
		src="/Survey/style/js/jquery.easing.1.3.js"></script>
	<script type="text/javascript" src="/Survey/style/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/Survey/style/js/SmoothScroll.js"></script>
	<script type="text/javascript"
		src="/Survey/style/js/jquery.scrollTo.min.js"></script>
	<script type="text/javascript"
		src="/Survey/style/js/jquery.localScroll.min.js"></script>
	<script type="text/javascript"
		src="/Survey/style/js/jquery.viewport.mini.js"></script>
	<script type="text/javascript" src="/Survey/style/js/jquery.countTo.js"></script>
	<script type="text/javascript" src="/Survey/style/js/jquery.appear.js"></script>
	<script type="text/javascript" src="/Survey/style/js/jquery.sticky.js"></script>
	<script type="text/javascript"
		src="/Survey/style/js/jquery.parallax-1.1.3.js"></script>
	<script type="text/javascript" src="/Survey/style/js/jquery.fitvids.js"></script>
	<script type="text/javascript"
		src="/Survey/style/js/owl.carousel.min.js"></script>
	<script type="text/javascript"
		src="/Survey/style/js/isotope.pkgd.min.js"></script>
	<script type="text/javascript"
		src="/Survey/style/js/imagesloaded.pkgd.min.js"></script>
	<script type="text/javascript"
		src="/Survey/style/js/jquery.magnific-popup.min.js"></script>
	<!-- Replace test API Key "AIzaSyAZsDkJFLS0b59q7cmW0EprwfcfUA8d9dg" with your own one below 
        **** You can get API Key here - https://developers.google.com/maps/documentation/javascript/get-api-key -->
	<script type="text/javascript"
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAZsDkJFLS0b59q7cmW0EprwfcfUA8d9dg"></script>
	<script type="text/javascript" src="/Survey/style/js/gmap3.min.js"></script>
	<script type="text/javascript" src="/Survey/style/js/wow.min.js"></script>
	<script type="text/javascript"
		src="/Survey/style/js/masonry.pkgd.min.js"></script>
	<script type="text/javascript"
		src="/Survey/style/js/jquery.simple-text-rotator.min.js"></script>
	<script type="text/javascript" src="/Survey/style/js/all.js"></script>
	<script type="text/javascript" src="/Survey/style/js/contact-form.js"></script>
	<script type="text/javascript"
		src="/Survey/style/js/jquery.ajaxchimp.min.js"></script>
	<!--[if lt IE 10]><script type="text/javascript" src="js/placeholder.js"></script><![endif]-->

</body>
</html>

