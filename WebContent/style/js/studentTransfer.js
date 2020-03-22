
function transferProcess(f){
	var transfer = confirm("해당 학생을 전학 처리 하시겠습니까?");
	
	if(transfer == true){
		if(confirm("한번 전학처리를 하게되면 다시 되돌릴 수 없습니다. 진행 하시겠습니까?")){
			f.action="/PeerSys/StudentTransfer.st"
			f.submit();
		}
		else{
			return;
		}
		
	}
	else if(transfer == false){
		return;
	}
}

function checkStudent(f){
	
	var sch_name = f.sch_name.value;
	var grade = f.grade.value;
	var grd_num = f.grd_num.value;
	var stu_num = f.stu_num.value;
	var stu_name = f.stu_name.value;
	
	var check = confirm(sch_name + grade + "학년 " + grd_num + "반 " + stu_num + "번 " + stu_name + " 학생이 맞습니까?");
	
	if(check == true){
		if(confirm("다시 한번 확인하세요. " + sch_name + grade + "학년 " + grd_num + "반 " + stu_num + "번 " + stu_name + " 학생이 맞습니까?")){
			f.action="/PeerSys/stuLogin.st"
			f.submit();
		}
		else{
			return;
		}
		
	}
	else if(check == false){
		return;
	}
}

function resultProcess(f){
	
	var sch_name = f.sch_name.value;
	var grade = f.stu_grade.value;
	var grd_num = f.stu_grdnum.value;
	var stu_num = f.stu_num.value;
	var stu_name = f.stu_name.value;
	
	var check = confirm(stu_name + " 학생의 결과를 확인 하시겠습니까?");
	
	if(check == true){
		f.action="#"
		f.submit();
	}
	else if(check == false){
		return;
	}
}

function teacherAddClassCheck(){
    if(confirm("새로 학급을 등록하게되면 기존의 학급 정보가 사라집니다. 새로이 등록 하시겠습니까?")){
        location.href = "/PeerSys/page_tea/ClassManagement/addClass.jsp";
        return true;
    } else {
        return false;
    }
}

function calculate(f){
	
	var sch_name = f.sch_name.value;
	var enddate = f.enddate.value;
	var title = f.title.value;
	
	var check = confirm("선택하신 설문은 " +sch_name + "에서 \n" + enddate + " 까지 실시한 '" + title + "' 입니다. \n해당 설문의 결과를 계산 하시겠습니까?");
	
	if(check == true){
		if(confirm("해당 설문의 결과를 계산합니다.") == true){
			f.action="/PeerSys/resultCalculate.sv"
			f.submit();
		}
		else return;
	}
	else if(check == false){
		return;
	}
}

function checkStuNumber(){
	
	var value = document.getElementById("stuNum").value;	
$.ajax({
 type: "POST",
 url: "/PeerSys/stuCheck.ax",
 dataType:"json",
 data: {number:value},
 success: function(result){
	 if(result.contains == "false"){
		 alert("이미 " + value + "번 학생이 있습니다. 다시 확인하여 주세요.");
		 document.getElementById("stuNum").focus();
		 return;
	 }
  }
 });
}

