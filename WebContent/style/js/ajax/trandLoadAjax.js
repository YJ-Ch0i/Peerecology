function cal(array) {
  var result = 0.0;

  for (var i = 0; i < array.length; i++){
    result += array[i];
  }
  return result;
}


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
   error: function (request,status,error) {
	   alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
  },
  error: function (jqXHR, textStatus, errorThrown) {
	   alert("오류가 발생하였습니다.");
}
 });
}


/*function BTselect_result(value){
	
	if(value == -2)
	{
		$('#barSplineSector').hide();
		$('#selectSector').show();			
	}
	else{
		$('#barSplineSector').show();
		$('#selectSector').hide();
	}
	
	var jsonValue = JSON.parse(value);
	
$.ajax({
 type: "POST",
 url: "/PeerSys/trandLoad.ax",
 dataType:"json",
 data: {btid:jsonValue.btid,
	 seq:jsonValue.seq,
	 scid:jsonValue.scid,
	 grade:jsonValue.grade,
	 grdNum:jsonValue.grdNum,
	 year:jsonValue.year,
	 surNo:jsonValue.surNo},
 success: function(result){

	 //로컬용
	 //const peerID = 8;	//break Point --> PeerID
	 
	 //서버용
	 const peerID = 1;
	 
	 if(jsonValue.btid == peerID){
		 var trandList = result[0];
		 var scoresList = result[1];
	 }
	 else{
		var scoresJson = document.getElementById("scoresJson").value;
		var trandList = result[0];
		var scoresList = JSON.parse(scoresJson);
	 }
	 var stuJson = document.getElementById("stuJson").value;
		var bigTrandJson = document.getElementById("bigTrandJson").value;
		var endSurJson = document.getElementById("endSurJson").value;
		var mixedTrand = document.getElementById("mixedTrand").value;
		
		
		var stuList = JSON.parse(stuJson);
		var bigTrandList = JSON.parse(bigTrandJson);
		var endSurList = JSON.parse(endSurJson);
		var mixTrand = JSON.parse(mixedTrand);
				
		
		var explanText;
		var btDesc;
		
		for(var i=0; i<bigTrandList.length; i++){
			if(bigTrandList[i].btID == jsonValue.btid){
				explanText = bigTrandList[i].explan;
				btDesc = bigTrandList[i].btDesc;
			}
			else{
				continue;
			}
		}
		var label = document.getElementById("bigTdesc");
		label.innerHTML = explanText;
		
		var score_list = [];
		var trand_list = [];
		var stu_list = [];
		var bigTrand_list = [];
		var endSur_list = [];
		
		for(var i=0; i<scoresList.length; i++){
			score_list.push(scoresList[i].score);
		}
		for(var i=0; i<stuList.length; i++){
			stu_list.push(stuList[i].name);
		}
		for(var i=0; i<trandList.length; i++){
			trand_list.push(trandList[i].trandDesc);
		}
		for(var i=0; i<endSurList.length; i++){
			endSur_list.push(endSurList[i].surIngSeq);
		}
		
		var scorelist = [];
		//막대그래프를 위한 각 설문별 평균
		for(var k=0; k<endSur_list.length; k++){
			var avg = 0;
			var avglist = [];
			var avgObj = new Object();
			for(var i=0; i<trandList.length; i++){
				var entity = 0;
				for(var j=0; j<scoresList.length; j++){							
					if(endSur_list[k] == scoresList[j].surIngSeq){			
						if(scoresList[j].trID === trandList[i].trandID){
							entity += scoresList[j].score;					
						}
						else continue;				
					}						
				}		
				avg = entity/stuList.length;
				
				//소수점 3자리에서 반올림하여 float으로 형변환
				avglist.push(parseFloat(avg.toFixed(3)));
			}
			avgObj.ingSeq = endSur_list[k];
			avgObj.score = avglist;
			scorelist.push(avgObj);
		}
		
		var serieses = [];
		for(var i=0; i<scorelist.length; i++){
			var score = scorelist[i].score;
			var forSeries = new Object();
			forSeries.type = 'column';
			forSeries.name = (i+1) + '차 학급점수';
			forSeries.data = score;
			serieses.push(forSeries);
		}
		
		
		//스플라인 그래프를 위한 평균
		var scorelist2 = [];
		for(var i=0; i<trandList.length; i++){
			var avg = 0;
			var avglist = [];
			var avgObj = new Object();
			for(var k=0; k<endSur_list.length; k++){
				var entity = 0;
				for(var j=0; j<scoresList.length; j++){							
					if(endSur_list[k] == scoresList[j].surIngSeq){			
						if(scoresList[j].trID === trandList[i].trandID){
							entity += scoresList[j].score;					
						}
						else continue;				
					}						
				}		
				avg = entity/stuList.length;
				
				//소수점 3자리에서 반올림하여 float으로 형변환
				avglist.push(parseFloat(avg.toFixed(3)));
			}			
			avgObj.trID = trand_list[i];
			avgObj.avgScore = cal(avglist)/endSur_list.length;
			scorelist2.push(avgObj);
		}		
		
		var avglistSpline = [];
		for(var i=0; i<scorelist2.length; i++){
			avglistSpline.push(parseFloat(scorelist2[i].avgScore.toFixed(3)));
		}
		
		var forSeries2 = new Object();
		forSeries2.type = 'spline';
		forSeries2.name = '전체 평균';
		forSeries2.data = avglistSpline;		
		
		serieses.push(forSeries2);
				
		
		var yAxisObject = new Object();
		if(jsonValue.btid == peerID){	//또래지명일때
			yAxisObject.min = 0.0;
			yAxisObject.max = 1.0;
		}
		else{
			yAxisObject.max = 0.0;
			yAxisObject.max = 5.0;
		}
		
		
		//하이차트
		Highcharts.chart('barchart', {
		    title: {
		        text: '학급 평균 : ' + btDesc
		    },
		    xAxis: {
		        categories: trand_list		     
		    },
		    yAxis: yAxisObject,
		    credits: {
		        enabled: false
		    },
		    series: serieses
		});
  },
//  error: function (request,status,error) {
//	   alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
//	   console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
// },
 error: function (jqXHR, textStatus, errorThrown) {
	   alert("오류가 발생하였습니다.");
}        
 });
{}
}*/

