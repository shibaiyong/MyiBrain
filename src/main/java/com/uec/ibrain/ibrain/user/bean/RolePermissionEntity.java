package com.uec.ibrain.ibrain.user.bean;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 角色权限关系映射 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public class RolePermissionEntity{

	
	private Integer innerid;
	
	private Integer roleId;
	
	private Integer permissionId;
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}
	public Integer getInnerid() {
		return innerid;
	}
	public void setInnerid(Integer innerid) {
		this.innerid = innerid;
	}

}
