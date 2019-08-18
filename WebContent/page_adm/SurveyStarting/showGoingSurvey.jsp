<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Service.*" %>
<%@ page import="SurveyRelationDTO.*" %>
<%@ page import="java.util.*" %>
<%@ page import="User.UserDTO.*" %>
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

		<!-- Head Section -->
		<section class="small-section bg-gray-lighter">
			<div class="relative container align-left">

				<div class="row">
<% 

	SurveyService surService = SurveyService.getInstance();
	AnswerService ansService = AnswerService.getInstance();
	StudentService stuService = StudentService.getInstance();
	
	String surIng = request.getParameter("surSeq");
	int questionCount1 = surService.getSurveyNo(Integer.parseInt(surIng));
	int questionCount = surService.getQuestionCount(questionCount1);
	String strGrade = request.getParameter("grade");
	int grade = Integer.parseInt(strGrade);
	String SCID = request.getParameter("SCID");
	
	// 학교 학년에 몇반 까지 있는지 리스트 
	ArrayList<Integer> classesList = new ArrayList<Integer>();
	classesList = stuService.getStudentList(SCID, grade);
	Calendar calendar = new GregorianCalendar(Locale.KOREA);
	int intYear = calendar.get(Calendar.YEAR);
	String year = Integer.toString(intYear);
	
	ArrayList<StudentDTO> studentListAttend2 = new ArrayList<StudentDTO>();
	
	ArrayList<SurveyGoingDTO> surGoingList = new ArrayList<SurveyGoingDTO>();
	surGoingList = surService.showAllGoingSurveys();
	SchoolService schService = SchoolService.getInstance();
	
	
%>
				</div>
			</div>
		</section>
		<!-- End Head Section -->

		<!-- Section -->
		<section class="page-section">
			<div class="container relative">
			<%if (surGoingList.size()==0){ %>
			<h3> 설문조사가 진행중인 학교가 없습니다. </h3>
			<%}else{  %>
			<% String SCIDName = surGoingList.get(0).getSCID_name(); %>
			<div class="works-filter font-alt">
					
					<% for(int i=0; i<classesList.size(); i++){ %>
					
                    <a href="#<%=classesList.get(i) %>" class="filter" data-filter=".<%=classesList.get(i)%>"><%=classesList.get(i) %>반</a>
                    <% 
                    // 학교 학년 반 을 통해서 userList 추출. 
                	
                	%>           
                    <%} %>
           </div>
                <ul class="works-grid work-grid-2 work-grid-gut clearfix font-alt hover-white" id="work-grid"  style="display:block;" >
                    <% for(int i=0; i<classesList.size(); i++){ %>
            <li class="work-item mix <%=classesList.get(i)%>" style="width:100%">
            
            <% studentListAttend2 = stuService.studentListAttend2(SCID, grade, classesList.get(i), year); %>
            
			<table class="table table-striped" style="text-align:center">
			<thead>
                            <tr>
                                <th>학교</th>
                                <th>학년</th>
                                <th>반</th>
                                <th>번호</th>
                                <th>이름</th>
                                <th>진행상황</th>
                            </tr>
            </thead>
            <tbody>
			<%for(int k=0; k<studentListAttend2.size(); k++){ %>
			<% 
			int answerCount = ansService.getAnswersCount(Integer.parseInt(surIng), studentListAttend2.get(k).getStu_id());
			%>
			<tr>
			<td><%= SCIDName %></td>
			<td><%= studentListAttend2.get(k).getGrade() %>학년 </td>
			<td><%= studentListAttend2.get(k).getGrd_num() %>반</td>
			<td><%= studentListAttend2.get(k).getNum() %></td>
			<td><%= studentListAttend2.get(k).getName() %></td>
			<td> <%= answerCount %> / <%=questionCount %> </td>
			</tr>
			<%} %>
			</tbody>
			</table>
            </li>
                    <%} %>    
                </ul>
			
			<%} %>
			
			</div>
		</section>
		<!-- End Section -->

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