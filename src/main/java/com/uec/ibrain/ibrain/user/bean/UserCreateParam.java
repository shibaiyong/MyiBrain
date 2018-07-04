package com.uec.ibrain.ibrain.user.bean;

import java.util.List;

public class UserCreateParam extends UserEntity {

	private static final long serialVersionUID = 82831225229875169L;
	
	private Integer tenantId;   //租户id
	
	private String tenantName;   //租户名
	
	private Integer orgId;    //组织机构id
	
	private String orgName;     //组织名
	
	private Integer roleId;    //角色id
	
	private String roleName;    //角色名
	
	private Integer effectiveTime;	//有效时间    天

	private List<Integer> regions;   //地区id列表
	
	private List<String> regionNames;    //地区名称列表
	
	private List<Integer> sourceOrgs;   //来源机构id列表
	
	private List<String> sourceOrgNames;   //来源机构名称列表
	
	private List<Integer> classifications;    //分类id列表
	
	private List<String> classifiNames;      //分类名称列表
	
	private List<Integer> carriers;        //载体id列表
	
	private List<String> carrierNames;     //载体名称

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public List<Integer> getRegions() {
		return regions;
	}

	public void setRegions(List<Integer> regions) {
		this.regions = regions;
	}

	public List<Integer> getSourceOrgs() {
		return sourceOrgs;
	}

	public void setSourceOrgs(List<Integer> sourceOrgs) {
		this.sourceOrgs = sourceOrgs;
	}

	public List<Integer> getClassifications() {
		return classifications;
	}

	public void setClassifications(List<Integer> classifications) {
		this.classifications = classifications;
	}

	public List<Integer> getCarriers() {
		return carriers;
	}

	public void setCarriers(List<Integer> carriers) {
		this.carriers = carriers;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(Integer effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<String> getRegionNames() {
		return regionNames;
	}

	public void setRegionNames(List<String> regionNames) {
		this.regionNames = regionNames;
	}

	public List<String> getSourceOrgNames() {
		return sourceOrgNames;
	}

	public void setSourceOrgNames(List<String> sourceOrgNames) {
		this.sourceOrgNames = sourceOrgNames;
	}

	public List<String> getClassifiNames() {
		return classifiNames;
	}

	public void setClassifiNames(List<String> classifiNames) {
		this.classifiNames = classifiNames;
	}

	public List<String> getCarrierNames() {
		return carrierNames;
	}

	public void setCarrierNames(List<String> carrierNames) {
		this.carrierNames = carrierNames;
	}

}
