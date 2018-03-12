/**
 * 增加物理服务器提交
 */
function submit() {
	$.ajax({

		data : getApp(),
		url : getLocalPath() + "ctrl.do/app/add",
		type : "POST",
		dataType : "text",
		async : false,
		success : function(data) {
			if (data = "success") {
				window.location.href = getLocalPath() + "app/appList.html";
			}
		},
		complete : function(httpRequest, textStatus) {
			if (httpRequest.status != 200) {
				alert("添加失败！状态码：" + httpRequest.status);
			}
		},
		error : function(e) {
			console.log(e);
		}
	});
}

function getApp() {
	var cservid = document.getElementById("cservid").value;
	var cprojid = document.getElementById("cprojid").value;
	var cname = document.getElementById("cname").value;
	var cpath = document.getElementById("cpath").value;
	var cbsfzr = document.getElementById("cbsfzr").value;
	var cbsfzrdh = document.getElementById("cbsfzrdh").value;
	var cport = document.getElementById("cport").value;
	var ccpuzy = document.getElementById("ccpuzy").value;
	var cramzy = document.getElementById("cramzy").value;
	var cdiskzy = document.getElementById("cdiskzy").value;
	var cstorezy = document.getElementById("cstorezy").value;
	var cbzsm = document.getElementById("cbzsm").value;

	var data = {
		"cservid" : cservid,
		"cprojid" : cprojid,
		"cname" : cname,
		"cpath" : cpath,
		"cbsfzr" : cbsfzr,
		"cbsfzrdh" : cbsfzrdh,
		"cport" : cport,
		"ccpuzy" : ccpuzy,
		"cramzy" : cramzy,
		"cdiskzy" : cdiskzy,
		"cstorezy" : cstorezy,
		"cbzsm" : cbzsm
	}

	return data;

}

var app = angular.module("appApp", []);

app.controller('appCtrl', function($scope) {
	$scope.servList = getServList();
	$scope.projList = getProjList();
	
});
