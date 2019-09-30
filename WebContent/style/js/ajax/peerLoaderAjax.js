
  // create an array with nodes
//  var nodes = new vis.DataSet([
//    {id: 1, label: 'Node 1'},
//    {id: 2, label: 'Node 2'},
//    {id: 3, label: 'Node 3'},
//    {id: 4, label: 'Node 4'},
//    {id: 5, label: 'Node 5'},
//    {id: 6, label: 'Node 6'},
//    {id: 7, label: 'Node 7'},
//    {id: 8, label: 'Node 8'}
//  ]);
//
//  // create an array with edges
//  var edges = new vis.DataSet([
//    {from: 1, to: 8, arrows:'to', dashes:true},
//    {from: 1, to: 3, arrows:'to'},
//    {from: 1, to: 2, arrows:'to'},
//    {from: 2, to: 4, arrows:'to'},
//    {from: 2, to: 5, arrows:'to'},
//    {from: 5, to: 6, arrows:{to:{scaleFactor:2}}},
//    {from: 6, to: 7, arrows:{middle:{scaleFactor:0.5},from:true}}
//  ]);
//
//  // create a network
//  var container = document.getElementById('peerNetwork');
//  var data = {
//    nodes: nodes,
//    edges: edges
//  };
//  var options = {};
//  var network = new vis.Network(container, data, options);
//  
  
function queSelect(value){
		
	if(value == -2){
		$('#networkSector').hide();
		$('#networkSelectSector').show();			
	}
	else{
		$('#networkSector').show();
		$('#networkSelectSector').hide();
	}
	
	var jsonValue = JSON.parse(value);
	
$.ajax({
 type: "POST",
 url: "/PeerSys/peerLoad.ax",
 dataType:"json",
 data: {qid:jsonValue.qid,
	 seq:jsonValue.seq,
	 scid:jsonValue.scid,
	 grade:jsonValue.grade,
	 grdNum:jsonValue.grdNum,
	 year:jsonValue.year},
 success: function(result){

	 var nodes = new vis.DataSet(result[0]);
	 var edges = new vis.DataSet(result[1]);
	 
	 var container = document.getElementById('peerNetwork');
	  var data = {
	    nodes: nodes,
	    edges: edges
	  };
	  var options = {
	      physics:{
	        barnesHut:{
	          gravitationalConstant: -3000,
	          springConstant:0.02
	        }
	      },
	      interaction:{
	    	  hover:true
	      }
	  };
	  var network = new vis.Network(container, data, options);	  
      
  },
   error: function (jqXHR, textStatus, errorThrown) {
   alert("오류가 발생하였습니다.");
  }                     
 });
}