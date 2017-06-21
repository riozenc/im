/**
 * Title:CompanyDAO.java
 * Author:czy
 * Datetime:2016年10月31日 下午4:11:43
 */
package ims.webapp.acc.dao;

import java.util.List;

import com.riozenc.quicktool.annotation.TransactionDAO;
import com.riozenc.quicktool.mybatis.dao.AbstractTransactionDAOSupport;
import com.riozenc.quicktool.mybatis.dao.BaseDAO;

import ims.webapp.acc.domain.CompanyDomain;
import ims.webapp.acc.domain.UserDomain;



@TransactionDAO
public class CompanyDAO extends AbstractTransactionDAOSupport implements BaseDAO<CompanyDomain> {

	@Override
	public int insert(CompanyDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().insert(getNamespace() + ".insert", t);
	}

	@Override
	public int delete(CompanyDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().delete(getNamespace() + ".delete", t);
	}

	@Override
	public int update(CompanyDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().update(getNamespace() + ".update", t);
	}

	@Override
	public CompanyDomain findByKey(CompanyDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().load(getNamespace() + ".findByKey", t);
	}

	@Override
	public List<CompanyDomain> findByWhere(CompanyDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().find(getNamespace() + ".findByWhere", t);
	}

	public CompanyDomain getCompanyByUser(UserDomain userDomain) {
		return getPersistanceManager().load(getNamespace() + ".getCompanyByUser", userDomain);
	}
}
