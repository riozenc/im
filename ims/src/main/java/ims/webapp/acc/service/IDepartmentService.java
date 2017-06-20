/**
 * Title:IDepartmentService.java
 * Author:riozenc
 * Datetime:2017年6月20日 上午10:38:14
**/
package ims.webapp.acc.service;

import java.util.List;

import com.riozenc.quicktool.springmvc.webapp.service.BaseService;

import ims.webapp.acc.domain.CompanyDomain;
import ims.webapp.acc.domain.DepartmentDomain;

public interface IDepartmentService extends BaseService<DepartmentDomain> {

	public List<DepartmentDomain> findDeparmentByCompany(CompanyDomain companyDomain);

 	}
