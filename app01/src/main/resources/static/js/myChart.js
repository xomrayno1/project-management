
var chartDataStr = decodeHtml(chartData);
var chartJsonArray = JSON.parse(chartDataStr);

var arrayLength = chartJsonArray.length;
var numericData = [];
var labelData = [];

for (var i = 0 ; i < arrayLength ; i++){
	numericData[i] = chartJsonArray[i].count;
	labelData[i]  = chartJsonArray[i].label;
}

 new Chart(document.getElementById("myPieChart"), {
    type: 'pie',
     data: {
        labels: labelData,
        datasets: [{
            label: 'My First dataset',
            backgroundColor: ["#346321","#fcf8e3","#4ea6d2"],
            borderColor: 'rgb(255, 99, 132)',
            data: numericData
        }]
    },

    // Configuration options go here
    options: {
    	title : {
    		display : true,
    		text : "Task By Status"
    	}
    }
});

function decodeHtml(html){
	var txt =document.createElement("textarea");
	txt.innerHTML = html;
	return txt.value;
}