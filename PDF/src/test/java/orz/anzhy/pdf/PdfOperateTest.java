/**
 * @projectName pdf_cover
 * @package com.thunisoft.anzhy
 * @className com.thunisoft.anzhy.PdfOperateTest
 * @copyright anzhy.
 */
package com.thunisoft.jdgtyypt.common.pdf;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.pdfparser.PDFStreamParser;
import org.apache.pdfbox.pdfwriter.ContentStreamWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.util.Charsets;
import org.apache.pdfbox.util.Matrix;
import org.apache.pdfbox.util.Vector;
import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * PdfCoverTest
 *
 * @author anzhy
 * @version 1.0
 * @description
 * @date 2021/3/12 10:37
 */
@Slf4j
public class PdfOperateTest {

    public static final String baseDir = System.getProperty("user.dir") + "/src/main/resources/";


    @Test
    public void testCove() throws Exception {
        PdfOperate pdfOperate = new PdfOperate();
        String baseDir = System.getProperty("user.dir");
        try {

            Rectangle rect = new Rectangle();
            rect.setX(150);
            rect.setY(150);
            rect.setH(20);
            rect.setW(30);

            pdfOperate.addCover(sourcePdfPath, targetPdfPath, 0, rect);
        } catch (IOException e) {
            log.error("增加遮罩异常！", e);
        }
    }


    @Test
    public void testCoveMultiByPercent() throws Exception {
        PdfOperate pdfOperate = new PdfOperate();

        Rectangle rect1 = new Rectangle();
        rect1.setXPercent(0.80410607f);
        rect1.setYPercent(0.24682396f);
        rect1.setHPercent(0.01209921f);
        rect1.setWPercent(0.03763901f);
        Rectangle rect2 = new Rectangle();
        rect2.setXPercent(0.56629598f);
        rect2.setYPercent(0.07803993f);
        rect2.setHPercent(0.11978221f);
        rect2.setWPercent(0.33019675f);
        List<Rectangle> list = new ArrayList<>();
        list.add(rect1);
        list.add(rect2);

        List<Rectangle> pdfList = parseToPdfCoordiante(list);

        Map<Integer, List<Rectangle>> map = new HashMap<>();
        map.put(1, pdfList);


        String fileName = "test4";
        String sourcePdfPath = baseDir + fileName + ".pdf";
        String targetPdfPath = baseDir + fileName + "_new.pdf";

        File pdfFile = new File(sourcePdfPath);
        PDDocument document = PDDocument.load(pdfFile);
        document = pdfOperate.addCoverByPercentAndReplace(document, map);
        document.save(targetPdfPath);
        document.close();

    }

    private static List<Rectangle> parseToPdfCoordiante(List<Rectangle> list) {
        List<Rectangle> newList = new ArrayList<>();
        list.forEach(r -> {
            Rectangle rect = new Rectangle();
            rect.setXPercent(r.getXPercent());
            rect.setYPercent(1.008f - r.getYPercent());  // 平移  0.008为偏移补偿，这个值是安致宜试出来的，根据不同情况需要调整
            rect.setWPercent(r.getWPercent());
            rect.setHPercent(r.getHPercent() * -1); // 取反
            newList.add(rect);
        });
        return newList;
    }

    @Test
    public void testCoveMulti() throws Exception {
        PdfOperate pdfOperate = new PdfOperate();
        try {

            Rectangle rect1 = new Rectangle();
            rect1.setX(70f);
            rect1.setY(70f);
            rect1.setH(-20f);
            rect1.setW(-30f);
            Rectangle rect2 = new Rectangle();
            rect2.setX(70f);
            rect2.setY(70f);
            rect2.setH(20f);
            rect2.setW(30f);
            List<Rectangle> list = new ArrayList<>();
            list.add(rect1);
            list.add(rect2);

            String fileName = "test4";
            String sourcePdfPath = baseDir + fileName + ".pdf";
            String targetPdfPath = baseDir + fileName + "_new.pdf";
            pdfOperate.addCover(sourcePdfPath, targetPdfPath, 0, list);
        } catch (IOException e) {
            log.error("增加遮罩异常！", e);
        }
    }


    public static final String sourcePdfPath = baseDir + "/src/main/resources/test3.pdf";
    public static final String targetPdfPath = baseDir + "/src/main/resources/test3_new.pdf";

