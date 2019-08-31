<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Service.*" %>
<%@ page import="SurveyRelationDTO.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/pageInclude/CheckAdminLogin.jsp"%>
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
<script type="text/javascript" src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
	
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
<% 
	SurveyService surService = SurveyService.getInstance();
	ArrayList<SurveyGoingDTO> surGoingList = new ArrayList<SurveyGoingDTO>();
	surGoingList = surService.showAllGoingSurveys();
	SchoolService schService = SchoolService.getInstance();
	
	
%>
					<div class="col-md-8">
						<h2 class="hs-line-11 font-alt mb-20 mb-xs-0">설문조사 진행중인 학교들</h2>
						<div class="hs-line-4 font-alt black"></div>
					</div>

				</div>
			</div>
		</section>
		<!-- End Head Section -->

		<!-- Section -->
		<section class="page-section">
			<div class="container relative">
			<%if (surGoingList.size()==0){ %>
			<h3> 설문조사가 진행중인 학교가 없습니다. </h3>
			<%}else{ %>
			<table class="table table-striped" style="text-align:center; width:100%">
			<thead>
                            <tr>
                                <th>학교 이름</th>
                                <th>설문 이름</th>
                                <th>대상 학년</th>
                                <th>시작일</th>
                                <th>종료일</th>
                                <th>상세보기</th>
                            </tr>
            </thead>
            <tbody>
			<%for(int i=0; i<surGoingList.size(); i++){ %>
			<tr>
			<td><%= surGoingList.get(i).getSCID_name() %></td>
			<td><%= surService.showSearchSurveyToSurveyNo(surGoingList.get(i).getSurveyNo()).getTitle() %> </td>
			<td><%= surGoingList.get(i).getGrade() %>학년</td>
			<td><%= surGoingList.get(i).getStartDate() %></td>
			<td><%= surGoingList.get(i).getEndDate() %></td>
			<td>
			<a href="showGoingSurvey.jsp?surSeq=<%=surGoingList.get(i).getIngSeq()%>&SCID=<%=surGoingList.get(i).getSCID() %>&grade=<%=surGoingList.get(i).getGrade() %>" 
			class="btn btn-mod btn-border-w btn-medium btn-round lightbox mfp-iframe">진행 상황보기</a>			
			</td>
			</tr>
			<%} %>
			</tbody>
			
			</table>
			
			<%} %>
			<ul class="pagination" style="list-stlye-type:none; text-align:center;">
  			</ul>
			</div>
		</section>
		<!-- End Section -->

		<!-- Footer -->
		<%@ include file="../../pageInclude/Footer.jsp"%>
		<!-- End Footer -->

	</div>
	<!-- End Page Wrap -->


	<!-- JS -->
	<script type="text/javascript" src="/PeerSys/style/js/pagingScript.js?version=7"></script>
	
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