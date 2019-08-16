<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import = "Service.SurveyService" %>
<%@ page import = "Service.SchoolService" %>
<%@ page import ="java.text.SimpleDateFormat"%>
<%@ page import = "SurveyRelationDTO.SurveyGoingDTO" %>
<%@ page import = "java.util.*" %>
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
						<h2 class="hs-line-11 font-alt mb-20 mb-xs-0">진행 완료된 설문조사</h2>
						<div class="hs-line-4 font-alt black"></div>
					</div>

				</div>
			</div>
		</section>
		<!-- End Head Section -->
		<!-- Section -->
		<section class="page-section">
			<div class="container relative">
				<div class="row">
					
	<% 
	SurveyService surService = SurveyService.getInstance();
	SchoolService schService = SchoolService.getInstance();
	ArrayList<SurveyGoingDTO> schResultSur = new ArrayList<SurveyGoingDTO>();
	ArrayList<SurveyGoingDTO> schFindResultSur = new ArrayList<SurveyGoingDTO>();
	schResultSur = surService.showUncalculatedSurvey();
	String input_schoolNm = request.getParameter("input_schoolNm");
	%>
					<form id="formHref" action="" class="form-inline form mb-20" role="form">
									<div class="search-wrap" style="width: 60%;">
										<button class="search-button animate"  title="Start Search">
											<i class="fa fa-search"></i>
										</button>
										<input style="width: 60%" type="text" name="input_schoolNm"
											class="form-control search-field" placeholder="학교명을 검색해주세요.">
									</div>
					</form>
			<%if (schResultSur.size()==0){ %>
			<h3> 설문조사한 학교가 없습니다. </h3>
			<%}else{ %>
			<table class="table table-striped" style="text-align:center">
			<thead>
                            <tr>
                                <th>학교 이름</th>
                                <th>설문 이름</th>
                                <th>실시 연도</th>
                                <th>대상 학년</th>
                                <th>시작일</th>
                                <th>종료일</th>
                                <th>상세보기</th>
                            </tr>
            </thead>
            <tbody>
            <%if(input_schoolNm==null){ %>
			<%for(int i=0; i<schResultSur.size(); i++){ %>
			<tr>
			<td><%= schService.getSchoolToSCID(schResultSur.get(i).getSCID()).getName() %></td>
			<td><%= surService.showSearchSurveyToSurveyNo(schResultSur.get(i).getSurveyNo()).getTitle() %> </td>
			<td><%= surService.searchEndSurveyToDate(schResultSur.get(i).getSCID(), schResultSur.get(i).getEndDate()).getYear() %> </td>
			<td><%= schResultSur.get(i).getGrade() %></td>
			<td><%= schResultSur.get(i).getStartDate() %></td>
			<td><%= schResultSur.get(i).getEndDate() %></td>
			<td>
			<form method="post">				
           		<input type="hidden" name="sch_name" value="<%= schService.getSchoolToSCID(schResultSur.get(i).getSCID()).getName() %>">
           		<input type="hidden" name="sch_code" value="<%= schResultSur.get(i).getSCID()%>">
           		<input type="hidden" name="grade" value="<%= schResultSur.get(i).getGrade()%>">
           		<input type="hidden" name="year" value="<%= surService.searchEndSurveyToDate(schResultSur.get(i).getSCID(), schResultSur.get(i).getEndDate()).getYear()%>">
           		<input type="hidden" name="ingSeq" value="<%= schResultSur.get(i).getIngSeq()%>">
           		<input type="hidden" name="surveyNo" value="<%= schResultSur.get(i).getSurveyNo()%>">
           		<input type="hidden" name="title" value="<%= surService.showSearchSurveyToSurveyNo(schResultSur.get(i).getSurveyNo()).getTitle()%>">
           		<input type="hidden" name="startdate" value="<%= schResultSur.get(i).getStartDate()%>">
           		<input type="hidden" name="enddate" value="<%= schResultSur.get(i).getEndDate() %>">           		           	
           		<input type="button" class="btn btn-mod btn-medium btn-round" onclick="calculate(this.form)" value="결과 계산하기">
           	</form>
			</td>
			</tr>
			<%} %>
			<%}else{ %>
			<% schFindResultSur = surService.showUncalculatedSurvey(input_schoolNm); %>
			<% if(schFindResultSur.size()!=0){ %>
			<% for(int i=0; i<schFindResultSur.size(); i++){ %>
			<tr>
			<td><%= schFindResultSur.get(i).getSCID_name() %></td>
			<td><%= surService.showSearchSurveyToSurveyNo(schFindResultSur.get(i).getSurveyNo()).getTitle() %> </td>
			<td><%= surService.searchEndSurveyToDate(schResultSur.get(i).getSCID(), schResultSur.get(i).getEndDate()).getYear() %> </td>
			<td><%= schFindResultSur.get(i).getGrade() %></td>
			<td><%= schFindResultSur.get(i).getStartDate() %></td>
			<td><%= schFindResultSur.get(i).getEndDate() %></td>
			<td>
			<!-- <button class="btn btn-mod btn-medium btn-round">결과보기</button> -->
			<form method="post">
           		<input type="hidden" name="sch_name" value="<%= schFindResultSur.get(i).getSCID_name() %>">
           		<input type="hidden" name="sch_code" value="<%= schFindResultSur.get(i).getSCID()%>">
           		<input type="hidden" name="grade" value="<%= schFindResultSur.get(i).getGrade()%>">
           		<input type="hidden" name="year" value="<%= surService.searchEndSurveyToDate(schFindResultSur.get(i).getSCID(), schResultSur.get(i).getEndDate()).getYear()%>">
           		<input type="hidden" name="ingSeq" value="<%= schFindResultSur.get(i).getIngSeq()%>">
           		<input type="hidden" name="surveyNo" value="<%= schFindResultSur.get(i).getSurveyNo()%>">
           		<input type="hidden" name="title" value="<%= surService.showSearchSurveyToSurveyNo(schFindResultSur.get(i).getSurveyNo()).getTitle()%>">
           		<input type="hidden" name="startdate" value="<%= schFindResultSur.get(i).getStartDate()%>">
           		<input type="hidden" name="enddate" value="<%= schFindResultSur.get(i).getEndDate() %>">
           		<input type="hidden" name="adminSearching" value="admin">                           		
           		<input type="button" class="btn btn-mod btn-medium btn-round" onClick="calculate(this.form)" value="결과 계산하기">
           	</form>
			</td>
			</tr>
			<%} %>
			<%}else{ %>
			<tr>
			<td colspan="5" >검색결과 존재하지 않습니다.</td>
			</tr>
			<%} %>
			<%} %>
			</tbody>
			</table>
			<%} %>
					
				</div>
				<ul class="pagination" style="list-stlye-type:none; text-align:center ">
				  
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
	<script type="text/javascript" src="/PeerSys/style/js/pagingScript.js?version=5"></script>
	<script type="text/javascript" src="/PeerSys/style/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/jquery.easing.1.3.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/bootstrap.min.js?version=5"></script>
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
	<!--[if lt IE 10]><script type="text/javascript" src="js/placeholder.js"></script><![endif]-->
	<script type="text/javascript" src="/PeerSys/style/js/studentTransfer.js"></script>

</body>
</html>