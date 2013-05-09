package org.cweili.htmlcompressor;

import java.io.CharArrayWriter;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * 
 * @author Cweili
 * @version 2013-5-8 下午5:37:27
 * 
 */
public class CharResponseWrapper extends HttpServletResponseWrapper {

	private CharArrayWriter charArrayWriter;

	public CharResponseWrapper(HttpServletResponse response) {
		super(response);
		charArrayWriter = new CharArrayWriter();
	}

	@Override
	public PrintWriter getWriter() {
		return new PrintWriter(charArrayWriter);
	}

	@Override
	public String toString() {
		return charArrayWriter.toString();
	}

	public CharArrayWriter getCharArrayWriter() {
		return charArrayWriter;
	}

}
