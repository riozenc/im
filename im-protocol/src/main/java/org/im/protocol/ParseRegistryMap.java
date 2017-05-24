/**
 * Title:ParseRegistryMap.java
 * Author:riozenc
 * Datetime:2017年4月20日 下午2:15:49
**/
package org.im.protocol;

import java.io.IOException;

import org.im.protocol.analysis.ParseMap;

public class ParseRegistryMap {
	public static final int GTRANSFER = 900;
	public static final int GREET = 901;
	public static final int CLOGIN = 1000;

	public static final int CREGISTER = 1001;
	public static final int SRESPONSE = 1002;
	public static final int CPRIVATECHAT = 1003;
	public static final int SPRIVATECHAT = 1004;

	public static void initRegistry() throws IOException {
//		ParseMap.register(900, Internal.GTransfer::parseFrom, Internal.GTransfer.class); // 内部传输协议用
//		ParseMap.register(900, parse, cla);
	}
}
