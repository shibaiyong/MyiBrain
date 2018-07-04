package com.uec.ibrain.ibrain.common.exception;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 业务异常,这种异常是会返回给用户看的 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public class BusinessException extends BaseException{
	private static final long serialVersionUID = - 4094447804930246372L;

	public BusinessException(String errorCode){
		super(errorCode);
		// TODO Auto-generated constructor stub
	}
	
	public BusinessException(String errorCode,Object[] params){
		super(errorCode,params);
	}

}
