/**
 * Title:Message.java
 * Author:riozenc
 * Datetime:2017年4月20日 下午1:54:35
**/
package org.im.protocol.msg;

/**
 * FE FE FE 开头
 * 
 * 标识 版本 校验 加密 命令号 ID Length Data Time 标识 0x003 0x0221 预留 预留 0x0017 - 0x1234 - -
 * 0x03 1字节 2字节 4字节 4字节 2字节 4字节 2字节 Length 8字节 1字节
 * 
 * @author riozenc
 *
 */
public interface Message {

	byte[] message2Byte();

	public Message byte2Message(byte[] bs);

	public int getOrder();// 命令

	public void setOrder(int order);

	public String getUID();

	public void setUID(String UID);
}
