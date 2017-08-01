/**
 * @Project:ims
 * @Title:IMSRequestParam.java
 * @Author:Riozenc
 * @Datetime:2017年8月1日 下午9:10:18
 * 
 */
package ims.web.support.contentNegotiationStrategy;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;
import org.springframework.web.context.request.NativeWebRequest;

public class IMSParameterContentNegotiationStrategy extends ParameterContentNegotiationStrategy {

	private String defaultMediaTypeKey = "json";

	public IMSParameterContentNegotiationStrategy(Map<String, MediaType> mediaTypes) {
		super(mediaTypes);
		// TODO Auto-generated constructor stub
		setParameterName("output");
	}

	@Override
	protected String getMediaTypeKey(NativeWebRequest request) {
		// TODO Auto-generated method stub
		return super.getMediaTypeKey(request) == null ? defaultMediaTypeKey : super.getMediaTypeKey(request);
	}

}
