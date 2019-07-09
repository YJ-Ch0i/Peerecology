
function transferProcess(f){
	var transfer = confirm("해당 학생을 전학 처리 하시겠습니까?");
	
	if(transfer == true){
		if(confirm("한번 전학처리를 하게되면 다시 되돌릴 수 없습니다. 진행 하시겠습니까?")){
			f.action="/PeerSys/StudentTransfer.st"
			f.submit();
		}
		else{
			alert("취소하셨습니다.");
			return;
		}
		
	}
	else if(transfer == false){
		alert("취소하셨습니다.");
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
			alert("취소되었습니다.");
			return;
		}
		
	}
	else if(check == false){
		alert("취소되었습니다.");
		return;
	}
}