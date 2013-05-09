package org.cweili.htmlcompressor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.htmlcompressor.compressor.HtmlCompressor;

/**
 * 
 * @author Cweili
 * @version 2013-5-8 下午5:39:48
 * 
 */
public class HtmlCompressorFilter implements Filter {

	// default settings
	private boolean removeComments = true;
	private boolean removeMultiSpaces = true;
	private boolean removeIntertagSpaces = true;
	private boolean simpleDoctype = true;
	private boolean removeFormAttributes = true;

	// optional settings
	private boolean removeQuotes = false;
	private boolean compressJavaScript = false;
	private boolean compressCss = false;
	private boolean removeScriptAttributes = false;
	private boolean removeStyleAttributes = false;
	private boolean removeLinkAttributes = false;
	private boolean removeInputAttributes = false;
	private boolean simpleBooleanAttributes = false;
	private boolean removeJavaScriptProtocol = false;
	private boolean removeHttpProtocol = false;
	private boolean removeHttpsProtocol = false;
	private boolean preserveLineBreaks = false;
	private String removeSurroundingSpaces = null;
	private boolean generateStatistics = false;

	// YUICompressor settings
	private boolean yuiJsNoMunge = false;
	private boolean yuiJsPreserveAllSemiColons = false;
	private boolean yuiJsDisableOptimizations = false;
	private int yuiJsLineBreak = -1;
	private int yuiCssLineBreak = -1;

