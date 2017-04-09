/**
 * @Project:im-gate
 * @Title:ServerBean.java
 * @Author:Riozenc
 * @Datetime:2017年4月9日 下午10:54:16
 * 
 */
package org.im.gate.bean;

public class ServerBean {
	protected String ip;
	protected String port;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

}
