/**
 * Title:ResultBean.java
 * Author:riozenc
 * Datetime:2017年6月20日 下午2:33:35
**/
package ims.webapp;

public class ResultBean {


	private int status;// 结果状态
	private String info;// 结果消息

	public ResultBean() {
	}

	public ResultBean(int status, String info) {
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
