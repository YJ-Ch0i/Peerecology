<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<%@ page import="Service.TeacherService"%>
<%@ page import="Service.StudentService"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="User.UserDTO.StudentDTO"%>
<%@ page import="Service.ResponseService"%>
<%@ page import="Survey.Survey.SurveyDTO.ResponseDTO"%>
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

<style>
svg {
	border: 1px solid black;
}

.bar {
	fill: skyblue;
}

.barNum {
	font-size: 9pt;
	text-anchor: middle;
}

.axis text {
	font-family: sans-serif;
	font-size: 11px;
}

.axis path, axis line {
	fill: none;
	stroke: black;
}

.barName {
	font-size: 6pt;
	text-anchor: middle;
}
</style>
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
		<%@include file="../../pageInclude/Header.jsp"%>
		<!-- End Navigation panel -->

		<!-- Head Section -->
		<section class="small-section bg-gray-lighter">
			<div class="relative container align-left">

				<div class="row">
					<h1 class="hs-line-11 font-alt mb-20 mb-xs-0">설문조사 결과</h1>
				</div>
			</div>
		</section>
		<!-- End Head Section -->


		<!-- Section -->
		<section class="page-section">
			<div class="container relative">
				<%= request.getAttribute("survey_id") %>
			</div>
			<div class="container relative">
			<li>반 전체의 성향별</li>
				<%
				//학생 전체의 번호순 배열	만듬
				//학생 전체의 번호순 점수 배열		만듬
				//전체 번호순 점수 배열의 평균 배열
				
				//학생 1명의 성향별 점수 배열
				//성향 전체의 배열
				//학생 전체의 성향별 점수 합계
					String ten_id = "침착성";
					ArrayList<Integer> stu_list = new ArrayList<>();	//반에서 설문조사 한 학생 id리스트
                	ArrayList<Integer> allResponse_list = new ArrayList<>();	//학생1명의 반응 리스트
                	ArrayList<ResponseDTO> allStuForTend_list = new ArrayList<>();	//반전체의 성향에 대한 반응 리스트
                	ArrayList<String> stu_name_list = new ArrayList<>();	//설문조사 한 학생 이름 리스트         	
          
                	int survey_id = Integer.parseInt((String) request.getAttribute("survey_id"));
                	ResponseService service = ResponseService.getInstance();
                	allResponse_list = service.AllStuScoreNonPick_Tend(survey_id, ten_id);	//점수
                	stu_list = service.student_Count(survey_id);
                	stu_name_list = service.searchStudent_SvID(survey_id);

                	for(int i=0; i < allResponse_list.size(); i++){
                		int score = allResponse_list.get(i);
                	%>
				<input type="hidden" id="score<%=i%>" value="<%= score%>">
				<%}%>
				<input type="hidden" id="count" value="<%= allResponse_list.size()%>">
				<input type="hidden" id="stu_count" value="<%= stu_name_list.size()%>">
				<%

					for(int i=0; i<stu_name_list.size(); i++){	
						int scoreForTend = 0;
						allStuForTend_list = service.AllStuScoreNonPickForTend(survey_id, ten_id);
						
						for(int j=0; j<allStuForTend_list.size(); j++){
							scoreForTend += allStuForTend_list.get(j).getScore();
						}
				%>
					<input type="hidden" id="stu_name<%= i%>" value="<%= stu_name_list.get(i) %>">
					<input type="hidden" id="stu_score_avg<%= i%>" value="<%= scoreForTend%>">
				<%}%>
				

				<svg id="allStu_Bar_admin"></svg>
			</div>
		</section>
		
		<section class="page-section">
			<div class="container relative">
				<li>학생 1인</li>
			<%=request.getAttribute("stu_id") %>
			<%
				ArrayList<Integer> stu_id_list = new ArrayList<>();	//반에서 설문조사 한 학생 id리스트
                	ArrayList<ResponseDTO> response_list = new ArrayList<>();	//학생1명의 반응 리스트
                	ArrayList<ResponseDTO> StuForTend_list = new ArrayList<>();	//반전체의 성향에 대한 반응 리스트
                	ArrayList<String> tendArray = new ArrayList<>();		//설문조사에서 조사한 성향 리스트
                	
                	String stu_id = "50";
                	response_list = service.StuScoreForNonPick(survey_id, stu_id);
                	System.out.println(response_list);
                	stu_list = service.student_Count(survey_id);

                	for(int i=0; i < response_list.size(); i++){
                		String ten = response_list.get(i).getTen_id();
                		int score = response_list.get(i).getScore();
                		
                		System.out.println(ten);
                		System.out.println(score);
                	%>
				<input type="hidden" id="tendData<%=i%>" value="<%= ten%>"> 
				<input type="hidden" id="scoreData<%=i%>" value="<%= score%>">
				<%}%>
				<input type="hidden" id="cnt" value="<%= response_list.size()%>">
				<input type="hidden" id="stu_cnt" value="<%= stu_list.size()%>">
				<% System.out.println(survey_id); %>
				<%
					
					for(int i=0; i<response_list.size(); i++){
						String tend = response_list.get(i).getTen_id();
						tendArray.add(tend);
					}
				
					for(int i=0; i<tendArray.size(); i++){	
						int scoreForTend = 0;
						allStuForTend_list = service.AllStuScoreNonPickForTend(survey_id, tendArray.get(i));
						
						for(int j=0; j<allStuForTend_list.size(); j++){
							scoreForTend += allStuForTend_list.get(j).getScore();
						}
				%>
					<input type="hidden" id="scoreForTend<%=i%>" value="<%= scoreForTend%>">
				<%}%>
			
			<svg id = "allStu_Bar_admin_forTend"></svg>
			</div>
		</section>
		<!-- End Section -->

		<!-- Footer -->
		<%@ include file="../../pageInclude/Footer.jsp"%>
		<!-- End Footer -->

	</div>
	<!-- End Page Wrap -->


	<!-- JS -->
	<script type="text/javascript" src="https://d3js.org/d3.v4.min.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/admin/allStu_Bar_forAdmin.js?ver=1.5"></script>
	<script type="text/javascript" src="/PeerSys/style/js/admin/allStu_Bar_admin_forTend.js?ver=1.5"></script>
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
	<script type="text/javascript"
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAZsDkJFLS0b59q7cmW0EprwfcfUA8d9dg"></script>
	<script type="text/javascript" src="/PeerSys/style/js/gmap3.min.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/wow.min.js"></script>
	<script type="text/javascript"
		src="/PeerSys/style/js/masonry.pkgd.min.js"></script>
	<script type="text/javascript"
		src="/PeerSys/style/js/jquery.simple-text-rotator.min.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/all.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/contact-form.js"></script>
	<script type="text/javascript"
		src="/PeerSys/style/js/jquery.ajaxchimp.min.js"></script>
	<!--[if lt IE 10]><script type="text/javascript" src="js/placeholder.js"></script><![endif]-->

</body>
</html>

