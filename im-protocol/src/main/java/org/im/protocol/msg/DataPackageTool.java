/**
 * Title:DataAssemblyTool.java
 * Author:riozenc
 * Datetime:2017年6月22日 下午12:06:30
**/
package org.im.protocol.msg;

import java.util.Date;

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

		// uuid
		byte[] uuid = new byte[4];

		// 数据
		byte[] data = message.toByte();

		// 数据长度
		byte[] length = string2byte(String.valueOf(data.length), 4);
		String order = ParseMap.getOrder(message.getClass());
		// String --> byte[]
		byte[] orderByte = string2byte(order, 4);

		// 时间
		String date = "17,06,28,16,45,59";
		byte[] dataByte = string2byte(date, 6);

		return null;
	}

	/**
	 * 拆分字符串 eg:(123,3) -->{00,01,23}
	 * 
	 * data 只能为数字
	 * 
	 * @param data
	 * @param length
	 * @return
	 */
	private static byte[] string2byte(String data, int length) {
		byte[] bs = new byte[length];
		char[] cs = data.toCharArray();
		for (int i = 0; i < cs.length; i++) {
			bs[bs.length - i - 1] = (byte) cs[cs.length - i - 1];
		}
		return bs;
	}

	public static void main(String[] args) {

//		byte[] bs = string2byte("5123", 4);
//
//		for (byte b : bs) {
//			System.out.print(b);
//		}
		String data ="ab";
		
		for (byte b : data.getBytes()) {
			System.out.print(b);
		}

	}
}
