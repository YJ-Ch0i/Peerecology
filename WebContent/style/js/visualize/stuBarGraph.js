var totalArray = document.getElementById("totalArray").value;
console.log(totalArray);
var trandArray = document.getElementById("trandArray").value;
console.log(trandArray);
var stuArray = document.getElementById("stuArray").value;
console.log(stuArray);

var totallist = JSON.parse(totalArray);
var trandlist = JSON.parse(trandArray);
var stulist = JSON.parse(stuArray);

console.log(totallist);
console.log(trandlist);
console.log(stulist);

var score_list = [];
var trand_list = [];
var stu_list = [];

for(var i=0; i<totallist.length; i++){
	score_list.push(totallist[i].score);
}
for(var i=0; i<trandlist.length; i++){
	trand_list.push(trandlist[i].trandDes);
}
for(var i=0; i<stulist.length; i++){
	stu_list.push(stulist[i].name);
}

var entity = 0;
var avg = 0;
var avglist = [];
for(var i=0; i<trandlist.length; i++){
	for(var j=0; j<totallist.length; j++){
		if(totallist[j].trand == trandlist[i].trandID){
			entity += totallist[j].score
		}		
	}
	avg = entity/stulist.length;
	avglist.push(avg);
}

Highcharts.chart('barchart', {
  chart: {
    type: 'bar'
  },
  title: {
    text: '성향별 점수'
  },
  subtitle: {
    text: '설문조사 내 학생의 성향별 점수를 수치화하여 나타내었습니다.'
  },
  xAxis: {
    //categories: ['Africa', 'America', 'Asia', 'Europe', 'Oceania'],
	categories: trand_list,
    title: {
      text: null
    }
  },
  yAxis: {
    min: 0,
    title: {
      text: '점수',
      align: 'high'
    },
    labels: {
      overflow: 'justify'
    }
  },
  tooltip: {
    valueSuffix: ' 점'
  },
  plotOptions: {
    bar: {
      dataLabels: {
        enabled: true
      }
    }
  },
  legend: {
    layout: 'vertical',
    align: 'right',
    verticalAlign: 'top',
    x: -40,
    y: 80,
    floating: true,
    borderWidth: 1,
    backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
    shadow: true
  },
  credits: {
    enabled: false
  },
  series: 
	  [{
    name: '반평균 점수',
    data: avglist
  }]
});