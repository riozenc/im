/**
 * Title:IUserService.java
 * Author:riozenc
 * Datetime:2017年6月20日 上午10:39:15
**/
package ims.webapp.acc.service;

import com.riozenc.quicktool.springmvc.webapp.service.BaseService;

import ims.webapp.acc.domain.CompanyDomain;
import ims.webapp.acc.domain.UserDomain;

public interface IUserService extends BaseService<UserDomain> {

	public String getPassword(UserDomain userDomain);

	public int insertUserRole(UserDomain userDomain, CompanyDomain companyDomain);

}
