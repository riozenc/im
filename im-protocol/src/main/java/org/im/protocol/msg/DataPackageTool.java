/**
 * Title:DataAssemblyTool.java
 * Author:riozenc
 * Datetime:2017年6月22日 下午12:06:30
**/
package org.im.protocol.msg;

import java.util.concurrent.ConcurrentHashMap;

import com.riozenc.quicktool.config.Global;

/**
 * 数据装配工具
 * 
 * @author riozenc
 *
 */
public class DataPackageTool {

	private static ConcurrentHashMap<Class<?>, byte[]> allOrderMap = new ConcurrentHashMap<>();
	
	

	public static byte[] packageMessage(AbstractMessage message) {
		// 版本号
		byte version = Byte.parseByte(Global.getConfig("protocol-version"));
		// 数据
		byte[] data = message.toByte();

		return null;
	}
}
