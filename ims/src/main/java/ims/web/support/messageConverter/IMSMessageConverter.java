/**
 * @Project:ims
 * @Title:IMSMessageConverter.java
 * @Author:Riozenc
 * @Datetime:2017年7月30日 下午5:51:48
 * 
 */
package ims.web.support.messageConverter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import com.riozenc.quicktool.common.util.json.JSONUtil;
import com.riozenc.quicktool.common.util.xml.XmlUtils;

public class IMSMessageConverter extends AbstractHttpMessageConverter<Object> {

	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	public IMSMessageConverter() {
		this(DEFAULT_CHARSET);
	}

	/**
	 * A constructor accepting a default charset to use if the requested content
	 * type does not specify one.
	 */
	public IMSMessageConverter(Charset defaultCharset) {
		super(defaultCharset, MediaType.TEXT_PLAIN, MediaType.ALL);
	}

	@Override
	public void setSupportedMediaTypes(List<MediaType> supportedMediaTypes) {
		// TODO Auto-generated method stub
		List<MediaType> list = new ArrayList<MediaType>();
		list.add(MediaType.APPLICATION_JSON_UTF8);
		list.add(MediaType.APPLICATION_XML);
		super.setSupportedMediaTypes(list);
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		// TODO Auto-generated method stub

		System.out.println(clazz);

		return null;
	}

	@Override
	protected void writeInternal(Object t, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		// TODO Auto-generated method stub
		Charset charset = getContentTypeCharset(outputMessage.getHeaders().getContentType());
		if (outputMessage.getHeaders().getContentType().isCompatibleWith(MediaType.APPLICATION_JSON_UTF8)) {
			// json
			StreamUtils.copy(JSONUtil.toJsonString(t), charset, outputMessage.getBody());
		} else if (outputMessage.getHeaders().getContentType().isCompatibleWith(MediaType.APPLICATION_XML)) {
			// xml
			StreamUtils.copy(XmlUtils.object2xml(t), charset, outputMessage.getBody());
		} else if (t.getClass() == String.class) {
			StreamUtils.copy((String) t, charset, outputMessage.getBody());
		} else {
			StreamUtils.copy("未知格式数据..", charset, outputMessage.getBody());
		}

	}

	private Charset getContentTypeCharset(MediaType contentType) {
		if (contentType != null && contentType.getCharset() != null) {
			return contentType.getCharset();
		} else {
			return getDefaultCharset();
		}
	}

}
