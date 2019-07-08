
function transfer(){
	var transf = confirm("해당 학생을 전학 처리 하시겠습니까?");
	var href = document.getElementById("transf").getAttribute("href");
	console.log(getName);
	console.log(href);
	if(transf == true){
		console.log(getName);
		location.href = "/PeerSys/StudentTransfer.st?stu_name=" + href;
		console.log(href);
	}
	else if(transf == false){
		console.log(getName);
		href="#";
		location.href = "#";
		console.log(href);
	}
}

function transfer2(){
	var transf = confirm("해당 학생을 전학 처리 하시겠습니까?");
	var name = this.name;
	console.log(name);
	if(transf == true){
		document.getElementById("transForm").submit();
	}
	else if(transf == false){
		return false;
	}
}



$("input ['name = transf11']").click(function(){
    //var id_check = $(this).val();
    //console.log(id_check);
    $(this).text('저만 클릭 하셨군요 ^ㅡ^');
});

//3. 이름으로 접근하기 가져 오기
//var value =  $ ( ' input [name = test_name] ' ). val ();


$('#transf').click(function(){
	$(this).text('저만 클릭 하셨군요 ^ㅡ^');
});


