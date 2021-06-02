package com.thunisoft.jdgtyypt.common.pdf;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.contentstream.PDFGraphicsStreamEngine;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.pdfwriter.ContentStreamWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImage;
import org.apache.pdfbox.util.Matrix;
import org.apache.pdfbox.util.Vector;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * PdfCoverHandler
 * @description
 * @author anzhy
 * @date 2021/3/23 18:15
 * @version 1.0
 */
@Slf4j
public class PdfCoverHandler extends PDFGraphicsStreamEngine {
    final PDDocument document;
    OutputStream replacementStream = null;
    ContentStreamWriter replacement = null;
    boolean inOperator = false;

    final List<Rectangle> covers;
    static final List<String> TEXT_SHOWING_OPERATORS = Arrays.asList("Tj", "TJ");
    final Queue<WordInfo> wordQueue = new LinkedBlockingQueue<>();

    /**
     *
     * @param page
     * @param document
     * @param covers
     */
    protected PdfCoverHandler(PDPage page, PDDocument document, List<Rectangle> covers) {
        super(page);
        this.document = document;
        this.covers = covers;
    }

    @Override
    protected void showGlyph(Matrix textRenderingMatrix, PDFont font, int code, Vector displacement)
            throws IOException {
        WordInfo word = new WordInfo();
        String string = font.toUnicode(code);
        log.info("anzhy word  {}", string);
        word.setFontType(font.getClass().getName());
        //printMatrix(textRenderingMatrix, code, string);后续应该使用坐标判断是否替换。
        word.setNeedReplace(needReplace(textRenderingMatrix));
        //word.setBytes(font.encode(string));  //有的文档是无法使用这个方法的。真是崩溃
        word.setByteCount(fontByteLength2(font));
        word.setWord(string);
        wordQueue.add(word);
        super.showGlyph(textRenderingMatrix, font, code, displacement);
    }

    /**
     * 计算字符用byte数据表示后的长度，但是有部分文档在encode的时候异常，所以暂时不用这个方法，<br>
     * 改用下面的{@link #fontByteLength2(PDFont)} 替代
     * @param font
     * @param word
     * @return
     */
    @SuppressWarnings("unused")
    private int fontByteLength(PDFont font, String word) {
        byte[] bytes;
        try {
            bytes = font.encode(word);
        } catch (IOException | IllegalArgumentException e) {
            String msg = String.format("此处字符转换有问题，需要查看具体错误，这里的问题还是比较严重的，目前还没有找到方法，原因：%s", e.getMessage());
            log.error(msg, e);
            bytes = new byte[]{0};
        }
        return bytes.length;
    }

    /**
     * 计算字符用byte数据表示后的长度
     * @param font
     * @return
     */
    private int fontByteLength2(PDFont font) {
        if (font instanceof PDType0Font) { // 复杂类型（汉字占两个byte）
            return 2;
        } else if (font instanceof PDTrueTypeFont) { // 简单类型（字母占一个byte）
            return 1;
        } else { // 不知道什么类型（字母占不知道个byte）
            return 1;
        }
    }

    private boolean needReplace(Matrix matrix) {
        for (Rectangle rect : covers) {

            float rectOriginX = rect.getX();
            float rectOriginY = rect.getY();
            float rectWidth = rect.getW();
            float rectHeight = rect.getH();
            float rectCenterX = rectOriginX + rectWidth / 2f;
            float rectCenterY = rectOriginY + rectHeight / 2f;


            float wordOriginX = matrix.getTranslateX();
            float wordOriginY = matrix.getTranslateY();
            float wordWidth = matrix.getScaleX();
            float wordHeight = matrix.getScaleY();
            float wordCenterX = wordOriginX + wordWidth / 2f;
            float wordCenterY = wordOriginY + wordHeight / 2f;

            float maxX = (Math.abs(rectWidth) + Math.abs(wordWidth)) / 2f;
            float maxY = (Math.abs(rectHeight) + Math.abs(wordHeight)) / 2f;

            if (Math.abs(rectCenterX - wordCenterX) < maxX && Math.abs(rectCenterY - wordCenterY) < maxY) {
                return true;
            }
        }
        return false;
    }

