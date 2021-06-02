package com.thunisoft.jdgtyypt.common.pdf;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

import java.io.IOException;
import java.util.List;

/**
 * MyPDFTextStrinpper
 * @description
 * @author anzhy
 * @date 2021/3/20 17:47
 * @version 1.0
 */
@Slf4j
public class MyPDFTextStripper extends PDFTextStripper {
    /**
     * Instantiate a new PDFTextStripper object.
     *
     * @throws IOException If there is an error loading the properties.
     */
    public MyPDFTextStripper() throws IOException {
        // do nothing
    }

    @Override
    protected void writeString(String text, List<TextPosition> textPositions) throws IOException {
        super.writeString(text, textPositions);
        log.info("anzhy ----------------------------------------------------");
        log.info("anzhy text : {}", text);
        textPositions.forEach(t -> log.info("anzhy 【unicode :{} , x:{} ,y:{} ,w:{} , h{}】", t.getUnicode(), t.getX(), t.getY(), t.getWidth(), t.getHeight()));
    }


}
