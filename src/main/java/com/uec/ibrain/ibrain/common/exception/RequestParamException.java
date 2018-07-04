package com.uec.ibrain.ibrain.common.exception;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 请求参数异常 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public class RequestParamException extends BaseException{

	private static final long serialVersionUID = - 4044779776629427280L;

	static String errorcode = "000000002";
	
	public RequestParamException(){
		super(errorcode);
	}

	public RequestParamException(Object[] params){
		super(errorcode,params);
	}
	
}
