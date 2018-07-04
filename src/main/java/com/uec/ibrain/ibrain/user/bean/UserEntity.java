package com.uec.ibrain.ibrain.user.bean;

import java.io.Serializable;
import java.util.Date;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 用户实体 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public class UserEntity implements Serializable {
	/**
	 * <p>Description: </p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = 3139040201538L;

	/**
	 * 主键
	 */
	private Integer innerid;  //用户id

	private String userName;    //用户名
	
	private String password;    //密码
	
	private String name;       //用户真实姓名
	
	private String email;      //邮箱
	
	private String mobilePhone;  //手机

	private String company;

	private String position;

	private String wechat;

	private String telePhone;      //固话
	
	private Date createTime;         //创建时间
	
	private Integer enabled = 1;
	
	private String salt;     //
	
	private int onlineNum;  //用户最大在线数
	
	private Date endTime;

	private Integer isNew;

	public Integer getIsNew() {
		return isNew;
	}

	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}

	public Integer getInnerid() {
		return innerid;
	}

	public void setInnerid(Integer innerid) {
		this.innerid = innerid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getTelePhone() {
		return telePhone;
	}

	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public int getOnlineNum() {
		return onlineNum;
	}

	public void setOnlineNum(int onlineNum) {
		this.onlineNum = onlineNum;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}
