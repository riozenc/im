/**
 * Title:LogicStarter.java
 * Author:riozenc
 * Datetime:2017年6月16日 下午4:10:18
**/
package org.im.logic.starter;

import org.dom4j.Element;
import org.im.logic.Worker;
import org.im.logic.bean.LogicBean;
import org.im.logic.server.DefaultLogicServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.riozenc.quicktool.common.util.xml.XmlParseUtils;
import com.riozenc.quicktool.config.Global;

public class LogicStarter {
	private static final Logger logger = LoggerFactory.getLogger(LogicStarter.class);
	public static int workNum = 1;

	public static void main(String[] args) throws Exception {
		configureAndStart();
	}

	private static void configureAndStart() {

		try {
			Element element = XmlParseUtils.readXml(Global.getConfig("xml"));

			LogicBean logicBean = XmlParseUtils.xmlToBean(element, LogicBean.class);
			Worker.startWorker(workNum);//工作组
			
			new Thread(() -> {
				DefaultLogicServer.startLogic(logicBean.getPort());
			}).start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
