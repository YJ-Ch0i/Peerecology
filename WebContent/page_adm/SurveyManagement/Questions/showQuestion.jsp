<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Service.QuestionService" %>
<%@ page import="SurveyRelationDTO.QuestionTypeDTO" %>
<%@ page import="java.util.*" %>
<%@ page import="SurveyRelationDTO.*" %>
<!DOCTYPE html>
<html>
<head>

</script>
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
<link rel="stylesheet" href="/PeerSys/style/css/style.css?version=1">
<link rel="stylesheet" href="/PeerSys/style/css/style-responsive.css">
<link rel="stylesheet" href="/PeerSys/style/css/vertical-rhythm.min.css">
<link rel="stylesheet" href="/PeerSys/style/css/owl.carousel.css">
<link rel="stylesheet" href="/PeerSys/style/css/magnific-popup.css">

</head>
<body class="appear-animate">

<%
int QID = 0;
if(request.getParameter("numbering") != null)
{
	QID = Integer.parseInt(request.getParameter("numbering"));
}
%>
	<!-- Page Loader -->
	<div class="page-loader">
		<div class="loader">Loading...</div>
	</div>
	<!-- End Page Loader -->
<%
QuestionService queService = QuestionService.getInstance();
AllDescQuestionDTO questionDTO = queService.showQuestion(QID);
ArrayList<QuestionOfferDTO> questionOfferList = queService.showQuestionOffer(questionDTO.getQue_typeID());
ArrayList<QuestionTrandTypeDTO> queTrands = queService.showAllTrand();
ArrayList<QuestionTypeDTO> queTypes = queService.showAllType();
%>
	<!-- Page Wrap -->
	<div class="page" id="top">
			<section class="page-section" >
			<form method="post" action="/PeerSys/questionUpdate.qs" id="form" role="form">
			
			<div class="row">
			성향 : 
			<select class="input-md form-control"> 
			<% for(int i=0; i<queTrands.size(); i++){ %>
			<option <% if(queTrands.get(i).getQ_trandDescipt().equals(questionDTO.getQue_trandTitle())) {%> selected <%} %> ><%=queTrands.get(i).getQ_trandDescipt() %></option>
			<%} %>
			</select>
			<p></p>
			 유형 :   
           	<select class="input-md form-control"> 
			<% for(int i=0; i<queTypes.size(); i++){ %>
			<option <% if(queTypes.get(i).getDescript().equals(questionDTO.getQue_typeTitle())) {%> selected <%} %>> <%=queTypes.get(i).getDescript() %></option>
			<%} %>
			</select>
			<p></p>
			<% if(questionOfferList.size()!=0){ %>
			<% for(int i=0; i<questionOfferList.size(); i++){ %>
			<!-- 세로보기 문항 -->
			<% if(questionDTO.isQ_typeDirection()==true){ %>
			<%= i+1 %>번 문항 : <%= questionOfferList.get(i).getTitle() %> <p></p>
			<!-- 가로보기 문항 -->
			<%}else{ %>
			<label class="radio-inline">
            <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1"><%= questionOfferList.get(i).getTitle() %>
        	</label>
			<%} %>
			<%} %>
			<%} %>
			
			<p></p>
          	 문항 이름 : <input type="text" name="trand_title" id="name" class="input-md form-control" maxlength="100" value="<%=questionDTO.getQue_title() %>">
     		<p></p>
     		역산 : 
      		<select class="input-md form-control"> 
			<option <% if(questionDTO.isQue_isReverseType() ==true) {%> selected <%} %> value="1"> 역산아니다.</option>
			<option <% if(questionDTO.isQue_isReverseType() ==false) {%> selected <%} %> value="0"> 역산이다.</option>
			</select>
			<p></p>
     		<input type="submit"  style="float:right" class="btn btn-mod btn-medium btn-round" value="수정하기">
            </div>
            </form>
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