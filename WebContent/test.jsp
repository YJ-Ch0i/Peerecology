<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="test.css?version=55">
</head>
<body>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.js" 
type="text/javascript"></script>
<script type="text/javascript">

$(document).ready(function(){

    $(".slidingDiv").show();
    $(".show_hide").show();

$('.show_hide').click(function(){
$(".slidingDiv").slideToggle();
});

});

</script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script type="text/javascript" src="test.js?version=2"></script> 
<div id="stick_footer_title"><a class="show_hide" href="#">Toggle Menu
 &#x25BC;</a></div>
<div class="slidingDiv">
<div id="stickyfooter">

<ul id="footer_menu"> 
    <li class="imgmenu"><a href="#"></a></li>

    <li><a href="#intro">Intro</a></li>
    <li><a href="#photos">Photos</a></li>

</ul>
</div>
    </div>
</body>
</html>