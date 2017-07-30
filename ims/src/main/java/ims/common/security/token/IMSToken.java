/**
 * Title:UsernamePasswordToken.java
 * Author:riozenc
 * Datetime:2017年6月20日 上午10:33:21
**/
package ims.common.security.token;

public class IMSToken extends com.riozenc.quicktool.shiro.token.UsernamePasswordToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2461137658676134483L;

	public IMSToken(String username, char[] password, boolean rememberMe, String host, String captcha,
			boolean mobileLogin) {
		super(username, password, rememberMe, host, captcha, mobileLogin);
	}
}
