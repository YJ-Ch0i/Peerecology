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


function BTselect_result(value){
	//var resultJson = document.getElementById('bigTrandAjax');
$.ajax({
 type: "POST",
 url: "/PeerSys/trandLoad.ax",
 dataType:"json",
 data: {btid:value},
 success: function(result){

	var scoresJson = document.getElementById("scoresJson").value;
	var trandList = result;
	var stuJson = document.getElementById("stuJson").value;
	var bigTrandJson = document.getElementById("bigTrandJson").value;
	var endSurJson = document.getElementById("endSurJson").value;
	var mixedTrand = document.getElementById("mixedTrand").value;
	
	console.log(trandList)
	
	var scoresList = JSON.parse(scoresJson);
	var stuList = JSON.parse(stuJson);
	var bigTrandList = JSON.parse(bigTrandJson);
	var endSurList = JSON.parse(endSurJson);
	var mixTrand = JSON.parse(mixedTrand);
	
	var explanText;
	var btDesc;
	console.log(bigTrandList)
	for(var i=0; i<bigTrandList.length; i++){
		if(bigTrandList[i].btID == value){
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
	
	console.log(scoresList);
	
	//막대그래프를 위한 각 설문별 평균
	for(var k=0; k<endSur_list.length; k++){
		var avg = 0;
		var avglist = [];
		var avgObj = new Object();
		for(var i=0; i<trand_list.length; i++){
			var entity = 0;
			var seq = 0;
			for(var j=0; j<scoresList.length; j++){							
				if(endSur_list[k] == scoresList[j].surIngSeq){			
					if(scoresList[j].trDesc === trand_list[i]){
						entity += scoresList[j].score;					
					}
					else continue;				
				}						
			}		
			seq++;
			avg = entity/stuList.length;
			
			//소수점 3자리에서 반올림하여 float으로 형변환
			avglist.push(parseFloat(avg.toFixed(3)));
			if(seq == avglist.length){			
				avgObj.ingSeq = endSur_list[k];
				avgObj.trID = trand_list[i];
				avgObj.score = avglist;
				scorelist.push(avgObj);
			}
		}
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
	
	console.log(scorelist)
	console.log(serieses);
	
	//스플라인 그래프를 위한 평균
	var avglist = [];
	for(var k=0; k<trandList.length; k++){
		for(var i=0; i<scorelist.length; i++){
			var sum = 0;
			var avg = 0;
			if(trandList[k].trandDesc == scorelist[i].trID){
				for(var j=0; j<scorelist[i].score.length; j++){	
					var x=i+1;
					console.log(x);
					if(x < scorelist.length || x == scorelist.length-1){
						console.log(scorelist[i].score[j])
						console.log(scorelist[x].score[j])
						sum = scorelist[i].score[j] + scorelist[x].score[j];
						avg = sum/scorelist.length;
						avglist.push(parseFloat(avg.toFixed(3)));
						
					}
					else if(x > scorelist.length){
						break;
					}
				}
			}			
		}
	}
	
	console.log(avglist);
	
	var forSeries2 = new Object();
	forSeries2.type = 'spline';
	forSeries2.name = '전체 평균';
	forSeries2.data = avglist;
	console.log(forSeries2);
	
	serieses.push(forSeries2);
	
	console.log(serieses);
	
	//하이차트
	Highcharts.chart('barchart', {
	    title: {
	        text: btDesc + ' 평균점수'
	    },
	    xAxis: {
	        categories: trand_list
	    },
	    series: serieses
	});

  },
   error: function (jqXHR, textStatus, errorThrown) {
   alert("오류가 발생하였습니다.");
  }                     
 });
}