/**
 * Title:XmlResultBean.java
 * Author:riozenc
 * Datetime:2017年6月20日 下午2:37:20
**/
package ims.common.xml;

public class XmlResultBean {

	private int status;// 结果状态
	private String info;// 结果消息

	public XmlResultBean() {
	}

	public XmlResultBean(int status, String info) {
		this.status = status;
		this.info = info;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
