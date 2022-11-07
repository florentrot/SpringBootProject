let chartDataStr = decodeHtml(chartData);
let chartJsonArray = JSON.parse(chartDataStr);

let arrayLength=chartJsonArray.length;
let numericData = [];
let labelData = [];

for(let i=0; i<arrayLength; i++) {
    numericData[i] = chartJsonArray[i].counted;
    labelData[i] = chartJsonArray[i].label;
}

// For a pie chart
new Chart(document.getElementById("myPieChart"), {
    type: 'pie',
    // The data for our dataset
    data: {
        labels: labelData,
        datasets: [{
            label: 'My First dataset',
            backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f"],
            data: numericData
        }]
    },

    // Configuration options go here, this part does not work
    options: {
    	plugins: {
                    title: {
                        display: true,
                        text: 'Project Statuses'
                    }
        }

    }
});

// getText inside textarea
// "[{"counted":1,"label":"COMPLETED"},{"counted":2,"label":"INPROGRESS"},{"counted":1,"label":"NOTSTARTED"}]"
function decodeHtml(html) {
    const txt = document.createElement("textarea");
    txt.innerHTML=html;
    return txt.value;
}