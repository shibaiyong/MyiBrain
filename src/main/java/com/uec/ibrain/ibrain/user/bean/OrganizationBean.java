package com.uec.ibrain.ibrain.user.bean;

import java.io.Serializable;


public class OrganizationBean implements Serializable {

	private static final long serialVersionUID = -2903975921703111418L;

	private int orgId;

	private String orgCode;

	private String orgName;

	private int parentOrgId;

	private int rootOrgId;

	private int tenantId;

	private int type;

	public OrganizationBean() {
	}

	public int getOrgId() {
		return this.orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public int getParentOrgId() {
		return this.parentOrgId;
	}

	public void setParentOrgId(int parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public int getRootOrgId() {
		return this.rootOrgId;
	}

	public void setRootOrgId(int rootOrgId) {
		this.rootOrgId = rootOrgId;
	}

	public int getTenantId() {
		return this.tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

}