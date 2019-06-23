<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% if(session.getAttribute("tea_id")!=null){  %>
<script> location.href = 'page_tea/login.jsp' </script>
<%} %>
<% if(session.getAttribute("stu_id")!=null){  %>
<script> location.href = 'page_stu/login.jsp' </script>
<%} %>
<% if(session.getAttribute("admin_id")!=null){  %>
<script> location.href = 'page_adm/login.jsp' </script>
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
<link rel="shortcut icon" href="style/images/favicon.png">

<!-- CSS -->
<link rel="stylesheet" href="style/css/bootstrap.min.css">
<link rel="stylesheet" href="style/css/style.css">
<link rel="stylesheet" href="style/css/style-responsive.css">
<link rel="stylesheet" href="style/css/vertical-rhythm.min.css">
<link rel="stylesheet" href="style/css/owl.carousel.css">
<link rel="stylesheet" href="style/css/magnific-popup.css">

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
		<%@include file="pageInclude/Header.jsp"%>
		<!-- End Navigation panel -->


		<!-- Head Section -->
		<section class="small-section bg-gray-lighter">
			<div class="relative container align-left">

				<div class="row">

					<div class="col-md-8">
						<h1 class="hs-line-11 font-alt mb-20 mb-xs-0">설문조사</h1>
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
				<div class="row">

					<div class="col-sm-6 mb-xs-40">

						<!-- Toggle -->
						<dl class="toggle">
							<dt>
								<a href="">관리자로 로그인하기</a>
							</dt>
							<dd>
								<form method="post" action="adminLogin.am" id="form" role="form">

									<div class="mb-20 mb-md-10">
										<!-- Name -->
										<input type="text" name="input_id" id="name"
											class="input-md form-control" placeholder="아이디"
											maxlength="100">
									</div>

									<div class="mb-20 mb-md-10">
										<!-- Password -->
										<input type="password" name="input_pass" id="password"
											class="input-md form-control" placeholder="비밀번호"
											maxlength="100">
									</div>
									<div class="btn-circle_change_location">
										<input type="submit" class="btn btn-mod btn-circle btn-small"
											value="로그인">
									</div>
								</form>
							</dd>

						</dl>
						<!-- End Toggle -->
					</div>
					<!-- End Col -->
				</div>
				<!-- End Row -->

			</div>
		</section>
		<!-- End Section -->

		<!-- Footer -->
		<%@ include file="pageInclude/Footer.jsp"%>
		<!-- End Footer -->

	</div>
	<!-- End Page Wrap -->


	<!-- JS -->
	<script type="text/javascript" src="style/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="style/js/jquery.easing.1.3.js"></script>
	<script type="text/javascript" src="style/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="style/js/SmoothScroll.js"></script>
	<script type="text/javascript" src="style/js/jquery.scrollTo.min.js"></script>
	<script type="text/javascript" src="style/js/jquery.localScroll.min.js"></script>
	<script type="text/javascript" src="style/js/jquery.viewport.mini.js"></script>
	<script type="text/javascript" src="style/js/jquery.countTo.js"></script>
	<script type="text/javascript" src="style/js/jquery.appear.js"></script>
	<script type="text/javascript" src="style/js/jquery.sticky.js"></script>
	<script type="text/javascript" src="style/js/jquery.parallax-1.1.3.js"></script>
	<script type="text/javascript" src="style/js/jquery.fitvids.js"></script>
	<script type="text/javascript" src="style/js/owl.carousel.min.js"></script>
	<script type="text/javascript" src="style/js/isotope.pkgd.min.js"></script>
	<script type="text/javascript" src="style/js/imagesloaded.pkgd.min.js"></script>
	<script type="text/javascript"
		src="style/js/jquery.magnific-popup.min.js"></script>
	<!-- Replace test API Key "AIzaSyAZsDkJFLS0b59q7cmW0EprwfcfUA8d9dg" with your own one below 
        **** You can get API Key here - https://developers.google.com/maps/documentation/javascript/get-api-key -->
	<script type="text/javascript"
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAZsDkJFLS0b59q7cmW0EprwfcfUA8d9dg"></script>
	<script type="text/javascript" src="style/js/gmap3.min.js"></script>
	<script type="text/javascript" src="style/js/wow.min.js"></script>
	<script type="text/javascript" src="style/js/masonry.pkgd.min.js"></script>
	<script type="text/javascript"
		src="style/js/jquery.simple-text-rotator.min.js"></script>
	<script type="text/javascript" src="style/js/all.js"></script>
	<script type="text/javascript" src="style/js/contact-form.js"></script>
	<script type="text/javascript" src="style/js/jquery.ajaxchimp.min.js"></script>
	<!--[if lt IE 10]><script type="text/javascript" src="js/placeholder.js"></script><![endif]-->

</body>
</html>
