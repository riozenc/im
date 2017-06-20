/**
 * Title:GroupInfoVO.java
 * Author:czy
 * Datetime:2016年9月13日 下午6:35:02
 */
package ims.webapp.acc.domain;

import java.util.List;

import com.riozenc.quicktool.annotation.TablePrimaryKey;
import com.riozenc.quicktool.mybatis.MybatisEntity;

/**
 * 群组
 * 
 * @author czy
 *
 */
public class GroupDomain implements MybatisEntity {

	@TablePrimaryKey
	private Long id;// ID ID bigint TRUE FALSE TRUE
	@TablePrimaryKey
	private String groupNo;// GROUP_NO 群组号 char(15) 15 FALSE FALSE FALSE
	private String groupName;// GROUP_NAME 群组名称 char(30) 30 FALSE FALSE FALSE
	private Integer groupType;// GROUP_TYPE 群组类别 int FALSE FALSE FALSE
	private String remark;
	private Integer status;// STATUS 状态 int FALSE FALSE FALSE

	private List<UserDomain> userList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getGroupType() {
		return groupType;
	}

	public void setGroupType(Integer groupType) {
		this.groupType = groupType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<UserDomain> getUserList() {
		return userList;
	}

	public void setUserList(List<UserDomain> userList) {
		this.userList = userList;
	}

}
