/**
 * 增加物理服务器提交
 */
function submit() {
	$.ajax({

		data : getServ(),
		url : getLocalPath() + "ctrl.do/serv/add",
		type : "POST",
		dataType : "text",
		async : false,
		success : function(data) {
			if (data = "success") {
				window.location.href = getLocalPath() + "serv/servList.html";
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

/**
 * 增加物理服务器提交
 */
function getServ() {
	var cname = document.getElementById("cname").value;
	var cfwqlx = document.getElementById("cfwqlx").value;
	var cczxt = document.getElementById("cczxt").value;
	var ccpu = document.getElementById("ccpu").value;
	var cram = document.getElementById("cram").value;
	var cdisk = document.getElementById("cdisk").value;
	var cstore = document.getElementById("cstore").value;
	var cnet = document.getElementById("cnet").value;
	var cip = document.getElementById("cip").value;
	var cbzsm = document.getElementById("cbzsm").value;

	var data = {
		"cname" : cname,
		"cfwqlx" : cfwqlx,
		"cczxt" : cczxt,
		"ccpu" : ccpu,
		"cram" : cram,
		"cdisk" : cdisk,
		"cstore" : cstore,
		"cnet" : cnet,
		"cip" : cip,
		"cbzsm" : cbzsm
	}

	return data;

}
