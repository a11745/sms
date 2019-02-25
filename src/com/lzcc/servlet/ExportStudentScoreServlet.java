package com.lzcc.servlet;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.Region;

import com.lzcc.dao.impl.ScoreDao;
import com.lzcc.po.Score;
import com.lzcc.po.User;
import com.lzcc.util.ExportExcel;

@SuppressWarnings("deprecation")
public class ExportStudentScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ScoreDao scoreDao = new ScoreDao();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(req, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String tid = request.getParameter("termId");
		String term = request.getParameter("term");
		int termId = 0;
		if(tid != null)
		{
			termId= Integer.parseInt(tid);
		}
		User user = (User) request.getSession().getAttribute("user");
		String whereCondition = "  userId = ?";
		Object[] values = {user.getId() };
		List<Score> scorelist = scoreDao.getListByConditionString(whereCondition,values);
	    Iterator<Score> iterator = scorelist.iterator();  
	    //剔除不属于这一学期的成绩
	    while(iterator.hasNext()){  
	    	Score s = iterator.next();  
	        if(s.getTcct().getTerm().getId()!= termId){  
	        	iterator.remove();  
	        }  
	    }  	
		int total = 0;
		for (int i = 0; i < scorelist.size(); i++) {
			
			total += scorelist.get(i).getScore();
		}
		String fileName = term + "-" + user .getUserName()+ ".xls";
		fileName = new String(fileName.getBytes("GBK"), "iso8859-1");
		response.reset();
		response.setHeader("Content-Disposition", "attachment;filename="
				+ fileName);// 指定下载的文件名
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		OutputStream output = response.getOutputStream();
		BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);

		// 定义单元格报头
		String worksheetTitle = term + "-" + user .getUserName()+"-"+ "成绩单";

		HSSFWorkbook wb = new HSSFWorkbook();

		// 创建单元格样式
		HSSFCellStyle cellStyleTitle = wb.createCellStyle();
		// 设置单元格字体
		HSSFFont font = wb.createFont();
		HSSFFont font2 = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setFontHeight((short)210);
		font2.setFontHeight((short)205);
		cellStyleTitle.setFont(font);

		cellStyleTitle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		cellStyleTitle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		cellStyleTitle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		cellStyleTitle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		// 指定单元格居中对齐
		cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 指定单元格垂直居中对齐
		cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 指定当单元格内容显示不下时自动换行
		cellStyleTitle.setWrapText(true);
		// ------------------------------------------------------------------
		HSSFCellStyle cellStyle = wb.createCellStyle();
		// 指定单元格居中对齐
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		// 指定单元格垂直居中对齐
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 指定当单元格内容显示不下时自动换行
		cellStyle.setWrapText(true);
		cellStyle.setFont(font2);
		// ------------------------------------------------------------------
	
		// 工作表名
		String name = "课程";
		String se = "成绩";
		HSSFSheet sheet = wb.createSheet();
		ExportExcel exportExcel = new ExportExcel(wb, sheet);
		// 创建报表头部
		exportExcel.createNormalHead(worksheetTitle, 3);
		// 定义第一行
		HSSFRow row1 = sheet.createRow(1);


		HSSFCell cell1 = row1.createCell(1);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString(name));
		// 第一行第er列
		cell1 = row1.createCell(2);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString(se));


		// 定义第二行
		HSSFRow row = sheet.createRow(2);
		HSSFCell cell = row.createCell(1);
		Score score = new Score();
		for (int i = 0; i < scorelist.size(); i++) {
			score = scorelist.get(i);
			row = sheet.createRow(i + 2);

			cell = row.createCell(1);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(new HSSFRichTextString(score.getTcct().getCourse().getCourseName()));

			cell = row.createCell(2);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(new HSSFRichTextString(score.getScore() + ""));

		}
		
		//起始行、起始列、结束行、结束列
		sheet.addMergedRegion(new Region(scorelist.size()+2, (short) (1), scorelist.size()+2,      
                (short) (2)));     
		HSSFRow row3 = sheet.createRow(scorelist.size()+2);
        HSSFCell cell3 = row3.createCell(1); 
        cell3.setCellValue(new HSSFRichTextString("本学期总分："+total+" ")); // 跨单元格显示的数据      
        cell3.setCellStyle(cellStyle);
        cell3 = row3.createCell(2);
        cell3.setCellStyle(cellStyle);
        // 样式      
//		HSSFRow row3 = sheet.createRow(scorelist.size()+2);
//		HSSFCell cell3 = row3.createCell(1);
//		cell3 = row3.createCell(1);
//		cell3.setCellStyle(cellStyle);
//		cell3.setCellValue(new HSSFRichTextString("本学期总成绩："+total+" "));
		try {
			bufferedOutPut.flush();
			wb.write(bufferedOutPut);
			bufferedOutPut.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Output   is   closed ");
		} finally {
			scorelist.clear();
		}
	}
	
}
