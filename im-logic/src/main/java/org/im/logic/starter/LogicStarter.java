/**
 * Title:LogicStarter.java
 * Author:riozenc
 * Datetime:2017年6月16日 下午4:10:18
**/
package org.im.logic.starter;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.im.logic.bean.LogicBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.riozenc.quicktool.common.util.xml.XmlParseUtils;
import com.riozenc.quicktool.config.Global;

public class LogicStarter {
	private static final Logger logger = LoggerFactory.getLogger(LogicStarter.class);
	public static int workNum = 1;

	private static void configureAndStart() {
		try {
			Element element = XmlParseUtils.parse(Global.getConfig("xml"));

			LogicBean logicBean = XmlParseUtils.xmlToBean(element, LogicBean.class);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
