var app = angular.module("servApp", []);

app.controller('servCtrl', function($scope) {
	$scope.servList = getServList();
});

/**
 * 获取物理服务器列表
 */
function getServList() {

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

function showAppList(id){
	var url = getLocalPath() + "app/appList.html?servid="+id;
	window.open(url);
}