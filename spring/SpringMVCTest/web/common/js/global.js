// 获取本地路径
function getLocalPath() {
	var curWwwPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	var localhostPaht = curWwwPath.substring(0, pos);
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	return (localhostPaht + projectName + "/");
	// return location.protocol + '//'+ document.domain + (location.port ? (":"
	// + location.port):"") + "/sjjzglptsy/";
}

function isShow(id) {
	var obj = document.getElementById(id);
	var state = obj.style.display;
	if (state == "none") {
		obj.style.display = "inherit";
	} else {
		obj.style.display = "none";
	}
}