    @Test
    public void testCoveAndReplace3() throws Exception {
        PdfOperate pdfOperate = new PdfOperate();
        try {

            // Rectangle rect1 = new Rectangle();
            // rect1.setX(0f);
            // rect1.setY(0f);
            // rect1.setH(10f);
            // rect1.setW(20f);
            Rectangle rect2 = new Rectangle();
            rect2.setX(260f);
            rect2.setY(675f);
            rect2.setH(10f);
            rect2.setW(30f);
            List<Rectangle> list = new ArrayList<>();
            // list.add(rect1);
            list.add(rect2);
            Map<Integer, List<Rectangle>> map = new HashMap<>();
            map.put(0, list);


            File pdfFile = new File(sourcePdfPath);
            PDDocument document = PDDocument.load(pdfFile);
            pdfOperate.addCoverAndReplace(document, map);
            document.save(targetPdfPath);
            document.close();
        } catch (IOException e) {
            throw e;
        }
    }


    @Test
    public void testCoveAndReplace2() throws Exception {
        PdfOperate pdfOperate = new PdfOperate();
        try {

            // Rectangle rect1 = new Rectangle();
            // rect1.setX(0f);
            // rect1.setY(0f);
            // rect1.setH(10f);
            // rect1.setW(20f);
            Rectangle rect2 = new Rectangle();
            rect2.setX(260f);
            rect2.setY(790f);
            rect2.setH(10f);
            rect2.setW(20f);
            List<Rectangle> list = new ArrayList<>();
            // list.add(rect1);
            list.add(rect2);
            Map<Integer, List<Rectangle>> map = new HashMap<>();
            map.put(0, list);


            File pdfFile = new File(sourcePdfPath);
            PDDocument document = PDDocument.load(pdfFile);
            pdfOperate.addCoverAndReplace(document, map);
            document.save(targetPdfPath);
            document.close();
        } catch (IOException e) {
            throw e;
        }
    }

    @Test
    public void testCoveAndReplace() throws Exception {
        PdfOperate pdfOperate = new PdfOperate();
        try {

            // Rectangle rect1 = new Rectangle();
            // rect1.setX(0f);
            // rect1.setY(0f);
            // rect1.setH(10f);
            // rect1.setW(20f);
            Rectangle rect2 = new Rectangle();
            rect2.setX(70f);
            rect2.setY(51f);
            rect2.setH(10f);
            rect2.setW(20);
            List<Rectangle> list = new ArrayList<>();
            // list.add(rect1);
            list.add(rect2);
            Map<Integer, List<Rectangle>> map = new HashMap<>();
            map.put(0, list);

            String fileName = "helloworld";
            String sourcePdfPath = baseDir + fileName + ".pdf";
            String targetPdfPath = baseDir + fileName + "_new.pdf";
            File pdfFile = new File(sourcePdfPath);
            PDDocument document = PDDocument.load(pdfFile);
            pdfOperate.addCoverAndReplace(document, map);
            document.save(targetPdfPath);
            document.close();
        } catch (IOException e) {
            throw e;
        }
    }

    @Test
    public void readPdf() throws IOException {
        String fileName = "test4";
        String sourcePdfPath = baseDir + fileName + ".pdf";
        String targetPdfPath = baseDir + fileName + "_new.pdf";
        File pdfFile = new File(sourcePdfPath);
        PDDocument document = PDDocument.load(pdfFile);
        PDFTextStripper pdfStripper = new MyPDFTextStripper();
        String text = pdfStripper.getText(document);
        log.debug(text);
        document.close();
    }


    /**
     *
     * 读取整个pdf文件中的文本，一次性输出。
     * 如何一页一页的读呢？不知道
     *
     * @throws IOException
     */
    @Test
    public void readPdf2() throws IOException {

        File pdfFile = new File(baseDir + "/src/main/resources/helloworld.pdf");
        PDDocument document = PDDocument.load(pdfFile);
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(document);
        log.debug(text);
        document.close();
    }

