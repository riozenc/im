/**
 * @Project:ims
 * @Title:IMSContentNegotiationManagerFactoryBean.java
 * @Author:Riozenc
 * @Datetime:2017年8月1日 下午9:34:44
 * 
 */
package ims.web.support.contentNegotiationStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;

public class IMSContentNegotiationManagerFactoryBean extends ContentNegotiationManagerFactoryBean {
	private Map<String, MediaType> mediaTypes = new HashMap<String, MediaType>();
	private ContentNegotiationManager contentNegotiationManager;

	public void addMediaTypes(Map<String, MediaType> mediaTypes) {
		if (mediaTypes != null) {
			this.mediaTypes.putAll(mediaTypes);
		}
	}

	@Override
	public void afterPropertiesSet() {

		List<ContentNegotiationStrategy> strategies = new ArrayList<ContentNegotiationStrategy>();

		ParameterContentNegotiationStrategy strategy = new IMSParameterContentNegotiationStrategy(this.mediaTypes);

		strategies.add(strategy);
		strategies.add(new HeaderContentNegotiationStrategy());

		contentNegotiationManager = new ContentNegotiationManager(strategies);
	}

	@Override
	public ContentNegotiationManager getObject() {
		// TODO Auto-generated method stub
		return contentNegotiationManager;
	}
}
