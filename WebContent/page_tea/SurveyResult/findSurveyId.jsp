<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ page import="Service.SurveyService"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="Survey.SurveyDTO.SurveyDTO"%>
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

	<!-- Page Wrap -->
	<div class="page" id="top">

		<!-- Navigation panel -->
		<%@include file="../../pageInclude/Header.jsp"%>
		<!-- End Navigation panel -->

		<!-- Head Section -->
		<section class="small-section bg-gray-lighter">
			<div class="relative container align-left">

				<div class="row">
					<h1 class="hs-line-11 font-alt mb-20 mb-xs-0">전체 학급</h1>
				</div>
			</div>
		</section>
		<!-- End Head Section -->


		<!-- Section -->
		<section class="page-section">
			<div class="container relative">

				<% 
                   ArrayList<SurveyDTO> list = (ArrayList<SurveyDTO>)request.getAttribute("svList");
                   String stu_id = (String)request.getAttribute("stu_id");
                   %>

				<form action="/Survey/surveyResultShow.sv" method="post">
					<ul
						class="works-grid work-grid-5 clearfix font-alt hover-white hide-titles"
						id="work-grid">

						<% for(int i=0; i<list.size(); i++){
                    %>

						<li class="work-item mix all"><input type="hidden"
							name="survey_id" value="<%= list.get(i).getSurvey_id() %>">
							<input type="hidden" name="stu_id" value="<%= stu_id %>">

							<button type="submit" style="margin: 10% 10% 10% 15%"
								class="btn btn-mod btn-circle btn-small">
								<%= list.get(i).getEnd_date() %>

							</button></li>

						<%} %>
					</ul>
				</form>
			</div>
		</section>
		<!-- End Section -->

		<!-- Footer -->
		<%@ include file="../../pageInclude/Footer.jsp"%>
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

