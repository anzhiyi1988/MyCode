var app = angular.module("showApp", []);

app.controller('showCtrl', function($scope) {
			getInfo(function(data) {
						$scope.infoMap = data;
						$scope.$apply();
					});
		});

/**
 * 获取物理服务器列表
 */
function getInfo(method) {
	var list;
	$.ajax({
				url : "http://192.2.29.80:8090/sblcjklogic/ajdrxx/data",
				data : {
					kssj : "2017-04-17",
					fyid : "520000",
					pagesize : 10,
					currpage : 1,
					drajzt : -1
				},
				type : "GET",
				dataType : "json",
				async : true,
				success : function(data) {
					list = data;
					method(list);
				},
				error : function(e) {
					console.log(e);
				}
			});
	return list;
}
