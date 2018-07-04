package com.uec.ibrain.ibrain.tenant.bean;

import java.io.Serializable;

/**
 * <p>Description: 第三方系统免密登录令牌对象</p>
 * <p>Author:jlchen/陈金梁</p>
 */
public class TenantAccessToken implements Serializable {

	//令牌
	private String accessToken;
	//租户信息
	private TenantBean tenant;
	//令牌截止时间
	private String expiration;
	//令牌缓存时间
	private Integer cacheSeconds;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public TenantBean getTenant() {
		return tenant;
	}

	public void setTenant(TenantBean tenant) {
		this.tenant = tenant;
	}

	public String getExpiration() {
		return expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}

	public Integer getCacheSeconds() {
		return cacheSeconds;
	}

	public void setCacheSeconds(Integer cacheSeconds) {
		this.cacheSeconds = cacheSeconds;
	}
}
