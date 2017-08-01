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

import ims.common.Common;
import ims.webapp.BaseAction;
import ims.webapp.ResultBean;
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
	public Object insert(DepartmentDomain departmentDomain) {
		if (departmentService.insert(departmentDomain) == 1) {

			return new ResultBean(Common.SUCCESS, "添加成功.");
		} else {
			return new ResultBean(Common.FAILD, "添加失败.");
		}
	}

	@ResponseBody
	@RequestMapping(params = "type=getDepartment")
	public Object getDepartment(DepartmentDomain departmentDomain) {
		List<DepartmentDomain> list = departmentService.findByWhere(departmentDomain);
		return list;
	}

	@ResponseBody
	@RequestMapping(params = "type=findDeparmentByCompany")
	public Object findDeparmentByCompany(CompanyDomain companyDomain) {
		List<DepartmentDomain> list = departmentService.findDeparmentByCompany(companyDomain);
		return list;
	}
}
