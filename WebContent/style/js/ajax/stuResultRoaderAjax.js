function cal(array) {
  var result = 0.0;

  for (var i = 0; i < array.length; i++){
    result += array[i];
  }
  return result;
}


function studentSelector(value){
	
	/*if(value == -2)
	{
		$('#barSplineSector').hide();
		$('#selectSector').show();			
	}
	else{
		$('#barSplineSector').show();
		$('#selectSector').hide();
	}*/
	
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
	var mixedTrand = document.getElementById("mixedTrand").value;
	
	var trandList = JSON.parse(trandJson);
	var bigTrandList = JSON.parse(bigTrandJson);
	var endSurList = JSON.parse(endSurJson);
	var mixTrand = JSON.parse(mixedTrand);
	
	console.log(trandList);
	console.log(stuScoresList);
	console.log(bigTrandList)
	
	var explanText;
	var btDesc;
	
	var score_list = [];
	var trand_list = [];
	var stu_list = [];
	var bigTrand_list = [];
	var endSur_list = [];
	
	for(var i=0; i<trandList.length; i++){
		trand_list.push(trandList[i].trandDesc);
	}
	for(var i=0; i<endSurList.length; i++){
		endSur_list.push(endSurList[i].surIngSeq);
	}
	
	var serieses = [];
	var trl = [];
	for(var k=0; k<endSur_list.length; k++){
		for(var i=0; i<bigTrandList.length; i++){
			var obj = new Object();
			var tr_list = [];
			var scores_list = [];
			for(var j=0; j<stuScoresList.length; j++){
				if(endSur_list[k] === stuScoresList[j].ingSeq){
					if(bigTrandList[i].btID === stuScoresList[j].btid){
						if(stuScoresList[j].trdesc != ""){
							tr_list.push(stuScoresList[j].trdesc);
						}
						else continue;
						scores_list.push(stuScoresList[j].score);
					}
					else continue;
				}
				else continue;
			}
			console.log(endSur_list[k])
			console.log(tr_list);
			console.log(scores_list)
			obj.seq = endSur_list[k];
			obj.trlist = tr_list;
			obj.btid = bigTrandList[i].btID;
			obj.scoreslist = scores_list;
			trl.push(obj);
		}
	}
	console.log(trl);
	
	/*var serieses = [];
	for(var k=0; k<endSur_list.length; k++){
		for(var j=0; j<trl.length; j++){
			if(bigTrandList[i].btID == trl[j].btid){
				var forSeries = new Object();
				forSeries.type = 'column';
				//forSeries.name = ()
			}
		}
		console.log(scores);
	}*/
	var scorelist = [];
	
	for(var k=0; k<endSur_list.length; k++){
		var avg = 0;
		var avglist = [];
		var avgObj = new Object();
		for(var i=0; i<trand_list.length; i++){
			var entity = 0;
			for(var j=0; j<stuScoresList.length; j++){
				var ovj = new Object();
				if(endSur_list[k] == stuScoresList[j].ingSeq){			
					if(stuScoresList[j].trdesc === trand_list[i]){
						obj.entity = stuScoresList[j].score;
						obj.btid = stuScoresList[j].btid;						
						obj.trid = stuScoresList[j].trid;
						console.log(endSur_list[k])
						console.log(obj.entity);
						console.log(obj.btid);
						console.log(obj.trid);
						console.log('');
						avglist.push(obj);
					}
					else continue;				
				}				
			}
		}
		avgObj.ingSeq = endSur_list[k];
		avgObj.score = avglist;
		scorelist.push(avgObj);
	}
	console.log(scorelist);
	
	
	//하이차트
	for(var i=0; i<bigTrandList.length; i++){
		
		var trl2 = [];
		for(var j=0; j<trandList.length; j++){
			if(trandList[j].bigID === bigTrandList[i].btID){
				trl2.push(trandList[j].trandDesc);
			}
		}
		console.log(trl2);
		
		/*var trScore = [];
		var obj = new Object();
		for(var j=0; j<endSur_list.length; j++){
			for(var k=0; k<trl.length; k++){
				if(endSur_list[j] == trl[k].seq){
					trScore.push(trl[k].scoreslist);
				}
			}
		}
		console.log(trScore);*/
		
		
		Highcharts.chart('rader' + bigTrandList[i].btID, {
		
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
		        categories: trl2,
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
