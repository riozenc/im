/**
 * Title:DataAssemblyTool.java
 * Author:riozenc
 * Datetime:2017年6月22日 下午12:06:30
**/
package org.im.protocol.msg;

import java.util.Calendar;

import org.dom4j.DocumentException;
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
		byte[] uuid = string2byte(message.getUid(), 4);
		int order = ParseMap.getOrder(message.getClass());
		// String --> byte[]
		byte[] orderByte = string2byte(Integer.toHexString(order), 4);

		// 数据
		byte[] data = message.toXmlByte();
		// 数据长度
		// byte[] dataLength = string2byte(String.valueOf(data.length), 4);
		byte[] dataLength = string2byte(Integer.toHexString(data.length), 4);

		// 时间
		byte[] date = getDate();
		// 加密方式
		byte encryption = 0;
		// 校验码
		byte[] crc = new byte[3];

		// 组合

		// 算报文长度
		int length = message.getHeads().length + 1 + uuid.length + orderByte.length + dataLength.length + data.length
				+ date.length + 1 + crc.length + 1;

		byte[] messageByte = new byte[length];
		int tempLength = 0;
		tempLength += copyByte(message.getHeads(), messageByte, tempLength);
		messageByte[tempLength] = version;
		tempLength += 1;
		tempLength += copyByte(uuid, messageByte, tempLength);
		tempLength += copyByte(orderByte, messageByte, tempLength);
		tempLength += copyByte(dataLength, messageByte, tempLength);
		tempLength += copyByte(data, messageByte, tempLength);
		tempLength += copyByte(date, messageByte, tempLength);
		messageByte[tempLength] = encryption;
		tempLength += 1;
		tempLength += copyByte(crc, messageByte, tempLength);
		messageByte[tempLength] = message.getEnd();
		tempLength += 1;

		if (tempLength == length) {
			// 完整性校验
			return messageByte;
		}
		return null;
	}

	public static Message unpackageMessage(AbstractMessage message, byte[] bs) {
		// 开始组装
		int size = 0;
		size += 3;// FE FE FE
		size += 1;// version
		// 获取uuid
		String uid = coverBytes(bs[4], bs[5], bs[6], bs[7]);
		size += 4;
		int order = Integer.parseInt(coverBytes(bs[8], bs[9], bs[10], bs[11]), 16);
		size += 4;
		int dataLength = Integer.parseInt(coverBytes(bs[12], bs[13], bs[14], bs[15]), 16);
		size += 4;
		byte[] body = new byte[dataLength];
		System.arraycopy(bs, size, body, 0, body.length);
		size += body.length;

		int year = bs[size];
		size += 1;
		int month = bs[size];
		size += 1;
		int day = bs[size];
		size += 1;
		int hour = bs[size];
		size += 1;
		int minute = bs[size];
		size += 1;
		int second = bs[size];
		size += 1;

		String date = 2000 + year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;

		int encryption = bs[size];
		size += 1;

		String crc = new String("" + coverByte(bs[size]) + coverByte(bs[size + 1]) + coverByte(bs[size + 2]));
		size += 3;

		message.setOrder(order);
		message.setUid(uid);
		message.setDateTime(date);
		try {
			DataXmlTool.xml2Bean(body, message);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}

	public static int coverByte(byte b) {
		return b & 0xFF;
	}

	public static String coverBytes(byte... bs) {
		StringBuilder stringBuilder = new StringBuilder();
		for (byte b : bs) {
			stringBuilder.append(Integer.toHexString(coverByte(b)));
		}
		// return Integer.parseInt(stringBuilder.toString(), 16);
		return stringBuilder.toString();
	}

	private static int copyByte(byte[] src, byte[] des, int location) {
		System.arraycopy(src, 0, des, location, src.length);
		return src.length;
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
			// bs[length - i - 1] = Byte.parseByte(stringBuilder.substring((len - i - 1) *
			// 2, (len - i) * 2));
			bs[length - i - 1] = (byte) Integer.parseInt(stringBuilder.substring((len - i - 1) * 2, (len - i) * 2), 16);
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

}
