/**
 * Title:ParseRegistryMap.java
 * Author:riozenc
 * Datetime:2017年4月20日 下午2:15:49
**/
package org.im.protocol;

import java.io.IOException;

import org.im.protocol.analysis.ParseMap;
import org.im.protocol.bean.RegisterBean;

public class ParseRegistryMap {
	public static final String CLIENT_REGISTER = "0900";

	public static void initRegistry() throws IOException {
		ParseMap.register(CLIENT_REGISTER, RegisterBean.class);//内部协议

	}

}
