/**
 * Title:DepartmentDAO.java
 * Author:czy
 * Datetime:2016年11月23日 下午5:26:39
 */
package ims.webapp.acc.dao;

import java.util.List;

import com.riozenc.quicktool.annotation.TransactionDAO;
import com.riozenc.quicktool.mybatis.dao.AbstractTransactionDAOSupport;
import com.riozenc.quicktool.mybatis.dao.BaseDAO;

import ims.webapp.acc.domain.CompanyDomain;
import ims.webapp.acc.domain.DepartmentDomain;

//@Repository
@TransactionDAO
public class DepartmentDAO extends AbstractTransactionDAOSupport implements BaseDAO<DepartmentDomain> {

	@Override
	public int insert(DepartmentDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().insert(getNamespace() + ".insert", t);
	}

	@Override
	public int delete(DepartmentDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().delete(getNamespace() + ".delete", t);
	}

	@Override
	public int update(DepartmentDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().update(getNamespace() + ".update", t);
	}

	@Override
	public DepartmentDomain findByKey(DepartmentDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().load(getNamespace() + ".findByKey", t);
	}

	@Override
	public List<DepartmentDomain> findByWhere(DepartmentDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().find(getNamespace() + ".findByWhere", t);
	}

	public List<DepartmentDomain> getDeparmentByCompany(CompanyDomain companyDomain) {
		return getPersistanceManager().find(getNamespace() + ".getDeparmentByCompany", companyDomain);
	}
}
