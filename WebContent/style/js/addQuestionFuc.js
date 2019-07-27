
function changeInput(offerSeq) 
{
if(offerSeq==-1)
{
	var strInput = 
		"&nbsp;&nbsp;&nbsp;보기 방향 :  <select name=\"q_typeDirection\" class=\"input-md form-control\" onChange=\"offerDirection(this.value);\">"
		+"	<option value=\"0\" selected>선택해주세요.</option>"
		+"	<option value=\"1\">세로 보기형</option>"
		+"	<option value=\"2\">가로 보기형</option>"
		+"	</select> <p></p>"
		+"&nbsp; 유형 이름 : <input type=\"text\" name=\"type_title\" id=\"name\" class=\"input-md form-control\" maxlength=\"100\"><p><p/>"
		+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 질문 개수 : <select name=\"offerSeq\" class=\"input-md form-control\" onChange=\"addInput(this.value);\">"
		+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;						<option value=\"0\">선택해주세요</option>"
	    +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                      <option value=\"1\">1</option>"
	    +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                      <option value=\"2\">2</option>"
	    +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                      <option value=\"3\">3</option>"
	    +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                      <option value=\"4\">4</option>"
	    +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                      <option value=\"5\">5</option>"
	    +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                      <option value=\"6\">6</option>"
	    +"          </select>"
	    +"<p></p>";
	    
	document.getElementById('isSelectCreate').innerHTML=strInput;
}
else if(offerSeq==1||offerSeq==2)
{
	$('#isAnswerValueDiv').hide();
	$('#isReverseDiv').hide();
	document.getElementById('isSelectCreate').innerHTML="";
	document.getElementById('inputBox').innerHTML = ""; 
}
else
{
	document.getElementById('isSelectCreate').innerHTML="";
	document.getElementById('inputBox').innerHTML = ""; 
}
}
function changeToBigTrand(offerSeq)
{
	if(offerSeq == -1)
		{
			$('#trandTypeDiv').hide();
		}
	else{
		$('#trandTypeDiv').show();
		}
}
function addInput(offerSeq) {
var strInput = "";
document.getElementById('inputBox').innerHTML = "";

for (var i=1; i <= offerSeq; i++) {
  strInput += "&nbsp;&nbsp; "+i+"번 내용: <input type=\"text\" name=\"type_OfferTitle"+i+"\" class=\"input-md form-control\" maxlength=\"100\"> <p></p>";
}

document.getElementById('inputBox').innerHTML = strInput; 
}
function isQuestionAnswering(answerValue){
	if(answerValue==0)
		{
		document.getElementById('inputAnswer').innerHTML = "";
		}
	else{
		document.getElementById('inputAnswer').innerHTML = "정답인 내용이나 번호 : <input type=\"text\" name=\"questionAnswer\" class=\"input-md form-control\" maxlength=\"100\"> <p></p>";
		}
}
