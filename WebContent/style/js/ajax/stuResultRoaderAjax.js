function cal(array) {
  var result = 0.0;

  for (var i = 0; i < array.length; i++){
    result += array[i];
  }
  return result;
}


function studentSelector(value){
	
	if(value == -2)
	{
		$('#radarSector').hide();
		$('#radarSelectSector').show();			
	}
	else{
		$('#radarSector').show();
		$('#radarSelectSector').hide();
	}
	
	var jsonValue = JSON.parse(value);
	
$.ajax({
 type: "POST",
 url: "/PeerSys/studentLoad.ax",
 dataType:"json",
 data: {stuid:jsonValue.sid,
	 scid:jsonValue.scid,
	 grade:jsonValue.grade,
	 grd_num:jsonValue.grd_num,
	 year:jsonValue.year},
 success: function(result){
	 
	var stuScoresList = result; //Json파일
	var trandJson = document.getElementById("trandJson").value;
	var bigTrandJson = document.getElementById("bigTrandJson").value;
	var endSurJson = document.getElementById("endSurJson").value;
	
	var trandList = JSON.parse(trandJson);
	var bigTrandList = JSON.parse(bigTrandJson);
	var endSurList = JSON.parse(endSurJson);
	
//	console.log(trandList);
//	console.log(stuScoresList);
//	console.log(bigTrandList)

	var endSur_list = [];
	
	for(var i=0; i<endSurList.length; i++){
		endSur_list.push(endSurList[i].surIngSeq);
	}

	//하이차트
	for(var i=0; i<bigTrandList.length; i++){
		
		var trlForSeries = [];
		var trl = [];
		var serieses = [];
		
		for(var j=0; j<trandList.length; j++){
			var trObj = new Object();
			if(trandList[j].bigID === bigTrandList[i].btID){
				trObj.trID = trandList[j].trandID;
				trObj.trDesc = trandList[j].trandDesc;
				trl.push(trObj);
				trlForSeries.push(trandList[j].trandDesc);
			}
		}

		for(var k=0; k<endSur_list.length; k++){
			var arr = [];
			var avg = 0;
			var columnSeries = new Object();
			for(var l=0; l<stuScoresList.length; l++){
				for(var j=0; j<trl.length; j++){
					if(endSur_list[k] == stuScoresList[l].ingseq){
						if(stuScoresList[l].bigTrandId == bigTrandList[i].btID){
							if(stuScoresList[l].trandId == trl[j].trID){
								arr.push(stuScoresList[l].score);
							}
							else continue;
						}
						else continue;
					}										
					else continue;					
				}
			}
			columnSeries.type = 'column';
			columnSeries.name = (k+1) + '차 설문';
			columnSeries.data = arr;
			serieses.push(columnSeries);
		}
		
		var label = document.getElementById("raderDesc" + bigTrandList[i].btID);
		label.innerHTML = bigTrandList[i].explan;

		
//		평균 --- 버그있음.
		
//		var scorelist2 = [];
//		for(var j=0; j<trl2.length; j++){
//			var avg = 0.0;
//			var avglist = [];
//			var entity = 0.0;
//			for(var k=0; k<endSur_list.length; k++){
//				for(var l=0; l<stuScoresList.length; l++){
//					if(endSur_list[k] == stuScoresList[l].ingseq){
//						if(stuScoresList[l].bigTrandId == bigTrandList[i].btID){
//							if(stuScoresList[l].trandDesc == trl2[j]){
//								entity += stuScoresList[l].score;
//								console.log(entity)
//							}
//							else continue;
//						}
//						else continue;
//					}										
//					else continue;					
//				}
//				console.log(entity);
//			}
//			avg = entity/endSur_list.length;
//			console.log(avg)
//			scorelist2.push(avg.toFixed(3));			
//		}
//		console.log(scorelist2);
//		
//		var avglistLine = [];
//		for(var i=0; i<scorelist2.length; i++){
//			avglistLine.push(parseFloat(scorelist2[i]));
//		}
//		
//		var lineSeries = new Object();
//		lineSeries.type = 'line';
//		lineSeries.name = '평균점수';
//		lineSeries.data = avglistLine;
//		serieses.push(lineSeries);
//		console.log(serieses);
		
		Highcharts.chart('radar' + bigTrandList[i].btID, {
		
		    chart: {
		        polar: true
		    },
		
		    title: {
		        text: bigTrandList[i].btDesc
		    },
		
		    subtitle: {
		        text: bigTrandList[i].btDesc + " 점수 그래프입니다."
		    },
		
		    pane: {
		        startAngle: 0,
		        endAngle: 360
		    },
		
		    xAxis: {
		        categories: trlForSeries,
		        tickmarkPlacement: 'on',
		        lineWidth: 0
		    },
		
		    yAxis: {
		        gridLineInterpolation: 'polygon',
		        lineWidth: 0,
		        min: 0
		    },
		    credits: {
		        enabled: false
		      },
		
		    series: serieses
		});
	}
 },
	error: function (jqXHR, textStatus, errorThrown) {
		   alert("오류가 발생하였습니다.");
	}
 });
}
