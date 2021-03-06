<%@page import="School.SchoolDTO.SchoolDTO"%>
<%@page import="Service.SchoolService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Service.TeacherService"%>
<%@ page import="User.UserDTO.TeacherDTO"%>
<%@ page import="java.util.*" %>

<%@ include file="/pageInclude/CheckTeacherLogin.jsp"%>
<% 
   TeacherService teacherService = TeacherService.getInstance();
   TeacherDTO teacherDTO = new TeacherDTO();
   teacherDTO = teacherService.teacherInfo((String)session.getAttribute("tea_id"));
 %>
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

	<!-- Page Wrap -->
	<div class="page" id="top">

		<!-- Navigation panel -->
		<%@include file="../pageInclude/Header.jsp"%>
		<!-- End Navigation panel -->

		<!-- Head Section -->
		<section class="small-section bg-gray-lighter">
			<div class="relative container align-left">

				<div class="row">
<%
Calendar currentCalendar = Calendar.getInstance();
String strYear = Integer.toString(currentCalendar.get(Calendar.YEAR));
%>

<% SchoolDTO school = SchoolService.getInstance().getSchoolToSCID(teacherDTO.getSCID()); %>

					<h1 class="hs-line-11 font-alt mb-20 mb-xs-0">
						<% if(teacherDTO.isMailcheck()==false){ %>
						이메일 인증 먼저해주세요.
						<%}
                         else if( !teacherDTO.getLastChangeYear().equals(strYear)|| teacherDTO.getSCID()==null){ %>
						학급을 추가해주세요.
						<%}else{ %>
						<%= school.getName()%>
						<%= teacherDTO.getGrade() %>학년
						<%= teacherDTO.getClasses() %>반
						<%= teacherDTO.getName() %> 선생님 환영합니다.
						<% } %>
					</h1>
				</div>
			</div>
		</section>
		<!-- End Head Section -->


		<!-- Section -->
		<section class="page-section">
			<div class="container relative">

				<!-- Row -->
				<div class="row">
					<!-- 안내문 -->
					<div class="row">
						<h4>환영합니다, <%=teacherDTO.getName() %>선생님. 아래 사용 매뉴얼을 다운로드 받아주시고, 매뉴얼대로 사용 해 주시면 감사하겠습니다.</h4>
					</div>
					<div class="row">
						<a href="/PeerSys/upload/user_Manual/Peer_System_사용_메뉴얼.zip" target="_blank"><button class="btn btn-mod btn-circle btn-medium">사용자 매뉴얼 다운로드</button></a>
					</div>
				</div>
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
	<script type="text/javascript" src="/PeerSys/style/js/studentTransfer.js"></script>
	<!--[if lt IE 10]><script type="text/javascript" src="js/placeholder.js"></script><![endif]-->

</body>
</html>

