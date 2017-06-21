/**
 * Title:CompanyServiceImpl.java
 * Author:riozenc
 * Datetime:2017年6月20日 上午10:26:41
**/
package ims.webapp.acc.service.impl;

import java.util.List;

import com.riozenc.quicktool.annotation.TransactionDAO;
import com.riozenc.quicktool.annotation.TransactionService;

import ims.webapp.acc.dao.CompanyDAO;
import ims.webapp.acc.domain.CompanyDomain;
import ims.webapp.acc.domain.UserDomain;
import ims.webapp.acc.service.ICompanyService;

@TransactionService
public class CompanyServiceImpl implements ICompanyService {
	@TransactionDAO
	private CompanyDAO companyDAO;;

	@Override
	public int insert(CompanyDomain t) {
		// TODO Auto-generated method stub
		return companyDAO.insert(t);
	}

	@Override
	public int delete(CompanyDomain t) {
		// TODO Auto-generated method stub
		return companyDAO.delete(t);
	}

	@Override
	public int update(CompanyDomain t) {
		// TODO Auto-generated method stub
		return companyDAO.update(t);
	}

	@Override
	public CompanyDomain findByKey(CompanyDomain t) {
		// TODO Auto-generated method stub
		return companyDAO.findByKey(t);
	}

	@Override
	public List<CompanyDomain> findByWhere(CompanyDomain t) {
		// TODO Auto-generated method stub
		return companyDAO.findByWhere(t);
	}

	@Override
	public CompanyDomain getCompanyByUser(UserDomain userDomain) {
		// TODO Auto-generated method stub
		return companyDAO.getCompanyByUser(userDomain);
	}

}
