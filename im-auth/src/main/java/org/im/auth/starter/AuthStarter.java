/**
 * @Project:im-auth
 * @Title:AuthStarter.java
 * @Author:Riozenc
 * @Datetime:2017年7月12日 上午9:22:54
 * 
 */
package org.im.auth.starter;

import org.dom4j.Element;
import org.im.auth.Worker;
import org.im.auth.bean.AuthBean;
import org.im.auth.server.AuthServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.riozenc.quicktool.common.util.xml.XmlParseUtils;
import com.riozenc.quicktool.config.Global;

public class AuthStarter {
	private static final Logger logger = LoggerFactory.getLogger(AuthStarter.class);

	public static int workNum = 1;
	public static Worker[] workers;

	public static void main(String[] args) throws Exception {

		configureAndStart();
	}

	private static void configureAndStart() throws Exception {
		Element element = XmlParseUtils.readXml(Global.getConfig("xml"));
		AuthBean authBean = XmlParseUtils.xmlToBean(element, AuthBean.class);
		new Thread(() -> AuthServer.startAuthServer(authBean.getPort())).start();

	}
}
