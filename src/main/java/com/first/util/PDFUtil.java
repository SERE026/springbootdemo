package com.first.util;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;

public class PDFUtil {
	
	private static Logger logger = LoggerFactory.getLogger(PDFUtil.class);

	/**
	 * PDF生成路径
	 */
	public static final String PDF_DOWNLOAD_PATH = "/trialRecord/pdf/";
	/********水印图片*******/
	public static final String IMG = "E:\\银承猫上线记录\\crontract\\mb\\official_seal.png";
	
	public static void main(String[] args) {
		
		String src = "E:\\银承猫上线记录\\crontract\\"; //模板地址
		String dest = "E:\\银承猫上线记录\\crontract\\m_hero"; //生成地址
		
		createWaterPdf(src,dest,null);
//		File file = new File(dest+"_temp");
//		file.delete();
	}
	
	/**
	 * 根据pdf模板填充相应的值： 1，如果是根据excel填充的话，在用Acrobat生成PDF模板前，
	 * Excel单元格格式最好设置成文本，否则pdf填充值时可能中文无法显示
	 */
	public static byte[] fromPDFTempletToPdfWithValue(Map<String, String> map,
			String src) {
		ByteArrayOutputStream bos = null;
		PdfStamper ps = null;
		Document document = null;
		byte[] result = null;
		try {
			PdfReader reader = new PdfReader(src);
			bos = new ByteArrayOutputStream();
			ps = new PdfStamper(reader, bos);
			/**
			 * 使用中文字体 如果是利用 AcroFields填充值的不需要在程序中设置字体，在模板文件中设置字体为中文字体就行了
			 */
			// BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
			// BaseFont.NOT_EMBEDDED);
			// Font font = new Font(bf, 12, Font.NORMAL);
			AcroFields s = ps.getAcroFields(); // 读取文本域
			Set<String> keys = map.keySet();// map里保存要填入的数据，key和文本域的name一样
			for (String key : keys) {
				s.setField(key, map.get(key));
			}
			document = new Document();
			document.open();
			ps.setFormFlattening(true);
			ps.close();
			result = bos.toByteArray();
			bos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			document.close();
			try {
				ps.close();
				bos.close();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;// 返回一个直接数组
	}

	
	/**
	 * 读取模板文件内容
	 * @param path
	 * @return
	 */
	public static String getFileContent(String path){
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(path), "UTF-8"));
			String ss = "";
			String t = "";
			while ((t = br.readLine()) != null) {
				// System.out.println(t);
				ss += t;
			}
			return ss;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 处理模板中待填充的数据
	 * @param params  待替换的参数列表
	 * @param htmlPath html模板 路径
	 * @return
	 */
	public static String getFieldContent(Map<String,Object> params,String htmlPath){
		String content = getFileContent(htmlPath);
		if(params == null) return content;
		List<String> keys = new ArrayList<String>(params.keySet());
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			Object obj = params.get(key);
			String value = obj != null ? obj.toString() : "";
			content = content.replace("{"+key+"}", value);
		}
		return content;
	}
	
