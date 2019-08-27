<%@page import="SurveyRelationDTO.QuestionDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Date"%>
<%@page import="Service.QuestionService"%>
<%@page import="SurveyRelationDTO.QuestionTrandTypeDTO"%>
<%@page import="SurveyRelationDTO.QuestionTrandManagerDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<%@ page import="Service.TeacherService"%>
<%@ page import="Service.StudentService"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="User.UserDTO.StudentDTO"%>
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
<!-- <link rel="stylesheet" href="/PeerSys/style/css/bootstrap.min.css"> -->
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="/PeerSys/style/css/style.css">
<link rel="stylesheet" href="/PeerSys/style/css/style-responsive.css">
<link rel="stylesheet" href="/PeerSys/style/css/animate.min.css">
<link rel="stylesheet" href="/PeerSys/style/css/vertical-rhythm.min.css">
<link rel="stylesheet" href="/PeerSys/style/css/owl.carousel.css">
<link rel="stylesheet" href="/PeerSys/style/css/magnific-popup.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/vis/4.21.0/vis.min.css" type="text/css" />

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
					<h1 class="hs-line-11 font-alt mb-20 mb-xs-0"><%=request.getAttribute("year")%>학년도 <%=request.getAttribute("sch_name")%> <%=request.getAttribute("grade") %>학년 <%=request.getAttribute("grdNum") %>반 설문조사 결과</h1>
				</div>
			</div>
		</section>
		<!-- End Head Section -->
				
				<%
					ArrayList<String> scoresJson = new ArrayList<>();
					scoresJson = (ArrayList<String>) request.getAttribute("scoresJson");
					request.setAttribute("scoresJson", scoresJson);
					
					ArrayList<String> trandJson = new ArrayList<>();
					trandJson = (ArrayList<String>) request.getAttribute("trandJson");
					request.setAttribute("trandJson", trandJson);
					
					ArrayList<String> stuJson = new ArrayList<>();
					stuJson = (ArrayList<String>) request.getAttribute("stuJson");
					request.setAttribute("stuJson", stuJson);
					
					ArrayList<String> bigTrandJson = new ArrayList<>();
					bigTrandJson = (ArrayList<String>) request.getAttribute("bigTrandJson");
					request.setAttribute("bigTrandJson", bigTrandJson);
					
					ArrayList<QuestionTrandManagerDTO> bigTrandList = new ArrayList<>();
					bigTrandList = (ArrayList<QuestionTrandManagerDTO>) request.getAttribute("bigTrandList");
					request.setAttribute("bigTrandList", bigTrandList);
					
					ArrayList<String> endSurJson = new ArrayList<>();
					endSurJson = (ArrayList<String>) request.getAttribute("endSurJson");
					request.setAttribute("endSurJson", endSurJson);
					
					ArrayList<String> mixedTrand = new ArrayList<>();
					mixedTrand = (ArrayList<String>) request.getAttribute("mixedTrand");
					request.setAttribute("mixedTrand", mixedTrand);
					
					ArrayList<StudentDTO> attendList = new ArrayList<>();
					attendList = (ArrayList<StudentDTO>) request.getAttribute("attendList");
					request.setAttribute("attendList", attendList);
					
					QuestionService queService = QuestionService.getInstance();
					ArrayList<QuestionTrandTypeDTO> trandTobigTrand = new ArrayList<>();
					for(int i=0; i<bigTrandList.size(); i++){
						QuestionTrandTypeDTO dto = new QuestionTrandTypeDTO();
						trandTobigTrand = queService.getTrandToBigTID(bigTrandList.get(i).getBigTrandID());						
					}
					
					String scid = (String) request.getAttribute("scid");
					int ingSeq = (Integer) request.getAttribute("ingSeq");
					int surNo = (Integer)request.getAttribute("surNo");
					Date startdate = (Date) request.getAttribute("startdate");
					Date enddate = (Date) request.getAttribute("enddate");		
					int grade = (Integer) request.getAttribute("grade");
					int grdNum = (Integer) request.getAttribute("grdNum");
					String year = (String) request.getAttribute("year");
					
					List<QuestionDTO> peerQueList = queService.getPeerQuestionListInSeq(surNo);
				%>
				
				<textarea id="scoresJson" style="display:none"><%=scoresJson %></textarea>
				<textarea id="trandJson" style="display:none"><%=trandJson %></textarea>
				<textarea id="stuJson" style="display:none"><%=stuJson %></textarea>
				<textarea id="bigTrandJson" style="display:none"><%=bigTrandJson %></textarea>
				<textarea id="endSurJson" style="display:none"><%=endSurJson %></textarea>				
				<textarea id="mixedTrand" style="display:none"><%=mixedTrand %></textarea>

		<!-- Section -->
		<section class="page-section">
			<div class="container relative">	
				<div class="text-right">
					<a href="#">예정</a>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-4">
								<h2 class="panel-title">문항별 학급 관계 시각화</h2>
							</div>
							<div class="col-xs-3">														
								<select id="" class="input-md form-control" onChange="queSelect(this.value);">
								<option value='-2' selected>선택 해 주세요</option>
								<%for(QuestionDTO dto : peerQueList){ %>
									<option value=
									'{"qid":"<%=dto.getQID()%>","seq":"<%=ingSeq%>","scid":"<%=scid %>","grade":"<%=grade%>","grdNum":"<%=grdNum%>", "year" : "<%=year%>"}'><%=dto.getTitle()%></option>
								<%} %>
								</select>
							</div>
						</div>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-12" style="min-height:40px; max-height:700px;">
								<div id="networkSector" class="row" style="display:none; min-height:400px; max-height:500px;">
									<div class="col-md-12" id="peerNetwork" style="position: relative; overflow: hidden; touch-action: pan-y; user-select: none; -webkit-user-drag: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); width: 100%; height: 500px;"></div>
									<div class="col-md-3" style="text-align:center; min-width:450px; max-width:450px; min-height:500px; max-height:600px; margin:0 auto">
										<div class="row" style="text-align:center; min-width:300px; max-width:300px; margin:0 auto">
											<br><br><br><br>
											<label id="" class="control-label" for="inputWarning2">11111111111111</label>
										</div>									
									</div>
								</div>
								<div id="networkSelectSector" class="row" style="height:200px">
									<div class="col-md-9" style="min-width:310px; max-width:600px; min-height:200px; max-height:200px;">
										<h2>문항을 선택 해 주세요</h2>									
									</div>									
								</div>
							</div>
						</div>
					</div>
				</div>										
								
				<br>
				<br>
				
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-4">
								<h2 class="panel-title">척도 분류별 학급전체 점수</h2>
							</div>
							<div class="col-xs-3">														
								<select id="trandBigName" class="input-md form-control" onChange="BTselect_result(this.value);">
								<option value='-2' selected>선택 해 주세요</option>
									<%for(int i=0; i<bigTrandList.size(); i++){ %>
										<option value="<%=bigTrandList.get(i).getBigTrandID()%>"><%=bigTrandList.get(i).getDescript() %></option>
									<%} %>
								</select>
							</div>
						</div>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-12">
								<div id="barSplineSector" style="display:none;" class="row">
									<div class="col-md-9" id="barchart" style="min-width:310px; max-width:600px; min-height:600px; max-height:1500px;">																		
									</div>		
									<div class="col-md-5" style="text-align:center; min-width:450px; max-width:450px; min-height:600px; max-height:1500px; margin:0 auto">
										<div class="row" style="text-align:center; min-width:300px; max-width:300px; margin:0 auto">
											<br><br><br><br><br><br>
											<label id="bigTdesc" class="control-label" for="inputWarning2"></label>
										</div>
									</div>
								</div>
								<div id="selectSector" class="row">
									<div class="col-md-9" id="barchart" style="min-width:310px; max-width:600px; min-height:200px; max-height:200px;">
										<h2>척도 분류를 선택 해 주세요</h2>									
									</div>		
								</div>					
													
								<div class="row">
									<div class="alert alert-warning" role="alert">
										<div class="row">
											<div class="col-xs-4">
												<h2 class="panel-title">학생별 점수</h2>
											</div>
											<div class="col-xs-3">														
												<select id="studentSector" class="input-md form-control" onChange="studentSelector(this.value);">
												<option value='-2' selected>학생을 선택 해 주세요</option>
													<%for(int i=0; i<attendList.size(); i++){ %>
														<option value=
														'{"sid":"<%=attendList.get(i).getStu_id()%>","scid":"<%=attendList.get(i).getScid() %>","grade":"<%=attendList.get(i).getGrade() %>","grd_num":"<%=attendList.get(i).getGrd_num()%>","year":"<%=attendList.get(i).getYear()%>"}'><%=attendList.get(i).getName() %></option>													
													<%} %>
												</select>
											</div>
										</div>
									</div>
								</div>			
							
								<div class="row" id="radarSector" style="display:none;">									
									<% for(int i=0; i<bigTrandList.size(); i++){ %>
									<hr size='5'>
										<br>														
										<div class="col-md-9" id="radar<%=bigTrandList.get(i).getBigTrandID() %>" style="min-width:310px; max-width:600px; min-height:600px; max-height:1500px;">									
										</div>
										<div class="col-md-5" style="text-align:center; min-width:450px; max-width:450px; min-height:600px; max-height:1500px; margin:0 auto">
											<div class="row" style="text-align:center; min-width:300px; max-width:300px; margin:0 auto">
												<br><br><br><br><br><br>
												<label id="raderDesc<%=bigTrandList.get(i).getBigTrandID() %>" class="control-label" for="inputWarning2"></label>
											</div>									
										</div>									
									<%} %>
								</div>								
								<div id="radarSelectSector" class="row">
									<div class="col-md-9" style="min-width:310px; max-width:600px; min-height:200px; max-height:200px;">
										<h2>학생을 선택 해 주세요</h2>									
									</div>									
								</div>																			
							</div>
							
						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- End Section -->

		<!-- Footer -->
		<%@ include file="../../pageInclude/Footer.jsp"%>
		<!-- End Footer -->

	</div>
	<!-- End Page Wrap -->


	<!-- JS -->
	<script type="text/javascript" src="https://d3js.org/d3.v4.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
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
	<script type="text/javascript" src="/PeerSys/style/js/wow.min.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/masonry.pkgd.min.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/jquery.simple-text-rotator.min.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/all.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/contact-form.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/jquery.ajaxchimp.min.js"></script>
	<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="https://code.highcharts.com/highcharts.js"></script>
	<script type="text/javascript" src="https://code.highcharts.com/highcharts-more.js"></script>
	<script type="text/javascript" src="https://code.highcharts.com/modules/exporting.js"></script>
	<script type="text/javascript" src="https://code.highcharts.com/modules/export-data.js"></script>
	<script type="text/javascript" src="https://code.highcharts.com/modules/networkgraph.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/vis/4.21.0/vis.min.js"></script>
	<!--[if lt IE 10]><script type="text/javascript" src="js/placeholder.js"></script><![endif]-->
	<script type="text/javascript" src="/PeerSys/style/js/studentTransfer.js"></script>
	<!-- <script type="text/javascript" src="/PeerSys/style/js/visualize/stuBarGraph.js"></script> -->
	<!-- <script type="text/javascript" src="/PeerSys/style/js/visualize/barSplineToResult.js"></script> -->
	<!-- <script type="text/javascript" src="/PeerSys/style/js/visualize/barSplineResultAjax.js"></script> -->
	<!-- <script type="text/javascript" src="/PeerSys/style/js/visualize/raiderGph.js"></script> -->
	<script type="text/javascript" src="/PeerSys/style/js/ajax/trandLoadAjax.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/ajax/stuResultRoaderAjax.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/ajax/peerLoaderAjax.js"></script>
	
	<!-- <script src="https://code.highcharts.com/highcharts.js"></script> -->
	<!-- <script type="text/javascript" src="/PeerSys/style/js/visualize/barSpline.js"></script> -->
	
	
</body>
</html>

