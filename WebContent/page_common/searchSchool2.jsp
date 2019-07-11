<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Service.*" %>
<%@ page import="java.util.*" %>
<%@ page import="School.SchoolDTO.*" %>
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
<script language="javascript">

function searchSchool()
{
	var schoolNm = document.getElementById("schoolNm").value;
	location.href="/PeerSys/page_common/searchSchool2.jsp?schoolNm="+schoolNm+"";
}
function addSchool()
{
	var chk_box = document.getElementsByName("inlineCheckbox1");
	var checked_Box = parent.document.getElementsByName("checkSch");
	for(var i=0; i<chk_box.length; i++)
		{
		if(chk_box[i].checked == true)
			{
			for(var k=0; k<checked_Box.length; k++)
			{
				if(checked_Box[k].value == chk_box[i].value)
					{
					checked_Box[k].checked = true;
					}
			}
			}
		}
	alert('추가되었습니다.');
}
</script>
<!-- CSS -->
<link rel="stylesheet" href="/PeerSys/style/css/bootstrap.min.css">
<link rel="stylesheet" href="/PeerSys/style/css/style.css?version=1">
<link rel="stylesheet" href="/PeerSys/style/css/style-responsive.css">
<link rel="stylesheet" href="/PeerSys/style/css/vertical-rhythm.min.css">
<link rel="stylesheet" href="/PeerSys/style/css/owl.carousel.css">
<link rel="stylesheet" href="/PeerSys/style/css/magnific-popup.css">

</head>
<body class="appear-animate">
	<!-- Page Loader -->
	<div class="page-loader">
		<div class="loader">Loading...</div>
	</div>
	<!-- End Page Loader -->
	<!-- Page Wrap -->
	<div class="page" id="top">
			<section class="page-section">
			<div class="row">
			<% String schoolNm = request.getParameter("schoolNm"); 
			   SchoolService schService = SchoolService.getInstance();
			   ArrayList<SchoolDTO> schList = new ArrayList<SchoolDTO>();
			   schList = schService.SearchSchoolAddress(schoolNm);
			%>
			<input id="schoolNm" name="schoolNm" type="text" class="input-md form-control" placeholder="학교명을 검색해주세요.">
			<button id="clickHref" onclick="searchSchool()" class="btn btn-mod btn-border-w btn-medium btn-round lightbox">
			검색 
			</button>
			<p></p>
			<% if(schList.size()==0) {%>
			등록 되어있지 않은 학교입니다.
			<%}else{%>
			<table class="table table-striped">
                            <tr>
                                <th>선택</th>
                                <th>학교 이름</th>
                                <th>학교 주소</th>
                            </tr>
			<%for(int i=0; i<schList.size(); i++){ %>
			<tr>
			<td><input type="checkbox" name="inlineCheckbox1" value="<%=schList.get(i).getSCID()%>"></td>
			<td><%= schList.get(i).getName()%> </td>
			<td><%= schList.get(i).getAddress() %></td>
			</tr>
			<%} %>
			</table>
			<%} %>
            </div>
            <p></p>
            <button style="float:right" onclick="addSchool()" class="btn btn-mod btn-medium btn-round" > 추가하기</button>
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