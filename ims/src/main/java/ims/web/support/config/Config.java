/**
 * @Project:ims
 * @Title:Config.java
 * @Author:Riozenc
 * @Datetime:2017年7月31日 下午10:05:55
 * 
 */
package ims.web.support.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;

@Configuration
public class Config {

	/**
	 * mvcContentNegotiationManager 为springmvc中的特定名字,若名字不同,则启动2个不同的manager
	 * @return
	 */
	@Bean(name = "mvcContentNegotiationManager")
	public ContentNegotiationManagerFactoryBean contentNegotiationManagerFactoryBean() {
		ContentNegotiationManagerFactoryBean bean = new ContentNegotiationManagerFactoryBean();
		bean.setFavorPathExtension(false);
		bean.setFavorParameter(true);
		bean.setParameterName("output");
		
		bean.addMediaType("json", MediaType.APPLICATION_JSON_UTF8);
		bean.addMediaType("xml", MediaType.APPLICATION_XML);
		
		return bean;
	}
}
