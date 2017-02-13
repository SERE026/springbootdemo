package com.first.util;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.AcroFields;
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

	public static void createPdf(String src, String dest) {
		try{
			// step 1
			Document document = new Document();
			// step 2
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream(dest));
			// step 3
			document.open();
			// step 4
			XMLWorkerHelper.getInstance().parseXHtml(writer, document,
					new FileInputStream(src), Charset.forName("UTF-8"));
			// step 5
			document.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 重写 字符设置方法，解决中文乱码问题
	 * 
	 */
	public static class MyFontsProvider extends XMLWorkerFontProvider {

		public MyFontsProvider() {
			super(null, null);
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

	private static Logger logger = LoggerFactory.getLogger(PDFUtil.class);

	/**
	 * PDF生成路径
	 */
	public static final String PDF_DOWNLOAD_PATH = "/trialRecord/pdf/";

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
	
	public static void exportPdf(String src,String dest){
		
		Document document = new Document();
		
		PdfWriter writer;
		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
		

			document.open();
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
					.parseXHtml(writer, document, new FileInputStream(src), new FileInputStream(src.replace(".html", ".css")),
							Charset.forName("UTF-8"), fontProvider);
	
			document.close();
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
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

}

