package com.uec.ibrain.ibrain.user.bean;


/**
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 用户角色关系映射 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public class UserRoleEntity{

	private Integer innerid;
	
	private Integer userId;
	
	private Integer roleId;

	public Integer getInnerid() {
		return innerid;
	}
	public void setInnerid(Integer innerid) {
		this.innerid = innerid;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	

}
