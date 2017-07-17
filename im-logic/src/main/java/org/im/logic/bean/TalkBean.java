/**
 * Title:TalkBean.java
 * Author:riozenc
 * Datetime:2017年7月17日 下午2:31:10
**/
package org.im.logic.bean;

/**
 * 对话bean（离线、在线）
 * 
 * @author riozenc
 *
 */
public class TalkBean {
	private String to;
	private String from;
	private String date;
	private String content;
	private String filePath;

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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
