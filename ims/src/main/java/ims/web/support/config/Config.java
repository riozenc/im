/**
 * @Project:ims
 * @Title:Config.java
 * @Author:Riozenc
 * @Datetime:2017年7月31日 下午10:05:55
 * 
 */
package ims.web.support.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;

import ims.web.support.contentNegotiationStrategy.IMSContentNegotiationManagerFactoryBean;

@Configuration
public class Config {

	/**
	 * mvcContentNegotiationManager 为springmvc中的特定名字,若名字不同,则启动2个不同的manager
	 * 
	 * @return
	 */
	@Bean(name = "mvcContentNegotiationManager")
	public ContentNegotiationManagerFactoryBean contentNegotiationManagerFactoryBean() {
		ContentNegotiationManagerFactoryBean bean = new IMSContentNegotiationManagerFactoryBean();
		bean.setFavorPathExtension(false);
		bean.setFavorParameter(false);
		Map<String, MediaType> mediaTypes = new HashMap<String, MediaType>();
		mediaTypes.put("json", MediaType.APPLICATION_JSON_UTF8);
		mediaTypes.put("xml", MediaType.APPLICATION_XML);
		bean.addMediaTypes(mediaTypes);
		return bean;
	}
}
