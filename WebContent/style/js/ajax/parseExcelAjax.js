$(function(){
	$("#pickedDownload").click(function(){
		var str = $("#downExcelPicked").serialize();
		$.ajax({
			 type: "POST",
			 url: "/PeerSys/pickedExcelDownload.ax",
			 data: str,
			 dataType:"json",
			 success: function(result){
				 
				 location.href=result.filePath;
			  },
			   error: function (request,status,error) {
				   alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				   console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
			  },
//			  error: function (jqXHR, textStatus, errorThrown) {
//				   alert("오류가 발생하였습니다.");
//			  }
			 });
	})
});

$(function(){
	$("#excelDownload").click(function(){
		var str = $("#downExcel").serialize();
		$.ajax({
			 type: "POST",
			 url: "/PeerSys/selectedDataExcelDownload.ax",
			 data: str,
			 dataType:"json",
			 success: function(result){
				 
				 location.href=result.filePath;
			  },
			   error: function (request,status,error) {
				   alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				   console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
			  },
//			  error: function (jqXHR, textStatus, errorThrown) {
//				   alert("오류가 발생하였습니다.");
//			  }
			 });
	})
});

$(function(){
	$("#scoresDownload").click(function(){
		var str = $("#downExcelScores").serialize();
		$.ajax({
			 type: "POST",
			 url: "/PeerSys/excelDownload.ax",
			 data: str,
			 dataType:"json",
			 success: function(result){			
				 
				 location.href=result.filePath;
				 
			  },
			   error: function (request,status,error) {
				   alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				   console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
			  },
//			  error: function (jqXHR, textStatus, errorThrown) {
//				   alert("오류가 발생하였습니다.");
//			  }
			 });
	})
});