package com.uec.ibrain.ibrain.tenant.bean;

import java.io.Serializable;
import java.util.Date;

public class TenantBean implements Serializable {
	
	/**
	 * <p>Description: </p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = 764346724156055900L;

	private int tenantId;

	private String company;

	private int crawlDays;

	private int crawlFreq;

	private Date createDatetime;

	private String displayName;

	private String name;

	private String reprintName;

	private String shortName;

	private String tenantMark;

	private String tenantSecret;

	private String salt;

	public TenantBean() {
	}

	public int getTenantId() {
		return this.tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getCrawlDays() {
		return this.crawlDays;
	}

	public void setCrawlDays(int crawlDays) {
		this.crawlDays = crawlDays;
	}

	public int getCrawlFreq() {
		return this.crawlFreq;
	}

	public void setCrawlFreq(int crawlFreq) {
		this.crawlFreq = crawlFreq;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReprintName() {
		return this.reprintName;
	}

	public void setReprintName(String reprintName) {
		this.reprintName = reprintName;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getTenantMark() {
		return this.tenantMark;
	}

	public void setTenantMark(String tenantMark) {
		this.tenantMark = tenantMark;
	}

	public String getTenantSecret() {
		return tenantSecret;
	}

	public void setTenantSecret(String tenantSecret) {
		this.tenantSecret = tenantSecret;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
}
