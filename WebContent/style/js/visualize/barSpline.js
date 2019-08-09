var totalArray = document.getElementById("totalArray").value;
console.log(totalArray);
var trandArray = document.getElementById("trandArray").value;
console.log(trandArray);
var stuArray = document.getElementById("stuArray").value;
console.log(stuArray);
var bigTrandArray = document.getElementById("bigTrandArray").value;
console.log(bigTrandArray);

var endSurArray= document.getElementById("endSurArray").value;
console.log(endSurArray);

var mixedTrand= document.getElementById("mixedTrand").value;
console.log(mixedTrand);

var totallist = JSON.parse(totalArray);
var trandlist = JSON.parse(trandArray);
var stulist = JSON.parse(stuArray);
var bigTrandlist = JSON.parse(bigTrandArray);
var mixTrandlist = JSON.parse(mixedTrand);

console.log(totallist);
console.log(trandlist);
console.log(stulist);
console.log(bigTrandlist);
console.log(mixTrandlist);

var score_list = [];
var trand_list = [];
var stu_list = [];
var bigTrand_list = [];
var trand_desc_list = [];

for(var i=0; i<totallist.length; i++){
	score_list.push(totallist[i].score);
}
for(var i=0; i<mixTrandlist.length; i++){
	if(mixTrandlist[i].bigTrand === bigTrandlist[0].bigTID){
		trand_list.push(mixTrandlist[i].trandType);
	}
	else if(bigTrandlist[0].bigTID != mixTrandlist[i].bigTrand){
		continue;
	}
}
for(var i=0; i<stulist.length; i++){
	stu_list.push(stulist[i].name);
}


for(var j=0; j<mixTrandlist.length; j++){
	if(mixTrandlist[j].bigTrand === bigTrandlist[0].bigTID){
		trand_desc_list.push(mixTrandlist[j].trandDesc);
	}
	else if(bigTrandlist[0].bigTID != mixTrandlist[j].bigTrand){
		continue;
	}
}
console.log(bigTrandlist[0].bigTID);
console.log(trand_list);
console.log(trand_desc_list);


var avg = 0;
var avglist = [];
for(var i=0; i<trand_list.length; i++){
	var entity = 0;
	for(var j=0; j<totallist.length; j++){
		if(totallist[j].trand === trand_list[i]){
			entity += totallist[j].score;
		}
		else continue;
	}
	avg = entity/stulist.length;
	avglist.push(avg);
	console.log(avglist);
}

var serieses = [];
var forSeries = new Object();
forSeries.type = 'column';
for(var i=0; i<trand_desc_list.length; i++){
	forSeries.type = trand_desc_list[i];
	forSeries.data = avglist;
}

Highcharts.chart('barchart', {
    title: {
        text: 'Combination chart'
    },
    xAxis: {
        categories: trand_list
    },
    labels: {
        items: [{
            html: 'Total fruit consumption',
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
    series: [{
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
    }]
});
