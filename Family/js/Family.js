$(function () {

        for (var i = 0; i < 10; i++) {

            var p = new Person(null, 0, i * 80);

            $('#panel').append(p.getDiv());
        }

    }
);

/**
 *
 * 图谱上的每个人
 *
 * @param data
 * @constructor
 */
function Person(data, top, left) {

    this.top = top;
    this.left = left;

    this.getDiv = function () {
        var div = '<div class="Person" style="top: ' + this.top + 'px;left: ' + this.left + 'px;"></div>';
        return $(div);
    }
}


/**
 *
 * 图谱上的每个横线
 *
 * @constructor
 */
function TransverseLine() {

    this.getDiv = function (top, left, width) {
        var div = '<div class="TransverseLine" style="width: ' + width + 'px;top: ' + top + 'px;left: ' + left + ';"></div>';
        return $(div);
    }
}

/**
 * 图谱上的每个竖线
 * @constructor
 */
function VerticalLine() {

    this.getDiv = function (top, left, height) {
        var div = '<div class="TransverseLine" style="height: ' + height + 'px;top: ' + top + 'px;left: ' + left + ';"></div>';
        return $(div);
    }
}