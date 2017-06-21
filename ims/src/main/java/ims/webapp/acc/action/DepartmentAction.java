/**
 * Title:DepartmentAction.java
 * Author:czy
 * Datetime:2016年11月24日 上午9:47:23
 */
package ims.webapp.acc.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.riozenc.quicktool.common.util.json.JSONUtil;
import com.riozenc.quicktool.common.util.xml.XmlUtils;

import ims.common.Common;
import ims.common.xml.XmlResultBean;
import ims.webapp.BaseAction;
import ims.webapp.acc.domain.CompanyDomain;
import ims.webapp.acc.domain.DepartmentDomain;
import ims.webapp.acc.service.IDepartmentService;

/**
 * 部门
 * 
 * @author riozenc
 *
 */
@ControllerAdvice
@RequestMapping("department")
public class DepartmentAction extends BaseAction {

	@Autowired
	@Qualifier("departmentServiceImpl")
	private IDepartmentService departmentService;
	
	@Override
	public String getIndex() {
		// TODO Auto-generated method stub
		return "department.jsp";
	}

	@RequestMapping(params = "type=insert")
	public String insert(DepartmentDomain departmentDomain) {
		if (departmentService.insert(departmentDomain) == 1) {

			return XmlUtils.object2xml(new XmlResultBean(Common.SUCCESS, "添加成功."));
		} else {
			return XmlUtils.object2xml(new XmlResultBean(Common.FAILD, "添加失败."));
		}
	}

	@ResponseBody
	@RequestMapping(params = "type=getDepartment")
	public String getDepartment(DepartmentDomain departmentDomain) {
		List<DepartmentDomain> list = departmentService.findByWhere(departmentDomain);
		return JSONUtil.toJsonString(list);
	}

	@ResponseBody
	@RequestMapping(params = "type=findDeparmentByCompany")
	public String findDeparmentByCompany(CompanyDomain companyDomain) {
		List<DepartmentDomain> list = departmentService.findDeparmentByCompany(companyDomain);

		return JSONUtil.toJsonString(list);
	}
}
