package com.uec.ibrain.ibrain.contact.bean;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="user_contact")
public class UserContactEntity {
	/**
	 * 主键
	 */
	@Id//指明这个属性映射为数据库的主键
	@GeneratedValue
	@Column(name="innerid",nullable=false)
	private Integer innerid;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="company")
	private String company;
	

	@Column(name="email")
	private String email;
	
	@Column(name="question")
	private String question;
	
	@Column(name="create_datetime")
	private Date createDatetime;
	
	@Column(name="comment")
	private String comment;
	
	@Column(name="cellphone")
	private String cellphone;
	
	@PrePersist//在对实体数据进行数据库添加操作之前。
	protected void prePersist() {
		this.createDatetime = new Date();
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}


}
