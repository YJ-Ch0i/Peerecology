
/*function transfer(){
	var stu_name = document.getElementById("stu_name").value;
	if(confirm(stu_name + "학생을 전학 처리 하시겠습니까?") == true){
		location.href="/PeerSys/StudentTransfer.st?stu_name=" + stu_name;
	}
	else{
		return false;
	}
}*/

document.getElementById('transf').onclick = function() {
	var stu_name = document.getElementByName("stu_name").value;
	if(confirm(stu_name + "학생을 전학 처리 하시겠습니까?") == true){
	    document.getElementById('transfForm').submit();
		//location.href="/PeerSys/StudentTransfer.st?stu=" + stu_name;
	}
	else{
		return false;
	}
    return false;
};
