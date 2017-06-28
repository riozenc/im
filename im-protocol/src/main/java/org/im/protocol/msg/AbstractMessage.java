/**
 * Title:AbstractMessage.java
 * Author:riozenc
 * Datetime:2017年6月14日 上午11:55:40
**/
package org.im.protocol.msg;

import com.riozenc.quicktool.config.Global;

/**
 * 消息体的基本功能，提供部分获取数据的方法和协议对象的转化
 * 
 * @author riozenc
 *
 */
public abstract class AbstractMessage implements Message {

	private static final byte[] heads = { (byte) 254, (byte) 254, (byte) 254 };// 头
																				// 长度3
	private byte version;// 协议版本 长度1
	private byte[] uuid = new byte[4];// 指令ID
	private byte[] order = new byte[4];// 命令 长度4 会补0
	private byte[] length = new byte[4];// 数据长度 长度4
	private byte[] data;// 数据 长度等于length
	private byte[] date = new byte[6];// 数据时间 长度6 年月日时分秒
	private byte encryption;// 加密方式 长度1
	private byte[] crc = new byte[3];// 校验 长度2
	private byte end = (byte) 254;// 结束符

	public byte[] getHeads() {
		return heads;
	}

	public byte getVersion() {
		return version;
	}

	public byte[] getUuid() {
		return uuid;
	}

	public byte[] getCrc() {
		return crc;
	}

	public byte getEncryption() {
		return encryption;
	}

	public byte[] getOrder() {
		return order;
	}

	public byte[] getDataLength() {
		return length;
	}

	public byte[] getData() {
		return data;
	}

	public byte[] getDate() {
		return date;
	}

	public byte getEnd() {
		return end;
	}

	@Override
	public byte[] message2Byte() {
		// TODO Auto-generated method stub

		// 版本号
		version = Byte.parseByte(Global.getConfig("protocol-version"));

		// 对象变数据
		this.data = toByte();

		return null;
	}

	@Override
	public Message byte2Message(byte[] bs) {
		// TODO Auto-generated method stub

		// 数据变对象

		return this;
	}

	public abstract byte[] toByte();

}