function BTselect_result(value){
	
	if(value == -2)
	{
		$('#barSplineSector').hide();
		$('#selectSector').show();			
	}
	else{
		$('#barSplineSector').show();
		$('#selectSector').hide();
	}	
	var jsonValue = JSON.parse(value);
	
$.ajax({
 type: "POST",
 url: "/PeerSys/bigTrandResultLoad.ax",
 dataType:"json",
 data: {btid:jsonValue.btid,
	 seq:jsonValue.seq,
	 scid:jsonValue.scid,
	 grade:jsonValue.grade,
	 grdNum:jsonValue.grdNum,
	 year:jsonValue.year,
	 surNo:jsonValue.surNo},
 success: function(result){

	 //로컬용
	 //const peerID = 8;	//break Point --> PeerID
	 
	 //서버용
	 const peerID = 1;
	 
	 var trandList = result[0];
	 var scoresList = result[1];
	 
	var bigTrandJson = document.getElementById("bigTrandJson").value;
	var endSurJson = document.getElementById("endSurJson").value;
	var stuJson = document.getElementById("stuJson").value;

	var bigTrandList = JSON.parse(bigTrandJson);
	var endSurList = JSON.parse(endSurJson);
	var stuList = JSON.parse(stuJson);
				
		
	var explanText;
	var btDesc;
		
	for(var i=0; i<bigTrandList.length; i++){
		if(bigTrandList[i].btID == jsonValue.btid){
			explanText = bigTrandList[i].explan;
			btDesc = bigTrandList[i].btDesc;
		}
		else{
			continue;
		}
	}
	var label = document.getElementById("bigTdesc");
	label.innerHTML = explanText;
	
	var trand_list = [];
	var bigTrand_list = [];
	var endSur_list = [];

	for(var i=0; i<trandList.length; i++){
		trand_list.push(trandList[i].trandDesc);
	}
	for(var i=0; i<endSurList.length; i++){
		endSur_list.push(endSurList[i].surIngSeq);
	}
	
	//계산 시작   ->목적 : n차 설문까지 막대그래프로 평균 표현

	
	var scores = [];
	for(var i=0; i<endSur_list.length; i++){
		var avg = 0;
		var avgList = [];
		var avgObj = new Object();
		
		for(var j=0; j<trandList.length; j++){
			var entity = 0;
			
			for(var k=0; k<scoresList.length; k++){
				if(endSur_list[i] == scoresList[k].surIngSeq){
					if(scoresList[k].trID == trandList[j].trandID){						
						entity += scoresList[k].score;						
					}
					else continue;
				}
				else continue;
			}			
			avg = entity/stuList.length;
			
			//소수점 3자리에서 반올림
			avgList.push(parseFloat(avg.toFixed(3)));
		}
		
		avgObj.ingSeq = endSur_list[i];
		avgObj.score = avgList;
		scores.push(avgObj);
	}
	
	var serieses = [];
	for(var i=0; i<scores.length; i++){
		var score = scores[i].score;
		var forSeries = new Object();
		forSeries.type = 'column';
		forSeries.name = (i+1) + '차 학급점수';
		forSeries.data = score;
		serieses.push(forSeries);
	}

//	//스플라인 그래프를 위한 평균
//	var scorelist2 = [];
//	for(var i=0; i<trandList.length; i++){
//		var avg = 0;
//		var avglist = [];
//		var avgObj = new Object();
//		for(var k=0; k<endSur_list.length; k++){
//			var entity = 0;
//			for(var j=0; j<scoresList.length; j++){							
//				if(endSur_list[k] == scoresList[j].surIngSeq){			
//					if(scoresList[j].trID === trandList[i].trandID){
//						entity += scoresList[j].score;					
//					}
//					else continue;				
//				}						
//			}		
//			avg = entity/stuList.length;
//			
//			//소수점 3자리에서 반올림하여 float으로 형변환
//			avglist.push(parseFloat(avg.toFixed(3)));
//		}			
//		avgObj.trID = trand_list[i];
//		avgObj.avgScore = cal(avglist)/endSur_list.length;
//		scorelist2.push(avgObj);
//	}		
//	
//	var avglistSpline = [];
//	for(var i=0; i<scorelist2.length; i++){
//		avglistSpline.push(parseFloat(scorelist2[i].avgScore.toFixed(3)));
//	}
//	
//	var forSeries2 = new Object();
//	forSeries2.type = 'spline';
//	forSeries2.name = '전체 평균';
//	forSeries2.data = avglistSpline;		
//	
//	serieses.push(forSeries2);
			
	
	var yAxisObject = new Object();
	if(jsonValue.btid == peerID){	//또래지명일때
		yAxisObject.min = 0.0;
		yAxisObject.max = 1.0;
	}
	else{
		yAxisObject.max = 0.0;
		yAxisObject.max = 5.0;
	}
	
	
	//하이차트
	Highcharts.chart('barchart', {
	    title: {
	        text: '학급 평균 : ' + btDesc
	    },
	    xAxis: {
	        categories: trand_list		     
	    },
	    yAxis: yAxisObject,
	    credits: {
	        enabled: false
	    },
	    series: serieses
	});
  },
//  error: function (request,status,error) {
//	   alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
//	   console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
// },
 error: function (jqXHR, textStatus, errorThrown) {
	   alert("오류가 발생하였습니다.");
}        
 });
}