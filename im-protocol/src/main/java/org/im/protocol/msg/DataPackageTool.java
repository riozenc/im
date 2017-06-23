/**
 * Title:DataAssemblyTool.java
 * Author:riozenc
 * Datetime:2017年6月22日 下午12:06:30
**/
package org.im.protocol.msg;

import java.util.Spliterator.OfInt;
import java.util.stream.IntStream;

import org.im.protocol.analysis.ParseMap;

import com.riozenc.quicktool.config.Global;

/**
 * 数据装配工具
 * 
 * @author riozenc
 *
 */
public class DataPackageTool {

	public static byte[] packageMessage(AbstractMessage message) {
		// 版本号
		byte version = Byte.parseByte(Global.getConfig("protocol-version"));
		// 数据
		byte[] data = message.toByte();

		String order = ParseMap.getOrder(message.getClass());

		// String --> byte[]
		byte[] orderByte = new byte[4];

		return null;
	}

	private static byte[] orderProcess(String data) {

		int length = data.length();

		byte[] bs = new byte[length];
		char[] cs = data.toCharArray();

		for (int i = 0; i < length; i++) {
			bs[i] = Byte.parseByte(cs[i]);
		}

		return bs;
	}

	public static void main(String[] args) {
		String s = "5123";
		IntStream intStream = s.chars();
		char[] cs = s.toCharArray();
		
		

		int[] is = intStream.toArray();

	}
}
