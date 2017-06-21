/**
 * Title:UserServiceImpl.java
 * Author:riozenc
 * Datetime:2017年6月20日 上午10:42:35
**/
package ims.webapp.acc.service.impl;

import java.util.List;

import com.riozenc.quicktool.annotation.TransactionDAO;
import com.riozenc.quicktool.annotation.TransactionService;
import com.riozenc.quicktool.common.util.cryption.en.WebPasswordUtils;

import ims.webapp.acc.dao.CompanyDAO;
import ims.webapp.acc.dao.UserDAO;
import ims.webapp.acc.domain.CompanyDomain;
import ims.webapp.acc.domain.UserDomain;
import ims.webapp.acc.service.IUserService;

@TransactionService
public class UserServiceImpl implements IUserService {
	@TransactionDAO()
	private UserDAO userDAO;

	@TransactionDAO()
	private CompanyDAO companyDAO;

	@Override
	public int insert(UserDomain t) {
		// TODO Auto-generated method stub
		t.setPassword(WebPasswordUtils.encodePassword(t.getPassword()));

		return userDAO.insert(t);
	}

	@Override
	public int delete(UserDomain t) {
		// TODO Auto-generated method stub
		return userDAO.delete(t);
	}

	@Override
	public int update(UserDomain t) {
		// TODO Auto-generated method stub
		if (t.getPassword() != null) {
			t.setPassword(WebPasswordUtils.encodePassword(t.getPassword()));
		}
		return userDAO.update(t);
	}

	@Override
	public UserDomain findByKey(UserDomain t) {
		// TODO Auto-generated method stub
		return userDAO.findByKey(t);
	}

	@Override
	public List<UserDomain> findByWhere(UserDomain t) {
		// TODO Auto-generated method stub
		return userDAO.findByWhere(t);
	}

	@Override
	public int insertUserRole(UserDomain userDomain, CompanyDomain companyDomain) {
		// TODO Auto-generated method stub
		return userDAO.insert(userDomain) & companyDAO.insert(companyDomain);
	}

}
