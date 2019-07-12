<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Service.QuestionService" %>
<%@ page import="SurveyRelationDTO.*" %>
<%@ page import="java.util.*" %>

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
<link rel="stylesheet" href="/PeerSys/style/css/style.css?version=2">
<link rel="stylesheet" href="/PeerSys/style/css/style-responsive.css">
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
		<%@include file="/pageInclude/Header.jsp"%>
		<!-- End Navigation panel -->

		<!-- Head Section -->
		<section class="small-section bg-gray-lighter">
			<div class="relative container align-left">

				<div class="row">

					<div class="col-md-8">
						<h2 class="hs-line-11 font-alt mb-20 mb-xs-0">설문문항 만들기</h2>
						<div class="hs-line-4 font-alt black"></div>
					</div>

				</div>
			</div>
		</section>
		<!-- End Head Section -->

		<!-- Section -->
		<section class="page-section">
			<div class="container relative">

				<!-- Row -->
				
<% 
QuestionService queSerivce = QuestionService.getInstance(); 
ArrayList<QuestionDTO> questions = new ArrayList<QuestionDTO>();
ArrayList<QuestionTrandTypeDTO> queTypes = new ArrayList<QuestionTrandTypeDTO>();
queTypes = queSerivce.showAllTrand();
questions = queSerivce.showAllQuestion();
%>
					<div class="works-filter font-alt">
					<form action="/PeerSys/questionDelete.qs" method="POST">
					<% for(int i=0; i<queTypes.size(); i++){ %>
					
                    <a href="#<%=queTypes.get(i).getQ_trandType()%>" class="filter" data-filter=".<%=queTypes.get(i).getQ_trandType()%>"><%=queTypes.get(i).getQ_trandDescipt()%></a>
                               
                    <%} %>
                    
                    </div>
                    
                <ul class="works-grid work-grid-5 clearfix font-alt hover-white hide-titles" id="work-grid">
                    <% for(int i=0; i<questions.size(); i++){ %>
                        <li class="work-item mix <%=questions.get(i).getTtype() %>">
                    <label class="checkbox-inline">
                    <input type="checkbox" name="deleteQuestion" id="deleteQuestion" value="<%=questions.get(i).getQID()%>">
                    <input type="hidden" name="deleteQuestion_title<%=questions.get(i).getQID()%>" value="<%=questions.get(i).getTitle() %>">
                    <a href="showQuestion.jsp?numbering=<%=questions.get(i).getQID() %>" style="margin:5%;" 
                    class="btn btn-mod btn-border-w btn-medium btn-round lightbox mfp-iframe">
                    
                    <%=questions.get(i).getTitle() %> </a>
                    </label>
                        </li>
                    <%} %>
                </ul>
                    <p>
                    <p>
                    
						<a href="QuestionAddPage.jsp" class="btn btn-mod btn-border-w btn-medium btn-round lightbox mfp-iframe">문항 추가하기</a>
						<input type="submit" class="btn btn-mod btn-medium btn-round" value="문항 삭제하기">
						<a href="TrandAddPage.jsp" class="btn btn-mod btn-border-w btn-medium btn-round lightbox mfp-iframe">성향 추가하기</a>
						<a href="TrandDeletePage.jsp" class="btn btn-mod btn-border-w btn-medium btn-round lightbox mfp-iframe">성향 삭제하기</a>
						<a href="TypeAddPage.jsp" class="btn btn-mod btn-border-w btn-medium btn-round lightbox mfp-iframe">유형 추가하기</a>
						<a href="TypeDeletePage.jsp" class="btn btn-mod btn-border-w btn-medium btn-round lightbox mfp-iframe">유형 삭제하기</a>
					</form>
					<!-- End Col -->


			</div>
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