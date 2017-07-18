/**
 * Title:GreetBean.java
 * Author:riozenc
 * Datetime:2017年7月17日 下午3:45:12
**/
package org.im.protocol.bean;

import org.im.protocol.msg.AbstractMessage;

public class GreetBean extends AbstractMessage {
	public static final int GREET_AUTH = 0;
	public static final int GREET_LOGIC = 1;
	private String userId = "admin";
	private int type;// 0:auth 1:logic

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUserId() {
		return userId;
	}

	@Override
	public void setUID(String UID) {
		// TODO Auto-generated method stub
		throw new RuntimeException("GreetBean userId is final...");
	}

	@Override
	public String getUID() {
		// TODO Auto-generated method stub
		return userId;
	}

}
