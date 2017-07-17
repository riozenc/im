/**
 * Title:RegisterBean.java
 * Author:riozenc
 * Datetime:2017年6月14日 下午5:18:18
**/
package org.im.protocol.bean;

import org.im.protocol.msg.AbstractMessage;

public class RegisterBean extends AbstractMessage  {

	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
