<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<%@ page import="Service.TeacherService"%>
<%@ page import="Service.StudentService"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="User.UserDTO.StudentDTO"%>
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
<!-- <link rel="stylesheet" href="/PeerSys/style/css/bootstrap.min.css"> -->
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="/PeerSys/style/css/style.css">
<link rel="stylesheet" href="/PeerSys/style/css/style-responsive.css">
<link rel="stylesheet" href="/PeerSys/style/css/animate.min.css">
<link rel="stylesheet" href="/PeerSys/style/css/vertical-rhythm.min.css">
<link rel="stylesheet" href="/PeerSys/style/css/owl.carousel.css">
<link rel="stylesheet" href="/PeerSys/style/css/magnific-popup.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

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
				
				<%
					ArrayList<String> totalList = new ArrayList<>();
					totalList = (ArrayList<String>) request.getAttribute("totalArray");
					request.setAttribute("totalArray", totalList);
					
					ArrayList<String> trandArray = new ArrayList<>();
					trandArray = (ArrayList<String>) request.getAttribute("trandArray");
					request.setAttribute("trandArray", trandArray);
					
					ArrayList<String> stuArray = new ArrayList<>();
					stuArray = (ArrayList<String>) request.getAttribute("stuArray");
					request.setAttribute("stuArray", stuArray);
				%>
				
				<textarea id="totalArray" style="display:none"><%=totalList %></textarea>
				<textarea id="trandArray" style="display:none"><%=trandArray %></textarea>
				<textarea id="stuArray" style="display:none"><%=stuArray %></textarea>
				

		<!-- Section -->
		<section class="page-section">
			<div class="container relative">	
				<div class="text-right">
					<a href="#">예정</a>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">또래관계보기</h3>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-9">
								<div id="classnetwork">
									<div>
										<canvas>
										
										</canvas>
									</div>
								</div>
								<small>무엇인가 설명</small>
							</div>
							<div class="col-md-3">
								설명이 들어갈 곳
							</div>
						</div>
					</div>
				</div>
				<div class="alert alert-warning" role="alert">
				
				</div>				
				<br>
				<br>
				
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">성향별 점수</h3>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-12">
								<div id="barchart" style="min-width:310px; max-width:1000px; min-height:600px; max-height:1500px;">									
								</div>
							</div>
						</div>
					</div>
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
	<script type="text/javascript" src="https://d3js.org/d3.v4.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
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
	<script type="text/javascript" src="/PeerSys/style/js/wow.min.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/masonry.pkgd.min.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/jquery.simple-text-rotator.min.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/all.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/contact-form.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/jquery.ajaxchimp.min.js"></script>
	<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="https://code.highcharts.com/highcharts.js"></script>
	<script type="text/javascript" src="https://code.highcharts.com/modules/exporting.js"></script>
	<script type="text/javascript" src="https://code.highcharts.com/modules/export-data.js"></script>
	<!--[if lt IE 10]><script type="text/javascript" src="js/placeholder.js"></script><![endif]-->
	<script type="text/javascript" src="/PeerSys/style/js/studentTransfer.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/visualize/stuBarGraph.js"></script>
	
	
</body>
</html>

