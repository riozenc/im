/**
 * Title:GroupAction.java
 * Author:czy
 * Datetime:2016年10月31日 下午4:06:31
 */
package ims.webapp.acc.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.riozenc.quicktool.common.util.json.JSONUtil;

import ims.common.security.util.UserUtils;
import ims.webapp.BaseAction;
import ims.webapp.acc.domain.GroupDomain;
import ims.webapp.acc.domain.UserDomain;
import ims.webapp.acc.service.IGroupService;



@ControllerAdvice
@RequestMapping("group")
public class GroupAction extends BaseAction {

	@Autowired
	@Qualifier("groupServiceImpl")
	private IGroupService groupservice;

	@Override
	public String getIndex() {
		// TODO Auto-generated method stub
		return "group.jsp";
	}
	
	@ResponseBody
	@RequestMapping(params = "type=getGroupByUser")
	public String getGroupByUser() {
		UserDomain userDomain = (UserDomain)UserUtils.getPrincipal().getUser();
		List<GroupDomain> list = groupservice.findGroupByUser(userDomain);
		return JSONUtil.toJsonString(list);
	}

	@ResponseBody
	@RequestMapping(params = "type=getGroupInfo")
	public String getGroupInfo(GroupDomain groupDomain) {
		GroupDomain group = groupservice.getGroupInfo(groupDomain);
		return JSONUtil.toJsonString(group);
	}

}
