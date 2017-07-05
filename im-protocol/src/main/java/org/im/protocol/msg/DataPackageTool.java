/**
 * Title:DataAssemblyTool.java
 * Author:riozenc
 * Datetime:2017年6月22日 下午12:06:30
**/
package org.im.protocol.msg;

import java.util.Calendar;
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
		int order = ParseMap.getOrder(message.getClass());
		// String --> byte[]
		byte[] orderByte = string2byte(Integer.toHexString(order), 4);
		// 时间
		byte[] dataByte = getDate();
		
		

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
		StringBuilder stringBuilder = new StringBuilder(data);
		if (data.length() % 2 == 0) {
			// 偶数
		} else {
			// 奇数
			stringBuilder.insert(0, "0");
		}
		// 保证是偶数,进行进一步拆分

		// 二选一
		// stringBuilder.substring(start, end)
		// char[] cs =
		// stringBuilder.toString().toCharArray();//单个处理效率，两个char组合效率下降，不如stringBuilder

		int len = stringBuilder.length() / 2;
		for (int i = 0; i < len; i++) {
			bs[length - i - 1] = Byte.parseByte(stringBuilder.substring((len - i - 1) * 2, (len - i) * 2));
		}

		// for (int i = (stringBuilder.length() / 2) - 1; i >= 0; i--) {
		// bs[i] = Byte.parseByte(stringBuilder.substring(i * 2, (i + 1) * 2));
		// }

		return bs;
	}

	public static byte[] getDate() {

		byte[] date = new byte[6];

		Calendar calendar = Calendar.getInstance();

		date[0] = (byte) (calendar.get(Calendar.YEAR) - 2000);
		date[1] = (byte) (calendar.get(Calendar.MONTH) + 1);
		date[2] = (byte) (calendar.get(Calendar.DAY_OF_MONTH));
		date[3] = (byte) (calendar.get(Calendar.HOUR_OF_DAY));
		date[4] = (byte) (calendar.get(Calendar.MINUTE));
		date[5] = (byte) (calendar.get(Calendar.SECOND));

		return date;
	}

	public static void main(String[] args) {
		byte[] bs = getDate();
		for (byte b : bs) {
			System.out.println(b);
		}
	}
}