	/**
	 * 根据路径创建pdf
	 * @param src
	 * @param dest
	 * @param params
	 */
	public static void createPdfForPath(String src,String dest,Map<String,Object> params){
		
		src = src+"/mb/mb.html";
		Document document = new Document();
		
		PdfWriter writer;
		try {
			
			writer = PdfWriter.getInstance(document, new FileOutputStream(dest+".pdf"));
			
			//waterMark(dest+".pdf", imageFile, outputFile, waterMarkName);
			PDFBuilder builder = new PDFBuilder();
			writer.setPageEvent(builder);
			
			generatePdf(src, params, document, writer);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param htmlPath  html
	 * @param dest
	 * @param params
	 */
	public static void createWaterPdf(String htmlPath,String dest,Map<String,Object> params){
		
		htmlPath = htmlPath+"/mb/mb.html";
		Document document = new Document();
		
		PdfWriter writer;
		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(dest+"_temp"));
			// 使用我们的字体提供器，并将其设置为unicode字体样式
			generatePdf(htmlPath, params, document, writer);
			
			waterPdfMark(dest+"_temp", dest+".pdf", IMG);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void generatePdf(String htmlPath, Map<String, Object> params, Document document, PdfWriter writer)
			throws FileNotFoundException, IOException, UnsupportedEncodingException {
		MyFontsProvider fontProvider = MyFontsProvider.getInstance();
		//fontProvider.addFontSubstitute("lowagie", "garamond");
		fontProvider.setUseUnicode(true);
		
		CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
		HtmlPipelineContext htmlContext = new HtmlPipelineContext(
				cssAppliers);
		htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
		htmlContext.autoBookmark(false);
		XMLWorkerHelper.getInstance().getDefaultCssResolver(true);
		
		File file = new File(htmlPath.replace(".html", ".css"));
		FileInputStream cssFile = null;
		if(file.exists()){
			cssFile = new FileInputStream(file);
		}
		
		//处理模板中的参数
		String content = getFieldContent(params, htmlPath);
		PDFBuilder builder = new PDFBuilder();
		writer.setPageEvent(builder);
		
		//必须先设置水印再打开
		document.open(); 
		
		XMLWorkerHelper.getInstance()
				.parseXHtml(writer, document, new ByteArrayInputStream(content.getBytes("Utf-8")), cssFile,
						Charset.forName("UTF-8"), fontProvider);
		writer.flush();
		document.close();
		writer.close();
	}
	
	
	/**
	 * 导出PDF文件
	 * 
	 * @param content
	 * @param response
	 */
	public void exportPdf(String fileName, String content,
			HttpServletResponse response) {

		FileOutputStream fos = null;
		FileInputStream in = null;
		OutputStream out = null;
		Document document = new Document();
		File newPath = null;
		try {
			if (StringUtils.isBlank(fileName)) {
				fileName = "文件名.pdf";
			}
			fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
			String dicPath = new File(".").getCanonicalPath();
			String srcPath = dicPath + PDF_DOWNLOAD_PATH + fileName;

			newPath = new File(dicPath + PDF_DOWNLOAD_PATH);
			newPath.mkdirs();
			// 删除临时文件
			boolean success = fileDelete(newPath);

			if (success) {
				newPath.mkdirs();
				File file = new File(srcPath);
				fos = new FileOutputStream(file);

				PdfWriter writer = PdfWriter.getInstance(document, fos);

				document.open();
				InputStream htmlInput = new ByteArrayInputStream(
						content.getBytes("UTF-8"));
				// 使用我们的字体提供器，并将其设置为unicode字体样式
				MyFontsProvider fontProvider = new MyFontsProvider();
				fontProvider.addFontSubstitute("lowagie", "garamond");
				fontProvider.setUseUnicode(true);
				
				CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
				HtmlPipelineContext htmlContext = new HtmlPipelineContext(
						cssAppliers);
				htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
				XMLWorkerHelper.getInstance().getDefaultCssResolver(true);

				XMLWorkerHelper.getInstance()
						.parseXHtml(writer, document, htmlInput, null,
								Charset.forName("UTF-8"), fontProvider);

				document.close();
				writer.close();
				// 设置文件ContentType类型，这样设置，会自动判断下载文件类型
				response.setContentType("multipart/form-data");
				// 设置响应头，控制浏览器下载该文件
				response.setHeader("content-disposition",
						"attachment;filename=" + fileName);
				// 读取要下载的文件，保存到文件输入流
				in = new FileInputStream(srcPath);
				// 创建输出流
				out = response.getOutputStream();
				// 创建缓冲区
				byte buffer[] = new byte[1024];
				int len = 0;
				// 循环将输入流中的内容读取到缓冲区当中
				while ((len = in.read(buffer)) > 0) {
					// 输出缓冲区的内容到浏览器，实现文件下载
					out.write(buffer, 0, len);
				}
			}
		} catch (DocumentException e) {
			logger.error("Export PDF error :" + e.getMessage());
			throw new RuntimeException("Export PDF error : ", e);
		} catch (IOException e) {
			logger.error("Export PDF error :" + e.getMessage());
			throw new RuntimeException("Export PDF error : ", e);
		} catch (Exception e) {
			logger.error("Export PDF error :" + e.getMessage());
			throw new RuntimeException("Export PDF error : ", e);
		} finally {
			try{
			fos.close();
			in.close();
			out.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			if (newPath != null) {
				fileDelete(newPath);
			}
		}
	}
	
	
	/** 
    * 给pdf文件添加水印 
    * @param InPdfFile 要加水印的原pdf文件路径 
    * @param outPdfFile 加了水印后要输出的路径 
    * @param markImagePath 水印图片路径 
    * @throws Exception 
    */  
	public static void waterPdfMark(String InPdfFile, String outPdfFile, String markImagePath)
			throws Exception {

		PdfReader reader = new PdfReader(InPdfFile);

		PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(outPdfFile));
		
		int pageSize = reader.getNumberOfPages();

		Image img = Image.getInstance(markImagePath);// 插入水印

		img.setAbsolutePosition(150, 100);

		//for (int i = 1; i <= pageSize; i++) {
			//PdfContentByte under = stamp.getUnderContent(i);
			PdfContentByte over = stamp.getOverContent(1);
			over.beginText();
			//addImage(image, image_width, 0, 0, image_height, x, y) 
			//content.addImage(image, 150, 0, 0, 150, 100, y);.
			PdfGState gs1 = new PdfGState();
			gs1.setFillOpacity(0.8f);
			over.setGState(gs1);
			Rectangle rect = reader.getPageSizeWithRotation(1);
			//over.addImage(img);
			over.addImage(img,150,0,0,150,170,200);
			over.endText();
			
			rect = reader.getPageSizeWithRotation(pageSize);
			over = stamp.getOverContent(pageSize);
			over.beginText();
			over.setGState(gs1);
			//over.addImage(img);
			over.addImage(img,150,0,0,150,180,570);
			over.endText();
		//}

		stamp.close();// 关闭
		reader.close();

		File tempfile = new File(InPdfFile);
		if (tempfile.isFile() && tempfile.exists()) {
			tempfile.delete();
		}

	}
	
	/**
	 * 删除文件
	 * 
	 * @param file
	 * @return
	 */
	private boolean fileDelete(File file) {
		if (file.isDirectory()) {
			String[] children = file.list();
			// 递归删除目录中的子目录下
			for (int i = 0; i < children.length; i++) {
				boolean success = fileDelete(new File(file, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// 目录此时为空，可以删除
		return file.delete();
	}
	
	/**
	 * 重写 字符设置方法，解决中文乱码问题
	 * 
	 */
	public static class MyFontsProvider extends XMLWorkerFontProvider {
		
		private static MyFontsProvider fontsProvider;
		
		private MyFontsProvider() {
			//TODO 第一个参数 字体所在目录 添加itext-asian.jar 即可，包括大部分字体
			//super("/fonts/", null);
			super(null,null);
		}
		
		public static synchronized MyFontsProvider getInstance(){
			
			if(fontsProvider == null){
				fontsProvider = new MyFontsProvider();
			}
			return fontsProvider;
		}

		@Override
		public Font getFont(final String fontname, String encoding, float size,
				final int style) {
			String fntname = fontname;
			if (fntname == null) {
				fntname = "宋体";
			}
			if (size == 0) {
				size = 4;
			}
			return super.getFont(fntname, encoding, size, style);
		}
	}

}

