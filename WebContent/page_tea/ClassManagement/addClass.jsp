<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="Service.TeacherService"%>
<%@ page import="java.util.Enumeration"%>
<%@ page import="User.UserDTO.TeacherDTO" %>
<%@ include file="/pageInclude/CheckTeacherLogin.jsp"%>
<% String tea_id = (String)session.getAttribute("tea_id"); 
   String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");
   String realUploadPath = uploadPath.substring(12);
   TeacherService teacherService = TeacherService.getInstance();
   TeacherDTO teacherDTO = new TeacherDTO();
   teacherDTO = teacherService.teacherInfo(tea_id);
   if(teacherDTO.isMailcheck()==false){ %>
<script> 
   alert("이메일 인증 먼저 해주세요.");
   location.href="/PeerSys/page_tea/login.jsp";
</script>	
<%} %>
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
<script type="javascript" src="/PeerSys/style/js/createTable.js?ver=11"></script>
</head>
<body class="appear-animate">
	<% 
        request.setCharacterEncoding("UTF-8");
        %>
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
					<h1 class="hs-line-11 font-alt mb-20 mb-xs-0">학급 추가</h1>
				</div>
			</div>
		</section>
		<!-- End Head Section -->


		<!-- Section -->
		<section class="page-section">
			<div class="container relative">

				<!-- Row -->
				<div class="row">
					<%
                            String sch_number = "";
                            if(request.getParameter("sch_number") != ""){
                               sch_number = (String) request.getParameter("sch_number");
                            }
                            String sch_name = "";
                            if(request.getParameter("sch_name") != ""){
                               sch_name = (String) request.getParameter("sch_name");
                            }
                            String sch_region = "";
                            if(request.getParameter("sch_region") != ""){
                               sch_region = (String) request.getParameter("sch_region");
                            }
                            
                            
                            if(sch_number == null && sch_name == null){%>
					<div class="mb-20 mb-md-10">
						<form action="/PeerSys/schoolSearch.jsp" method="post" id="form"
							role="form">
							
							<h3>본인이 소속되어 있는 학교를 선택 해 주세요.</h3>
							
							<input type="text" name="schoolNm" id="schoolNm"
								value="<%if( request.getAttribute("sch_name")!=null){ %> <%= request.getAttribute("sch_name") %> <%} %>"
								class="input-md form-control" placeholder="검색 할 학교명을입력해주세요.">
							<!-- School-Name -->
							<input type="submit" class="btn btn-mod btn-circle btn-medium"
								value="학교검색">

						</form>
					</div>
					<% } else{
						%>			
					<form action="csv_upload.jsp" method="post"	enctype="multipart/form-data">
						
						<h3>학급 내 학생명부 CSV파일을 양식에 맞춰 선택한 후 업로드하기 버튼을 눌러주세요</h3>
						첨부 파일 : <input type="file" name="file"><br/> 
						<input type="submit" value="업로드하기">
	
					<br><br>
					</form>

					<form action="/PeerSys/classAdd.tc" method="post" id="form"
						role="form">
						<% if(sch_number != "" && sch_name != ""){ %>
						<%if(sch_region!=null)  {%>
						<h3>
							<%= sch_region %>
						</h3>
						<%} %>
						<input type="hidden" name="sch_address" value="<%= sch_region%>">
						<input type="hidden" name="sch_code" value="<%= sch_number%>">
						<input type="hidden" name="sch_name" value="<%= sch_name%>">
						<%} %>
						<div class="mb-20 mb-md-10">
							<input type="text" name="grade_num" class="input-md form-control"
								style="width: 10%;" maxlength="100">
							<p style="display: inline-block">학년</p>
							<input type="text" name="class_num" class="input-md form-control"
								style="width: 10%;" maxlength="100">
							<p style="display: inline-block">반</p>
						</div>

						<div class="mb-20 mb-md-10" id=divObj></div>
						<input type="hidden" name="uploadPath" value="<%=uploadPath %>">
						<input type="submit"
							style="margin: 5% 0 0 50%; display: inline-block;"
							class="btn btn-mod btn-circle btn-medium" value="학급 등록">
						<a href="#" style="margin: 5% 0 0 0;"
							class="btn btn-mod btn-circle btn-medium">뒤로가기</a>
					</form>
					
					<%} %>
					<!-- End Col -->
				</div>
				<!-- End Row -->
			</div>
		</section>
		<!-- End Section -->

		<!-- Footer -->
		<%@ include file="../../pageInclude/Footer.jsp"%>
		<!-- End Footer -->

	</div>
	<!-- End Page Wrap -->


	<!-- JS -->
	<script type="text/javascript" src="/PeerSys/style/js/createTable.js"></script>
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
