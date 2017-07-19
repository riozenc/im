package org.im.auth.cache.bean;

/**
 * 认证用户
 * 
 * @author riozenc
 *
 */
public class AuthenticatedUser {
	private String userId;
	private long loginTime;

	public AuthenticatedUser(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(long loginTime) {
		this.loginTime = loginTime;
	}

}
