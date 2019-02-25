package com.lzcc.util;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class CharEncodingFilter implements Filter {
	private String oldEncoding = "iso-8859-1";
	private String newEncoding = "utf-8";

	@Override
	public void destroy() {
		System.out.println("字符编码过滤器被注销...");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// 向下转型得到request对象
		HttpServletRequest request = (HttpServletRequest) req;
		// 判断request的提交方式
		if (request.getMethod().equalsIgnoreCase("post")) {
			// post方式提交
			request.setCharacterEncoding(newEncoding);
		} else {
			// get方式提交
			@SuppressWarnings("unchecked")
			Map<String, String[]> map = request.getParameterMap();
			Set<Entry<String, String[]>> entrySet = map.entrySet();
			for (Entry<String, String[]> entry : entrySet) {
				// 有可能是复选框提交（存在多个值），因此使用String[]
				String[] value = entry.getValue();
				for (int i = 0; i < value.length; i++) {
					value[i] = new String(value[i].getBytes(oldEncoding),
							newEncoding);
				}
			}
		}

		// 继续向后过滤
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println("字符编码过滤器被启动...");
		String tmpEncoding = config.getInitParameter("newEncoding");
		if (tmpEncoding != null && tmpEncoding.length() > 0) {
			newEncoding = tmpEncoding;
		}
	}
}
