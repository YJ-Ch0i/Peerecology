<%@page import="School.SchoolDTO.SchoolDTO"%>
<%@page import="Service.SchoolService"%>
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
					<h1 class="hs-line-11 font-alt mb-20 mb-xs-0">전학생 추가</h1>
				</div>
			</div>
		</section>
		<!-- End Head Section -->


		<!-- Section -->
		<section class="page-section">
			<div class="container relative">
			
			<%
				SchoolService schService = SchoolService.getInstance();
				SchoolDTO school = schService.getSchoolToSCID(teacherDTO.getSCID());
			%>

				<!-- Row -->
				<div class="row">
				
                    <form action="/PeerSys/AddStudent.st" method="post" id="form" role="form">
                    	<h3><%=teacherDTO.getName()%> 선생님의  <%=school.getName()%> <%=teacherDTO.getGrade()%> 학년  <%=teacherDTO.getClasses()%> 반</h3>
                    	<h4> 전학생의 번호와 성별, 그리고 이름을 입력 해 주세요.</h4>
							<div class="mb-20 mb-md-10">
								<input type="text" name="grade_num" class="input-md form-control"
									style="width: 5%;" maxlength="100" readonly value="<%= teacherDTO.getGrade()%>">
								<p style="display: inline-block">학년</p>
								<input type="text" name="class_num" class="input-md form-control"
									style="width: 5%;" maxlength="100" readonly value="<%= teacherDTO.getClasses()%>">
								<p style="display: inline-block">반</p><br>				
								번호 : <input type="text" id="stuNum" name="stu_num" class="input-md form-control"
									style="width: 15%;" maxlength="150" placeholder="ex) 10" onChange="checkStuNumber();" required>
									<p style="display: inline-block">번</p><br>
								성별 : <input type="text" name="stu_gender" class="input-md form-control"
									style="width: 15%;" maxlength="150" placeholder="ex) 남자 or 여자" required>
									<p style="display: inline-block"></p><br><br>
								이름 : <input type="text" name="stu_name" class="input-md form-control"
									style="width: 15%;" maxlength="150" placeholder="ex) 강동원" required>								
							</div>
	
							<div class="mb-20 mb-md-10" id=divObj></div>
							<input type="submit"
								style="margin: 5% 0 0 50%; display: inline-block;"
								class="btn btn-mod btn-circle btn-medium" value="전학생 등록">
							<a href="manageMentClass.jsp" style="margin: 5% 0 0 0;"
								class="btn btn-mod btn-circle btn-medium">뒤로가기</a>
					</form>
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
