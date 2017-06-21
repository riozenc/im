/**
 * Title:GroupDAO.java
 * Author:czy
 * Datetime:2016年11月21日 上午11:31:22
 */
package ims.webapp.acc.dao;

import java.util.List;

import com.riozenc.quicktool.annotation.TransactionDAO;
import com.riozenc.quicktool.mybatis.dao.AbstractTransactionDAOSupport;
import com.riozenc.quicktool.mybatis.dao.BaseDAO;

import ims.webapp.acc.domain.GroupDomain;
import ims.webapp.acc.domain.UserDomain;

@TransactionDAO
public class GroupDAO extends AbstractTransactionDAOSupport implements BaseDAO<GroupDomain> {

	@Override
	public int insert(GroupDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().insert(getNamespace() + ".insert", t);
	}

	@Override
	public int delete(GroupDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().delete(getNamespace() + ".delete", t);
	}

	@Override
	public int update(GroupDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().update(getNamespace() + ".update", t);
	}

	@Override
	public GroupDomain findByKey(GroupDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().load(getNamespace() + ".findByKey", t);
	}

	@Override
	public List<GroupDomain> findByWhere(GroupDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().find(getNamespace() + ".findByWhere", t);
	}

	public List<GroupDomain> findGroupByUser(UserDomain userDomain) {
		return getPersistanceManager().find(getNamespace() + ".findGroupByUser", userDomain);
	}

}
