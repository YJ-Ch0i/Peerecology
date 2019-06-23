<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="/PeerSys/style/css/vertical-rhythm.min.css">
<link rel="stylesheet" href="/PeerSys/style/css/owl.carousel.css">
<link rel="stylesheet" href="/PeerSys/style/css/magnific-popup.css">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-2.1.0.min.js?ver=1"></script>


</head>
<body class="appear-animate">

	<!-- Page Loader -->
	<div class="page-loader">
		<div class="loader">Loading...</div>
	</div>
	<!-- End Page Loader -->

	<!-- Page Wrap -->
	<div class="page" id="top">
		<!-- Section -->
		<section class="page-section">
			<div class="container relative">

				<!-- Row -->
				<div class="row">

					<div class="col-sm-6 mb-xs-40">

						<!-- Toggle -->
						<dl class="toggle">

							<div class="firstOption">
								또래지명 <label class="radio-inline"> <input type="radio"
									onclick="display1()" name="que_type" value="또래지명"> <img
									width="200%" height="200%"
									src="/PeerSys/style/img/ex_ddorae.png">
								</label>
							</div>
							<div class="firstOption">
								N지 선다(N개 보기 변경) <label class="radio-inline"> <input
									type="radio" onclick="display2()" name="que_type"
									value="N지 선다(N개 보기 변경)"> <img width="200%"
									height="200%" src="/PeerSys/style/img/ex_Nselect_version2.png">
								</label>

							</div>

							<div class="firstOption">
								N지 선다(체크 형식) <label class="radio-inline"> <input
									type="radio" name="que_type" id="que_type" onclick="display4()"
									value="N지 선다(체크 형식)"> <img width="200%" height="200%"
									src="/PeerSys/style/img/ex_Nselect_version2.png">
								</label>
							</div>

							<div class="firstOption">
								N지 선다(2개 보기 변경) <label class="radio-inline"> <input
									type="radio" onclick="display3()" name="que_type"
									value="N지 선다(2개 보기 변경)"> <img width="200%"
									height="200%" src="/PeerSys/style/img/ex_Nselect_version5.png">
								</label>
							</div>
							<div class="firstOption">
								주관식 <label class="radio-inline"> <input type="radio"
									name="que_type" id="que_type" onclick="display5()" value="주관식">
									<img width="200%" height="200%"
									src="/PeerSys/style/img/ex_subjective_question.png">
								</label>
							</div>
							<div id="option1" style="display: none">
								<%@ include file="../include/surveyType1.jsp"%>
							</div>
							<div id="option2" style="display: none">
								<%@ include file="../include/surveyType2.jsp"%>
							</div>
							<div id="option3" style="display: none">
								<%@ include file="../include/surveyType3.jsp"%>
							</div>
							<div id="option4" style="display: none">
								<%@ include file="../include/surveyType4.jsp"%>
							</div>
							<div id="option5" style="display: none">
								<%@ include file="../include/surveyType5.jsp"%>
							</div>

						</dl>
						<!-- End Toggle -->
					</div>

					<!-- End Col -->

				</div>
				<!-- End Row -->

			</div>
		</section>
		<!-- End Section -->

	</div>
	<!-- End Page Wrap -->



	<!-- JS -->
	<script type="text/javascript"
		src="/PeerSys/style/js/showSurvey.js?ver=9"></script>
	<script type="text/javascript"
		src="/PeerSys/style/js/surveyTypeDisplay.js?ver=6"></script>
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