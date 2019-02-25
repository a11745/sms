package com.lzcc.util;

import java.io.Serializable;
/**
 * 分页工具类
 *
 *
 */
@SuppressWarnings("serial")
public class Page implements Serializable {
	private int pageNo = 1;
	private int pageSize =10;
	private int displayPageNumber = 10;
	private int recordCount=0; // 总记录数
	private int pageCount; // 共多少页
	private String url=null; // 分页跳转的目标url
	private Object data=null; // 分页后的数据(List,Map,Array)
	@SuppressWarnings("unused")
	private String html; // 最后在页面上生成的html标签信息
	
	private static String pagerInfoStringToo = "每页<span>{pageSize}</span>条记录/共<span>{recordCount}</span>条记录"
		+ "&nbsp;第<span>{pageNo}</span>页/共<span>{pageCount}</span>页";
	
	// 开头是否有省略号
	private boolean hasPreOmit = false;
	// 结尾是否有省略号
	private boolean hasSuffixOmit = false;
	// 页面下标
	private int[] pagenums = new int[0];

	public int getPageNo() {
		if (pageNo < 1)
			return 1;
		if (pageNo > getPageCount())
			return getPageCount();
		return pageNo;
	}


	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}


	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getPageCount() {
		pageCount = recordCount % pageSize == 0 ? recordCount / pageSize
				: recordCount / pageSize + 1;
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getHtml() {
		if (pageNo>pageCount) {
			pageNo = pageCount;
		}
		if (pageNo==0) {
			pageNo = 1;
		}
		int index = pageNo;

		int count = pageCount = recordCount % pageSize == 0 ? recordCount
				/ pageSize : recordCount / pageSize + 1;

		if (count <= displayPageNumber) {

			int[] pageIndexes = new int[count];

			for (int i = 1; i <= count; i++) {
				pageIndexes[i - 1] = i;
			}
			pagenums = pageIndexes;
			hasPreOmit = false;
			hasSuffixOmit = false;
		} else if (pageNo - displayPageNumber / 2 > 0
				&& pageNo + displayPageNumber / 2 < count) {
			int[] pageIndexes = new int[displayPageNumber];
			for (int i = pageNo - displayPageNumber / 2, _index = 0; i < pageNo
					+ displayPageNumber / 2; i++, _index++) {
				pageIndexes[_index] = i;
			}
			pagenums = pageIndexes;
			hasPreOmit = true;
			hasSuffixOmit = true;
		} else if (pageNo - displayPageNumber / 2 > 0
				&& pageNo + displayPageNumber / 2 >= count) {
			int[] pageIndexes = new int[displayPageNumber];
			for (int i = count - displayPageNumber + 1, _index = 0; i <= pageCount; i++, _index++) {
				pageIndexes[_index] = i;
			}
			pagenums = pageIndexes;
			hasPreOmit = true;
			hasSuffixOmit = false;
		} else {
			int[] pageIndexes = new int[displayPageNumber];
			for (int i = 1; i <= displayPageNumber; i++) {
				pageIndexes[i - 1] = i;
			}
			pagenums = pageIndexes;
			hasPreOmit = false;
			hasSuffixOmit = true;
		}

		String pagerInfoString = "<div><input type='hidden' name='asyn' value='true'><a href=\"" + url
				+ "?pno=1\" style=\"color:#3399CC\" >首页</a><a href=\"" + url + "?pno=" + (pageNo - 1)
				+ "\" style=\"color:#3399CC\" >上一页</a>";

		if (hasPreOmit) {
			pagerInfoString = pagerInfoString
					+ "<span style='padding:2px 4px;'><a>...</a></span>";
		}
		for (int i = 0; i < pagenums.length; i++) {

			if (index == pagenums[i]) {
				pagerInfoString = pagerInfoString
						+ "<span><a class=\"current\" style=\"color:#3399CC\">" + "&nbsp;"
						+ pagenums[i] + "&nbsp;" + "</a></span>";
			} else {
				pagerInfoString = pagerInfoString + "<a href=\"" + url
						+ "?pno=" + pagenums[i] + "\" style=\"color:#3399CC\">" + "&nbsp;"
						+ pagenums[i] + "&nbsp;" + "</a>";
			}
		}

		if (hasSuffixOmit) {
			pagerInfoString = pagerInfoString
					+ "<span style='padding:2px 4px;'><a>...</a></span>";
		}
		pagerInfoString = pagerInfoString + "<a href=\"" + url + "?pno="
				+ (pageNo + 1) + "\" style=\"color:#3399CC\">下一页</a><a href=\"" + url + "?pno="
				+ count + "\" style=\"color:#3399CC\">尾页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		pagerInfoString = pagerInfoString +pagerInfoStringToo+ "&nbsp;&nbsp;&nbsp;&nbsp;" +
				"&nbsp;&nbsp;&nbsp;</div>";
		/*pagerInfoString = pagerInfoString +"</form>";*/
		String htmlString = "";
		int previousPage = getPageNo() <= 1 ? getPageNo() : getPageNo() - 1;
		int nextPage = getPageNo() >= getPageCount() ? getPageCount()
				: getPageNo() + 1;

		htmlString += pagerInfoString;
		htmlString = htmlString
				.replace("{previousPage}", String.valueOf(previousPage))
				.replace("{nextPage}", String.valueOf(nextPage))
				.replace("{lastPage}", String.valueOf(getPageCount()))
				.replace("{pageSize}", String.valueOf(pageSize))
				.replace("{pageCount}", String.valueOf(pageCount))
				.replace("{recordCount}", String.valueOf(recordCount))
				.replace("{pageNo}", String.valueOf(getPageNo()))
				.replace("{url}", getUrl());

		return htmlString;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
