/**
 * Title:RegisterBean.java
 * Author:riozenc
 * Datetime:2017年6月14日 下午5:18:18
**/
package org.im.protocol.bean;

import org.im.protocol.msg.AbstractMessage;

import com.riozenc.quicktool.common.util.xml.XmlUtils;

public class RegisterBean extends AbstractMessage {

	private String userId;
	private String password;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public byte[] toByte() {
		// TODO Auto-generated method stub
		String data = XmlUtils.object2xml(this);
		return data.getBytes();
	}

}
