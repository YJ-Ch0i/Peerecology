<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Service.QuestionService" %>
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
<link rel="stylesheet" href="/PeerSys/style/css/style.css?version=1">
<link rel="stylesheet" href="/PeerSys/style/css/style-responsive.css">
<link rel="stylesheet" href="/PeerSys/style/css/vertical-rhythm.min.css">
<link rel="stylesheet" href="/PeerSys/style/css/owl.carousel.css">
<link rel="stylesheet" href="/PeerSys/style/css/magnific-popup.css">
<script type="text/javascript" src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body class="appear-animate">
<% 
QuestionService queSerivce = QuestionService.getInstance(); 
ArrayList<QuestionTrandTypeDTO> queTrands = new ArrayList<QuestionTrandTypeDTO>();
ArrayList<QuestionTrandManagerDTO> queBigTrands = new ArrayList<QuestionTrandManagerDTO>();
ArrayList<QuestionTypeDTO> queTypes = new ArrayList<QuestionTypeDTO>();
queBigTrands = queSerivce.showAllBigTrands();
queTrands = queSerivce.showAllTrand();
queTypes = queSerivce.showAllType();
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
			<form method="post" action="/PeerSys/questionAdd.qs" id="form" role="form">
			<% if(queTrands.size()!=0) {%>
			척도 분류 : 
			<select id="trandBigName" name="trand_managerID" class="input-md form-control" onChange="BTselect(this.value);"> 
			<option value="-1" selected>선택해주세요.</option>
			<% for(int i=0; i<queBigTrands.size(); i++){ %>
			<option value="<%=queBigTrands.get(i).getBigTrandID()%>"><%=queBigTrands.get(i).getDescript() %></option>
			<%} %>
			</select>
			<%}else{ %>
			<script> alert('척도를 먼저 만들어 주세요.'); </script>
			<script> location.href = 'TrandAddPage.jsp' </script>
			<%} %>
            <p></p>
            <div id="trandTypeDiv" style="display:none;">
           	척도 이름 : 
           	<select id="que_trandTypeID" name="que_trandTypeID" class="input-md form-control"> 
			<option value='-1'>척도를 선택 해 주세요</option>
			</select>
			<p></p>
			</div>
           	 응답 유형 :   
           	<select class="input-md form-control" name="que_typeID" id="whatType" onChange="changeInput(this.value);"> 
			<option value="-1" selected>직접 입력하기</option>
			<% for(int i=0; i<queTypes.size(); i++){ %>
			<option value="<%=queTypes.get(i).getQ_typeID() %>"><%=queTypes.get(i).getDescript() %></option>
			<%} %>
			</select>
			<p></p>
			<div id="isSelectCreate">
			&nbsp;&nbsp;&nbsp;응답 유형 이름 : <input type="text" name="type_title" id="name" class="input-md form-control" maxlength="100">
			<p></p> 
			&nbsp;&nbsp;&nbsp;보기 방향 :  <select name="q_typeDirection" class="input-md form-control" onChange="offerDirection(this.value);">
	                   						<option value="0" selected>선택해주세요.</option>
											<option value="1">세로 보기형</option>
											<option value="2">가로 보기형</option>
				  	  					</select>
			<p></p>
			&nbsp;&nbsp;&nbsp;질문 개수 :  <select name="offerSeq" class="input-md form-control" onChange="addInput(this.value);">
										<option value="0">선택해주세요</option>
									 	<option value="1">1</option>
			                            <option value="2">2</option>
			                            <option value="3">3</option>
			                            <option value="4">4</option>
			                            <option value="5">5</option>
			                            <option value="6">6</option>
			          </select>
			          <p></p>
            
			<p></p>
			<p></p>
            </div>   
			<div id="inputBox">
			</div>
			<p></p>
		          문항 이름 : <input type="text" name="que_title" id="name" class="input-md form-control" maxlength="100">
		    <p></p>
		    <div id="isReverseDiv">
      		역산 : 
      		<select name="isReverseType" class="input-md form-control" > 
			<option value="1"> 역산아니다.</option>
			<option value="0"> 역산이다.</option>
			</select>
			<p></p>
			</div>
			<div id="isAnswerValueDiv">
			정답 여부 : 
			<select name="isAnswer" class="input-md form-control" onChange="isQuestionAnswering(this.value);"> 
			<option value="0"> 정답이 없다.</option>
			<option value="1"> 정답이 있다.</option>
			</select>
			<p></p>
			</div>
			<div id="inputAnswer">
			</div>
     		<input type="submit"  style="float:right" class="btn btn-mod btn-medium btn-round" value="추가하기">
			</form>
            </div>
			</section>
	</div>
	 <table id="tblList" border="1"></table>

	<!-- JS -->
	
	<script type="text/javascript" src="/PeerSys/style/js/addQuestionFuc.js"></script>
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
	<script type="text/javascript" src="/PeerSys/style/js/ajax/trandLoadAjax.js"></script>
	<!--[if lt IE 10]><script type="text/javascript" src="js/placeholder.js"></script><![endif]-->

</body>
</html>