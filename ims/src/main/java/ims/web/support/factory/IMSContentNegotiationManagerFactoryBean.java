/**
 * @Project:ims
 * @Title:IMSContentNegotiationManagerFactoryBean.java
 * @Author:Riozenc
 * @Datetime:2017年7月30日 下午10:54:13
 * 
 */
package ims.web.support.factory;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * 功能待定 
 * ContentNegotiationManagerFactoryBean重写
 * WebMvcConfigurerAdapter重新配置
 * @author rioze
 *
 */
@Configuration
@EnableWebMvc
public class IMSContentNegotiationManagerFactoryBean extends WebMvcConfigurerAdapter {

	// <property name="mediaTypes">
	// <props>
	// <prop key="json">application/json;charset=utf-8</prop>
	// <prop key="xml" >application/xml;charset=utf-8</prop>
	// </props>
	// </property>
	//
	// <property name="parameterName" value="output"></property>
	// <property name="favorPathExtension" value="false"></property>
	// <property name="favorParameter" value="true"></property>

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		// TODO Auto-generated method stub

		configurer.favorParameter(true);
		configurer.favorPathExtension(false);
		configurer.parameterName("czy");

		super.configureContentNegotiation(configurer);
	}

}
