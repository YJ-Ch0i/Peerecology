<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script LANGUAGE = "JavaScript"> 
function change_menu1(frm){ 
switch(frm){ 
case "1": 
document.all('layer1').style.visibility="visible"; 
document.all('layer2').style.visibility="hidden"; 
break; 
case "2": 
document.all('layer2').style.visibility="visible"; 
document.all('layer1').style.visibility="hidden"; 
break; 
} 
} 
</script> 

<form name="aaa"> 

    <select name='cate1_id' size='1' onchange='change_menu1(this.value)'> 
<option selected value="0">아무거나선택</option> 
<option selected value="1">1</option> 
<option selected value="2">2</option> 
</select> 

<div id="layer1" style="width:87px; height:19px; position:absolute; left:454px; top:279px; z-index:1; visibility:hidden;"> 
레이어1 
</div> 

<div id="layer2" style="width:87px; height:19px; position:absolute; left:454px; top:279px; z-index:1; visibility:hidden;"> 
레이어2 
</div> 
</form>
</body>
</html>