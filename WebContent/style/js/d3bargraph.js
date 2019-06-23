//var dataset = [10, 20, 40, 50, 100, 143, 160, 77, 120];
//var xSet = ["적극성", "친밀성", "공격성", "우정", "또라이기질"];

var scoreDataset = new Array();
var tendDataset = new Array();
var scoreAvgDataset = new Array();
var tendata, scoredata;
var scoreSum = 0;
var scoreAvg = 0;
var count = document.getElementById("count").value;	//학생 1명에 대한 데이터 수
var stu_count_ofClass = document.getElementById("stu_count").value;
for(var i = 0; i < count; i++){
	tendata = document.getElementById("tend" + i).value;
	scoredata = document.getElementById("score" + i).value;
	sumForTendDataset = document.getElementById("scoreForTend" + i).value;
	
	scoredata = parseInt(scoredata);
	tendDataset.push(tendata);
	scoreDataset.push(scoredata);
	
	scoreAvg = Math.round(sumForTendDataset/stu_count_ofClass);
	scoreAvgDataset.push(scoreAvg);
}
/*scoreAvg = Math.round(scoreSum/stu_count_ofClass);

for(var i = 0; i < count; i++){
	scoreAvgDataset.push(scoreAvg);
}*/

console.log(tendDataset);
console.log(scoreDataset);
console.log("학생수 : " + stu_count_ofClass);
console.log("점수 합계 : " + scoreSum);
console.log("점수 평균 : " + scoreAvg);

console.log("평균 배열 : " + scoreAvgDataset)
var xOffset = 20;
var barSize = 15;
var barOffset = 50; //2개씩 짝지어진 막대그래프의 중간지점간 거리
var svgWidth;
if(scoreDataset.length == 1){
    svgWidth = scoreDataset.length * 130;
}
else if(scoreDataset.length < 4 && scoreDataset.length != 1){
    svgWidth = scoreDataset.length * 90;
}
else if(scoreDataset.length >= 4 && scoreDataset.length < 6){
    svgWidth = scoreDataset.length * 70;
}
else if(scoreDataset.length >= 6 && scoreDataset.length < 8){
    svgWidth = scoreDataset.length * 64;
}
else if(scoreDataset.length >= 8 && scoreDataset.length < 12){
    svgWidth = scoreDataset.length * 60;
}
else if(scoreDataset.length >= 12 && scoreDataset.length < 30){
    svgWidth = scoreDataset.length * 56;
}
else if(datasscoreDatasetet.length >= 30 && scoreDataset.length < 60){
    svgWidth = scoreDataset.length * 54;
}
else{
    svgWidth = scoreDataset.length * 52;
}
var svgHeight = Math.max.apply(null, scoreDataset) + 100, barPadding = 5;
//svg컨테이너의 크기를 화면크기와 데이터 양에 맞게 설정


console.log(svgHeight);
console.log(scoreDataset.length);
var barElements;
var colorScale = d3.scaleLinear()
    .domain([0, 144])
    .range(["green", "red"]);

barElements = d3.select("#barchart")
    .attr("width", svgWidth)
    .attr("height", svgHeight)
    .selectAll("rect")
    .data(scoreDataset);

barElements2 = d3.select("#barchart")
    .selectAll("rect")
    .data(scoreAvgDataset);

barElements.enter()
    .append("rect")
    .attr("y", function(d) {
        return svgHeight - d - xOffset;
    })
    .attr("height", function(d){
        return d;
    })
    .attr("width", barSize)
    .attr("transform", function(d, i) {
        var translate = [55.5 + (50 * i + barSize/1.8), 0];
        return "translate(" + translate + ")"; 
    })
    .attr("fill", function(d){
        return colorScale(d);
    });

barElements2.enter()
    .append("rect")
    .attr("y", function(d){
        return svgHeight - d - xOffset;
    })
    .attr("height", function(d){
        return d;
    })
    .attr("width", barSize)
    .attr("transform", function(d, i) {
        var translate = [38.5 + (50 * i + barSize/1.8), 0];
        return "translate(" + translate + ")";    
    })
    .attr("fill", function(d){
        return colorScale(d);
    });