    /**
     * <p>
     * This method writes content stream operations to the target canvas. The default
     * implementation writes them as they come, so it essentially generates identical
     * copies of the original instructions {@link #processOperator(Operator, List)}
     * forwards to it.
     * </p>
     * <p>
     * Override this method to achieve some fancy editing effect.
     * </p>
     */
    protected void write(ContentStreamWriter contentStreamWriter, Operator operator, List<COSBase> operands) throws IOException {

        if (TEXT_SHOWING_OPERATORS.contains(operator.getName())) {
            for (COSBase cos : operands) {
                if (cos instanceof COSString) {
                    replace((COSString) cos);
                }
                if (cos instanceof COSArray) {
                    loopReplace((COSArray) cos);
                }
            }
        }
        wordQueue.clear();
        contentStreamWriter.writeTokens(operands);
        contentStreamWriter.writeToken(operator);
    }

    private void loopReplace(COSArray cos) {
        for (COSBase subCos : cos) {
            if (subCos instanceof COSString)
                replace((COSString) subCos);
        }
    }

    private void replace(COSString cos) {
        byte[] sourBytes = cos.getBytes();
        int i = 0;
        while (i < sourBytes.length) {
            WordInfo word = wordQueue.poll();
            if (word == null) continue;
            int byteCount = word.getByteCount();
            for (int j = 0; j < byteCount; j++) {
                if (word.isNeedReplace()) sourBytes[i] = 32;
                i++;
            }
        }
        cos.setValue(sourBytes);
    }

    @Override
    public void appendRectangle(Point2D p0, Point2D p1, Point2D p2, Point2D p3) {
        // Do nothing
    }

    @Override
    public void drawImage(PDImage pdImage) {
        // Do nothing
    }

    @Override
    public void clip(int windingRule) {
        // Do nothing

    }

    @Override
    public void moveTo(float x, float y) {
        // Do nothing

    }

    @Override
    public void lineTo(float x, float y) {
        // Do nothing

    }

    @Override
    public void curveTo(float x1, float y1, float x2, float y2, float x3, float y3) {
        // Do nothing

    }

    @Override
    public Point2D getCurrentPoint() {
        return null;
    }

    @Override
    public void closePath() {
        // Do nothing

    }

    @Override
    public void endPath() {
        // Do nothing

    }

    @Override
    public void strokePath() {
        // Do nothing
    }

    @Override
    public void fillPath(int windingRule) {
        // Do nothing
    }

    @Override
    public void fillAndStrokePath(int windingRule) {
        // Do nothing
    }

    @Override
    public void shadingFill(COSName shadingName) {
        // Do nothing
    }

    /**
     * <p>
     * This method retrieves the next operation before its registered
     * listener is called. The default does nothing.
     * </p>
     * <p>
     * Override this method to retrieve state information from before the
     * operation execution.
     * </p>
     */
    protected void nextOperation(Operator operator, List<COSBase> operands) {
        // Do nothing
    }

    @Override
    public void processPage(PDPage page) throws IOException {
        PDStream stream = new PDStream(document);
        replacementStream = stream.createOutputStream(COSName.FLATE_DECODE);
        replacement = new ContentStreamWriter(replacementStream);
        super.processPage(page);
        replacementStream.close();
        page.setContents(stream);
        replacement = null;
        replacementStream = null;
    }

    @Override
    protected void processOperator(Operator operator, List<COSBase> operands) throws IOException {
        if (inOperator) {
            super.processOperator(operator, operands);
        } else {
            inOperator = true;
            nextOperation(operator, operands);
            super.processOperator(operator, operands);
            write(replacement, operator, operands);
            inOperator = false;
        }
    }
}
