$.ajax({
    method: "get",
    url: "js/family.json",
    dataType: 'json',
    success: function (rs) {
        var data = rs.data;
        var painter = new Painter(data);
        var picture = painter.paint();
        $('body').append(picture);
    }
});


var PERSON_WIDTH = 60; // 每个人的宽度
var PERSON_HEIGHT = 70; // 每个人的高度
var TEN = 10; // 叶子夫妻间的间隔
var FAMILY_WIDTH = 40; //家庭间的间隔
var LEV1_TOP = -400;
var LEV2_TOP = -200;
var LEV3_TOP = 0;
var LEV4_TOP = 200;
var LEV5_TOP = 400;
var V_HEIGHT = 20; // 竖线20像素


/**
 * 画师
 * @param data
 * @constructor
 */
function
Painter(data) {

    var panel = $('<div id="panel" class="fd_panel"></div>');


    var lev4_len = 0;
    var lev4_1_person_arr = []; // 第4层第1组人
    var lev4_1_point_arr = [];

    var lev5_len = 0;
    var lev5_1_person_arr = []; // 第5层第1组人
    var lev5_2_person_arr = []; // 第5层第2组人

    this.paint = function () {
        this.paintBdcr();

        this.paintChildChild();
        this.paintGrandChildren();
        this.paintToPanel();

        console.log(lev4_len);
        console.log(lev4_len);
        return panel;
    };

    this.paintBdcr = function () {
        var bdcr = new Person(data, LEV3_TOP, 0 - PERSON_WIDTH / 2);
        panel.append(bdcr.getDiv());
    };

    /**
     * 完成特有孙子的摆放
     */
    this.paintChildChild = function () {

        var bdcr = data;
        if (bdcr.children == null || bdcr.children.length < 1) {
            return;
        }

        bdcr.children.forEach(function (child) {

            var start = lev5_len;
            child.children.forEach(function (childchild) {

                lev5_1_person_arr.push(new Person(childchild, LEV5_TOP, lev5_len));
                lev5_len += PERSON_WIDTH;

                childchild.spouses.forEach(function (spouse) {
                    lev5_len += TEN;

                    lev5_1_person_arr.push(new Person(spouse, LEV5_TOP, lev5_len));
                    lev5_len += PERSON_WIDTH;
                });
                lev5_len += FAMILY_WIDTH;
            });
            lev5_len = lev5_len - FAMILY_WIDTH;

            lev4_1_person_arr.push(new Person(child, LEV4_TOP, (start + lev5_len) / 2));
            lev4_len += PERSON_WIDTH;
            if(lev5_len > lev4_len ){
                lev4_len = lev5_len;
            }else{
                lev5_len = lev4_len;
            }

            lev5_len += FAMILY_WIDTH;

        });

        lev5_len -= FAMILY_WIDTH;

    };

    /**
     * 完成特有孙子的摆放
     */
    this.paintGrandChildren = function () {

        var pointArray = [];
        var bdcr = data;
        if (bdcr.grantChildren == null || bdcr.grantChildren.length < 1) {
            return;
        }

        /** @namespace bdcr.grantChildren */
        for (var i in bdcr.grantChildren) {
            if (!bdcr.grantChildren.hasOwnProperty(i)) {
                continue;
            }

            var family_start = lev5_len;
            var grantChild = bdcr.grantChildren[i];
            var pObj = new Person(grantChild, LEV5_TOP, lev5_len);
            lev5_1_person_arr.push(pObj);
            lev5_len += PERSON_WIDTH;

            /** @namespace grantChild.spouses */
            for (var j in grantChild.spouses) {
                if (!grantChild.spouses.hasOwnProperty(j)) {
                    continue;
                }
                lev5_len += TEN;
                var grantChildSpouse = grantChild.spouses[j];
                var pObj2 = new Person(grantChildSpouse, LEV5_TOP, lev5_len);
                lev5_1_person_arr.push(pObj2);
                lev5_len += PERSON_WIDTH;
            }

            var family_left = (lev5_len + family_start) / 2;
            var point = new Point(family_left, LEV5_TOP);
            pointArray.push(point);
            lev5_len += FAMILY_WIDTH;
        }
        lev5_len -= FAMILY_WIDTH;





        var startPoint = new Point(0, PERSON_HEIGHT);

        pointArray.forEach(function (point) {
            // point.x = point.x - start / 2;
            var line = new LineLev3Lev5(startPoint, point);
            panel.append(line.getDiv());
        });


    };

    this.paintToPanel = function (){

        lev4_1_person_arr.forEach(function (person) {
            // person.left = person.left - start / 2;
            panel.append(person.getDiv());
        });


        lev5_1_person_arr.forEach(function (person) {
            // person.left = person.left - start / 2;
            panel.append(person.getDiv());
        });
    }


}

/**
 *
 * 图谱上的每个人
 *
 * @param data 一个人的json实体，和java类对应
 * @param top  位置
 * @param left 位置
 * @constructor
 */
function Person(data, top, left) {
    // this.cbh = data.cbh
    this.cxm = data.cxm;
    // this.nzjlx = data.nzjlx;
    // this.czjhm = data.czjhm;
    // this.edges = data.edges;

    this.top = top;
    this.left = left;

    this.getDiv = function () {
        var div = '<div class="fd_person" style="top: ' + this.top + 'px;left: ' + this.left + 'px;width: ' + PERSON_WIDTH + 'px;height: ' + PERSON_HEIGHT + 'px">' + this.cxm + '</div>';
        return $(div);
    }
}

/**
 *
 * 图谱上的每个横线
 *
 * @constructor
 */
function TransverseLine(top, left, width) {

    this.width = width;
    this.top = top;
    this.left = left;

    this.getDiv = function () {
        var div = '<div class="fd_transverseLine" style="width: ' + this.width + 'px;top: ' + this.top + 'px;left: ' + this.left + 'px;"></div>';
        return $(div);
    }
}

/**
 * 图谱上的每个竖线
 * @constructor
 */
function VerticalLine(top, left, height) {

    this.height = height;
    this.top = top;
    this.left = left;

    this.getDiv = function () {
        var div = '<div class="fd_verticalLine" style="height: ' + this.height + 'px;top: ' + this.top + 'px;left: ' + this.left + 'px;"></div>';
        return $(div);
    }
}

/**
 *
 * @param x
 * @param y
 * @constructor
 */
function Point(x, y) {
    this.x = x;
    this.y = y;
}


/**
 *
 * @param startPoint
 * @param endPoint
 * @constructor
 */
function LineLev3Lev5(startPoint, endPoint) {

    var v1Height = Math.abs(startPoint.y - endPoint.y) - V_HEIGHT;
    var v1Left = startPoint.x;
    var v1Top = startPoint.y;
    var v1 = new VerticalLine(v1Top, v1Left, v1Height);
    var div = v1.getDiv();

    var t1Width = Math.abs(startPoint.x - endPoint.x);
    var t1left = startPoint.x > endPoint.x ? endPoint.x : startPoint.x;
    var t1Top = endPoint.y - V_HEIGHT;
    var t1 = new TransverseLine(t1Top, t1left, t1Width);
    div.after(t1.getDiv());

    var v2Height = V_HEIGHT;
    var v2Left = endPoint.x;
    var v2Top = endPoint.y - V_HEIGHT;
    var v2 = new VerticalLine(v2Top, v2Left, v2Height);
    div.after(v2.getDiv());

    this.getDiv = function () {
        return div;
    }
}