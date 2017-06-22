/**
 * @Project:im-gate
 * @Title:GateStarter.java
 * @Author:Riozenc
 * @Datetime:2017年4月9日 下午12:19:36
 * 
 */
package org.im.gate.starter;

import org.dom4j.Element;
import org.im.gate.bean.AuthBean;
import org.im.gate.bean.GateBean;
import org.im.gate.bean.LogicBean;
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
			GateBean gateBean = XmlParseUtils.xmlToBean(element, GateBean.class);
			AuthBean authBean = XmlParseUtils.xmlToBean(element, AuthBean.class);
			LogicBean logicBean = XmlParseUtils.xmlToBean(element, LogicBean.class);

			new Thread(() -> {
				new DefaultGate().startGate(gateBean.getPort());
			}).start();

//			new Thread(() -> DefaultGateAuthServer.startGateAuthConnection(authBean.getIp(), authBean.getPort()))
//					.start();
//
//			new Thread(() -> DefaultGateLogicServer.startGateLogicConnection(logicBean.getIp(), logicBean.getPort()))
//					.start();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
