<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Service.*" %>
<%@ page import="School.SchoolDTO.SchoolDTO" %>
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
<script language="javascript">

function searchSchool()
{
	var schoolNm = document.getElementById("schoolNm").value;
	$("#clickHref").attr("href","/PeerSys/page_common/searchSchool2.jsp?schoolNm="+schoolNm+"");
}
function versionPick()
{
	var checkSchs = document.getElementsByName("checkSch");
	
	var str = ""
	for(var i=0; i<checkSchs.length; i++)
		{
		if(checkSchs[i].checked==true)
			{
			str += checkSchs[i].value + ",";
			}
		}
	if(str == "")
	{
		alert('진행할 학교를 체크 해주세요!');
	}
	else
	{
	$("#clickVersionPick").attr("href","/PeerSys/page_common/versionPick.jsp?str="+str+"");
	}
}
</script>
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

	<!-- Page Wrap -->
	<div class="page" id="top">

		<!-- Navigation panel -->
		<%@include file="../../pageInclude/Header.jsp"%>
		<!-- End Navigation panel -->



		<!-- Head Section -->
		<section class="small-section bg-gray-lighter">
			<div class="relative container align-left">

				<div class="row">

					<div class="col-md-8">
						<h2 class="hs-line-11 font-alt mb-20 mb-xs-0">설문조사 진행</h2>
						<div class="hs-line-4 font-alt black"></div>
					</div>

				</div>
			</div>
		</section>
		<!-- End Head Section -->


		<!-- Section -->
		<section class="page-section">
			<div class="container relative">
<%
SchoolService schService = SchoolService.getInstance();
ArrayList<SchoolDTO> schList = new ArrayList<SchoolDTO>();
schList = schService.school_List();
%>
				<div class="works-filter font-alt">
				<a href="#" class="filter active" data-filter="*" >모든학교</a>
				<a href="#Elementary" class="filter" data-filter=".Elementary">초등학교</a>
                <a href="#Middle" class="filter" data-filter=".Middle">중학교</a>
                <p></p>
				<ul class="works-grid work-grid-5 clearfix font-alt hover-white hide-titles" style="margin-bottom:5%" id="work-grid">
				<%if(schList.size()!=0){ %>
				<%for(int i=0; i<schList.size(); i++){ %>
                        <li class="work-item mix <%if(schList.get(i).getName().contains("초등학교")){%>Elementary<%}else{%>Middle<%}%>">
                        <label class="checkbox-inline">
                                        <input type="checkbox" name="checkSch" value="<%=schList.get(i).getSCID() %>"><%=schList.get(i).getName() %>
                        </label>
                <%} %>
                <%}else{ %>
              	 등록되어 있는 학교가 없습니다.
                <%} %>
                </ul>
                <ul class="pagination" style="list-stlye-type:none; text-align:center;">
    
  				</ul>
                </div>
                			<input id="schoolNm" name="schoolNm" type="text" class="input-md form-control" placeholder="학교명을 검색해주세요.">
							<a id="clickHref" href="" onclick="searchSchool()" class="btn btn-mod btn-border-w btn-medium btn-round lightbox mfp-iframe">
							 검색 
							</a>
							<a id="clickVersionPick" href="" onclick="versionPick()" class="btn btn-mod btn-border-w btn-medium btn-round lightbox mfp-iframe">
							 진행하기
							 </a>
						<p></p>
				  	

					
				
			</div>
		</section>
		<!-- End Section -->

		<!-- Footer -->
		<%@ include file="../../pageInclude/Footer.jsp"%>
		<!-- End Footer -->

	</div>
	<!-- End Page Wrap -->


	<!-- JS -->
	
	<script type="text/javascript" src="/PeerSys/style/js/pagingSchool.js"></script>
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