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
		// 数据
		byte[] data = message.toByte();

		// 数据长度
		byte[] length = string2byte(String.valueOf(data.length), 4);
		String order = ParseMap.getOrder(message.getClass());
		// String --> byte[]
		byte[] orderByte = string2byte(order, 4);

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
			bs[bs.length-i-1] = (byte) cs[cs.length - i - 1];
		}
		return bs;
	}

	public static void main(String[] args) {
		// 1498462908182
//		53495051
		byte a = 53;
		byte b = 49;
		byte c = 50;
		byte d = 51;
//
		byte[] bs = new byte[4];
		bs[0] = a;
		bs[1] = b;
		bs[2] = c;
		bs[3] = d;
		System.out.println(new String(bs));

//		byte[] bs = string2byte("5123", 4);
//		
//		for(byte b : bs){
//			System.out.print(b);
//		}
//		
		
		
		// Long time = System.currentTimeMillis();
		// String t = time.toString();
		//
		// System.out.println(new Date(time));
		//
		// for (byte b : t.getBytes()) {
		// System.out.print(b);
		// }

	}
}
