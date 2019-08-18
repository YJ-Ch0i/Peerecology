<%@page import="view.viewDTO.SearchEndsurveyDTO"%>
<%@page import="Service.SurveyService"%>
<%@page import="User.UserDTO.TeacherDTO"%>
<%@page import="User.UserDTO.StudentDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Service.StudentService"%>
<%@page import="Service.TeacherService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/pageInclude/CheckTeacherLogin.jsp"%>
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
<link rel="stylesheet" href="/PeerSys/style/css/bootstrap.min.css">
<link rel="stylesheet" href="/PeerSys/style/css/style.css">
<link rel="stylesheet" href="/PeerSys/style/css/style-responsive.css">
<link rel="stylesheet" href="/PeerSys/style/css/animate.min.css">
<link rel="stylesheet" href="/PeerSys/style/css/vertical-rhythm.min.css">
<link rel="stylesheet" href="/PeerSys/style/css/owl.carousel.css">
<link rel="stylesheet" href="/PeerSys/style/css/magnific-popup.css">
</head>

<% String tea_id = (String)session.getAttribute("tea_id"); 
   TeacherService service = TeacherService.getInstance();
   Boolean isEmailChecked =service.getEamilChecked(tea_id);
   if(isEmailChecked==false){ %>
<script> 
   alert("이메일 인증 먼저 해주세요.");
   location.href="/PeerSys/page_tea/login.jsp";
   </script>
<%} %>
<body class="appear-animate">

	<!-- Page Loader -->
	<div class="page-loader">
		<div class="loader">Loading...</div>
	</div>
	<!-- End Page Loader -->

	<!-- Page Wrap -->
	<div class="page" id="top">

		<!-- Navigation panel -->
		<%@include file="../../pageInclude/Header.jsp"%>
		<!-- End Navigation panel -->

		<!-- Head Section -->
		<section class="small-section bg-gray-lighter">
			<div class="relative container align-left">

				<div class="row">
					<h1 class="hs-line-11 font-alt mb-20 mb-xs-0">설문조사 선택</h1>
				</div>
			</div>
		</section>
		<!-- End Head Section -->


		<!-- Section -->
		<section class="page-section">
			<div class="container relative">

				<%                    
                   TeacherService teaService = TeacherService.getInstance();    
				   TeacherDTO teacher = teaService.teacherInfo(tea_id); 
				   ArrayList<SearchEndsurveyDTO> list = new ArrayList<SearchEndsurveyDTO>();
                   SurveyService surService = SurveyService.getInstance();
                   list = surService.searchEndSurvey(teacher.getSCID());				 
                   %>               
                   
				<div class="row">				

					<% for(int i=0; i<list.size(); i++){ %>
						<form action="/PeerSys/resultTeacher.sv" method="post">
							<input type="hidden" value="<%=list.get(i).getSurveyNo() %>" name="surveyNo">
							<input type="hidden" value="<%=list.get(i).getIngSeq() %>" name="ingSeq">
							<input type="hidden" value="<%=list.get(i).getSCID() %>" name="scid">
							<input type="hidden" value="<%=list.get(i).getStartDate() %>" name="startdate">
							<input type="hidden" value="<%=list.get(i).getEndDate() %>" name="enddate">
							<input type="hidden" value="<%=list.get(i).getTitle() %>" name="title">
							<button type="submit"
								class="btn btn-mod btn-circle btn-medium" style="width: 50%; margin-bottom: 10px;">
								<%= i+1%> ) <%=list.get(i).getTitle() %> - 종료일 : <%= list.get(i).getEndDate() %> 
							</button>
						</form>
					
					<%} %>

					<!-- End Col -->

				</div>
			</div>
		</section>
		<!-- End Section -->

		<!-- Footer -->
		<%@ include file="../../pageInclude/Footer.jsp"%>
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
	<!-- Replace test API Key "AIzaSyAZsDkJFLS0b59q7cmW0EprwfcfUA8d9dg" with your own one below 
        **** You can get API Key here - https://developers.google.com/maps/documentation/javascript/get-api-key -->
	<script type="text/javascript" src="/PeerSys/style/js/wow.min.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/masonry.pkgd.min.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/jquery.simple-text-rotator.min.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/all.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/contact-form.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/jquery.ajaxchimp.min.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/studentTransfer.js"></script>
	<!--[if lt IE 10]><script type="text/javascript" src="js/placeholder.js"></script><![endif]-->

</body>
</html>
