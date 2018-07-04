package com.uec.ibrain.ibrain.user.bean;

import java.util.Date;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 角色实体 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public class RoleEntity {
	/**
	 * 主键
	 */
	private Integer innerid;

	private String name;
	
	private Date createDatetime;
	
	private String description;
	

	public Integer getInnerid() {
		return innerid;
	}

	public void setInnerid(Integer innerid) {
		this.innerid = innerid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
