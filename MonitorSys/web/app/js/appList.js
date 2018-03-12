var app = angular.module("appApp", []);

app.controller('appCtrl', function($scope) {
	$scope.appList = getAppList();
});

/**
 * 获取物理服务器列表
 */
function getAppList() {

	var params = getParamsFromPath();
	var list;
	$.ajax({
		data : params,
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

function getParamsFromPath() {

	var url = window.document.location.href;
	var index = url.indexOf("?");
	var param = url.substring(index + 1, url.length);
	var servid = "";
	var projid = "";

	index = param.indexOf("servid");
	if (index > -1) {
		servid = param.substring(param.indexOf("=") + 1, param.length);
	}

	index = param.indexOf("projid")
	if (index > -1) {
		projid = param.substring(param.indexOf("=") + 1, param.length);
	}

	var params = {
		"servid" : servid,
		"projid" : projid
	};
	return params;
}