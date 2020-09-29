package com.donaldy.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.*;

/**
 * @author donald
 * @date 2020/09/29
 */
public class Pdf2Image {

    @Test
    public void test() {

        try (
                PDDocument document = PDDocument.load(new File("/home/donald/Downloads/《图解TCP IP(第5版)》.((日)竹下隆史).[PDF].&ckook.pdf"))
        ) {
            // choose your printing method:
            //print(document);
            //printWithAttributes(document);
            //printWithDialog(document);
            //printWithDialogAndAttributes(document);
            //printWithPaper(document);

            printLocalImage(document, "/home/donald/Downloads/image.png");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printLocalImage(PDDocument document, String outFilePath) throws IOException {

        // 读取图片到缓冲数组中
        BufferedImage image = convertToImage(document);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        ImageIO.write(image, "png", out);

        InputStream inputStream = new ByteArrayInputStream(out.toByteArray());

        writeToLocal(outFilePath, inputStream);
    }

    private static BufferedImage convertToImage(PDDocument document) throws IOException {

        PDFRenderer pdfRenderer = new PDFRenderer(document);

        if (document.getNumberOfPages() < 0) {

            System.out.println("number of pages < 0");

            return null;
        }

        return pdfRenderer.renderImageWithDPI(0, 10, ImageType.RGB);
    }

    private static void writeToLocal(String path, InputStream input)
            throws IOException {
        int index;
        byte[] bytes = new byte[1024];
        FileOutputStream downloadFile = new FileOutputStream(path);
        while ((index = input.read(bytes)) != -1) {
            downloadFile.write(bytes, 0, index);
            downloadFile.flush();
        }
        downloadFile.close();
        input.close();
    }

    // 调用：打印机
    private static void print(PDDocument document) throws PrinterException {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPageable(new PDFPageable(document));
        job.print();
    }
}