barElements.enter()
    .append("text")
    .attr("y", function(d, i){
        return svgHeight - d - xOffset;
    })
    .attr("x", function(d, i) {
        var translate = 55.5 + (50 * i + barSize/1.8);
        return translate+1; 
    })
    .text(function(d){
        return d;
    })
    .attr("fill", "red")
    .attr("font-size", 10);

barElements2.enter()
    .append("text")
    .attr("y", function(d, i){
        return svgHeight - d - xOffset;
    })
    .attr("x", function(d, i) {
        var translate = 38.5 + (50 * i + barSize/1.8);
        return translate+1;    
    })
    .text(function(d){
        return d;
    })
    .attr("fill", "red")
    .attr("font-size", 10);

var xScale = d3.scaleTime()
    .domain(["a",""])
    .range([-20, svgWidth]);

var yScale = d3.scaleLinear()
   .domain([0, 0])
   .range([svgHeight, 0]);

var x_axis = d3.axisBottom()
    .scale(xScale);

var y_axis = d3.axisLeft()
    .scale(yScale);

d3.select("#barchart")
    .append("g")
    .attr("transform", "translate(30, 10)")
    .call(y_axis);

var xAxisTranslate = svgHeight - xOffset;

d3.select("#barchart")
    .append("rect")
    .attr("width", svgWidth)
    .attr("height", "1")
    .attr("transform", "translate(30, " + xAxisTranslate + ")");

d3.select("#barchart")
    .append("g")
    .attr("transform", "translate(30, " + xAxisTranslate + ")")
    .call(x_axis);

barElements.enter()
    .append("text")
    .attr("y", function(d, i){
        return svgHeight-5;
    })
    .attr("x", function(d, i) {
        return 48 + (i * barOffset);
    })
    .text(function(d, i){
        return tendDataset[i];
    })
    .attr("fill", "red")
    .attr("font-size", 10);


    
 
var scoreDataset = new Array(); 
var tendDataset = new Array(); 
var scoreAvgDataset = new Array(); 
var tendata, scoredata; 
var scoreSum = 0; 
var scoreAvg = 0; 
var count = document.getElementById("count").value;	//학생 1명에 대한 데이터 수 
var stu_count_ofClass = document.getElementById("stu_count").value; 
for(var i = 0; i < count; i++){ 
	tendata = document.getElementById("tend" + i).value; 
	scoredata = document.getElementById("score" + i).value; 
	sumForTendDataset = document.getElementById("scoreForTend" + i).value; 
	 
	scoredata = parseInt(scoredata); 
	tendDataset.push(tendata); 
	scoreDataset.push(scoredata); 
	 
	scoreAvg = Math.round(sumForTendDataset/stu_count_ofClass); 
	scoreAvgDataset.push(scoreAvg); 
} 
/*scoreAvg = Math.round(scoreSum/stu_count_ofClass); 
 
for(var i = 0; i < count; i++){ 
	scoreAvgDataset.push(scoreAvg); 
}*/ 
 
console.log(tendDataset); 
console.log(scoreDataset); 
console.log("학생수 : " + stu_count_ofClass); 
console.log("점수 합계 : " + scoreSum); 
console.log("점수 평균 : " + scoreAvg); 
 
console.log("평균 배열 : " + scoreAvgDataset) 
var xOffset = 20; 
var barSize = 15; 
var barOffset = 50; //2개씩 짝지어진 막대그래프의 중간지점간 거리 
var svgWidth; 
if(scoreDataset.length == 1){ 
    svgWidth = scoreDataset.length * 130; 
} 
else if(scoreDataset.length < 4 && scoreDataset.length != 1){ 
    svgWidth = scoreDataset.length * 90; 
} 
else if(scoreDataset.length >= 4 && scoreDataset.length < 6){ 
    svgWidth = scoreDataset.length * 70; 
} 
else if(scoreDataset.length >= 6 && scoreDataset.length < 8){ 
    svgWidth = scoreDataset.length * 64; 
} 
else if(scoreDataset.length >= 8 && scoreDataset.length < 12){ 
    svgWidth = scoreDataset.length * 60; 
} 
else if(scoreDataset.length >= 12 && scoreDataset.length < 30){ 
    svgWidth = scoreDataset.length * 56; 
} 
else if(datasscoreDatasetet.length >= 30 && scoreDataset.length < 60){ 
    svgWidth = scoreDataset.length * 54; 
} 
else{ 
    svgWidth = scoreDataset.length * 52; 
} 
var svgHeight = Math.max.apply(null, scoreDataset) + 100, barPadding = 5; 
//svg컨테이너의 크기를 화면크기와 데이터 양에 맞게 설정 
 
 
console.log(svgHeight); 
console.log(scoreDataset.length); 
var barElements; 
var colorScale = d3.scaleLinear() 
    .domain([0, 144]) 
    .range(["green", "red"]); 
 
