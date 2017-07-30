/**
 * Title:Principal.java
 * Author:czy
 * Datetime:2016年11月27日 上午10:58:02
 */
package ims.common.security;

public class Principal extends com.riozenc.quicktool.shiro.Principal {

	private Object user;
	private String privateKey;// 私钥
	private String publicKey;// 公钥
	private String token;// 令牌

	public Principal() {
	}

	public Principal(Object user) {
		this.user = user;
	}

	public Object getUser() {
		return user;
	}

	public void setUser(Object user) {
		this.user = user;
	}

}
