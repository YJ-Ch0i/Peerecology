<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import = "Service.SurveyService" %>
<%@ page import = "SurveyRelationDTO.SurveyDTO" %>
<%@ page import = "java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<title>Rhythm &mdash; One & Multi Page Creative Theme</title>
<meta name="description" content="">
<meta name="keywords" content="">
<meta charset="utf-8">
<script language="javascript">

function searchVersion()
{
	var versionTitle = document.getElementById("versionTitle").value;
	var thisUrl = window.location.href;
	if(thisUrl.indexOf("&versionTitle")!=-1)
		{
		thisUrl = thisUrl.slice(0,thisUrl.indexOf("&versionTitle"));
		}
	location.href= thisUrl + "&versionTitle="+versionTitle;
}
</script>
<meta name="author" content="Roman Kirichik">
<!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

<!-- Favicons -->
<link rel="shortcut icon" href="/PeerSys/style/images/favicon.png">
<!-- CSS -->
<link rel="stylesheet" href="/PeerSys/style/css/bootstrap.min.css">
<link rel="stylesheet" href="/PeerSys/style/css/style.css?version=1">
<link rel="stylesheet" href="/PeerSys/style/css/style-responsive.css">
<link rel="stylesheet" href="/PeerSys/style/css/vertical-rhythm.min.css">
<link rel="stylesheet" href="/PeerSys/style/css/owl.carousel.css">
<link rel="stylesheet" href="/PeerSys/style/css/magnific-popup.css">

</head>
<body class="appear-animate">
<%
ArrayList<SurveyDTO> surveyList = new ArrayList<SurveyDTO>();
SurveyService surveyService = SurveyService.getInstance();
surveyList = surveyService.showAllSurveys();
%>
	<!-- Page Loader -->
	<div class="page-loader">
		<div class="loader">Loading...</div>
	</div>
	<!-- End Page Loader -->
	<!-- Page Wrap -->
	<div class="page" id="top">
			<section class="page-section">
			<div class="row">
			<% 
				String str = request.getParameter("str"); 
			    String[] splitStr = str.split(",");
			    String version = request.getParameter("versionTitle");
			%>
			
			<form action="/PeerSys/goingSurvey.sv" method="POST">
			<input id="versionTitle" name="versionTitle" type="text" class="input-md form-control" placeholder="설문조사 이름을 검색해주세요.">
			<button id="clickSearch" onclick="searchVersion()" class="btn btn-mod btn-border-w btn-medium btn-round lightbox">
			검색하기
			</button>
			<p></p>
				<div class="col-sm-6 mb-xs-40">
					<ul class="works-grid work-grid-5 work-grid-gut clearfix font-alt hover-white" id="work-grid">
			<%if(version==null){ %>
			<%if(surveyList.size()==0){ %>
			<p>설문조사를 등록하지 않았습니다.</p>
			<%}else{ %>
			<%for(int i=0; i<surveyList.size(); i++){ %>
			<li class="work-item mix design photography">
			<label class="radio-inline">
                                        <input type="radio" name="checkSurvey" value="<%=surveyList.get(i).getSurveyNo()%>">선택
            </label>
                         <a href="/PeerSys/page_adm/SurveyManagement/showVersionPage.jsp?surveyNo=<%=surveyList.get(i).getSurveyNo()%>"  style="background:white;" class="btn btn-mod btn-border-w btn-medium btn-round lightbox mfp-iframe">
                         		<div class="work-img">
							<p style="text-align: center; color:black;">
								<%= surveyList.get(i).getTitle() %>
							</p>
							<div class="work-descr">
								<p style="text-align: center">미리보기</p>
							</div>
						</div>
                         </a>
                     </li>
			<%} %>
			<%} %>
			<!-- 검색 했을 때 -->
			<%}else{ %>
			<% ArrayList<SurveyDTO> searchList = surveyService.showSearchSurveys(version); %>
			<% if(searchList.size()==0){ %>
			<li class="work-item mix design photography">
			검색 결과 존재하지 않습니다.
			</li>
			<%}else{ %>
			<% for(int k=0; k<searchList.size(); k++){ %>
			<li class="work-item mix design photography">
			
			<label class="radio-inline">
                                        <input type="radio" name="checkSurvey" value="<%=searchList.get(k).getSurveyNo()%>">선택
            </label>
                         <a href="/PeerSys/page_adm/SurveyManagement/showVersionPage.jsp?surveyNo=<%=searchList.get(k).getSurveyNo()%>"  style="background:white;" class="btn btn-mod btn-border-w btn-medium btn-round lightbox mfp-iframe">
                         		<div class="work-img">
							<p style="text-align: center; color:black;">
								<%= searchList.get(k).getTitle() %>
							</p>
							<div class="work-descr">
								<p style="text-align: center">미리보기</p>
							</div>
						</div>
                         </a>
                     </li>
            <%} %>
			<%} %>
			<%} %>
					</ul>
				</div>
			<p></p>
			
			
			<% for(int i=0; i<splitStr.length; i++){%>
			
			<input type="hidden" name="schools" value="<%=splitStr[i]%>">
			
			<%} %>
			<div class="col-sm-6 mb-xs-40" style="width:100%;">
			 <div class="mb-20 mb-md-10">
                 <!-- Date-->
            	 시작 날짜 - <input type="date" name="startDate" id="date" class="input-md form-control">
             </div>
             <div class="mb-20 mb-md-10">
                 <!-- Date-->
               	  종료 날짜 - <input type="date" name="endDate" id="date" class="input-md form-control">
             </div>
			<input type="submit" style="float:right;" class="btn btn-mod btn-medium btn-round" value="등록하기">
			</div>
			</form>
			</div>
			</section>
	</div>


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