//Two ways to call to make function


//	https://canvasjs.com/docs/charts/basics-of-creating-html5-chart/date-time-axis/
//Choices on how to make the date on the axis

/*
Converting string to TimeStamp (and Date) in JS
var d = new Date("Tue Feb 23 2016 20:11:42 GMT+0200 (EET)");

var timeStamp = d.getTime();

*/


/*
Using timestamp
dataPoints: [                  
              { x: 1088620200000, y :71},
              { x: 1104517800000, y : 55 },
              { x: 1112293800000, y:  50 },
              { x: 1136053800000, y : 65 },
              { x: 1157049000000, y : 95 },
              { x: 1162319400000, y : 68 },
              { x: 1180636200000, y : 28 },
              { x: 1193855400000, y : 34 },
              { x: 1209580200000, y : 14 },
              { x: 1230748200000, y : 34 },
              { x: 1241116200000, y : 44 },
              { x: 1262284200000, y : 84 },
              { x: 1272652200000, y : 4  },
              { x: 1291141800000, y : 44 },
              { x: 1304188200000, y : 11 }
      ]
*/


// One is to make an event listener and call it upon document  creation
document.addEventListener('DOMContentLoaded',domloaded,false);	
function domloaded(){
		window.onload = function () {
			  var chart = new CanvasJS.Chart("name_of_id", {//name_of_id is whatever the id you want to put the chart into
				  title:{
						text: "Hello Metru Nui"  //**Change the title here
					},
				  
				  axisX:{
						title: "date",
						interlacedColor: "#F0F8FF"
					},
				  
				  axisY:{
						title: "Points",
						 
					},
				  
				  
				  data: [
				  {
					  type: "line", //type of chart to make
					  xValueType: "dateTime", //For timestamp in the x axis
					  dataPoints: [ //dataPoints bracket/array is required. This can be empty.
					 /* { x: 10, y: 10 }
					  { x: 20, y: 15 },
					  { x: 30, y: 25 },
					  { x: 40, y: 30 },
					  { x: 50, y: 28 }*/
					  { x: 1088620200000, y :71},
					{ x: 1104517800000, y : 55 },
					{ x: 1112293800000, y:  50 }
					
					  ]
				  }
				  ]
			  });
				chart.options.data[0].dataPoints.push({ x: 1136053800000, y : 65 });//Must be before the render.
				chart.options.data[0].dataPoints.push({ x: 1136053900000, y : 9 });
				chart.render();
			  
	}
	
	
}

/*
chart.options.title.text = "Updated Chart Title";
 
chart.options.data[0].dataPoints.push({y: 23}); // Add a new dataPoint to dataPoints array
 
chart.options.data[0].dataPoints[3].y = 27;  // Update an existing dataPoint
*/


//Another is to make a function and call it
/*
function domloaded(){
		window.onload = function () {
			  var chart = new CanvasJS.Chart("name_of_id", {
				  data: [
				  {
					  type: "column",
					  dataPoints: [
					  { x: 10, y: 10 },
					  { x: 20, y: 15 },
					  { x: 30, y: 25 },
					  { x: 40, y: 30 },
					  { x: 50, y: 28 }
					  ]
				  }
				  ]
			  });
	 
			  chart.render();
	}
}
domloaded();
*/