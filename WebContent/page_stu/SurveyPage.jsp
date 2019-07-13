<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="Service.*"%>
<%@ page import="SurveyRelationDTO.*"%>
<%@ page import="User.UserDTO.StudentDTO" %>
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
	<%
	//설문지의 question 인덱스들
	ArrayList<SurveyManagerDTO> queManagerList = new ArrayList<SurveyManagerDTO>();
	queManagerList = (ArrayList<SurveyManagerDTO>)request.getAttribute("questionsDTO");
	
	AllDescQuestionDTO queList = new AllDescQuestionDTO();
	// 같은 반 다른학생들의 정보
	ArrayList<StudentDTO> OtherStuList = new ArrayList<StudentDTO>();
	OtherStuList = (ArrayList<StudentDTO>)session.getAttribute("OtherStuList");
	QuestionService queSerivce = QuestionService.getInstance();
    %>
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
	</div>
	<!-- Section -->
	<section class="small-section bg-gray-lighter">
			<div class="relative container align-left">

				<div class="row">

					<div class="col-md-8">
						<h5 class="hs-line-11 font-alt mb-20 mb-xs-0"><%=session.getAttribute("stu_desc") %></h5>
						<div class="hs-line-4 font-alt black"></div>
					</div>

				</div>
			</div>
		</section>
	<section class="page-section" style="padding:50px 0">
	<div class="container relative">
	<div class="row">
	<form action="/PeerSys/surveyAnswerRegister.as" method="post">
	<%for(int i=0; i<queManagerList.size();i++){ %>
	<input type="hidden" name="QID" value="<%=queManagerList.get(i).getQID() %>">
	<div class="mb-20 mb-md-10" style="margin-bottom:5%"> 
	<% 
		queList = queSerivce.showQuestion(queManagerList.get(i).getQID()); 
	%>
	<h4><%=i+1%>번 . <%= queList.getQue_title() %></h4>
	<p></p>
	
	<!-- 문항보기 -->
	<!-- 객관식일 경우 -->
	<%if(queList.getQue_typeOfferSeq()!=0){ %>
	<% 
		ArrayList<QuestionOfferDTO> queOffer = new ArrayList<QuestionOfferDTO>();
		queOffer = queSerivce.showQuestionOffer(queList.getQue_typeID()); 
	%>
		<% for(int k=0; k<queOffer.size(); k++){ %>
			<!-- 세로보기 문항 -->
			<% if(queList.isQ_typeDirection()==true){ %>
			<label class="radio-inline"> 
				<input type="radio" name="checkAnswer<%=i%>" id="inlineRadio1" value="<%=k+1%>"><%= queOffer.get(k).getTitle() %>
			</label>
			<!-- 가로보기 문항 -->
			<%}else{ %>
			<p></p>
			<label class="radio-inline">
	          	<input type="radio" name="checkAnswer<%=i%>" id="inlineRadio1" value="<%=k+1%>">
	          	<%= queOffer.get(k).getTitle() %>
		    </label>
			<%} %>
		 <%} %> 
	<!-- 또래지명일 경우 -->
	<%}else if(queList.getQue_typeTitle().equals("또래지명")){ %>
	<% for(int k=0; k<OtherStuList.size(); k++) {%>
	<label class="checkbox-inline"> 
				<input type="checkbox" name="checkDDorae<%=i%>" id="checkBox" value="<%=OtherStuList.get(k).getStu_id() %>">
				<%= OtherStuList.get(k).getName() %>
	</label>
	<%} %>
	<!-- 주관식일 경우 -->
	
	<%}else{ %>
	<input type="text" name="checkAnswer<%=i%>" id="tea_name" class="input-md form-control" placeholder="" maxlength="100" required>
	<%} %>
	</div>
	<%} %>
	
	<input type="submit" style="float:right" class="btn btn-mod btn-medium btn-round" >
	</form>
	</div>
	</div>
	</section>
	<!-- End Section -->
	
	<!-- Footer -->
	<%@ include file="../pageInclude/Footer.jsp"%>
	<!-- End Footer -->


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
	<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAZsDkJFLS0b59q7cmW0EprwfcfUA8d9dg"></script>
	<script type="text/javascript" src="/PeerSys/style/js/gmap3.min.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/wow.min.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/masonry.pkgd.min.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/jquery.simple-text-rotator.min.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/all.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/contact-form.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/jquery.ajaxchimp.min.js"></script>
	<!--[if lt IE 10]><script type="text/javascript" src="js/placeholder.js"></script><![endif]-->

</body>
</html>