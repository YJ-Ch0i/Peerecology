<%@page import="School.SchoolDTO.SchoolDTO"%>
<%@page import="Service.SchoolService"%>
<%@page import="User.UserDTO.TeacherDTO"%>
<%@page import="User.UserDTO.StudentDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Service.StudentService"%>
<%@page import="Service.TeacherService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
<link rel="stylesheet" href="/PeerSys/style/css/bootstrap.min.css">
<link rel="stylesheet" href="/PeerSys/style/css/style.css">
<link rel="stylesheet" href="/PeerSys/style/css/style-responsive.css">
<link rel="stylesheet" href="/PeerSys/style/css/animate.min.css">
<link rel="stylesheet" href="/PeerSys/style/css/vertical-rhythm.min.css">
<link rel="stylesheet" href="/PeerSys/style/css/owl.carousel.css">
<link rel="stylesheet" href="/PeerSys/style/css/magnific-popup.css">

<script type="text/javascript" src="/PeerSys/style/js/studentTransfer.js"></script>
</head>

<% String tea_id = (String)session.getAttribute("tea_id"); 
   TeacherService service = TeacherService.getInstance();
   Boolean isEmailChecked =service.getEamilChecked(tea_id);
   
   if(session.getAttribute("tea_id") == null){%>
   <script>
   		alert("로그인을 해주세요.");
		location.href="/PeerSys/page_tea/login.jsp";
   </script>
   <%} else if(isEmailChecked==false){ %>
	<script> 
   		alert("이메일 인증 먼저 해주세요.");
   		location.href="/PeerSys/page_tea/login.jsp";
   </script>
<%} %>
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
					<h1 class="hs-line-11 font-alt mb-20 mb-xs-0">전체 학급</h1>
				</div>
			</div>
		</section>
		<!-- End Head Section -->


		<!-- Section -->
		<section class="page-section">
			<div class="container relative">

				<%                    
                   TeacherService teaService = TeacherService.getInstance();    
				   TeacherDTO teacher = teaService.teacherInfo(tea_id);
				   SchoolService schService = SchoolService.getInstance();	
				   SchoolDTO school = schService.getSchoolToSCID(teacher.getSCID());
                   StudentService studentService = StudentService.getInstance();
                   ArrayList<StudentDTO> stu_list = studentService.studentList(tea_id, teacher.getSCID());      
                   
                   %>
				 <div class="row">
				 	<h3 class="hs-line-11 font-alt mb-20 mb-xs-0">학급 학생 관리</h3>
				 	<div class="col-md-10"></div>
                  	<div class="col-md-2">       
                  		<a href="#" class="btn btn-mod btn-circle btn-medium">전학생 추가하기</a> 
                  	</div>
                 </div>
                 <div class="row">
                 	<br>                
                 </div>
                 <div class="row">
                  	<div>       
                        <table class="table table-striped">
                            <tr>
                                <th class="">
									학교
                                </th>
                                <th>
									학년
                                </th>
                                <th>
									반
                                </th>
                                <th>
									번호
                                </th>
                                <th>
									성별
                                </th>
                                <th>
									이름
                                </th>
                                <th>
                                	재학 여부
                                </th>
                                <th>
                                	재학/전학 처리
                                </th>
                            </tr>
                            
                            <%
                           		String gender = "";
                            	String transf = "";
                            	
                            	for(StudentDTO stu : stu_list){
                            		if(stu.getGender() == 1){
                                		gender = "남자";
                                	}
                            		if(stu.getGender() == 2){
                            			gender = "여자";
                            		}
                            		if(stu.isTransfer() == true){
                            			transf = "재학";
                            		}
                            		if(stu.isTransfer() == false){
                            			transf = "전학";
                            		}
                            %>
                            <tr>
                                <td class="hidden-xs">
                                    <%=school.getScname() %>
                                </td>
                                <td>
                                    <%=stu.getGrade() %> 학년
                                </td>
                                <td>
                                    <%=stu.getGrd_num() %> 반
                                </td>
                                <td>
                                    <%=stu.getNum() %>
                                </td>
                                <td>
                                    <%=gender %>
                                </td>
                                <td>
                                    <%=stu.getName() %>
                                </td>
                                <td>
                                	<%=transf %>
                                </td>
                                <td> 
                                	<form id="transfForm" action="/PeerSys/StudentTransfer.st" method="post">
                                		<input type="hidden" id="stu_name" name="stu_name" value="<%= stu.getName()%>">
	                                    <i class="fa fa-times"></i><input id="transf" type="button"
	                                    class="btn btn-mod btn-circle btn-small" value="전학 처리">
                                	</form>                                   
                                    <!-- <a href="#" style="text-decoration:none"><i class="fa fa-times"></i><span class="hidden-xs">전학처리</span></a> -->                                                                        
                                </td>
                            <% } %>
                            </tr>
                    	</table>
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
	<script type="text/javascript" src="/PeerSys/style/js/wow.min.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/masonry.pkgd.min.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/jquery.simple-text-rotator.min.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/all.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/contact-form.js"></script>
	<script type="text/javascript" src="/PeerSys/style/js/jquery.ajaxchimp.min.js"></script>
	<!--[if lt IE 10]><script type="text/javascript" src="js/placeholder.js"></script><![endif]-->

</body>
</html>
