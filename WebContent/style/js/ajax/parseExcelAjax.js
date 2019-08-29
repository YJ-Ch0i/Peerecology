$(function(){
	$("#excelDownload").click(function(){
		var str = $("#downExcel").serialize();
		console.log(str);
		$.ajax({
			 type: "POST",
			 url: "/PeerSys/excelDownload.ax",
			 data: str,
			 dataType:"json",
			 success: function(result){
				 
				 alert(result);
			     alert("통신 성공! 엑셀 다운로드");
			  },
			   error: function (request,status,error) {
				   alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			  },
//			  error: function (jqXHR, textStatus, errorThrown) {
//				   alert("오류가 발생하였습니다.");
//			  }
			 });
	})
});