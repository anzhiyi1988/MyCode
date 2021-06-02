package com.thunisoft.jdgtyypt.common.pdf;

import lombok.Data;

/**
 * Word
 * @description
 * @author anzhy
 * @date 2021/3/23 10:57
 * @version 1.0
 */
@Data
public class WordInfo {
    String word;
    boolean needReplace;
    String fontType;
    byte[] bytes;
    int byteCount;
}
