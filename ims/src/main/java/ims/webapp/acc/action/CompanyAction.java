/**
 * Title:CompanyAction.java
 * Author:czy
 * Datetime:2016年10月31日 下午4:04:52
 */
package ims.webapp.acc.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.riozenc.quicktool.common.util.json.JSONUtil;
import com.riozenc.quicktool.common.util.xml.XmlUtils;

import ims.common.Common;
import ims.common.security.util.UserUtils;
import ims.common.xml.XmlResultBean;
import ims.webapp.BaseAction;
import ims.webapp.acc.domain.CompanyDomain;
import ims.webapp.acc.domain.UserDomain;
import ims.webapp.acc.service.ICompanyService;

/**
 * 公司
 * 
 * @author riozenc
 *
 */
@ControllerAdvice
@RequestMapping("company")
public class CompanyAction extends BaseAction {

	@Autowired
	@Qualifier("companyServiceImpl")
	private ICompanyService companyService;

	@ResponseBody
	@RequestMapping(params = "type=insert")
	public String insert(CompanyDomain companyInfoVO) {
		try {
			companyService.insert(companyInfoVO);
			return XmlUtils.object2xml(new XmlResultBean(Common.SUCCESS, "新增成功"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return XmlUtils.object2xml(new XmlResultBean(Common.FAILD, "新增失败"));
		}
	}

	@ResponseBody
	@RequestMapping(params = "type=getCompany")
	public String getCompanyByUser() {
		CompanyDomain company = companyService.getCompanyByUser((UserDomain) UserUtils.getPrincipal().getUser());
		return JSONUtil.toJsonString(company);
	}

}
