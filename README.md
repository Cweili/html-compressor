HTML Compressor
===========

A filter implements htmlcompressor(http://code.google.com/p/htmlcompressor/).

Dependency
-----------
Javascript, CSS Compress:
[YUI Compressor](https://github.com/yui/yuicompressor)

Examples
-----------

web.xml
```xml
	<filter>
		<filter-name>HtmlCompressorFilter</filter-name>
		<filter-class>org.cweili.htmlcompressor.HtmlCompressorFilter</filter-class>
		<init-param>
			<param-name>compressJavaScript</param-name>
			<param-value>true</param-value>
		</init-param>
		<init-param>
			<param-name>compressCss</param-name>
			<param-value>true</param-value>
		</init-param>
	</filter>
	<filter-mapping>
		<filter-name>HtmlCompressorFilter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>
```