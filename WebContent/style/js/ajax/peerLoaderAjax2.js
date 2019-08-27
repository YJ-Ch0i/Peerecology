Highcharts.chart('classnetwork', {
	
	  "chart": {
	    "type": "networkgraph",
	    "height": "100%"      
	  },
	  "title": {
	    "text": "Network graph demo"
	  },
	  "subtitle": {
	    "text": "A Force-Directed Network Graph in Highcharts"
	  },
	  "plotOptions": {
	    "networkgraph": {
	      "keys": ["from", "to"],
	      "layoutAlgorithm": {
	        "enableSimulation":true,	        
	      }
	    },
	  series: {
          lineWidth: 10
      }
	
	  },
	  "series": [
	    {
	      "dataLabels": {
	        "enabled": true,            
	        "linkFormat": "{point.fromNode.name} \u2192 {point.toNode.name}",
	        textPath: {
                enabled: true,
                attributes: {
                    dy: 14,
                    startOffset: '60%',
                    textLength: 80
                }
            },
            format: 'Node: {point.name}'
	      },
	      "data": [{"from" : "a", "to": "b"},
	              {"from": "a", "to": "c"},
	               {"from": "a", "to": "d"},
	               {"from": "b", "to": "a"}],
	      "marker" : {"radius" : 18}
	    }
	
	  ],
	  "tooltip" :
	   {
	    "enabled" : true, 
	    "formatter" : function() {
	      //return "<div> <span>"+ '{point.fromNode.name} \u2192 {point.toNode.name}' + "</span> </div>"
	    }
	  }
	
});


Highcharts.chart('classnetwork', {
    chart: {
        type: 'networkgraph'
    },
    plotOptions: {
        networkgraph: {
            layoutAlgorithm: {
                enableSimulation: true,
                linkLength : 50,                                
                lineWidth : 5
            },
		    series: {
		        lineWidth: 5
		    }
        }
        
    },

    series: [{
        dataLabels: {
            enabled: true,
            linkTextPath: {
                attributes: {
                    dy: 12,
                    startOffset : '70%'
                }
               
            },
            linkFormat: '\u2192',
            textPath: {
                enabled: true,
                attributes: {
                    dy: 14,
                    startOffset: '45%',
                    textLength: 80
                }
            },
            format: ''
        },
        color: '#A53E32',
        link: {
            width: 5,
            color: '#131313',
            length: 80,
            dashStyle: 'dash',
            //opacity: 100
        },
        marker: {
            radius: 15
        },
        data: [{
            from: 'n1',
            to: 'n2',
            arrows:'to'
        },{
            from: 'n2',
            to: 'n1'
        },{
            from: 'n2',
            to: 'n3'
        }, {
            from: 'n3',
            to: 'n4'
        }, {
            from: 'n4',
            to: 'n5'
        }, {
            from: 'n5',
            to: 'n1'
        }]
    }]
});
