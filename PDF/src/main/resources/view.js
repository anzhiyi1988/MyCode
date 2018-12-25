/**
 * 跳转到指定页
 * @param num
 */
function renderPage(num) {
    pageRendering = true;
    // Using promise to fetch the page
    pdfDoc.getPage(num).then(function (page) {
        var viewport = page.getViewport(scale);
        canvas.height = viewport.height;
        canvas.width = viewport.width;

        // Render PDF page into canvas context
        var renderContext = {
            canvasContext: ctx,
            viewport: viewport
        };
        var renderTask = page.render(renderContext);

        // Wait for rendering to finish
        renderTask.promise.then(function () {
            pageRendering = false;
            if (pageNumPending !== null) {
                // New page rendering is pending
                renderPage(pageNumPending);
                pageNumPending = null;
            }
        });
    });

}


/**
 * 放大
 */
function scalePlus() {

}


/**
 * 缩小
 */
function scaleReduce() {

}


/**
 * 实际大小
 */
function pageActual() {

}

var url = './helloworld.pdf';

pdfjsLib.GlobalWorkerOptions.workerSrc = './pdf.worker.js';

var loadingTask = pdfjsLib.getDocument(url);

loadingTask.promise.then(function (pdf) {

    pdf.getPage(1).then(function (page) {


        var scale = 1;

        var viewport = page.getViewport(scale);


        var canvas = document.getElementById('pdf-canvas');

        var context = canvas.getContext('2d');

        canvas.height = viewport.height;

        canvas.width = viewport.width;

        var renderContext = {

            canvasContext: context,

            viewport: viewport

        };

        page.render(renderContext);

    });


});