    @Test
    public void readPdf3() throws IOException {

        // File pdfFile = new File(baseDir + "/src/main/resources/helloworld.pdf");
        File pdfFile = new File(baseDir + "/src/main/resources/zt.pdf");
        PDDocument doc = PDDocument.load(pdfFile);
        PDPageTree tree = doc.getPages();
        tree.forEach(page -> {
            System.out.println("page----------------------------------------");
            InputStream in = null;
            try {
                in = page.getContents();
                byte[] b = new byte[100];
                int flag;
                while ((flag = in.read(b)) != -1) {
                    System.out.println(new String(b));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        tree.forEach(page -> {
            System.out.println("page************************************************");
            Iterator<PDStream> streams = page.getContentStreams();
            while (streams.hasNext()) {
                PDStream s = streams.next();
                COSStream cos = s.getCOSObject();
                String x = cos.toTextString();
                System.out.println(x);
            }
        });

        doc.close();
    }


    @Test
    public void writeImage() throws IOException {

        // File pdfFile = new File(baseDir + "/src/main/resources/helloworld.pdf");
        File pdfFile = new File(baseDir + "/src/main/resources/zt.pdf");
        PDDocument doc = PDDocument.load(pdfFile);
        PDPage page = doc.getPage(0);


        PDImageXObject pdImage = PDImageXObject.createFromFile(baseDir + "/src/main/resources/pic.png", doc);
        PDPageContentStream contents = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND, false);
        contents.drawImage(pdImage, 90, 40);
        contents.close();
        doc.save(baseDir + "/src/main/resources/test1.pdf");
        doc.close();
    }


    /**
     * 文字替换，但是不知道坐标啊
     *
     * @throws IOException
     */
    @Test
    public void replaceText() throws IOException {
        File pdfFile = new File(baseDir + "/src/main/resources/zt.pdf");
        // File pdfFile = new File(baseDir + "/src/main/resources/helloworld.pdf");
        // File pdfFile = new File(baseDir + "/src/main/resources/test3.pdf");

        PDDocument doc = PDDocument.load(pdfFile);
        PDPage page = doc.getPage(0);

        PDFStreamParser parser = new PDFStreamParser(page); //此类解析PDF字节流并提取操作数等。
        parser.parse();
        List<Object> tokens = parser.getTokens();
        for (int j = 0; j < tokens.size(); j++) {
            Object o = tokens.get(j);
            if (o instanceof Operator) {
                Operator op = (Operator) o;
                if (op.getName().equals("Tj")) {
                    Object cos = tokens.get(j - 1);
                    printCOSString((COSString) cos);
                } else if (op.getName().equals("TJ")) {
                    Object cos = tokens.get(j - 1);
                    COSArray previous = (COSArray) cos;
                    for (int k = 0; k < previous.size(); k++) {
                        Object arrElement = previous.getObject(k);
                        if (arrElement instanceof COSString) {
                            printCOSString((COSString) arrElement);
                        }
                    }
                }
            }
        }
        PDStream updatedStream = new PDStream(doc);
        OutputStream out = updatedStream.createOutputStream();
        ContentStreamWriter tokenWriter = new ContentStreamWriter(out);
        tokenWriter.writeTokens(tokens);
        page.setContents(updatedStream);
        out.close();
        doc.save(baseDir + "/src/main/resources/test1.pdf");
        doc.close();
    }


    /**
     * 清楚所有文字，只剩下图片
     * @throws IOException
     */
    @Test
    public void testRemoveTextDocument() throws IOException {

        File pdfFile = new File(baseDir + "/src/main/resources/zt.pdf");
        PDDocument doc = PDDocument.load(pdfFile);
        PDPageTree tree = doc.getPages();
        for (PDPage page : tree) {
            PdfContentStreamEditor identity = new PdfContentStreamEditor(page, doc) {

                final List<String> TEXT_SHOWING_OPERATORS = Arrays.asList("Tj", "'", "\"", "TJ");

                @Override
                protected void write(ContentStreamWriter contentStreamWriter, Operator operator, List<COSBase> operands) throws IOException {
                    String operatorString = operator.getName();
                    System.out.println("anzhy ------------------------------------ " + operatorString);
                    if (TEXT_SHOWING_OPERATORS.contains(operatorString)) {
                        return;
                    }

                    super.write(contentStreamWriter, operator, operands);
                }
            };
            identity.processPage(page);
        }
        doc.save(baseDir + "/src/main/resources/test1.pdf");
        doc.close();

    }


    /**
     * 清除指定的文字
     * @throws IOException
     */
    @Test
    public void testReplaceTextDocument() throws IOException {

        File pdfFile = new File(baseDir + "/src/main/resources/test3.pdf");
        // File pdfFile = new File(baseDir + "/src/main/resources/zt.pdf");
        PDDocument doc = PDDocument.load(pdfFile);
        PDPageTree tree = doc.getPages();
        for (PDPage page : tree) {

            PdfContentStreamEditor identity = new PdfContentStreamEditor(page, doc) {

                final List<String> TEXT_SHOWING_OPERATORS = Arrays.asList("Tj", "TJ");
                final StringBuilder recentChars = new StringBuilder();

                @Override
                protected void showGlyph(Matrix textRenderingMatrix, PDFont font, int code, Vector displacement)
                        throws IOException {
                    String string = font.toUnicode(code);
                    font.getFontMatrix();
                    if (string != null) {
                        recentChars.append(string);
                        printMatrix(textRenderingMatrix, code, string);
                    }
                    super.showGlyph(textRenderingMatrix, font, code, displacement);
                }

                @Override
                protected void write(ContentStreamWriter contentStreamWriter, Operator operator, List<COSBase> operands) throws IOException {
                    String recentText = recentChars.toString();
                    recentChars.setLength(0);
                    String operatorString = operator.getName();
                    // System.out.println("anzhy write line : " + recentText);

                    if (TEXT_SHOWING_OPERATORS.contains(operatorString)) {  //&& "[QR]".equals(recentText)) {
                        for (COSBase cos : operands) {
                            if (cos instanceof COSString) {
                                COSString c = (COSString) cos;
                                String string = c.getString();
                                log.info("anzhy string {}", string);
                                c.setValue("A".getBytes());
                            } else if (cos instanceof COSArray) {
                                log.info("anzhy cosarray ");
                            }
                        }
                    }

                    super.write(contentStreamWriter, operator, operands);
                }
            };
            identity.processPage(page);
        }
        doc.save(baseDir + "/src/main/resources/test1.pdf");
        doc.close();

    }


    /**
     * 遍历文档中的文字
     * @throws IOException
     */
    @Test
    public void loopWordInDocument() throws IOException {

        File pdfFile = new File(baseDir + "/src/main/resources/test3.pdf");
        // File pdfFile = new File(baseDir + "/src/main/resources/zt.pdf");
        PDDocument doc = PDDocument.load(pdfFile);

        PDPage page = doc.getPage(0);

        PdfContentStreamEditor identity = new PdfContentStreamEditor(page, doc) {

            final List<String> set = Arrays.asList("怎", "样", "写", "好");
            final List<String> TEXT_SHOWING_OPERATORS = Arrays.asList("Tj", "TJ");
            final StringBuilder recentChars = new StringBuilder();
            final Queue<WordInfo> wordQueue = new LinkedBlockingQueue<>();

            @Override
            protected void showGlyph(Matrix textRenderingMatrix, PDFont font, int code, Vector displacement)
                    throws IOException {
                WordInfo word = new WordInfo();
                String string = font.toUnicode(code);
                word.setFontType(font.getClass().getName());
                printMatrix(textRenderingMatrix, code, string); //后续应该使用坐标判断是否替换。
                word.setNeedReplace(set.contains(string));
                //word.setBytes(font.encode(string));  //有的文档是无法使用这个方法的。真是崩溃
                word.setWord(string);
                wordQueue.add(word);
                super.showGlyph(textRenderingMatrix, font, code, displacement);
            }

            @Override
            protected void write(ContentStreamWriter contentStreamWriter, Operator operator, List<COSBase> operands) throws IOException {
                String recentText = recentChars.toString();
                recentChars.setLength(0);
                String operatorString = operator.getName();

                if (TEXT_SHOWING_OPERATORS.contains(operatorString)) {  //&& "[QR]".equals(recentText)) {
                    for (COSBase cos : operands) {
                        if (cos instanceof COSString) {
                            printCOSString((COSString) cos, wordQueue);
                        } else if (cos instanceof COSArray) {
                            for (COSBase subCos : (COSArray) cos) {
                                if (subCos instanceof COSString)
                                    printCOSString((COSString) subCos, wordQueue);
                            }
                        }
                    }
                }
                wordQueue.clear();
                super.write(contentStreamWriter, operator, operands);
            }
        };
        identity.processPage(page);
        doc.save(baseDir + "/src/main/resources/test1.pdf");
        doc.close();

    }


    /**
     * 遍历文档中的文字
     * @throws IOException
     */
    @Test
    public void loopReplaceInDocument() throws IOException {

        // File pdfFile = new File(baseDir + "/src/main/resources/test3.pdf");
        File pdfFile = new File(baseDir + "/src/main/resources/zt.pdf");
        PDDocument doc = PDDocument.load(pdfFile);

        PDPage page = doc.getPage(0);

        PdfContentStreamEditor identity = new PdfContentStreamEditor(page, doc) {

            // final List<String> set = Arrays.asList("怎", "样", "写", "好");
            final List<String> set = Arrays.asList("典", "轩");
            final List<String> TEXT_SHOWING_OPERATORS = Arrays.asList("Tj", "TJ");
            final StringBuilder recentChars = new StringBuilder();
            final Queue<WordInfo> wordQueue = new LinkedBlockingQueue<>();

            @Override
            protected void showGlyph(Matrix textRenderingMatrix, PDFont font, int code, Vector displacement)
                    throws IOException {
                WordInfo word = new WordInfo();
                String string = font.toUnicode(code);
                word.setFontType(font.getClass().getName());
                //printMatrix(textRenderingMatrix, code, string);后续应该使用坐标判断是否替换。
                word.setNeedReplace(set.contains(string));
                //word.setBytes(font.encode(string));  //有的文档是无法使用这个方法的。真是崩溃
                word.setWord(string);
                wordQueue.add(word);
                super.showGlyph(textRenderingMatrix, font, code, displacement);
            }

            @Override
            protected void write(ContentStreamWriter contentStreamWriter, Operator operator, List<COSBase> operands) throws IOException {
                String recentText = recentChars.toString();
                recentChars.setLength(0);
                String operatorString = operator.getName();

                if (TEXT_SHOWING_OPERATORS.contains(operatorString)) {  //&& "[QR]".equals(recentText)) {
                    for (COSBase cos : operands) {
                        if (cos instanceof COSString) {
                            replace((COSString) cos, wordQueue);
                        } else if (cos instanceof COSArray) {
                            for (COSBase subCos : (COSArray) cos) {
                                if (subCos instanceof COSString)
                                    replace((COSString) subCos, wordQueue);
                            }
                        }
                    }
                }
                wordQueue.clear();
                super.write(contentStreamWriter, operator, operands);
            }
        };
        identity.processPage(page);
        doc.save(baseDir + "/src/main/resources/test1.pdf");
        doc.close();

    }

    private static void replace(COSString cos, Queue<WordInfo> wordQueue) {
        byte[] sourBytes = cos.getBytes();
        for (int i = 0; i < sourBytes.length; ) {
            log.info("anzhy replace start -------------------------------------------------");
            WordInfo word = wordQueue.poll();
            byte[] queuBtyes = word.getBytes();
            log.info("anzhy needReplace :" + word.isNeedReplace());
            log.info("anzhy word :" + word.getWord());
            log.info("anzhy replace font type : {}", word.getFontType());
            if (PDType0Font.class.getName().equals(word.getFontType())) { // 复杂类型（汉字占两个byte）
                int indexA = i++;
                int indexB = i++;
                printByte("sourByte", sourBytes[indexA]);
                printByte("sourByte", sourBytes[indexB]);
                if (word.isNeedReplace()) {
                    sourBytes[indexA] = 3;
                    sourBytes[indexB] = -35;
                }
            } else if (PDTrueTypeFont.class.getName().equals(word.getFontType())) { // 简单类型（字母占一个byte）
                int indexA = i++;
                printByte("sourByte", sourBytes[indexA]);
                if (word.isNeedReplace()) {
                    byte b = 32;
                    sourBytes[indexA] = b;
                }
            } else { // 不知道什么类型（字母占不知道个byte）
                i = i + queuBtyes.length;
            }
            printBytes("queuByte", queuBtyes);
        }
        cos.setValue(sourBytes);
    }


    @Test
    public void testStringByte() {
        String s = "三典";
        byte[] bs = s.getBytes();

        for (byte b : bs) {
            System.out.println(b);
        }

    }


    public static void printByte(String msg, byte b) {
        log.info("anzhy printBytes {}: {}", msg, String.valueOf(b));
    }

    public static void printMatrix(Matrix matrix, int code, String string) {
        float scaleX = matrix.getScaleX();
        float scaleY = matrix.getScaleY();
        float shearX = matrix.getShearX();
        float shearY = matrix.getShearY();
        float ScalingFactorX = matrix.getScalingFactorX();
        float ScalingFactorY = matrix.getScalingFactorY();
        float TranslateX = matrix.getTranslateX();
        float TranslateY = matrix.getTranslateY();
        float XPosition = matrix.getXPosition();
        float YPosition = matrix.getYPosition();
        log.info("anzhy 【code：{}，unicode :{} , scaleX:{},scaleY:{},shearX:{},shearY:{},ScalingFactorX:{},ScalingFactorY:{},TranslateX:{},TranslateY:{},XPosition:{},YPosition:{}】", code, string, scaleX, scaleY, shearX, shearY, ScalingFactorX, ScalingFactorY, TranslateX, TranslateY, XPosition, YPosition);
    }

    public static void printBytes(String msg, byte[] bs) {
        if (bs == null) return;
        for (byte b : bs) {
            log.info("anzhy printBytes {}: {}", msg, String.valueOf(b));
        }
    }


    public static void printCOSString(COSString cos, Queue<WordInfo> wordQueue) {
        byte[] sourBytes = cos.getBytes();
        for (int i = 0; i < sourBytes.length; ) {
            log.info("anzhy replace start -------------------------------------------------");
            WordInfo word = wordQueue.poll();
            log.info("anzhy needReplace :" + word.isNeedReplace());
            log.info("anzhy word :" + word.getWord());
            log.info("anzhy replace font type : {}", word.getFontType());
            byte[] queuBtyes = word.getBytes();
            if (PDType0Font.class.getName().equals(word.getFontType())) { // 复杂类型（汉字占两个byte）
                int indexA = i++;
                int indexB = i++;
                printByte("sourByte", sourBytes[indexA]);
                printByte("sourByte", sourBytes[indexB]);

            } else if (PDTrueTypeFont.class.getName().equals(word.getFontType())) { // 简单类型（字母占一个byte）
                int indexA = i++;
                printByte("sourByte", sourBytes[indexA]);

            } else { // 不知道什么类型（字母占不知道个byte）
                i = i + queuBtyes.length;
            }
            printBytes("queuByte", queuBtyes);

            log.info("anzhy replace end ------------------");
        }
    }

    public static void printCOSString(COSString cos) throws UnsupportedEncodingException {

        String string = cos.getString();

        log.info("anzhy COSString ----------------------------------------");
        byte[] bs = cos.getBytes();
        for (byte b : bs) {
            log.info("anzhy COSString {}【{}】", "byte", String.valueOf(b));
        }
        log.info("anzhy COSString {}【{}】", "hex", cos.toHexString());
        log.info("anzhy COSString {}【{}】", Charsets.UTF_8, new String(cos.getBytes(), Charsets.UTF_8));
        log.info("anzhy COSString {}【{}】", Charsets.US_ASCII, new String(cos.getBytes(), Charsets.US_ASCII));
        log.info("anzhy COSString {}【{}】", Charsets.UTF_16BE, new String(cos.getBytes(), Charsets.UTF_16BE));
        log.info("anzhy COSString {}【{}】", Charsets.UTF_16LE, new String(cos.getBytes(), Charsets.UTF_16LE));
        log.info("anzhy COSString {}【{}】", Charsets.ISO_8859_1, new String(cos.getBytes(), Charsets.ISO_8859_1));
        log.info("anzhy COSString {}【{}】", Charsets.WINDOWS_1252, new String(cos.getBytes(), Charsets.WINDOWS_1252));
        // log.info("anzhy COSString {}【{}】", Charsets.WINDOWS_1252,new String(cos.getBytes(), Charsets.WINDOWS_1252));
        log.info("anzhy COSString 【{}】", string);
        log.info("anzhy COSString ----------------------------------------");
    }
}
