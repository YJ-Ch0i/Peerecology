function BTselect(value){
	
	if(value == -1)
	{
		$('#trandTypeDiv').hide();
	}
	else{
		$('#trandTypeDiv').show();
	}
	var changeBigTrandValue = $('#trandBigName').val();
	
$.ajax({
 type: "POST",
 url: "/PeerSys/trandLoad.ax",
 dataType:"json",
 data: {btid:value},
 success: function(result){

  //SELECT BOX 초기화           
  $("#que_trandTypeID").find("option").remove().end().append("<option value=''>척도를 선택 해 주세요</option>");
  
  //list 개수 만큼 option 추가
   $.each(result, function(i){
    $("#que_trandTypeID").append("<option value='"+result[i].trandID+"'>"+result[i].trandDesc+"</option>")
   });    
  },
   error: function (jqXHR, textStatus, errorThrown) {
   alert("오류가 발생하였습니다.");
  }                     
 });
}