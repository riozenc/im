/**
 * Title:IGroupService.java
 * Author:riozenc
 * Datetime:2017年6月20日 上午10:38:44
**/
package ims.webapp.acc.service;

import java.util.List;

import com.riozenc.quicktool.springmvc.webapp.service.BaseService;

import ims.webapp.acc.domain.GroupDomain;
import ims.webapp.acc.domain.UserDomain;

public interface IGroupService extends BaseService<GroupDomain> {

	public List<GroupDomain> findGroupByUser(UserDomain userDomain);

	public GroupDomain getGroupInfo(GroupDomain groupDomain);

}
