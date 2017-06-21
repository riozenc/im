/**
 * Title:UserAction.java
 * Author:czy
 * Datetime:2016年9月13日 下午6:44:52
 */
package ims.webapp.acc.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.riozenc.quicktool.common.util.json.JSONUtil;
import com.riozenc.quicktool.common.util.xml.XmlUtils;

import ims.common.Common;
import ims.common.security.Principal;
import ims.common.security.util.UserUtils;
import ims.common.xml.XmlResultBean;
import ims.webapp.BaseAction;
import ims.webapp.acc.domain.CompanyDomain;
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
	public String insert(UserDomain userDomain) {

		if (userDomain.getPassword() == null) {
			return "密码不能为空...";
		}

		int i = userService.insert(userDomain);
		if (i == 1) {
			return XmlUtils.object2xml(new XmlResultBean(Common.SUCCESS, "新增成功."));
		} else {
			return XmlUtils.object2xml(new XmlResultBean(Common.FAILD, "新增失败(" + i + ")."));
		}
	}

	@ResponseBody
	@RequestMapping(params = "type=getUserById")
	public String getUserById(UserDomain userDomain) {
		UserDomain user = userService.findByKey(userDomain);
		return JSONUtil.toJsonString(user);
	}

	@ResponseBody
	@RequestMapping(params = "type=getLoginUser")
	public String getLoginUser() {

		Principal principal = UserUtils.getPrincipal();
		// UserDomain user = userService.getUser(userDomain);
		return JSONUtil.toJsonString(principal.getUser());
	}

	@ResponseBody
	@RequestMapping(params = "type=test1")
	public String test1() {

		UserDomain userDomain = new UserDomain();
		userDomain.setUserId("adsads");

		CompanyDomain companyDomain = new CompanyDomain();
		companyDomain.setCompanyNo("3");

		userService.insertUserRole(userDomain, companyDomain);
		return null;
	}

	@ResponseBody
	@RequestMapping(params = "type=test2")
	public String test2() {
		UserDomain userDomain = new UserDomain();
		userDomain.setUserId("adsads");

		userService.insert(userDomain);
		return null;
	}

	@ResponseBody
	@RequestMapping(params = "type=test3")
	public String test3() {
		UserDomain userDomain1 = new UserDomain();
		userDomain1.setUserId("adsads111");
		UserDomain userDomain2 = new UserDomain();
		userDomain2.setUserId("adsads222");

		List<UserDomain> list = new ArrayList<UserDomain>();
		list.add(userDomain1);
		list.add(userDomain2);

		// userService.insertList(list);

		userService.insert(userDomain1);
		return null;
	}

}
