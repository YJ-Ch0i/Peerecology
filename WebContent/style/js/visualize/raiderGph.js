/*var scoresJson = document.getElementById("scoresJson").value;
var trandJson = document.getElementById("trandJson").value;
var stuJson = document.getElementById("stuJson").value;
var bigTrandJson = document.getElementById("bigTrandJson").value;

var scoresList = JSON.parse(scoresJson);
var trandList = JSON.parse(trandJson);
var stuList = JSON.parse(stuJson);
var bigTrandList = JSON.parse(bigTrandJson);
console.log(bigTrandList)

var score_list = [];
var trand_list = [];
var stu_list = [];
var bigTrand_list = [];

for(var i=0; i<scoresList.length; i++){
	score_list.push(scoresList[i].score);
}
for(var i=0; i<stuList.length; i++){
	stu_list.push(stuList[i].name);
}
for(var i=0; i<trandList.length; i++){
	trand_list.push(trandList[i].trandDesc);
}


var avg = 0;
var avglist = [];
for(var i=0; i<trand_list.length; i++){
	var entity = 0;
	for(var j=0; j<score_list.length; j++){
		if(scoresList[j].trDesc === trand_list[i]){
			entity += scoresList[j].score;
		}
		else continue;
	}
	avg = entity/stuList.length;
	avglist.push(avg);
}

var serieses = [];
var forSeries = new Object();
forSeries.type = 'column';
forSeries.name = '1차 학급점수';
forSeries.data = avglist;

serieses.push(forSeries);

for(var i=0; i<bigTrandList.length; i++){
	var forSeries = new Object();
	forSeries.type = 'area';
	forSeries.name = '학급점수';
	forSeries.data = avglist;

	serieses.push(forSeries);
}

console.log(serieses);*/

var scoresJson = document.getElementById("scoresJson").value;
var trandJson = document.getElementById("trandJson").value;
var stuJson = document.getElementById("stuJson").value;
var bigTrandJson = document.getElementById("bigTrandJson").value;
var endSurJson = document.getElementById("endSurJson").value;
var mixedTrand = document.getElementById("mixedTrand").value;

var scoresList = JSON.parse(scoresJson);
var trandList = JSON.parse(trandJson);
var stuList = JSON.parse(stuJson);
var bigTrandList = JSON.parse(bigTrandJson);
var endSurList = JSON.parse(endSurJson);
var mixTrand = JSON.parse(mixedTrand);

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
		avglist.push(avg);
		if(seq == avglist.length){			
			avgObj.ingSeq = endSur_list[k];
			avgObj.score = avglist;
			scorelist.push(avgObj);
		}
	}
}

var serieses = [];
for(var i=0; i<scorelist.length; i++){
	var forSeries = new Object();
	forSeries.type = 'column';
	forSeries.name = (i+1) + '차 학급점수';
	forSeries.data = scorelist[i].score;
	serieses.push(forSeries);
}

console.log(serieses);

var avglist = [];
for(var i=0; i<scorelist.length; i++){
	var sum = 0;
	var avg = 0;
	for(var j=0; j<scorelist[i].score.length; j++){	
		var x=i+1;
		console.log(x);
		if(x < scorelist.length || x == scorelist.length-1){
			sum = scorelist[i].score[j] + scorelist[i+1].score[j];
			avg = sum/scorelist.length;
			avglist.push(avg);
			
		}
		else if(x > scorelist.length || x == scorelist.length-1){
			break;
		}
	}
}
console.log(avglist);

var forSeries2 = new Object();
forSeries2.type = 'line';
forSeries2.name = '전체 평균';
forSeries2.data = avglist;
console.log(forSeries2);

serieses.push(forSeries2);

console.log(serieses);

for(var i=0; i<bigTrandList.length; i++){
	Highcharts.chart('rader' + i, {
	
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
	        categories: trand_list,
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





/*var scoresJson = document.getElementById("scoresJson").value;
var trandJson = document.getElementById("trandJson").value;
var stuJson = document.getElementById("stuJson").value;
var bigTrandJson = document.getElementById("bigTrandJson").value;
var endSurJson = document.getElementById("endSurJson").value;
var mixedTrand = document.getElementById("mixedTrand").value;

var scoresList = JSON.parse(scoresJson);
var trandList = JSON.parse(trandJson);
var stuList = JSON.parse(stuJson);
var bigTrandList = JSON.parse(bigTrandJson);
var endSurList = JSON.parse(endSurJson);
var mixTrand = JSON.parse(mixedTrand);

console.log(mixTrand);

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

for(var k=0; k<endSur_list.length; k++){
	var avg = 0;
	var avglist = [];
	var trList = [];
	var btList = [];
	var avgObj = new Object();
	for(var i=0; i<mixTrand.length; i++){
		var entity = 0;
		
		for(var l=0; l<bigTrandList.length; l++){
			var seq = 0;
			if(bigTrandList[l].btID == mixTrand[i].btID){
				for(var j=0; j<scoresList.length; j++){							
					if(endSur_list[k] == scoresList[j].surIngSeq){			
						if(scoresList[j].trDesc === mixTrand[i].trDesc){
							entity += scoresList[j].score;					
						}
						else continue;				
					}						
				}
				seq++;
				console.log(seq)
				avg = entity/stuList.length;
				avglist.push(avg);
				trList.push(mixTrand[i].trDesc);
				btList.push(mixTrand[i].btID);
				console.log(avglist)
				if(seq == avglist.length){			
					avgObj.ingSeq = endSur_list[k];
					avgObj.btID = btList;
					avgObj.trID = trList;
					avgObj.score = avglist;
					scorelist.push(avgObj);
				}
			}
		}
	}
}

console.log(avgObj);
console.log(scorelist);

var serieses = [];
for(var i=0; i<scorelist.length; i++){
	var forSeries = new Object();
	forSeries.type = 'column';
	forSeries.name = (i+1) + '차 학급점수';
	forSeries.data = scorelist[i].score;
	console.log(forSeries);
	serieses.push(forSeries);
}

console.log(serieses);

var avglist = [];
for(var i=0; i<scorelist.length; i++){
	var sum = 0;
	var avg = 0;
	for(var j=0; j<scorelist[i].score.length; j++){	
		var x=i+1;
		console.log(x);
		if(x < scorelist.length || x == scorelist.length-1){
			console.log(scorelist[i].score[j]);
			console.log(scorelist[i+1].score[j]);
			sum = scorelist[i].score[j] + scorelist[i+1].score[j];
			avg = sum/scorelist.length;
			avglist.push(avg);
			
		}
		else if(x > scorelist.length || x == scorelist.length-1){
			console.log(scorelist[x].score[j]);
			break;
		}
	}
}
console.log(avglist);
var forSeries = new Object();
forSeries.type = 'line';
forSeries.name = '전체 평균';
forSeries.data = avglist;

serieses.push(forSeries);


for(var i=0; i<bigTrandList.length; i++){
	var trList = [];
	for(var j=0; j< mixTrand.length; j++){
		if(bigTrandList[i].btID == mixTrand[j].btID){
			trList.push(mixTrand[j].trDesc);
		}
		else{
			continue;
		}
		console.log(trList);
		Highcharts.chart('rader' + i, {
			
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
		        categories: trand_list,
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
}*/