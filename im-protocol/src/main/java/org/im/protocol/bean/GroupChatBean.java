/**
 * Title:GroupChatBean.java
 * Author:riozenc
 * Datetime:2017年7月18日 上午8:57:28
**/
package org.im.protocol.bean;

/**
 * 群聊
 * 
 * @author riozenc
 *
 */
public class GroupChatBean {
	private String to;
	private String from;
	private String dateTime;
	private String content;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
