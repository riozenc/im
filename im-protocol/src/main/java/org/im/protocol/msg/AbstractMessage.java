/**
 * Title:AbstractMessage.java
 * Author:riozenc
 * Datetime:2017年6月14日 上午11:55:40
**/
package org.im.protocol.msg;

/**
 * 消息体的基本功能，提供部分获取数据的方法和协议对象的转化
 * 
 * @author riozenc
 *
 */
public abstract class AbstractMessage implements Message {

	private byte[] heads;// 头 长度3
	private byte version;// 协议版本 长度1
	private byte[] crc;// 校验 长度2
	private byte encryption;// 加密方式 长度1
	private byte[] order;// 命令 长度4
	private byte[] length;// 数据长度 长度4
	private byte[] data;// 数据 长度等于length
	private byte[] date;// 数据时间 长度4

	public byte[] getHeads() {
		return heads;
	}

	public byte getVersion() {
		return version;
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

	@Override
	public byte[] toByteArray() {
		// TODO Auto-generated method stub

		// 组装数据

		return null;
	}

	@Override
	public Message byte2Message(byte[] bs) {
		// TODO Auto-generated method stub

		// 数据变对象

		return null;
	}

	public abstract byte[] toByte();
}
