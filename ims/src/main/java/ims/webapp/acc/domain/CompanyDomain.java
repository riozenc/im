/**
 * Title:CompanyInfoVo.java
 * Author:czy
 * Datetime:2016年9月13日 下午6:36:35
 */
package ims.webapp.acc.domain;

import com.riozenc.quicktool.annotation.TablePrimaryKey;
import com.riozenc.quicktool.mybatis.MybatisEntity;

/**
 * 公司Domain
 * 
 * @author riozenc
 *
 */
public class CompanyDomain implements MybatisEntity {

	@TablePrimaryKey
	private Long id;// ID ID bigint TRUE FALSE TRUE
	@TablePrimaryKey
	private String companyNo;// COMPANY_NO 公司号 char(15) 15 TRUE FALSE TRUE
	private String companyName;// COMPANY_NAME 公司名 varchar(15) 15 FALSE FALSE
								// FALSE
	private Integer companyType;// COMPANY_TYPE 公司类别 int FALSE FALSE FALSE
	private Integer status;// STATUS 状态 int FALSE FALSE FALSE

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getCompanyType() {
		return companyType;
	}

	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
