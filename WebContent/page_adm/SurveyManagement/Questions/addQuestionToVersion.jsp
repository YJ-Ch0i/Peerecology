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

<script language="javascript" >

function addQuestion() {
var cntQuestion = 0;
var inputCheckQuestions = ""
	inputCheckQuestions = parent.document.getElementById('addedQuestion').innerHTML;
checkQuestions = document.getElementsByName("checkQuestions");
splitQuestions = inputCheckQuestions.split('input type="text"');
cnt = splitQuestions.length-1;
var size = checkQuestions.length;

	for(var i=0; i<size; i++)
	{
		if(checkQuestions[i].checked)
			{
			var questionNumber = checkQuestions[i].value.split('_');
			cnt++;
			inputCheckQuestions +="<p id=\"text"+cnt+"\" style=\"display:inline;\">"+ cnt + "번 문항 : </p>"
								+ "<input type=\"text\" name=\"questionName" +cnt+" \" id=\"name\"" 
							 	+"class=\"input-md form-control\" maxlength=\"100\" readonly value=\""+questionNumber[0]+"\">"
							 	+"<input type=\"button\" value=\"삭제하기\" style=\"font-size: 15px; height: 35px;\" onclick=\"deleteQuestion("+cnt+")\">"
								+"<input type=\"hidden\" value=\"" + questionNumber[1] + "\" name=\"questionId\">"
							 	+"<p></p>";
			}
	}
parent.document.getElementById('addedQuestion').innerHTML = inputCheckQuestions;
alert('추가되었습니다.')
self.document.location.reload();
	
}
</script>
</head>
<body class="appear-animate">
<% 
QuestionService queSerivce = QuestionService.getInstance(); 
ArrayList<QuestionTypeDTO> types = new ArrayList<QuestionTypeDTO>();
types = queSerivce.showAllType();
%>
	<!-- Page Loader -->
	<div class="page-loader">
		<div class="loader">Loading...</div>
	</div>
	<!-- End Page Loader -->

	<!-- Page Wrap -->
	<div class="page" id="top">
			<section class="page-section">
			<div class="container relative">

				<!-- Row -->
				
<% 
ArrayList<QuestionDTO> questions = new ArrayList<QuestionDTO>();
ArrayList<QuestionTypeDTO> queTypes = new ArrayList<QuestionTypeDTO>();
queTypes = queSerivce.showAllType();
questions = queSerivce.showAllQuestion();
%>
					<form method="POST" name="form1" id="form1" role="form">
			 
					<div class="works-filter font-alt">
					
					<a href="#" class="filter active" data-filter="*"><모든 문항></a> 
					<% for(int i=0; i<queTypes.size(); i++){ %>
					
                    <a href="#<%=queTypes.get(i).getQ_typeID()%>" class="filter" data-filter=".<%=queTypes.get(i).getQ_typeID() %>"> < <%=queTypes.get(i).getDescript() %> >
                    </a>
                    <%} %>
                    </div>
                    
                <ul class="works-grid work-grid-5 clearfix font-alt hover-white hide-titles" id="work-grid">
                <%if(questions.size()==0){ %> 문항을 아직 등록하지 않으셨습니다. <%} %>
                    <% for(int i=0; i<questions.size(); i++){ %>
                     <li class="work-item mix <%=questions.get(i).getQType() %>">
                    <div class="mb-20 mb-md-10" id="hiddingHidden">
                                    <label class="checkbox-inline">
                                         <input type="checkbox" name="checkQuestions" id="inlineCheckbox1" value="<%=questions.get(i).getTitle() %>_<%=questions.get(i).getQID()%>">
                                         <%=questions.get(i).getTitle() %>
                                    </label>
                     
                     </div>
                     </li>
                    <%} %>
                </ul>
                 <input type="button" style="float:right" class="btn btn-mod btn-medium btn-round" value="추가하기" onclick="addQuestion()">
                 
                 </form>
			</div>
			
		</section>
	</div>

	<!-- JS -->
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