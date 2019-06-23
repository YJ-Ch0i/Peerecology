  	var click_Time = 0;
  
	function insertTable(){
	if(click_Time!=0)
	{
	alert("초기화를 눌러주세요.");	
	}   
	else
	{
	click_Time = 1;
    var num1 = stu_count.value;         // txt1의 값을 num1에 초기화 시킨다.
    var num2=2;         // txt2의 값을 num2에 초기화 시킨다. 
    var student_count=1;
    for(var i=0;i<num1;i++){
      var trObj=document.createElement("tr");     //txt1에 입력된 값만큼 for문을 돌려 tr태그를 생성
      var studentNumber=document.createElement("td");  //txt2에 입력된 값만큼 for문을 돌려 td태그를 생성
      studentNumber.innerHTML= "<p style=\"margin-top: 10px;margin-right: 30px; margin-left: 30px\"> " + student_count + "</p>"  ;
      
      trObj.appendChild(studentNumber);
      var studentName=document.createElement("td");
      studentName.innerHTML= "<input style=\"text-align:center\" name=\"stu_name"+student_count+"\" id=\"stu_name\" type=\"text\" placeholder=\"이름\">"+
      "<input style=\"text-align:center\" name=\"stu_birth"+student_count+"\" type=\"text\" placeholder=\"생년월일 ex)010829\">";
      trObj.appendChild(studentName);
     divObj.appendChild(trObj);    //div 태그에 tr태그를 붙여넣는다.
     student_count++;
    }
    }  
	}
   function deleteTable() {
    click_Time = 0;
    var c=document.getElementById("b");
    divObj.innerHTML="";    // div 를 ""을 넣어 초기화 시키다. 
    txt1.value="";
    txt2.value="";
   }  
  
  