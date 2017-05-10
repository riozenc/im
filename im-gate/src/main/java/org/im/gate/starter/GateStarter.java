/**
 * @Project:im-gate
 * @Title:GateStarter.java
 * @Author:Riozenc
 * @Datetime:2017年4月9日 下午12:19:36
 * 
 */
package org.im.gate.starter;

import java.security.cert.CertificateException;

import javax.net.ssl.SSLException;

import org.dom4j.Element;
import org.im.gate.bean.GateBean;
import org.im.gate.server.DefaultGate;

import com.riozenc.quicktool.common.util.xml.XmlParseUtils;
import com.riozenc.quicktool.config.Global;

public class GateStarter {

	public static void main(String[] args) throws Exception {
		configureAndStart();
	}

	private static void configureAndStart() {

		try {
			// parse xml File and apply it
			Element element = XmlParseUtils.parse(Global.getConfig("xml"));
			GateBean bean = XmlParseUtils.xmlToBean(element, GateBean.class);

			new Thread(() -> {
				try {
					new DefaultGate().startGate(bean.getPort());
				} catch (CertificateException | SSLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}).start();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
