<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import = "Service.SurveyService" %>
<%@ page import = "SurveyRelationDTO.SurveyDTO" %>
<%@ page import = "java.util.ArrayList" %>
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
<link rel="stylesheet" href="/PeerSys/style/css/style.css?version=4">
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

					<div class="col-md-8">
						<h2 class="hs-line-11 font-alt mb-20 mb-xs-0">설문조사 유형</h2>
						<div class="hs-line-4 font-alt black"></div>
					</div>

				</div>
			</div>
		</section>
		<!-- End Head Section -->
		<!-- Section -->
		<section class="page-section" style="padding:50px 0;">
			<div class="container relative">
				
					<div class="col-sm-6 mb-xs-40" style="width:100%;">
					<form action="/PeerSys/versionDelete.sv">
					 <ul class="works-grid work-grid-5 work-grid-gut clearfix font-alt hover-white">
			
<%
ArrayList<SurveyDTO> surveyList = new ArrayList<SurveyDTO>();
SurveyService surveyService = SurveyService.getInstance();
surveyList = surveyService.showAllSurveys();
%>			         
						<%if(surveyList.size()==0){ %>
						<p>버젼이 등록되있지 않습니다.</p>
						<%}else{ %>
						<%for(int i=0; i<surveyList.size(); i++){ %>
						<li class="work-item mix design photography" style="margin:4%; width:15%">
						<label class="checkbox-inline">
						<input type="checkbox" name="deleteVersionValues" id="inlineCheckbox1" value="<%=surveyList.get(i).getSurveyNo()%>">
            
                            <a href="showVersionPage.jsp?surveyNo=<%=surveyList.get(i).getSurveyNo()%>"   class="btn btn-mod btn-border-w btn-medium btn-round lightbox mfp-iframe" style="background:white; padding:0;">
                            		<div class="work-img">
										<p style="text-align: center; color:black;">
											<%= surveyList.get(i).getTitle() %>
										</p>
										<span></span>
										<div class="work-descr">
											<p style="text-align: center">미리보기</p>
										</div>
									</div>
                            </a>
                        </label>
                        </li>
						<%} %>
						<%} %>
						
				 </ul>
				 <p></p>
			 <ul class="pagination" style="list-stlye-type:none; text-align:center;">
    
  			 </ul>
  			 <p></p>
						<button onclick="location.href='versionAddPage.jsp'" type="button"
							class="btn btn-mod btn-border btn-large btn-round" >설문조사 추가하기</button>
						<input type="submit"  class="btn btn-mod btn-border btn-large btn-round" value="설문조사 삭제하기">
						<button onclick="location.href='Questions/QuestionPage.jsp'" type="button"
							class="btn btn-mod btn-border btn-large btn-round" >설문문항 추가하기</button>

				</form>
				</div>
				
					<!-- End Col -->
	
				</div>

		</section>
		<!-- End Section -->

		<!-- Footer -->
		<%@ include file="../../pageInclude/Footer.jsp"%>
		<!-- End Footer -->

	</div>
	<!-- End Page Wrap -->


	<!-- JS -->
	<script type="text/javascript" src="/PeerSys/style/js/pagingFormSurvey.js?version=3"></script>
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