	/*
	 * （non-Javadoc）
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.removeComments = setBooleanInitParameter(
				filterConfig.getInitParameter("removeComments"), this.removeComments);
		this.removeMultiSpaces = setBooleanInitParameter(
				filterConfig.getInitParameter("removeMultiSpaces"), this.removeMultiSpaces);
		this.removeIntertagSpaces = setBooleanInitParameter(
				filterConfig.getInitParameter("removeIntertagSpaces"), this.removeIntertagSpaces);
		this.simpleDoctype = setBooleanInitParameter(
				filterConfig.getInitParameter("simpleDoctype"), this.simpleDoctype);
		this.removeFormAttributes = setBooleanInitParameter(
				filterConfig.getInitParameter("removeFormAttributes"), this.removeFormAttributes);

		this.removeQuotes = setBooleanInitParameter(filterConfig.getInitParameter("removeQuotes"),
				this.removeQuotes);
		this.compressJavaScript = setBooleanInitParameter(
				filterConfig.getInitParameter("compressJavaScript"), this.compressJavaScript);
		this.compressCss = setBooleanInitParameter(filterConfig.getInitParameter("compressCss"),
				this.compressCss);
		this.removeScriptAttributes = setBooleanInitParameter(
				filterConfig.getInitParameter("removeScriptAttributes"),
				this.removeScriptAttributes);
		this.removeStyleAttributes = setBooleanInitParameter(
				filterConfig.getInitParameter("removeStyleAttributes"), this.removeStyleAttributes);
		this.removeLinkAttributes = setBooleanInitParameter(
				filterConfig.getInitParameter("removeLinkAttributes"), this.removeLinkAttributes);
		this.removeInputAttributes = setBooleanInitParameter(
				filterConfig.getInitParameter("removeInputAttributes"), this.removeInputAttributes);
		this.simpleBooleanAttributes = setBooleanInitParameter(
				filterConfig.getInitParameter("simpleBooleanAttributes"),
				this.simpleBooleanAttributes);
		this.removeJavaScriptProtocol = setBooleanInitParameter(
				filterConfig.getInitParameter("removeJavaScriptProtocol"),
				this.removeJavaScriptProtocol);
		this.removeHttpProtocol = setBooleanInitParameter(
				filterConfig.getInitParameter("removeHttpProtocol"), this.removeHttpProtocol);
		this.removeHttpsProtocol = setBooleanInitParameter(
				filterConfig.getInitParameter("removeHttpsProtocol"), this.removeHttpsProtocol);
		this.preserveLineBreaks = setBooleanInitParameter(
				filterConfig.getInitParameter("preserveLineBreaks"), this.preserveLineBreaks);
		this.generateStatistics = setBooleanInitParameter(
				filterConfig.getInitParameter("generateStatistics"), this.generateStatistics);

		this.yuiJsNoMunge = setBooleanInitParameter(filterConfig.getInitParameter("yuiJsNoMunge"),
				this.yuiJsNoMunge);
		this.yuiJsPreserveAllSemiColons = setBooleanInitParameter(
				filterConfig.getInitParameter("yuiJsPreserveAllSemiColons"),
				this.yuiJsPreserveAllSemiColons);
		this.yuiJsDisableOptimizations = setBooleanInitParameter(
				filterConfig.getInitParameter("yuiJsDisableOptimizations"),
				this.yuiJsDisableOptimizations);

		this.yuiJsLineBreak = setIntegerInitParameter(
				filterConfig.getInitParameter("yuiJsLineBreak"), this.yuiJsLineBreak);
		this.yuiCssLineBreak = setIntegerInitParameter(
				filterConfig.getInitParameter("yuiCssLineBreak"), this.yuiCssLineBreak);

	}

	/*
	 * （non-Javadoc）
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		if ("GET".equalsIgnoreCase((httpRequest.getMethod()))) {
			CharResponseWrapper charResponse = new CharResponseWrapper(
					(HttpServletResponse) response);
			filterChain.doFilter(request, charResponse);
			String before;
			try {
				before = charResponse.getCharArrayWriter().toString();
			} catch (Exception e) {
				return;
			}
			if (null != before && !"".equals(before)) {
				HtmlCompressor hc = new HtmlCompressor();
				hc.setRemoveComments(removeComments);
				hc.setRemoveMultiSpaces(removeMultiSpaces);
				hc.setRemoveIntertagSpaces(removeIntertagSpaces);
				hc.setRemoveQuotes(removeQuotes);
				hc.setCompressJavaScript(compressJavaScript);
				hc.setCompressCss(compressCss);
				hc.setSimpleDoctype(simpleDoctype);
				hc.setRemoveScriptAttributes(removeScriptAttributes);
				hc.setRemoveStyleAttributes(removeStyleAttributes);
				hc.setRemoveLinkAttributes(removeLinkAttributes);
				hc.setRemoveFormAttributes(removeFormAttributes);
				hc.setRemoveInputAttributes(removeInputAttributes);
				hc.setSimpleBooleanAttributes(simpleBooleanAttributes);
				hc.setRemoveJavaScriptProtocol(removeJavaScriptProtocol);
				hc.setRemoveHttpProtocol(removeHttpProtocol);
				hc.setRemoveHttpsProtocol(removeHttpsProtocol);
				hc.setPreserveLineBreaks(preserveLineBreaks);
				hc.setRemoveSurroundingSpaces(removeSurroundingSpaces);
				hc.setGenerateStatistics(generateStatistics);
				hc.setYuiJsNoMunge(yuiJsNoMunge);
				hc.setYuiJsPreserveAllSemiColons(yuiJsPreserveAllSemiColons);
				hc.setYuiJsDisableOptimizations(yuiJsDisableOptimizations);
				hc.setYuiJsLineBreak(yuiJsLineBreak);
				hc.setYuiCssLineBreak(yuiCssLineBreak);
				String compressed = hc.compress(before);
				response.getWriter().write(compressed);
			}
		} else {
			filterChain.doFilter(request, response);
		}
	}

	/*
	 * （non-Javadoc）
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {

	}

	private boolean setBooleanInitParameter(String parameterValue, boolean defaultValue) {
		if (null != parameterValue) {
			try {
				return "true".equalsIgnoreCase(parameterValue);
			} catch (Exception e) {
				return defaultValue;
			}
		}
		return defaultValue;
	}

	private int setIntegerInitParameter(String parameterValue, int defaultValue) {
		if (null != parameterValue) {
			try {
				return Integer.parseInt(parameterValue);
			} catch (Exception e) {
				return defaultValue;
			}
		}
		return defaultValue;
	}

}