barElements = d3.select("#barchart") 
    .attr("width", svgWidth) 
    .attr("height", svgHeight) 
    .selectAll("rect") 
    .data(scoreDataset); 
 
barElements2 = d3.select("#barchart") 
    .selectAll("rect") 
    .data(scoreAvgDataset); 
 
barElements.enter() 
    .append("rect") 
    .attr("y", function(d) { 
        return svgHeight - d - xOffset; 
    }) 
    .attr("height", function(d){ 
        return d; 
    }) 
    .attr("width", barSize) 
    .attr("transform", function(d, i) { 
        var translate = [55.5 + (50 * i + barSize/1.8), 0]; 
        return "translate(" + translate + ")";  
    }) 
    .attr("fill", function(d){ 
        return colorScale(d); 
    }); 
 
barElements2.enter() 
    .append("rect") 
    .attr("y", function(d){ 
        return svgHeight - d - xOffset; 
    }) 
    .attr("height", function(d){ 
        return d; 
    }) 
    .attr("width", barSize) 
    .attr("transform", function(d, i) { 
        var translate = [38.5 + (50 * i + barSize/1.8), 0]; 
        return "translate(" + translate + ")";     
    }) 
    .attr("fill", function(d){ 
        return colorScale(d); 
    }); 
 
barElements.enter() 
    .append("text") 
    .attr("y", function(d, i){ 
        return svgHeight - d - xOffset; 
    }) 
    .attr("x", function(d, i) { 
        var translate = 55.5 + (50 * i + barSize/1.8); 
        return translate+1;  
    }) 
    .text(function(d){ 
        return d; 
    }) 
    .attr("fill", "red") 
    .attr("font-size", 10); 
 
barElements2.enter() 
    .append("text") 
    .attr("y", function(d, i){ 
        return svgHeight - d - xOffset; 
    }) 
    .attr("x", function(d, i) { 
        var translate = 38.5 + (50 * i + barSize/1.8); 
        return translate+1;     
    }) 
    .text(function(d){ 
        return d; 
    }) 
    .attr("fill", "red") 
    .attr("font-size", 10); 
 
var xScale = d3.scaleTime() 
    .domain(["a",""]) 
    .range([-20, svgWidth]); 
 
var yScale = d3.scaleLinear() 
   .domain([0, 0]) 
   .range([svgHeight, 0]); 
 
var x_axis = d3.axisBottom() 
    .scale(xScale); 
 
var y_axis = d3.axisLeft() 
    .scale(yScale); 
 
d3.select("#barchart") 
    .append("g") 
    .attr("transform", "translate(30, 10)") 
    .call(y_axis); 
 
var xAxisTranslate = svgHeight - xOffset; 
 
d3.select("#barchart") 
    .append("rect") 
    .attr("width", svgWidth) 
    .attr("height", "1") 
    .attr("transform", "translate(30, " + xAxisTranslate + ")"); 
 
d3.select("#barchart") 
    .append("g") 
    .attr("transform", "translate(30, " + xAxisTranslate + ")") 
    .call(x_axis); 
 
barElements.enter() 
    .append("text") 
    .attr("y", function(d, i){ 
        return svgHeight-5; 
    }) 
    .attr("x", function(d, i) { 
        return 48 + (i * barOffset); 
    }) 
    .text(function(d, i){ 
        return tendDataset[i]; 
    }) 
    .attr("fill", "red") 
    .attr("font-size", 10); 
 
 
     