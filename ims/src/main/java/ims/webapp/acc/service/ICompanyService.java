/**
 * Title:ICompanyService.java
 * Author:riozenc
 * Datetime:2017年6月20日 上午10:26:25
**/
package ims.webapp.acc.service;

import com.riozenc.quicktool.springmvc.webapp.service.BaseService;

import ims.webapp.acc.domain.CompanyDomain;
import ims.webapp.acc.domain.UserDomain;

public interface ICompanyService extends BaseService<CompanyDomain> {
	public CompanyDomain getCompanyByUser(UserDomain userDomain);
}
