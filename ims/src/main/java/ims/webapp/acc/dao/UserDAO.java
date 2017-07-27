/**
 * @Project:crm
 * @Title:UserDAO.java
 * @Author:Riozenc
 * @Datetime:2016年11月8日 下午11:11:53
 * 
 */
package ims.webapp.acc.dao;

import java.util.List;

import org.apache.ibatis.session.ExecutorType;

import com.riozenc.quicktool.annotation.TransactionDAO;
import com.riozenc.quicktool.mybatis.dao.AbstractTransactionDAOSupport;
import com.riozenc.quicktool.mybatis.dao.BaseDAO;

import ims.webapp.acc.domain.CompanyDomain;
import ims.webapp.acc.domain.GroupDomain;
import ims.webapp.acc.domain.UserDomain;

@TransactionDAO
public class UserDAO extends AbstractTransactionDAOSupport implements BaseDAO<UserDomain> {

	@Override
	public int insert(UserDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().insert(getNamespace() + ".insert", t);
	}

	@Override
	public int delete(UserDomain t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(UserDomain t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserDomain findByKey(UserDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().load(getNamespace() + ".findByKey", t);
	}

	@Override
	public List<UserDomain> findByWhere(UserDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().find(getNamespace() + ".findByWhere", t);
	}

	public UserDomain getUser(UserDomain t) {
		return getPersistanceManager().load(getNamespace() + ".getUser", t);
	}

	public List<UserDomain> findUserByCompany(CompanyDomain companyDomain) {
		return getPersistanceManager().find(getNamespace() + ".findUserByCompany", companyDomain);
	}

	public List<UserDomain> findUserByGroup(GroupDomain groupDomain) {
		return getPersistanceManager().find(getNamespace() + ".findUserByGroup", groupDomain);
	}

	public String getPassword(UserDomain userDomain) {
		return getPersistanceManager().load(getNamespace() + ".getPassword", userDomain);
	}

	public int insertList(List<UserDomain> list) {
		return getPersistanceManager(ExecutorType.BATCH).insertList(getNamespace() + ".insert", list);
	}
}
