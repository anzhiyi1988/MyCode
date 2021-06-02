package orz.anzhy.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * test
 * @description
 * @author anzhy
 * @date 2021/3/20 16:14
 * @version 1.0
 */
public class PdfTest {

    @Test
    public void readPdf2() throws IOException {

        File pdfFile = new File(baseDir + "/src/main/resources/zt.pdf");
        PDDocument document = PDDocument.load(pdfFile);
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(document);
        log.debug(text);
        document.close();
    }
}
