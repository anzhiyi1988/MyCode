var app = angular.module("monitorApp", []);

app.controller('monitorCtrl', function($scope) {
	$scope.servList = getServList();
	$scope.appList = getAppList();
});

/**
 * 获取服务器列表
 */
function getServList() {
	var list;
	$.ajax({
		url : getLocalPath() + "ctrl.do/serv/list",
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

/**
 * 获取服务器列表
 */
function getAppList() {
	var list;
	$.ajax({
		url : getLocalPath() + "ctrl.do/app/list",
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
