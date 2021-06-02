package com.thunisoft.jdgtyypt.common.pdf;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * PdfCover
 *
 * @author anzhy
 * @version 1.0
 * @description
 * @date 2021/3/12 10:37
 */
@Slf4j
public class PdfOperate {


    /**
     * 给【原文件】的【指定页】增加【一个矩形】遮罩，遮罩后输出到【目标文件】中。
     * @param sourcePdfPath  原文件 绝对路径+文件名。
     * @param targetPdfPath  目标文件 绝对路径+文件名。
     * @param pageNum     指定的页码
     * @param rect    矩形信息
     * @throws Exception
     */
    @Deprecated
    public void addCover(String sourcePdfPath, String targetPdfPath, Integer pageNum, Rectangle rect) throws Exception {
        File pdfFile = new File(sourcePdfPath);
        PDDocument document = PDDocument.load(pdfFile);
        PDPage page = document.getPage(pageNum);

        if (canAddRectangle(page, rect)) {
            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, false);
            contentStream.setNonStrokingColor(Color.BLACK);
            contentStream.addRect(rect.getX(), rect.getY(), rect.getW(), rect.getH());
            contentStream.fill();
            contentStream.close();
            File targetPdfFile = new File(targetPdfPath);
            document.save(targetPdfFile);
            document.close();
        } else {
            log.error("图形超过页面大小");
            document.close();
            throw new Exception("图形超过页面大小");
        }
    }

    /**
     * 给【原文件】的【指定页】增加【多个矩形】遮罩，遮罩后输出到【目标文件】中。
     * @param sourcePdfPath 原文件 绝对路径+文件名。
     * @param targetPdfPath 目标文件 绝对路径+文件名。
     * @param pageNum 指定的页码
     * @param rectList 多个矩形信息
     * @throws Exception
     */
    @Deprecated
    public void addCover(String sourcePdfPath, String targetPdfPath, Integer pageNum, List<Rectangle> rectList) throws Exception {
        File pdfFile = new File(sourcePdfPath);
        PDDocument document = PDDocument.load(pdfFile);
        PDPage page = document.getPage(pageNum);

        PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, false);
        contentStream.setNonStrokingColor(Color.black);
        for (Rectangle rect : rectList) {
            if (canAddRectangle(page, rect)) {
                contentStream.addRect(rect.getX(), rect.getY(), rect.getW(), rect.getH());
            } else {
                log.error("图形超过页面大小");
                contentStream.close();
                document.close();
                throw new Exception("图形超过页面大小");
            }
        }
        contentStream.fill();
        contentStream.close();
        File targetPdfFile = new File(targetPdfPath);
        document.save(targetPdfFile);
        document.close();
    }


    /**
     *  给【原文件】的【指定页】增加【多个矩形】遮罩，遮罩后输出到【目标文件】中。<br>
     *  此处的矩形内的 原点、宽和高 存储的是百分比。
     *
     * @param sourcePdfPath 【原文件】
     * @param targetPdfPath 【目标文件】
     * @param pageNum 【指定页】
     * @param rectList 【多个矩形】
     * @throws Exception
     */
    @Deprecated
    public void addCoverByPercent(String sourcePdfPath, String targetPdfPath, Integer pageNum, List<Rectangle> rectList) throws Exception {
        File pdfFile = new File(sourcePdfPath);
        PDDocument document = PDDocument.load(pdfFile);
        PDPage page = document.getPage(pageNum);

        float pageH = page.getMediaBox().getHeight();
        float pageW = page.getMediaBox().getWidth();

        PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, false);


        contentStream.setNonStrokingColor(Color.black);
        for (Rectangle rect : rectList) {
            if (canAddRectangle(page, rect)) {
                float x = rect.getXPercent() * pageW;
                float y = rect.getYPercent() * pageH;
                float w = rect.getWPercent() * pageW;
                float h = rect.getHPercent() * pageH;
                contentStream.addRect(x, y, w, h);
            } else {
                log.error("图形超过页面大小");
                contentStream.close();
                document.close();
                throw new Exception("图形超过页面大小");
            }
        }
        contentStream.fill();
        contentStream.close();
        File targetPdfFile = new File(targetPdfPath);
        document.save(targetPdfFile);
        document.close();
    }


    public PDDocument addCover(PDDocument doc, Map<Integer, List<Rectangle>> covers) throws IOException {
        PDPageTree pages = doc.getPages();
        for (int i = 0; i < pages.getCount(); i++) {
            PDPage currPage = pages.get(i);
            List<Rectangle> currPageCovers = covers.get(i);
            if (CollectionUtils.isEmpty(currPageCovers))
                continue;
            addCover(doc, currPage, currPageCovers);

        }
        return doc;
    }


    public PDDocument replace(PDDocument doc, Map<Integer, List<Rectangle>> covers) throws IOException {
        PDPageTree pages = doc.getPages();
        for (int i = 0; i < pages.getCount(); i++) {
            PDPage currPage = pages.get(i);
            List<Rectangle> currPageCovers = covers.get(i);
            if (CollectionUtils.isEmpty(currPageCovers))
                continue;
            replace(doc, currPage, currPageCovers);
        }
        return doc;
    }

    public PDDocument addCoverByPercent(PDDocument doc, Map<Integer, List<Rectangle>> covers) throws IOException {
        PDPageTree pages = doc.getPages();
        for (int i = 0; i < pages.getCount(); i++) {
            PDPage currPage = pages.get(i);
            List<Rectangle> currPageCovers = covers.get(i);
            if (CollectionUtils.isEmpty(currPageCovers)) {
                continue;
            }
            countXYWH(currPage, currPageCovers);
            addCover(doc, currPage, currPageCovers);
        }
        return doc;
    }


    public PDDocument replaceByPercent(PDDocument doc, Map<Integer, List<Rectangle>> covers) throws IOException {
        PDPageTree pages = doc.getPages();
        for (int i = 0; i < pages.getCount(); i++) {
            PDPage currPage = pages.get(i);
            List<Rectangle> currPageCovers = covers.get(i);
            if (CollectionUtils.isEmpty(currPageCovers)) {
                continue;
            }
            countXYWH(currPage, currPageCovers);
            replace(doc, currPage, currPageCovers);
        }
        return doc;
    }

    /**
     *
     * @param doc
     * @param covers
     * @return
     * @throws IOException
     */
    public PDDocument addCoverAndReplace(PDDocument doc, Map<Integer, List<Rectangle>> covers) throws IOException {
        PDPageTree pages = doc.getPages();
        for (int i = 0; i < pages.getCount(); i++) {
            PDPage currPage = pages.get(i);
            List<Rectangle> currPageCovers = covers.get(i);
            if (CollectionUtils.isEmpty(currPageCovers))
                continue;
            replace(doc, currPage, currPageCovers);
            addCover(doc, currPage, currPageCovers);
        }
        return doc;
    }

    /**
     *
     * @param doc
     * @param covers
     * @return
     * @throws IOException
     */
    public PDDocument addCoverByPercentAndReplace(PDDocument doc, Map<Integer, List<Rectangle>> covers) throws IOException {
        PDPageTree pages = doc.getPages();
        for (int i = 0; i < pages.getCount(); i++) {
            PDPage currPage = pages.get(i);
            List<Rectangle> currPageCovers = covers.get(i);
            if (CollectionUtils.isEmpty(currPageCovers)) {
                continue;
            }
            countXYWH(currPage, currPageCovers);
            replace(doc, currPage, currPageCovers);
            addCover(doc, currPage, currPageCovers);
        }
        return doc;
    }


    private PDDocument replace(PDDocument doc, PDPage page, List<Rectangle> covers) throws IOException {
        PdfCoverHandler pdfCoverHandler = new PdfCoverHandler(page, doc, covers);
        pdfCoverHandler.processPage(page);
        return doc;
    }

    private PDDocument addCover(PDDocument doc, PDPage page, List<Rectangle> covers) throws IOException {
        PDPageContentStream contentStream = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND, false);
        contentStream.setNonStrokingColor(Color.gray);
        for (Rectangle rect : covers) {
            if (canAddRectangle(page, rect)) {
                contentStream.addRect(rect.getX(), rect.getY(), rect.getW(), rect.getH());
            } else {
                log.warn("图形超过页面大小,{},{}", rect.toString(), page.getMediaBox().toString());
            }
        }
        contentStream.stroke();
        contentStream.close();
        return doc;
    }

    private void countXYWH(PDPage page, List<Rectangle> currPageCovers) {

        float pageH = page.getMediaBox().getHeight();
        float pageW = page.getMediaBox().getWidth();

        for (Rectangle rect : currPageCovers) {
            rect.setX(rect.getXPercent() * pageW);
            rect.setY(rect.getYPercent() * pageH);
            rect.setW(rect.getWPercent() * pageW);
            rect.setH(rect.getHPercent() * pageH);
        }
    }

    /**
     * 通过矩形的四个点，判断是否可以画在页面上。<br>
     * 判断规则是只要有一个点落在范围内，就可以画。
     *
     * @param page
     * @param rect
     * @return
     */
    private boolean canAddRectangle(PDPage page, Rectangle rect) {
        float rectMinX = rect.getX();
        float rectMaxX = rectMinX + rect.getW();
        float rectMinY = rect.getY();
        float rectMaxY = rectMinY + rect.getH();
        PDRectangle pdRectangle = page.getMediaBox();
        return pdRectangle.contains(rectMinX, rectMinY) || pdRectangle.contains(rectMaxX, rectMinY)
                || pdRectangle.contains(rectMinX, rectMaxY) || pdRectangle.contains(rectMaxX, rectMaxY);
    }

}
