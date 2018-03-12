//$(function() {
var e = document.getElementById('main');
var myChart = echarts.init(e);
myChart.setOption({
	title : {
		text : '心跳监控'
	},
	xAxis : {
		data : []
	},
	yAxis : {},
	series : [ {
		type : 'line',
		data : []
	} ]
});

setInterval(function() {

	var list = getMonitorList();

	myChart.setOption({
		xAxis : {
			data : list.xaxis
		},
		series : [ {
			data : list.series
		} ]
	});
}, 5000);

// })

function getMonitorList() {

	var url = getLocalPath() + "ctrl.do/monitor/list/id"
	var list;
	$.ajax({
		data : {
			"fkid" : "1"
		},
		url : url,
		type : "GET",
		dataType : "text",
		async : false,
		success : function(data) {
			list = eval("(" + data + ")");
		},
		error : function(e) {
			console.log(e);
		}
	});
	return list;
}
