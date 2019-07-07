<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Service.VersionService"%>
<%@ page import="Version.VersionDTO.VersionDTO"%>
<%@ page import="java.util.ArrayList"%>
test
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

					<div class="col-md-8">
						<h2 class="hs-line-11 font-alt mb-20 mb-xs-0">
							<%= request.getParameter("sch_name") %>
							<%= request.getParameter("grade_class") %>
							설문조사 설정
						</h2>
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
					<%
                       VersionService service = VersionService.getInstance();
                       ArrayList<VersionDTO> allList = service.VersionSearch();
                    %>
					<% if(allList == null){ %>
					<script>
                        alert("현재 추가되어있는 버전이 없습니다.");
                        location.href='formSurvey.jsp';
                        </script>
					<%}else{ %>
					<form action="/PeerSys/surveyRegister.sv" method="post">
						<input type="hidden"
							value="<%= request.getParameter("class_id") %>">
						<ul
							class="works-grid work-grid-5 work-grid-gut clearfix font-alt hover-white"
							id="work-grid">


							<% for(int i=0; i<allList.size(); i++ ){ %>
							<li class="work-item mix design photography">test : <%= allList.get(i).getVersion_id() %>
								<label class="radio-inline"> <input type="radio"
									name="version_id" id="inlineRadio1"
									value="<%=allList.get(i).getVersion_id() %>">선택
							</label> <a
								href="/PeerSys/page_admin/SurveyManagement/showVersionPage.jsp?numbering=<%=allList.get(i).getVersion_id()%>"
								class="work-ext-link">
									<div class="work-img">
										<p style="text-align: center">
											<%= allList.get(i).getTitle() %>
										</p>
										<div class="work-descr">
											<p style="text-align: center">미리보기</p>
										</div>
									</div>
							</a>
							</li>
							<%} %>
							<%} %>
						</ul>
						<div class="col-sm-6 mb-xs-40" style="margin: 1%;">
							<input type="hidden" name="class_id"
								value="<%= request.getAttribute("class_id") %>"> 시작일 : <input
								type="date" name="start_date" id="date"
								class="input-md form-control" style="width: 30%;">
						</div>
						<div class="col-sm-6 mb-xs-40" style="margin: 1%;">
							종료일 : <input type="date" name="end_date" id="date"
								class="input-md form-control" style="width: 30%;">
						</div>
						<div class="col-sm-6 mb-xs-40">
							<button type="submit" style="float: right;">등록하기</button>
						</div>
					</form>
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