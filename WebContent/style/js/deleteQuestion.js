function deleteQuestion(deleteNumbering) 
{
var cnt = 0;
var deleteZeroString = "<p id=\"text"+(deleteNumbering-1)+"\" style=\"display:inline;\">" +(deleteNumbering-1)+ "번 문항 : </p>";
var deleteFirstString = "<p id=\"text"+(deleteNumbering)+"\" style=\"display:inline;\">" +(deleteNumbering)+"번 문항 : </p>";
var deleteSecondString = "<p id=\"text"+(deleteNumbering+1)+"\" style=\"display:inline;\">" +(deleteNumbering+1)+ "번 문항 : </p>";
var inputedQuestions = document.getElementById('addedQuestion').innerHTML;
var zeroDeleteString = inputedQuestions.indexOf(deleteZeroString);
var startDeleteString = inputedQuestions.indexOf(deleteFirstString);
var lastDeleteString = inputedQuestions.indexOf(deleteSecondString);
var completeDeleteString = "";
if(lastDeleteString==-1 && deleteZeroString==-1)
	{
	document.getElementById('addedQuestion').innerHTML = completeDeleteString;
	return;
	}
var sliceString = inputedQuestions.slice(startDeleteString,lastDeleteString);
var completeDeleteString = inputedQuestions.replace(sliceString,"");
splitQuestions = completeDeleteString.split('input type="text"');
var updateNumbering = "";
cnt = splitQuestions.length-1;
console.log("cnt->"+cnt);
var numbering = (deleteNumbering+1)*1-1;
if(cnt==1)
	{
	var replaceText = "<p id=\"text"+(deleteNumbering+1)+"\" style=\"display:inline;\">" +(deleteNumbering+1)+ "번 문항 : </p>";
	var replaceQuestionName = "name=\"questionName" +(deleteNumbering+1)+" \"";
	var replaceOnclick = "onclick=\"deleteQuestion("+(deleteNumbering+1)+")\"";
	var replaceName = "name=\"questionId" +(deleteNumbering+1)+ "\"";
	completeDeleteString = completeDeleteString.replace(replaceText,"<p id=\"text"+(numbering)+"\" style=\"display:inline;\">" +(numbering)+ "번 문항 : </p>");
	completeDeleteString = completeDeleteString.replace(replaceQuestionName,"name=\"questionName" +(numbering)+" \"");
	completeDeleteString = completeDeleteString.replace(replaceOnclick,"onclick=\"deleteQuestion("+(numbering)+")\"");
	completeDeleteString = completeDeleteString.replace(replaceName,"name=\"questionId" +(numbering)+ "\"");
	}
else
{
for(var i=cnt; i>=0;i--)
	{
	console.log("numbring->"+numbering);
	console.log("deleteNumbering->"+deleteNumbering)
	var replaceText = "<p id=\"text"+(deleteNumbering+1)+"\" style=\"display:inline;\">" +(deleteNumbering+1)+ "번 문항 : </p>";
	var replaceQuestionName = "name=\"questionName" +(deleteNumbering+1)+" \"";
	var replaceOnclick = "onclick=\"deleteQuestion("+(deleteNumbering+1)+")\"";
	var replaceName = "name=\"questionId" +(deleteNumbering+1)+ "\"";
	completeDeleteString = completeDeleteString.replace(replaceText,"<p id=\"text"+(numbering)+"\" style=\"display:inline;\">" +(numbering)+ "번 문항 : </p>");
	completeDeleteString = completeDeleteString.replace(replaceQuestionName,"name=\"questionName" +(numbering)+" \"");
	completeDeleteString = completeDeleteString.replace(replaceOnclick,"onclick=\"deleteQuestion("+(numbering)+")\"");
	completeDeleteString = completeDeleteString.replace(replaceName,"name=\"questionId" +(numbering)+ "\"");
	numbering++;
	deleteNumbering++;
	}
}
document.getElementById('addedQuestion').innerHTML = completeDeleteString;
}