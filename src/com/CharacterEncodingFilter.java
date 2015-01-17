package com;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {

	protected String encoding = null;// ��������ʽ����
	protected FilterConfig filterConfig = null;// ������������ö���

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig; // ��ʼ�����������ö���
		this.encoding = filterConfig.getInitParameter("encoding");// ��ȡ�����ļ���ָ���ı����ʽ
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (encoding != null) {
			request.setCharacterEncoding(encoding); // ��������ı���
			response.setContentType("text/html; charset=" + encoding);// ����Ӧ�������������ͣ����������ʽ��
		}
		chain.doFilter(request, response); // ���ݸ���һ��������
	}

	public void destroy() {
		this.encoding = null;
		this.filterConfig = null;
	}
}
