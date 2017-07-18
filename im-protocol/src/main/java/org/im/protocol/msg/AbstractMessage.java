/**
 * Title:AbstractMessage.java
 * Author:riozenc
 * Datetime:2017年6月14日 上午11:55:40
**/
package org.im.protocol.msg;

import java.io.IOException;

import org.dom4j.DocumentException;

import com.riozenc.quicktool.common.util.xml.XmlUtils;

/**
 * 消息体的基本功能，提供部分获取数据的方法和协议对象的转化
 * 
 * eg: FE FE FE 01 00 00 00 01 00 00 00 01 00 00 00 10 11 11 11 11 11 17 06 28
 * 22 36 59 01 99 99 16
 * 
 * 
 * @author riozenc
 *
 */
public abstract class AbstractMessage implements Message {

	private static final byte[] heads = { (byte) 254, (byte) 254, (byte) 254 };// 头长度3

	private byte version;// 协议版本 长度1
	private byte[] uidByte = new byte[4];// 指令ID//个人帐号
	private byte[] orderByte = new byte[4];// 命令 长度4 会补0
	private byte[] length = new byte[4];// 数据长度 长度4
	private byte[] data;// 数据 长度等于length
	private byte[] date = new byte[6];// 数据时间 长度6 年月日时分秒
	private byte encryption;// 加密方式 长度1
	private byte[] crc = new byte[3];// 校验 长度2
	private byte end = (byte) 254;// 结束符

	private String UID;
	private int order;

	protected byte[] getHeads() {
		return heads;
	}

	protected byte getVersion() {
		return version;
	}

	protected byte[] getUIDByte() {
		return uidByte;
	}

	protected byte[] getCrc() {
		return crc;
	}

	protected byte getEncryption() {
		return encryption;
	}

	protected byte[] getOrderByte() {
		return orderByte;
	}

	protected byte[] getDataLength() {
		return length;
	}

	protected byte[] getData() {
		return data;
	}

	protected byte[] getDate() {
		return date;
	}

	protected byte getEnd() {
		return end;
	}

	@Override
	public byte[] message2Byte() {
		// TODO Auto-generated method stub
		// 对象变数据
		this.data = toXmlByte();
		return DataPackageTool.packageMessage(this);
	}

	@Override
	public Message byte2Message(byte[] bs) {
		// TODO Auto-generated method stub
		try {
			return DataXmlTool.xml2Bean(bs, this);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public byte[] toXmlByte() {
		// TODO Auto-generated method stub
		String data = XmlUtils.object2xml(this);
		return data.getBytes();
	}

	public Message process(byte[] bytes) throws IOException {
		// TODO Auto-generated method stub
		return byte2Message(bytes);
	}

	@Override
	public String getUID() {
		// TODO Auto-generated method stub
		return UID;
	}

	@Override
	public void setUID(String UID) {
		this.UID = UID;
	}

	@Override
	public int getOrder() {
		return order;
	}

	@Override
	public void setOrder(int order) {
		this.order = order;
	}

}
