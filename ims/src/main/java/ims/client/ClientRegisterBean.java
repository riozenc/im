/**
 * @Project:ims
 * @Title:ClientRegisterBean.java
 * @Author:Riozenc
 * @Datetime:2017年6月21日 下午10:39:13
 * 
 */
package ims.client;

public class ClientRegisterBean {
	private String userId;
	private String phone;
	private String email;

	public ClientRegisterBean(String userId, String phone, String email) {
		this.userId = userId;
		this.phone = phone;
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
