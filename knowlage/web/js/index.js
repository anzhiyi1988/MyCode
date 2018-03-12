function setHeight() {
	var iframe = document.getElementById("iframe-context");
	var height1 = iframe.contentWindow.document.body.scrollHeight;
	var height2 = iframe.contentWindow.document.documentElement.scrollHeight;
	var height = Math.max(height1, height2);
	iframe.height = height;
}

function boom(newsrc) {
	var iframe = document.getElementById("iframe-context");
	iframe.src = newsrc;
}