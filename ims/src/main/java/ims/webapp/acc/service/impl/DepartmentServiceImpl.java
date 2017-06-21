/**
 * Title:DepartmentServiceImpl.java
 * Author:riozenc
 * Datetime:2017年6月20日 上午10:40:16
**/
package ims.webapp.acc.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.riozenc.quicktool.annotation.TransactionDAO;
import com.riozenc.quicktool.annotation.TransactionService;

import ims.webapp.acc.dao.DepartmentDAO;
import ims.webapp.acc.dao.UserDAO;
import ims.webapp.acc.domain.CompanyDomain;
import ims.webapp.acc.domain.DepartmentDomain;
import ims.webapp.acc.domain.UserDomain;
import ims.webapp.acc.service.IDepartmentService;

@TransactionService
public class DepartmentServiceImpl implements IDepartmentService {

	@TransactionDAO
	private DepartmentDAO departmentDAO;

	@TransactionDAO
	private UserDAO userDAO;

	@Override
	public int insert(DepartmentDomain t) {
		// TODO Auto-generated method stub
		return departmentDAO.insert(t);
	}

	@Override
	public int delete(DepartmentDomain t) {
		// TODO Auto-generated method stub
		return departmentDAO.delete(t);
	}

	@Override
	public int update(DepartmentDomain t) {
		// TODO Auto-generated method stub
		return departmentDAO.update(t);
	}

	@Override
	public DepartmentDomain findByKey(DepartmentDomain t) {
		// TODO Auto-generated method stub
		return departmentDAO.findByKey(t);
	}

	@Override
	public List<DepartmentDomain> findByWhere(DepartmentDomain t) {
		// TODO Auto-generated method stub
		return departmentDAO.findByWhere(t);
	}

	@Override
	public List<DepartmentDomain> findDeparmentByCompany(CompanyDomain companyDomain) {
		// TODO Auto-generated method stub
		List<DepartmentDomain> departmentDomains = departmentDAO.getDeparmentByCompany(companyDomain);
		List<UserDomain> userDomains = userDAO.findUserByCompany(companyDomain);
		HashMap<Long, DepartmentDomain> map = new HashMap<Long, DepartmentDomain>();
		for (DepartmentDomain temp : departmentDomains) {
			temp.setUserList(new LinkedList<>());
			map.put(temp.getId(), temp);
		}
		for (UserDomain temp : userDomains) {
			if (map.get(temp.getDepartmentId()) != null) {
				map.get(temp.getDepartmentId()).getUserList().add(temp);
			}
		}
		return departmentDomains;
	}

}
