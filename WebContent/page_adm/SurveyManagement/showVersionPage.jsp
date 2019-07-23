<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="Service.*"%>
<%@ page import="SurveyRelationDTO.*"%>
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
<link rel="stylesheet" href="/PeerSys/style/css/animate.min.css">
<link rel="stylesheet" href="/PeerSys/style/css/vertical-rhythm.min.css">
<link rel="stylesheet" href="/PeerSys/style/css/owl.carousel.css">
<link rel="stylesheet" href="/PeerSys/style/css/magnific-popup.css">
<script type="text/javascript" src="http://code.jquery.com/jquery-3.2.1.min.js"></script>

</head>
<body class="appear-animate">

	<%
int surveyNo =0;
if(request.getParameter("surveyNo") != null)
{
	surveyNo = Integer.parseInt(request.getParameter("surveyNo"));
}
QuestionService questionService = QuestionService.getInstance();
SurveyService surveyService = SurveyService.getInstance();
ArrayList<SurveyManagerDTO> questions = new ArrayList<SurveyManagerDTO>();
questions = surveyService.showQuestionsToManager(surveyNo);
ArrayList<AllDescQuestionDTO> roadQuestions = new ArrayList<AllDescQuestionDTO>();
for(int i=0; i<questions.size(); i++)
{
	AllDescQuestionDTO question = questionService.showQuestion(questions.get(i).getQID());
	roadQuestions.add(question);
}
%>
	<!-- Page Loader -->
		<div class="page-loader">
		<div class="loader">Loading...</div>
	</div>
	<!-- End Page Loader -->
	<!-- Page Wrap -->
	<div class="page" id="top">
			<section class="page-section" >
			
			<div class="row">
			<% for(int i=0; i<roadQuestions.size(); i++ ){%>
			<div class="numberQuestion">
			<%=i+1 %>번 : <%= roadQuestions.get(i).getQue_title() %> ( 척도 -> <%= roadQuestions.get(i).getQue_trandTitle() %> ) 
			<%if( roadQuestions.get(i).getQue_answer() != null ){ %> ( 정답 -> <%=roadQuestions.get(i).getQue_answer() %> ) <%} %>
			<p></p>
			<!-- 주관식일 경우 --> 
			<%if(roadQuestions.get(i).getQue_typeOfferSeq()==0){%>
			&nbsp; &nbsp; <input type="text" class="input-md form-control" maxlength="100" readonly value="<%= roadQuestions.get(i).getQue_typeTitle() %>">
			
			<!-- 객관식일 경우 -->
			<%}else{ %>
			<% 
				ArrayList<QuestionOfferDTO> queOffer = questionService.showQuestionOffer(roadQuestions.get(i).getQue_typeID()); 
			    roadQuestions.get(i).setQuestionOffer(queOffer);
			%>
			<% for(int k=0; k< queOffer.size(); k++){ %>
			<% if(roadQuestions.get(i).isQ_typeDirection()==true){ %>
			&nbsp; &nbsp; <%= k+1 %> 번 : <%=queOffer.get(k).getTitle() %><!-- 가로보기 문항 -->
			<%}else{ %>
			<label class="radio-inline">
            <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1"><%= queOffer.get(k).getTitle() %>
        	</label>
			<%} %>
			
			<%} %>
			<%} %>
			<p></p>
			</div>
			<%}%>
			
			<ul class="pagination" style="list-stlye-type:none; text-align:center;">
    
  			</ul>
			</div>
			
            </section>
	</div>
	<!-- End Page Wrap -->


	<!-- JS -->
	<script type="text/javascript" src="/PeerSys/style/js/pagingVersionShowingSurvey.js"></script>
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