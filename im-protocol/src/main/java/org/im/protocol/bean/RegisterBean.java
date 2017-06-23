/**
 * Title:RegisterBean.java
 * Author:riozenc
 * Datetime:2017年6月14日 下午5:18:18
**/
package org.im.protocol.bean;

import java.io.IOException;

import org.im.protocol.analysis.ParseMap;
import org.im.protocol.analysis.ParseMap.Parsing;
import org.im.protocol.msg.AbstractMessage;
import org.im.protocol.msg.Message;

import com.riozenc.quicktool.common.util.xml.XmlUtils;

public class RegisterBean extends AbstractMessage implements Parsing {

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

	@Override
	public Message process(byte[] bytes) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
