/**
 * Title:UserAction.java
 * Author:czy
 * Datetime:2016年9月13日 下午6:44:52
 */
package ims.webapp.acc.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ims.common.Common;
import ims.common.security.Principal;
import ims.common.security.util.UserUtils;
import ims.webapp.BaseAction;
import ims.webapp.ResultBean;
import ims.webapp.acc.domain.UserDomain;
import ims.webapp.acc.service.IUserService;

/**
 * 用户
 * 
 * @author riozenc
 *
 */
@ControllerAdvice
@RequestMapping("user")
public class UserAction extends BaseAction {

	@Autowired
	@Qualifier("userServiceImpl")
	private IUserService userService;

	@Override
	public String getIndex() {
		// TODO Auto-generated method stub
		return "user.jsp";
	}

	@ResponseBody
	@RequestMapping(params = "type=insert")
	public Object insert(UserDomain userDomain) {

		if (userDomain.getPassword() == null) {
			return "密码不能为空...";
		}
		int i = userService.insert(userDomain);
		if (i == 1) {
			return new ResultBean(Common.SUCCESS, "新增成功.");
		} else {
			return new ResultBean(Common.FAILD, "新增失败(" + i + ").");
		}
	}

	@ResponseBody
	@RequestMapping(params = "type=getUserById")
	public Object getUserById(UserDomain userDomain) {
		UserDomain user = userService.findByKey(userDomain);
		return user;
	}

	@ResponseBody
	@RequestMapping(params = "type=getLoginUser")
	public Object getLoginUser() {

		Principal principal = UserUtils.getPrincipal();
		// UserDomain user = userService.getUser(userDomain);
		return principal.getUser();
	}

}
