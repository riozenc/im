/**
 * Title:UserUtils.java
 * Author:czy
 * Datetime:2016年11月27日 下午2:15:15
 */
package ims.common.security.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.riozenc.quicktool.exception.LoginTimeOutException;

import ims.common.security.Principal;

public class UserUtils {
	public static Principal getPrincipal() {
		Subject subject = SecurityUtils.getSubject();
		Principal principal = (Principal) subject.getPrincipal();

		if (principal == null) {
			throw new LoginTimeOutException("未登录...");
		}
		return principal;
	}
}
