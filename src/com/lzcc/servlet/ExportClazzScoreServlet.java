package com.lzcc.servlet;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Comparator;
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

import com.lzcc.dao.impl.ScoreDao;
import com.lzcc.po.Score;
import com.lzcc.util.ExportExcel;

public class ExportClazzScoreServlet extends HttpServlet {
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
		String tId = request.getParameter("tcctId");
		String clazzName = request.getParameter("clazzName");
		String courseName = request.getParameter("courseName");
		String mx = request.getParameter("max");
		String mn = request.getParameter("min");
		String ag = request.getParameter("avg");
		String term = request.getParameter("term");

		Integer tcctId = null;
		Integer min = null;
		Integer avg = null;
		Integer max = null;
		if (tId != null && ag != null && mn != null && mx != null) {
			tcctId = Integer.parseInt(tId);
			min = Integer.parseInt(mn);
			avg = Integer.parseInt(ag);
			max = Integer.parseInt(mx);
		}
		String whereCondition = " tcctId = ?";
		Object[] values = { tcctId };
		List<Score> scorelist = scoreDao.getListByConditionString(
				whereCondition, values);
		Collections.sort(scorelist, new Comparator<Score>() {
			@Override
			public int compare(Score score1, Score score2) {
				return (score2.getScore() + "").compareTo(score1.getScore()
						+ "");
			}
		});
		int[] rank = null;
		if (scorelist != null) {
			rank = new int[scorelist.size()];
			for (int i = 0; i < rank.length; i++) {
				rank[i] = i + 1;
			}
		}
		String fileName = term + "-" + clazzName + "-" + courseName + ".xls";
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
		String worksheetTitle = term + "-" + clazzName + "-" + courseName
				+ "成绩单";

		HSSFWorkbook wb = new HSSFWorkbook();

		// 创建单元格样式
		HSSFCellStyle cellStyleTitle = wb.createCellStyle();
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
		// ------------------------------------------------------------------
		// 设置单元格字体
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setFontHeight((short) 200);
		cellStyleTitle.setFont(font);

		// 工作表名
		String name = "姓名";
		String se = "成绩";
		String rk = "名次";

		HSSFSheet sheet = wb.createSheet();
		ExportExcel exportExcel = new ExportExcel(wb, sheet);
		// 创建报表头部
		exportExcel.createNormalHead(worksheetTitle, 4);
		// 定义第一行
		HSSFRow row1 = sheet.createRow(1);

		HSSFCell cell1 = row1.createCell(0);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue("  ");

		cell1 = row1.createCell(1);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString(name));
		// 第一行第er列
		cell1 = row1.createCell(2);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString(se));

		// 第一行第san列
		cell1 = row1.createCell(3);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString(rk));

		// 定义第二行
		HSSFRow row = sheet.createRow(2);
		HSSFCell cell = row.createCell(1);
		Score score = new Score();
		for (int i = 0; i < scorelist.size(); i++) {
			score = scorelist.get(i);
			row = sheet.createRow(i + 2);

			cell = row.createCell(1);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(new HSSFRichTextString(score.getUser()
					.getUserName()));

			cell = row.createCell(2);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(new HSSFRichTextString(score.getScore() + ""));

			cell = row.createCell(3);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(new HSSFRichTextString(rank[i] + ""));

		}
		// 定义第三行
		HSSFRow row3 = sheet.createRow(scorelist.size()+2);
		HSSFCell cell3 = row3.createCell(1);
		
		cell3 = row3.createCell(1);
		cell3.setCellStyle(cellStyle);
		cell3.setCellValue(new HSSFRichTextString("最高分："+max+" "));

		cell3 = row3.createCell(2);
		cell3.setCellStyle(cellStyle);
		cell3.setCellValue(new HSSFRichTextString("最低分："+min+""));

		cell3 = row3.createCell(3);
		cell3.setCellStyle(cellStyle);
		cell3.setCellValue(new HSSFRichTextString("平均分："+avg+""));
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
