<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class="main-nav js-stick">
	<div class="full-wrapper relative clearfix">
		<!-- Logo ( * your text or image into link tag *) -->
		<div class="nav-logo-wrap local-scroll">
			<a href="/PeerSys/index.jsp" class="logo"> <img
				src="/PeerSys/style/images/peer_logo.png" alt="" />
			</a>
		</div>
		<div class="mobile-nav">
			<i class="fa fa-bars"></i>
		</div>

		<!-- Main Menu -->
		<!-- 관리자 로그인 -->
		<% if(session.getAttribute("admin_id") != null){ %>
		<div class="inner-nav desktop-nav">
			<ul class="clearlist">
				<!-- 교사 직접 등록하기 -->
				<li>
				<a href="/PeerSys/page_adm/adminTeacherRegist.jsp" class="mn-has-sub"> 교사 직접등록하기 </a></li>
					<!-- 설문조사 관리 메뉴 -->
				<li><a
					href="/PeerSys/page_adm/SurveyManagement/formSurvey.jsp"
					class="mn-has-sub"> 설문조사 관리</a></li>

				<!-- 설문조사 진행 메뉴 -->
				<li><a href="#" class="mn-has-sub"> 설문조사 진행 <i class="fa fa-angle-down"></i></a>

					<ul class="mn-sub mn-has-multi">
						<li>
							<!-- formSurvey.jsp에 있는 유형 중 한가지를 선택해서 쓸 수 있도록 만들기. --> 
							<a href="/PeerSys/page_adm/SurveyStarting/startSurvey.jsp"> 설문조사 진행 </a> 
							<a href="/PeerSys/page_adm/SurveyStarting/goingSurvey.jsp"> 진행중인 설문조사 </a>
							<a href="/PeerSys/page_adm/SurveyResult/calculateSurvey.jsp"> 계산 대기중인 설문조사 </a>
						</li>
					</ul></li>

				<!-- 설문조사 결과 메뉴 SurveyResult -->
				<li><a href="#" class="mn-has-sub"> 설문조사 결과  <i class="fa fa-angle-down"></i></a>
					<ul class="mn-sub mn-has-multi">
						<li>
							<!-- formSurvey.jsp에 있는 유형 중 한가지를 선택해서 쓸 수 있도록 만들기. --> 
							<a href="/PeerSys/page_adm/SurveyResult/resultSchool.jsp"> 학교별 결과 </a> 
							<!-- <a href="/PeerSys/page_adm/SurveyResult/resultStudent.jsp"> 학생 검색 </a> -->
						</li>
					</ul>
				</li>
				<li><a href="/PeerSys/adminLogout.ad" class="mn-has-sub">
						로그아웃 </a></li>
				<!-- Divider -->
				<li><a>&nbsp;</a></li>
				<!-- End Divider -->

			</ul>
		</div>

		<%}
                    else if(session.getAttribute("tea_id") != null){ %>
		<!-- 선생님 로그인시 -->
		<!-- Main Menu -->
		<div class="inner-nav desktop-nav">
			<ul class="clearlist">

				<!-- Item With Sub -->
				<li>
					<!-- Item With Sub -->
				<li><a
					href="/PeerSys/page_tea/SurveyResult/findSurveyPage.jsp"
					class="mn-has-sub"> 설문조사 결과 </a> <!-- End Sub Multilevel --></li>
				<!-- End Item With Sub -->
				
				<!-- <li><a
					href="/PeerSys/page_tea/ingManager/ingSurvey.jsp"
					class="mn-has-sub"> 설문조사 진행 </a> </li> -->
				
				<!-- End Sub Multilevel -->
				<!-- Item With Sub -->
				<li><a href="#" class="mn-has-sub active"> 학급 관리 <i
						class="fa fa-angle-down"></i></a>
					<ul class="mn-sub mn-has-multi">
						<li>
						<a onclick="teacherAddClassCheck()"> 학급추가 </a>
						</li>
						<li><a
							href="/PeerSys/page_tea/ClassManagement/manageMentClass.jsp">
								학생 관리 </a></li>
					</ul></li>
				<li><a href="/PeerSys/teacherLogout.tc" class="mn-has-sub">
						로그아웃 </a></li>
				<!-- End Item With Sub -->

				<!-- Divider -->
				<li><a>&nbsp;</a></li>
				<!-- End Divider -->

			</ul>
		</div>
		<!-- End Main Menu -->

		<%}
                    else if(session.getAttribute("stu_id")!=null){ %>
		<!--  학생로그인 일때 -->
		<div class="inner-nav desktop-nav">
			<ul class="clearlist">


				<li><a href="/PeerSys/stuLogout.st" class="mn-has-sub">
						로그아웃 </a></li>

				<!-- Divider -->
				<li><a>&nbsp;</a></li>
				<!-- End Divider -->

			</ul>
		</div>
		<%} %>
		<!-- End Main Menu -->
</nav>