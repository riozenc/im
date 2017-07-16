package ims.webapp.sys.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.im.protocol.bean.RegisterBean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.riozenc.quicktool.common.util.StringUtils;
import com.riozenc.quicktool.common.util.xml.XmlUtils;

import ims.client.Client;
import ims.common.Common;
import ims.common.security.Principal;
import ims.common.security.filter.PasswordShiroFilter;
import ims.common.xml.XmlResultBean;
import ims.webapp.acc.domain.UserDomain;

@ControllerAdvice
@RequestMapping("loginAction")
public class LoginAction {

	@ResponseBody
	@RequestMapping(value = "login")
	public String login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		String errorClassName = (String) httpServletRequest
				.getAttribute(PasswordShiroFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		if (errorClassName == null) {
			// 成功
			Subject subject = SecurityUtils.getSubject();
			Principal principal = (Principal) subject.getPrincipal();

			if (principal == null) {
				// 非法请求
				return loginFail("IncorrectCredentialsException", httpServletRequest, httpServletResponse);
			}
			UserDomain userDomain = (UserDomain) principal.getUser();
			// im通讯服务器进行注册请求
			Client.writeAndFlush(createRegister(userDomain));

			return XmlUtils.object2xml(new XmlResultBean(Common.SUCCESS, XmlUtils.object2xml(principal.getUser())));
		} else {
			// 失败
			return loginFail(errorClassName, httpServletRequest, httpServletResponse);
		}
	}

	@ResponseBody
	@RequestMapping(value = "relogin")
	public String relogin(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		Subject subject = SecurityUtils.getSubject();
		Principal principal = (Principal) subject.getPrincipal();

		return null;
	}

	public String loginFail(String errorClassName, HttpServletRequest request, HttpServletResponse response) {

		String username = WebUtils.getCleanParam(request, PasswordShiroFilter.DEFAULT_USERNAME_PARAM);
		boolean rememberMe = WebUtils.isTrue(request, PasswordShiroFilter.DEFAULT_REMEMBER_ME_PARAM);
		boolean mobile = WebUtils.isTrue(request, PasswordShiroFilter.DEFAULT_MOBILE_PARAM);
		String exception = (String) request.getAttribute(PasswordShiroFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		String message = (String) request.getAttribute(PasswordShiroFilter.DEFAULT_MESSAGE_PARAM);

		if (StringUtils.isBlank(message)) {
			message = "用户或密码错误, 请重试.";
		}

		// 非授权异常，登录失败，验证码加1。
		if (!UnauthorizedException.class.getName().equals(exception)) {
			isValidateCodeLogin(username, true, false);
			// model.addAttribute("isValidateCodeLogin", true);
		}

		return XmlUtils.object2xml(new XmlResultBean(Common.FAILD, message));
	}

	@RequestMapping(value = "/logout")
	public String logout(String username, String password) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		SecurityUtils.getSecurityManager().logout(subject);
		return null;
	}

	/**
	 * 包装通讯注册类
	 * 
	 * @param userDomain
	 * @return
	 */
	private RegisterBean createRegister(UserDomain userDomain) {
		RegisterBean registerBean = new RegisterBean();
		registerBean.setUserId(userDomain.getUserId());
		return registerBean;
	}

	public static boolean isValidateCodeLogin(String useruame, boolean isFail, boolean clean) {
		Map<String, Integer> loginFailMap = null;// (Map<String, Integer>)
													// CacheUtils.get("loginFailMap");
		if (loginFailMap == null) {
			loginFailMap = new HashMap<>();
			// CacheUtils.put("loginFailMap", loginFailMap);
		}
		Integer loginFailNum = loginFailMap.get(useruame);
		if (loginFailNum == null) {
			loginFailNum = 0;
		}
		if (isFail) {
			loginFailNum++;
			loginFailMap.put(useruame, loginFailNum);
		}
		if (clean) {
			loginFailMap.remove(useruame);
		}
		return loginFailNum >= 3;
	}
}
