<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Service.QuestionService" %>
<%@ page import="SurveyRelationDTO.QuestionTypeDTO" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<script language="javascript">

function addInput(offerSeq) {
var strInput = "";
document.getElementById('inputBox').innerHTML = "";

for (var i=1; i <= offerSeq; i++) {
  strInput += "&nbsp;&nbsp; "+i+"번 내용: <input type=\"text\" name=\"type_OfferTitle"+i+"\" class=\"input-md form-control\" maxlength=\"100\"><br><br>";
}

document.getElementById('inputBox').innerHTML = strInput; 
}

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
                                
			<form method="post" action="/PeerSys/typeAdd.qs" id="form" role="form">
			
			
										
			유형 이름 : <input type="text" name="type_title" id="name" class="input-md form-control" maxlength="100">
			<p></p> 
			질문 개수 :  <select name="offerSeq" class="input-md form-control" onChange="addInput(this.value);">
										<option value="0">주관식</option>
										<option value="0">또래지명</option>
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                        <option value="5">5</option>
                                        <option value="6">6</option>
                      </select>
                      
            <p></p>
            <div id="inputBox">
            
            </div>
			<input type="submit" style="float:right;" class="btn btn-mod btn-medium btn-round" value="추가하기">
			
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