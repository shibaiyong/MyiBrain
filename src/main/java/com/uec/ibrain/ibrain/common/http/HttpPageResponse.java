package com.uec.ibrain.ibrain.common.http;

import java.util.List;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: http分页请求返回实体 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public class HttpPageResponse<T> {
	
	private List<T> content;
	
	private Integer totalElements;
	
	private Integer totalPages;
	
	private Integer numberOfElements;

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public Integer getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Integer totalElements) {
		this.totalElements = totalElements;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(Integer numberOfElements) {
		this.numberOfElements = numberOfElements;
	}
	
	

}
