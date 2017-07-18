/**
 * Title:PrivateChatBean.java
 * Author:riozenc
 * Datetime:2017年7月18日 上午8:56:34
**/
package org.im.protocol.bean;

import org.im.protocol.msg.AbstractMessage;

/**
 * 私聊
 * 
 * @author riozenc
 *
 */
public class PrivateChatBean extends AbstractMessage {
	private String to;
	private String from;
	private String dateTime;
	private String content;

	public String getTo() {
		return to;
	}

	public String getFrom() {
		return from;
	}

	public String getDateTime() {
		return dateTime;
	}

	public String getContent() {
		return content;
	}

}
