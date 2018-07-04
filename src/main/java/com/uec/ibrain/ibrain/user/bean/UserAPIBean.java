package com.uec.ibrain.ibrain.user.bean;

import java.io.Serializable;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 接口返回用户对象实体 </p>
 * <p>Author:jlchen/陈金梁</p>
 */
public class UserAPIBean implements Serializable {


	private String uid;  //用户id

	private String username;    //用户真实姓名
	
	private String account;    //用户名

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
}
