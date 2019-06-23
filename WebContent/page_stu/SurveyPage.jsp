<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="Question.QuestionDTO.QuestionDTO"%>
<%@ page import="Service.StudentService"%>
<%@ page import="User.UserDTO.StudentDTO"%>
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
<link rel="shortcut icon" href="/Survey/style/images/favicon.png">

<!-- CSS -->
<link rel="stylesheet" href="/Survey/style/css/bootstrap.min.css">
<link rel="stylesheet" href="/Survey/style/css/style.css">
<link rel="stylesheet" href="/Survey/style/css/style-responsive.css">
<link rel="stylesheet" href="/Survey/style/css/animate.min.css">
<link rel="stylesheet" href="/Survey/style/css/vertical-rhythm.min.css">
<link rel="stylesheet" href="/Survey/style/css/owl.carousel.css">
<link rel="stylesheet" href="/Survey/style/css/magnific-popup.css">

</head>
<body class="appear-animate">
	<% 
    int cntQuestions = (int)session.getAttribute("cntQuestions");
    ArrayList<QuestionDTO> questionList = (ArrayList<QuestionDTO>)session.getAttribute("questionList");
    String class_id = (String) session.getAttribute("class_id");
    StudentService stuService = StudentService.getInstance();
    ArrayList<StudentDTO> studentList = (ArrayList<StudentDTO>)stuService.StudentSearchToClass_id(class_id);
    %>
	<!-- Page Loader -->
	<div class="page-loader">
		<div class="loader">Loading...</div>
	</div>
	<!-- End Page Loader -->

	<!-- Page Wrap -->
	<div class="page" id="top">

		<!-- Navigation panel -->
		<%@include file="../pageInclude/Header.jsp"%>
		<!-- End Navigation panel -->
	</div>
	<!-- Section -->
	<section class="page-section">

		<% 
                     String[] tenList = new String[30];
        %>
		<h1 style="text-align: center;">
			<%= session.getAttribute("stu_desc") %>
		</h1>
		<form style="margin-left: 25px;"
			action="/Survey/registerSurveyResult.rs" method="post">
			<input type="hidden" name="stu_cnt" value="<%=studentList.size()%>">
			<% for(int i=0; i<questionList.size(); i++){ %>
			<% 
                   boolean isAlreadyPut = true;
                   for(int j=0; j<tenList.length; j++)
                   {
                	  if(tenList[j]!=null&&tenList[j].equals(questionList.get(i).getTen_id()))  
                	  {
                		  isAlreadyPut = false;
                	  }
                   }
                   if(isAlreadyPut)  tenList[i] = questionList.get(i).getTen_id();
                   %>
                   
			<% if(questionList.get(i).getQue_type().equals("N지 선다(N개 보기 변경)")) {%>
			<input type="hidden" name="<%=i+1 %>que_type"
				value="<%=questionList.get(i).getQue_type() %>"> <input
				type="hidden" name="<%=i+1%>tency_id"
				value="<%= questionList.get(i).getTen_id() %>">
			<div style="display: block; margin-top: 10px">
				<div
					style="display: flex; margin: 0 auto; -webkit-box-pack: justify; justify-content: space-between; text-align: center;">
					<h4>
						<%=i+1 %>
						.
						<%= questionList.get(i).getQue_text() %>
					</h4>
				</div>
				<% String[] splitQuestExamTxt = questionList.get(i).getExam_text().split(","); %>
				<% for(int k=0; k<splitQuestExamTxt.length; k++){ %>
				<label style="width: 100%; margin-top: 1px;"> <input
					type="radio" id="<%=i+1 %>nn_select" name="<%=i+1 %>nn_select"
					value="<%=k+1%>"> <%= splitQuestExamTxt[k] %>
				</label>
			</div>
			<%} %>

			<%}else if(questionList.get(i).getQue_type().equals("N지 선다(2개 보기 변경)")) {%>
			<input type="hidden" name="<%=i+1 %>que_type"
				value="<%=questionList.get(i).getQue_type() %>"> <input
				type="hidden" name="<%=i+1%>tency_id"
				value="<%= questionList.get(i).getTen_id() %>">
			<% String[] splitQuestExamTxt2 = questionList.get(i).getExam_text().split(","); %>
			<div style="display: block; margin-top: 10px">
				<div
					style="display: flex; margin: 5px 0 5px 0; -webkit-box-pack: justify; justify-content: space-between; text-align: center;">
					<h4>
						<%= i+1 %>
						.
						<%= questionList.get(i).getQue_text() %>
					</h4>
				</div>
				<div style="display: flex; width: 100%">
					<div
						style="align-items: stretch; display: flex; flex-grow: 1; flex-direction: column; text-align: center; max-width: 30%;">
						<div
							style="align-items: stretch; display: flex; flex-grow: 1; flex-direction: column; text-align: center; max-width: 30%;"></div>
						<div
							style="align-items: stretch; display: flex; flex-grow: 1; flex-direction: column; text-align: center; max-width: 30%;">
							<%= splitQuestExamTxt2[0] %></div>
					</div>
					<% for(int q=0; q<questionList.get(i).getExam_cnt() ;q++){ %>
					<label style="width: 20%">
						<div
							style="display: flex; align-item: center; width: 10%; text-align: center; cursor: default; -webkit-box-pack: center; justify-content: center; min-height: 3em; padding: 0 5px"></div>
						<div
							style="display: flex; align-item: center; width: 10%; -webkit-box-pack: center; justify-content: center; min-height: 3em; padding: 0 5px">
							<input type="radio" id="<%=i+1 %>n2_select"
								name="<%=i+1 %>n2_select" value="<%=q+1%>">
						</div>
					</label>
					<%} %>
					<div
						style="align-items: stretch; display: flex; flex-grow: 1; flex-direction: column; text-align: center; max-width: 30%;">
						<div
							style="align-items: stretch; display: flex; flex-grow: 1; flex-direction: column; text-align: center; max-width: 20%;"></div>
						<div
							style="align-items: stretxch; display: flex; flex-grow: 1; flex-direction: column; text-align: center; max-width: 30%;">
							<%=splitQuestExamTxt2[1] %></div>
					</div>
				</div>
			</div>
			<%} else if(questionList.get(i).getQue_type().equals("또래지명")) {%>
			<div
				style="display: flex; margin: 5px 0 5px 0; -webkit-box-pack: justify; justify-content: space-between; text-align: center;">

				<input type="hidden" name="<%=i+1 %>que_type"
					value="<%=questionList.get(i).getQue_type() %>">
				<div style="width: 80%; margin-top: 10px">
					<div
						style="display: flex; margin: 5px 0 5px 0; -webkit-box-pack: justify; justify-content: space-between; text-align: center;">
						<h4>
							<%=i+1 %>
							.
							<%= questionList.get(i).getQue_text() %>
						</h4>
					</div>
					<% for(int j=0; j<studentList.size(); j++){ %>
					<div style="margin-top: 5px" class="col-md-2 col-lg-2 mb-md-10">
					<% int stu_num = studentList.get(j).getStu_num(); %>
						<%if((int)session.getAttribute("stu_num") != stu_num){ %>
						<label>
						
						<input type="checkBox" name="<%=i+1%>chkStu"
							value="<%= studentList.get(j).getStu_num()%>"> 
					    <%= studentList.get(j).getStu_name() %>
							
						</label>
						<%} %>
						
					</div>

					<%}%>
				</div>
			</div>
			<%}else if(questionList.get(i).getQue_type().equals("주관식")) {%>
			<input type="hidden" name="<%=i+1 %>que_type" value="<%=questionList.get(i).getQue_type() %>">
			<input type="hidden" name="<%=i+1%>tency_id" value="<%= questionList.get(i).getTen_id() %>">
			<div style="display: block; margin-top: 10px">
				<div
					style="display: flex; margin: 5px 0 5px 0; -webkit-box-pack: justify; justify-content: space-between; text-align: center;">
					<h4><%=i+1 %>
						.
						<%= questionList.get(i).getQue_text() %></h4>
				</div>
				<textarea class="input-md form-control" style="height: 120px;"
					name="<%= i+1 %>subjectAnswer"></textarea>
				<input type="hidden" name="<%=i+1%>subAnswer"
					value="<%=questionList.get(i).getAnswer_text() %>">
			</div>

			<%}else if(questionList.get(i).getQue_type().equals("N지 선다(체크 형식)")) { %>
			<input type="hidden" name="<%=i+1 %>que_type" value="<%=questionList.get(i).getQue_type() %>"> 
			<input type="hidden" name="<%=i+1%>tency_id" value="<%= questionList.get(i).getTen_id() %>"> 
			<input type="hidden" name="<%=i+1%>exam_answer" value="<%= questionList.get(i).getExample_answer() %>">
			<div style="display: block; margin-top: 10px">
				<div
					style="display: flex; margin: 0 auto; -webkit-box-pack: justify; justify-content: space-between; text-align: center;">
					<h4>
						<%=i+1 %>
						.
						<%= questionList.get(i).getQue_text() %>
					</h4>
				</div>
				<% String[] splitQuestExamTxt = questionList.get(i).getExam_text().split(","); %>
				<% for(int k=0; k<splitQuestExamTxt.length; k++){ %>
				<label style="width: 100%; margin-top: 1px;"> <input
					type="radio" id="<%=i+1 %>nn_select" name="<%=i+1 %>nnc_select"
					value="<%=k+1%>"> <%= splitQuestExamTxt[k] %> <input
					type="hidden" name="<%=i+1%>nnc_answer"
					value="<%= questionList.get(i).getExample_answer() %>">
				</label>
			</div>
			<%} %>
			<%} %>
			<%} %>
			<% for(int i=0; i<tenList.length; i++){ %>
			<input type="hidden" value="<%= tenList[i] %>" name="tenList">
			<%} %>
			<input type="submit">
		</form>
	</section>
	<!-- End Section -->

	<!-- Footer -->
	<%@ include file="../pageInclude/Footer.jsp"%>
	<!-- End Footer -->

	</div>
	<!-- End Page Wrap -->


	<!-- JS -->
	<script type="text/javascript"
		src="/Survey/style/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript"
		src="/Survey/style/js/jquery.easing.1.3.js"></script>
	<script type="text/javascript" src="/Survey/style/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/Survey/style/js/SmoothScroll.js"></script>
	<script type="text/javascript"
		src="/Survey/style/js/jquery.scrollTo.min.js"></script>
	<script type="text/javascript"
		src="/Survey/style/js/jquery.localScroll.min.js"></script>
	<script type="text/javascript"
		src="/Survey/style/js/jquery.viewport.mini.js"></script>
	<script type="text/javascript" src="/Survey/style/js/jquery.countTo.js"></script>
	<script type="text/javascript" src="/Survey/style/js/jquery.appear.js"></script>
	<script type="text/javascript" src="/Survey/style/js/jquery.sticky.js"></script>
	<script type="text/javascript"
		src="/Survey/style/js/jquery.parallax-1.1.3.js"></script>
	<script type="text/javascript" src="/Survey/style/js/jquery.fitvids.js"></script>
	<script type="text/javascript"
		src="/Survey/style/js/owl.carousel.min.js"></script>
	<script type="text/javascript"
		src="/Survey/style/js/isotope.pkgd.min.js"></script>
	<script type="text/javascript"
		src="/Survey/style/js/imagesloaded.pkgd.min.js"></script>
	<script type="text/javascript"
		src="/Survey/style/js/jquery.magnific-popup.min.js"></script>
	<!-- Replace test API Key "AIzaSyAZsDkJFLS0b59q7cmW0EprwfcfUA8d9dg" with your own one below 
        **** You can get API Key here - https://developers.google.com/maps/documentation/javascript/get-api-key -->
	<script type="text/javascript"
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAZsDkJFLS0b59q7cmW0EprwfcfUA8d9dg"></script>
	<script type="text/javascript" src="/Survey/style/js/gmap3.min.js"></script>
	<script type="text/javascript" src="/Survey/style/js/wow.min.js"></script>
	<script type="text/javascript"
		src="/Survey/style/js/masonry.pkgd.min.js"></script>
	<script type="text/javascript"
		src="/Survey/style/js/jquery.simple-text-rotator.min.js"></script>
	<script type="text/javascript" src="/Survey/style/js/all.js"></script>
	<script type="text/javascript" src="/Survey/style/js/contact-form.js"></script>
	<script type="text/javascript"
		src="/Survey/style/js/jquery.ajaxchimp.min.js"></script>
	<!--[if lt IE 10]><script type="text/javascript" src="js/placeholder.js"></script><![endif]-->

</body>
</html>