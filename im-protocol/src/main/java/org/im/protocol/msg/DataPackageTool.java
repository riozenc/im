/**
 * Title:DataAssemblyTool.java
 * Author:riozenc
 * Datetime:2017年6月22日 下午12:06:30
**/
package org.im.protocol.msg;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
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
	 * 拆分字符串 eg:(123,2) -->{01,23}
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
		StringBuilder stringBuilder = new StringBuilder(data);
		if (data.length() % 2 == 0) {
			// 偶数
		} else {
			// 奇数
			stringBuilder.insert(0, "0");
		}
//保证是偶数,进行进一步拆分
//		for (int i = 0; i < (cs.length + 1) / 2; i++) {
//			if (cs.length == 1) {
//				bs[bs.length - i - 1] = Byte.parseByte(String.valueOf(cs[cs.length - i - 1]));
//			} else {
//				bs[bs.length - i - 1] = Byte
//						.parseByte(String.valueOf(cs[cs.length - i * 2 - 2] + "" + cs[cs.length - i * 2 - 1]));
//			}
//		}
		return bs;
	}

	public static void main(String[] args) {
		//
		// byte[] bs = string2byte("9654", 2);
		//
		// for (byte b : bs) {
		// System.out.print(b);
		// }
		String a = "1";
		StringBuilder stringBuilder = new StringBuilder(a);
		System.out.println(stringBuilder);
		System.out.println(stringBuilder.insert(0, "0"));
		System.out.println(stringBuilder);
	}
}
