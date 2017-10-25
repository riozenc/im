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
	public void setUid(String uid) {
		// TODO Auto-generated method stub
		// 不予处理，无法修改
		// throw new RuntimeException("GreetBean userId is final...");
	}

	@Override
	public String getUid() {
		// TODO Auto-generated method stub
		return "000000";
	}

}
