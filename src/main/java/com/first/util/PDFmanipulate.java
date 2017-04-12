package com.first.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.first.util.PDFUtil.MyFontsProvider;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

/**
 * 设置页面附加属性
 *
 */
public class PDFmanipulate {
	
	public static final String SRC = "E:\\银承猫上线记录\\crontract\\mb.pdf";
    public static final String DEST = "E:\\银承猫上线记录\\crontract\\mb_hero3.pdf";
    public static final String IMG = "E:\\银承猫上线记录\\crontract\\ycm_itext.png";
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        //file.getParentFile().mkdirs();
        manipulatePdf3(SRC, DEST);
    }
	
    
    // 文字水印
	public static void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        PdfContentByte under = stamper.getUnderContent(1);
        //Font f = new Font(FontFamily.HELVETICA, 15);
        Font f = MyFontsProvider.getInstance().getFont(null);
        Phrase p = new Phrase("中This watermark is added UNDER the existing content", f);
        ColumnText.showTextAligned(under, Element.ALIGN_CENTER, p, 297, 650, 0);
        PdfContentByte over = stamper.getOverContent(1);
        p = new Phrase("国This watermark is added ON TOP OF the existing content", f);
        ColumnText.showTextAligned(over, Element.ALIGN_CENTER, p, 297, 500, 0);
        p = new Phrase("人This TRANSPARENT watermark is added ON TOP OF the existing content", f);
        over.saveState();
        PdfGState gs1 = new PdfGState();
        gs1.setFillOpacity(0.5f);
        over.setGState(gs1);
        ColumnText.showTextAligned(over, Element.ALIGN_CENTER, p, 297, 450, 0);
        over.restoreState();
        stamper.close();
        reader.close();
    }
	
	//图片水印
	public static void manipulatePdf1(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        int n = reader.getNumberOfPages();
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        // text watermark
        //Font f = new Font(FontFamily.HELVETICA, 30);
        Font f = MyFontsProvider.getInstance().getFont(null);
        Phrase p = new Phrase("文字横着My watermark (text)", f);
        // image watermark
        Image img = Image.getInstance(IMG);
        float w = img.getScaledWidth();
        float h = img.getScaledHeight();
        // transparency
        PdfGState gs1 = new PdfGState();
        gs1.setFillOpacity(0.5f);
        // properties
        PdfContentByte over;
        Rectangle pagesize;
        float x, y;
        // loop over every page
        for (int i = 1; i <= n; i++) {
            pagesize = reader.getPageSizeWithRotation(i);
            x = (pagesize.getLeft() + pagesize.getRight()) / 2;
            y = (pagesize.getTop() + pagesize.getBottom()) / 2;
            over = stamper.getOverContent(i);
            over.saveState();
            over.setGState(gs1);
            if (i % 2 == 1)
                ColumnText.showTextAligned(over, Element.ALIGN_CENTER, p, x, y, 0);
            else
                over.addImage(img, w, 0, 0, h, x - (w / 2), y - (h / 2));
            over.restoreState();
        }
        stamper.close();
        reader.close();
    }
	
	public static void manipulatePdf2(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        int n = reader.getNumberOfPages();
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        stamper.setRotateContents(false);
        // text watermark
        //Font f = new Font(FontFamily.HELVETICA, 30);
        Font f = MyFontsProvider.getInstance().getFont(null);
        f.setSize(30);
        Phrase p = new Phrase("文字竖着My watermark (text)", f);
        // image watermark
        Image img = Image.getInstance(IMG);
        float w = img.getScaledWidth();
        float h = img.getScaledHeight();
        // transparency
        PdfGState gs1 = new PdfGState();
        gs1.setFillOpacity(0.5f);
        // properties
        PdfContentByte over;
        Rectangle pagesize;
        float x, y;
        // loop over every page
        for (int i = 1; i <= n; i++) {
            pagesize = reader.getPageSize(i);
            x = (pagesize.getLeft() + pagesize.getRight()) / 2;
            y = (pagesize.getTop() + pagesize.getBottom()) / 2;
            over = stamper.getOverContent(i);
            over.saveState();
            over.setGState(gs1);
            if (i % 2 == 1)
                ColumnText.showTextAligned(over, Element.ALIGN_CENTER, p, x, y, 0);
            else
                over.addImage(img, w, 0, 0, h, x - (w / 2), y - (h / 2));
            over.restoreState();
        }
        stamper.close();
        reader.close();
    }
	
	public static void manipulatePdf3(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        int n = reader.getNumberOfPages();
        PdfContentByte canvas;
        Rectangle pageSize;
        float x, y;
        for (int p = 1; p <= n; p++) {
            pageSize = reader.getPageSizeWithRotation(p);
            // left of the page
            x = pageSize.getLeft();
            // middle of the height
            y = (pageSize.getTop() + pageSize.getBottom()) / 2;
            // getting the canvas covering the existing content
            canvas = stamper.getOverContent(p);
            // adding some lines to the left
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER,
                new Phrase("This is some extra text added to the left of the page"),
                x + 18, y, 90);
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER,
                new Phrase("This is some more text added to the left of the page"),
                x + 34, y, 90);
        }
        stamper.close();
    }
}
