/**
 * @Project:im-protocol
 * @Title:TEST.java
 * @Author:Riozenc
 * @Datetime:2017年7月7日 上午10:16:57
 * 
 */
package org.im.protocol;

import java.lang.reflect.Field;

import org.dom4j.DocumentException;
import org.im.protocol.bean.GreetBean;
import org.im.protocol.bean.RegisterBean;

import com.riozenc.quicktool.common.util.reflect.ReflectUtil;
import com.riozenc.quicktool.common.util.xml.XmlParseUtils;

public class TEST {
	public static void main(String[] args) throws Exception {

		// <gate>
		// <id value="1"/>
		//
		// <!--waiting for client incoming -->
		// <gatebean ip="0.0.0.0" port="9090"/>
		//
		// <authbean ip="127.0.0.1" port="9999"/>
		//
		// <logicbean ip="127.0.0.1" port="7070"/>
		// </gate>

		// String xml =
		// "<registerbean><userId>chiziyue</userId><password>123123</password></registerbean>";
		//
		// RegisterBean bean = new RegisterBean();
		// bean = (RegisterBean) bean.byte2Message(xml.getBytes());
		// System.out.println(bean.getUserId());

		
	}
}
