/**
 * @Project:ims
 * @Title:PasswordShiroRealm.java
 * @Author:Riozenc
 * @Datetime:2017年7月27日 下午9:16:10
 * 
 */
package ims.common.security.realm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.riozenc.quicktool.shiro.Principal;
import com.riozenc.quicktool.shiro.realm.AbstractPasswordShiroRealm;

import ims.webapp.acc.domain.UserDomain;
import ims.webapp.acc.service.IUserService;

public class PasswordShiroRealm extends AbstractPasswordShiroRealm {

	@Autowired
	@Qualifier("userServiceImpl")
	private IUserService userService;

	@Override
	public String getPassword(String loginName) {
		// TODO Auto-generated method stub

		// 判断loginName
		UserDomain userDomain = new UserDomain();

		return userService.getPassword(userDomain);
	}

	@Override
	protected String getHashAlgorithmName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getHashIterations() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Principal createPrincipal(String loginName) {
		// TODO Auto-generated method stub

		Principal principal = new Principal();
		UserDomain userDomain = new UserDomain();
		userDomain.setUserName(loginName);
		userDomain = userService.findByKey(userDomain);
		userDomain.setPassword("Want password?");
		principal.setUserId(userDomain.getUserId());
		principal.setUserName(userDomain.getUserName());
		principal.setPhone(userDomain.getPhone());
		principal.setMailAddress(userDomain.getMailAddress());
		principal.setSex(userDomain.getSex());
		principal.setImageUrl(userDomain.getImageUrl());

		return principal;
	}

}
