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
forSeries2.type = 'spline';
forSeries2.name = '전체 평균';
forSeries2.data = avglist;
console.log(forSeries2);

serieses.push(forSeries2);

console.log(serieses);


Highcharts.chart('barchart', {
    title: {
        text: '학급 평균 결과'
    },
    xAxis: {
        categories: trand_list
    },
    labels: {
        items: [{
            html: '설문 내 척도별 학급 평균점수',
            style: {
                left: '50px',
                top: '18px',
                color: ( // theme
                    Highcharts.defaultOptions.title.style &&
                    Highcharts.defaultOptions.title.style.color
                ) || 'black'
            }
        }]
    },
    series: serieses
    	/*[{
        type: 'column',
        name: 'Jane',
        data: [3, 2, 1, 3, 4]
    }, {
        type: 'column',
        name: 'John',
        data: [2, 3, 5, 7, 6]
    }, {
        type: 'column',
        name: 'Joe',
        data: [4, 3, 3, 9, 0]
    }, {
        type: 'spline',
        name: 'Average',
        data: [3, 2.67, 3, 6.33, 3.33],
        marker: {
            lineWidth: 2,
            lineColor: Highcharts.getOptions().colors[3],
            fillColor: 'white'
        }
    }]*/
});
