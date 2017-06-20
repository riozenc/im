/**
 * Title:UsernamePasswordToken.java
 * Author:riozenc
 * Datetime:2017年6月20日 上午10:33:21
**/
package ims.common.security.token;

public class UsernamePasswordToken extends org.apache.shiro.authc.UsernamePasswordToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2461137658676134483L;

	private String captcha;
	private boolean mobileLogin;

	public UsernamePasswordToken() {
		super();
	}

	public UsernamePasswordToken(String username, char[] password, boolean rememberMe, String host, String captcha,
			boolean mobileLogin) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
		this.mobileLogin = mobileLogin;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public boolean isMobileLogin() {
		return mobileLogin;
	}

}
