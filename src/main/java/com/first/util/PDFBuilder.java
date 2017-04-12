package com.first.util;


import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.first.util.PDFUtil.MyFontsProvider;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfIndirectReference;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfTextArray;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * 设置页面附加属性
 *
 */
public class PDFBuilder extends PdfPageEventHelper {

	/********水印图片*******/
	public static final String IMG = "E:\\银承猫上线记录\\crontract\\mb\\official_seal.png";
	
    /**
     * 页眉
     */
    public String header = "";

    /**
     * 文档字体大小，页脚页眉最好和文本大小一致
     */
    public int presentFontSize = 12;

    /**
     * 文档页面大小，最好前面传入，否则默认为A4纸张
     */
    public Rectangle pageSize = PageSize.A4;
    
    /**
     * 最后一页
     */
    private int lastPage = 0;
    
    // 模板
    public PdfTemplate total;
    
    // 基础字体对象
    public BaseFont bf = null;

    // 利用基础字体生成的字体对象，一般用于生成中文文字
    public Font fontDetail = null;

    /**
     * 
     * Creates a new instance of PdfReportM1HeaderFooter 无参构造方法.
     * 
     */
    public PDFBuilder() {

    }

    /**
     * 
     * Creates a new instance of PdfReportM1HeaderFooter 构造方法.
     * 
     * @param yeMei
     *            页眉字符串
     * @param presentFontSize
     *            数据体字体大小
     * @param pageSize
     *            页面文档大小，A4，A5，A6横转翻转等Rectangle对象
     */
    public PDFBuilder(String yeMei, int presentFontSize, Rectangle pageSize) {
        this.header = yeMei;
        this.presentFontSize = presentFontSize;
        this.pageSize = pageSize;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setPresentFontSize(int presentFontSize) {
        this.presentFontSize = presentFontSize;
    }

    /**
     * 
     * TODO 文档打开时创建模板
     * 
     * @see com.itextpdf.text.pdf.PdfPageEventHelper#onOpenDocument(com.itextpdf.text.pdf.PdfWriter,
     *      com.itextpdf.text.Document)
     */
    public void onOpenDocument(PdfWriter writer, Document document) {
        total = writer.getDirectContent().createTemplate(50, 50);// 共 页 的矩形的长宽高
        System.out.println("-------document-----start--"+document.getPageNumber());
    }
    
    /**
     * 当每页打开的时候
     */
    public void onStartPage(PdfWriter writer,Document document) {
    	this.lastPage = document.getPageNumber();
    	//System.out.println("-------page-----start--"+document.getPageNumber());
    }

    /**
     * 
     * TODO 关闭每页的时候，写入页眉，写入'第几页共'这几个字。
     * 
     * @see com.itextpdf.text.pdf.PdfPageEventHelper#onEndPage(com.itextpdf.text.pdf.PdfWriter,
     *      com.itextpdf.text.Document)
     */
    public void onEndPage(PdfWriter writer, Document document) {
    	//System.out.println("-------page-----end--"+document.getPageNumber());
        this.addPage(writer, document);
    }
    //加分页
    public void addPage(PdfWriter writer, Document document){
        try {
            if (bf == null) {
                bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", false);
            }
            if (fontDetail == null) {
                fontDetail = new Font(bf, presentFontSize, Font.NORMAL);// 数据体字体
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 1.写入页眉
        ColumnText.showTextAligned(writer.getDirectContent(),
                Element.ALIGN_LEFT, new Phrase(header, fontDetail),
                document.left(), document.top() + 20, 0);
        // 2.写入前半部分的 第 X页/共
        int pageS = writer.getPageNumber();
        String foot1 = "第 " + pageS + " 页 /共";
        Phrase footer = new Phrase(foot1, fontDetail);

        // 3.计算前半部分的foot1的长度，后面好定位最后一部分的'Y页'这俩字的x轴坐标，字体长度也要计算进去 = len
        float len = bf.getWidthPoint(foot1, presentFontSize);

        // 4.拿到当前的PdfContentByte
        PdfContentByte cb = writer.getDirectContent();

        // 5.写入页脚1，x轴就是(右margin+左margin + right() -left()- len)/2.0F
        // 再给偏移20F适合人类视觉感受，否则肉眼看上去就太偏左了
        // ,y轴就是底边界-20,否则就贴边重叠到数据体里了就不是页脚了；注意Y轴是从下往上累加的，最上方的Top值是大于Bottom好几百开外的。
        ColumnText
                .showTextAligned(
                        cb,
                        Element.ALIGN_CENTER,
                        footer,
                        (document.rightMargin() + document.right()
                                + document.leftMargin() - document.left() - len) / 2.0F + 20F,
                        document.bottom() - 20, 0);

        // 6.写入页脚2的模板（就是页脚的Y页这俩字）添加到文档中，计算模板的和Y轴,X=(右边界-左边界 - 前半部分的len值)/2.0F +
        // len ， y 轴和之前的保持一致，底边界-20
        cb.addTemplate(total, (document.rightMargin() + document.right()
                + document.leftMargin() - document.left()) / 2.0F + 20F,
                document.bottom() - 20); // 调节模版显示的位置

    }
    
    //加图片水印
    private void addWatermark(PdfWriter writer){
        // 水印图片
        try {
        	int curPage = writer.getCurrentPageNumber();
        	System.out.println("curpage"+curPage);
        	//if(this.lastPage <= curPage || curPage == 1){ //首页和最后一页 且不能再onEndPage 处调用
        		//处于文字上方
        		PdfContentByte content = writer.getDirectContent();
	        	Rectangle pageSize = writer.getPageSize();
	        	Image image = Image.getInstance(IMG);
	            
	            //PdfContentByte content = writer.getDirectContentUnder();  //处于文字下方，被内容挡住
	            //PdfContentByte content = writer.getDirectContent();  //处于文字上方
	            content.beginText();
	            // 开始写入水印
	            /*for(int k=0;k<5;k++){ //竖着多少个
	                for (int j = 0; j <4; j++) { //横着多少个
	                    image.setAbsolutePosition(150*j,170*k);
	                	content.addImage(image);
	                }
	            }*/
	            float y = pageSize.getTop();
	            if(curPage == 1){
	            	y = y-600;
	            }else{
	            	y = y-200;
	            }
	            image.setAbsolutePosition(100,y);
	            // transparency
	            PdfGState gs1 = new PdfGState();
	            gs1.setFillOpacity(0.8f);
	            content.setGState(gs1);
	        	//content.addImage(image);
	            //addImage(image, image_width, 0, 0, image_height, x, y).
	            content.addImage(image, 150, 0, 0, 150, 100, y);
	            content.endText();
        	//}
        } catch (IOException | DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 
     * TODO 关闭文档时，替换模板，完成整个页眉页脚组件
     * 
     * @see com.itextpdf.text.pdf.PdfPageEventHelper#onCloseDocument(com.itextpdf.text.pdf.PdfWriter,
     *      com.itextpdf.text.Document)
     */
    public void onCloseDocument(PdfWriter writer, Document document) {
    	
        // 7.最后一步了，就是关闭文档的时候，将模板替换成实际的 Y 值,至此，page x of y 制作完毕，完美兼容各种文档size。
        total.beginText();
        total.setFontAndSize(bf, presentFontSize);// 生成的模版的字体、颜色
        String foot2 = " " + (writer.getPageNumber()) + " 页";
        total.showText(foot2);// 模版显示的内容
        total.endText();
        total.closePath();
        
    }
}
