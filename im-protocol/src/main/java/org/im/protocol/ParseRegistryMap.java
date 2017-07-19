/**
 * Title:ParseRegistryMap.java
 * Author:riozenc
 * Datetime:2017年4月20日 下午2:15:49
**/
package org.im.protocol;

import org.im.protocol.analysis.ParseMap;
import org.im.protocol.bean.GreetBean;
import org.im.protocol.bean.PrivateChatBean;
import org.im.protocol.bean.RegisterBean;

public class ParseRegistryMap {
	public static final int GREET = 900;
	public static final int CLIENT_REGISTER = 1000;
	public static final int CLIENT_PRIVATE_CHAT = 1001;

	public static void initRegistry() {

		ParseMap.register(GREET, GreetBean.class); // 内部传输协议用
		ParseMap.register(CLIENT_REGISTER, RegisterBean.class);// 注册
		ParseMap.register(CLIENT_PRIVATE_CHAT, PrivateChatBean.class);// 私聊
	}

}
