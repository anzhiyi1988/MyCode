package com.thunisoft.jdgtyypt.common.pdf;

import lombok.Data;

/**
 * Point
 *
 * @author anzhy
 * @version 1.0
 * @description
 * @date 2021/3/12 11:10
 */


@Data
public class Rectangle {

    float wPercent = 0;
    float hPercent = 0;
    float xPercent = 0;
    float yPercent = 0;

    float w = 0; // width
    float h = 0; // height
    float x = 0;
    float y = 0;

    @Override
    public String toString() {
        return String.format("【x:{%f} ,y:{%f} ,w:{%f} , h{%f}】", x, y, w, h);
    }

}
