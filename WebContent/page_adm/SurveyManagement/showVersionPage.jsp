<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="Question.QuestionDTO.QuestionDTO"%>
<%@ page import="Service.QuestionService"%>
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
int numbering =0;
if(request.getParameter("numbering") != null){
	numbering = Integer.parseInt(request.getParameter("numbering"));
}
QuestionService questionService = QuestionService.getInstance();
questionService.cntQuestions(numbering);
String version_name = questionService.version_name(numbering);
ArrayList<QuestionDTO> questionList = questionService.QuestionRoad(numbering);
%>
	<!-- Page Loader -->
	<div class="page-loader">
		<div class="loader">Loading...</div>
	</div>
	<!-- End Page Loader -->

	<!-- Page Wrap -->
	<div class="page" id="top">

		<!-- Navigation panel -->
		<%@include file="/pageInclude/Header.jsp"%>
		<!-- End Navigation panel -->
	</div>
	<!-- Section -->
	
	<section class="small-section bg-gray-lighter">
			<div class="relative container align-left">

				<div class="row">

					<div class="col-md-8">
						<h1 class="hs-line-11 font-alt mb-20 mb-xs-0"><%= version_name %></h1>
						<div class="hs-line-4 font-alt black"></div>
					</div>

				</div>
			</div>
	</section>
	<section class="page-section">
		<form style="margin-left: 25px;">
			<% for(int i=0; i<questionList.size(); i++){ %>
			<% if(questionList.get(i).getQue_type().equals("N지 선다(N개 보기 변경)")) {%>
			<div style="display: block; margin-top: 10px">
				<div
					style="display: flex; margin: 0 auto; -webkit-box-pack: justify; justify-content: space-between; text-align: center;">
					<h4>
						<%=i+1 %>
						.
						<%= questionList.get(i).getQue_text() %>
					</h4>
				</div>
				<% String[] splitQuestExamTxt = questionList.get(i).getExam_text().split(","); %>
				<% for(int k=0; k<splitQuestExamTxt.length; k++){ %>
				<label style="width: 100%; margin-top: 1px;"> <input
					type="radio" id="inlineRadio1" name="inlineRadioOptions"> <%= splitQuestExamTxt[k] %>
				</label>
			</div>
			<%} %>
			<%} else if(questionList.get(i).getQue_type().equals("N지 선다(체크 형식)")) {%>
			<div style="display: block; margin-top: 10px">
				<div
					style="display: flex; margin: 0 auto; -webkit-box-pack: justify; justify-content: space-between; text-align: center;">
					<h4>
						<%=i+1 %>
						.
						<%= questionList.get(i).getQue_text() %>
						(정답 :
						<%= questionList.get(i).getExample_answer()  %>)
					</h4>
				</div>
				<% String[] splitQuestExamTxt = questionList.get(i).getExam_text().split(","); %>
				<% for(int k=0; k<splitQuestExamTxt.length; k++){ %>
				<label style="width: 100%; margin-top: 1px;"> <input
					type="radio" id="inlineRadio1" name="inlineRadioOptions"> <%= splitQuestExamTxt[k] %>
				</label>
			</div>
			<%} %>
			<%} else if(questionList.get(i).getQue_type().equals("N지 선다(2개 보기 변경)")) {%>
			<% String[] splitQuestExamTxt2 = questionList.get(i).getExam_text().split(","); %>
			<div style="display: block; margin-top: 10px">
				<div
					style="display: flex; margin: 5px 0 5px 0; -webkit-box-pack: justify; justify-content: space-between; text-align: center;">
					<h4>
						<%= i+1 %>
						.
						<%= questionList.get(i).getQue_text() %>
					</h4>
				</div>
				<div style="display: flex; width: 100%">
					<div
						style="align-items: stretch; display: flex; flex-grow: 1; flex-direction: column; text-align: center; max-width: 30%;">
						<div
							style="align-items: stretch; display: flex; flex-grow: 1; flex-direction: column; text-align: center; max-width: 30%;"></div>
						<div
							style="align-items: stretch; display: flex; flex-grow: 1; flex-direction: column; text-align: center; max-width: 30%;">
							<%= splitQuestExamTxt2[0] %></div>
					</div>
					<% for(int q=0; q<questionList.get(i).getExam_cnt() ;q++){ %>
					<label style="width: 20%">
						<div
							style="display: flex; align-item: center; width: 10%; text-align: center; cursor: default; -webkit-box-pack: center; justify-content: center; min-height: 3em; padding: 0 5px"></div>
						<div
							style="display: flex; align-item: center; width: 10%; -webkit-box-pack: center; justify-content: center; min-height: 3em; padding: 0 5px">
							<input type="radio" id="inlineRadio1" name="inlineRadioOptions">
						</div>
					</label>
					<%} %>
					<div
						style="align-items: stretch; display: flex; flex-grow: 1; flex-direction: column; text-align: center; max-width: 30%;">
						<div
							style="align-items: stretch; display: flex; flex-grow: 1; flex-direction: column; text-align: center; max-width: 20%;"></div>
						<div
							style="align-items: stretxch; display: flex; flex-grow: 1; flex-direction: column; text-align: center; max-width: 30%;">
							<%=splitQuestExamTxt2[1] %></div>
					</div>
				</div>
			</div>
			<%} else if(questionList.get(i).getQue_type().equals("또래지명")) {%>
			<div style="width: 80%; margin-top: 10px">
				<div
					style="display: flex; margin: 5px 0 5px 0; -webkit-box-pack: justify; justify-content: space-between; text-align: center;">
					<h4>
						<%=i+1 %>
						.
						<%= questionList.get(i).getQue_text() %>
					</h4>
				</div>
				<%} else if(questionList.get(i).getQue_type().equals("주관식")) {%>
				<div style="display: block; margin-top: 10px">
					<div
						style="display: flex; margin: 5px 0 5px 0; -webkit-box-pack: justify; justify-content: space-between; text-align: center;">
						<h4>
							<%=i+1 %>
							.
							<%= questionList.get(i).getQue_text() %>
						</h4>
					</div>
				</div>
				<%} %>
				<%} %>
			
		</form>
	</section>
	<!-- End Section -->

	<!-- Footer -->
	<%@ include file="/pageInclude/Footer.jsp"%>
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