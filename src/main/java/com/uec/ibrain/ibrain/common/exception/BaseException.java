package com.uec.ibrain.ibrain.common.exception;


import com.uec.ibrain.ibrain.common.log.ErrorCodeManager;

/**
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 自定义异常基类 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public class BaseException extends Exception{

	private static final long serialVersionUID = - 5544771150110827762L;

	/**
	 * <p>Description: 错误码 </p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Fields errorcode 
	 */
	private String errorCode;
	
	/**
	 * <p>Description: 描述字符串，说明异常的起因，或说明等 </p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Fields discription 
	 */
	private String message;
	
	/**
	 * <p>Description:参数 </p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Fields params 
	 */
	private Object[] params;
	
	/**
	 * <p>Description: 用以封装异常结构链 </p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Fields cause 
	 */
	private Throwable cause;
	
	public String getErrorCode(){
		return errorCode;
	}

	public void setErrorCode(String errorCode){
		this.errorCode = errorCode;
	}

	@Override
	public String getMessage(){
		return message;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public Object[] getParams(){
		return params;
	}

	public void setParams(Object[] params){
		this.params = params;
	}

	@Override
	public Throwable getCause(){
		return cause;
	}

	public void setCause(Throwable cause){
		this.cause = cause;
	}

	public BaseException(){
		super();
	}

	public BaseException(Throwable cause){
		super(cause);
		this.cause = cause;
	}

	public BaseException(String errorCode){
		super(ErrorCodeManager.getText(errorCode));
		this.message = ErrorCodeManager.getText(errorCode);
		this.errorCode = errorCode;
	}

//	public BaseException(String message){
//		super(message);
//		this.message = message;
//	}

	public BaseException(String errorCode,Throwable cause){
		super(ErrorCodeManager.getText(errorCode),cause);
		this.message = ErrorCodeManager.getText(errorCode);
		this.errorCode = errorCode;
		this.cause = cause;
	}

	public BaseException(String errorCode,Object[] params){
		super(ErrorCodeManager.getText(errorCode,params));
		this.message = ErrorCodeManager.getText(errorCode,params);
		this.errorCode = errorCode;
		this.params = params;
	}

	public BaseException(String errorCode,Object[] params,Throwable cause){
		super(ErrorCodeManager.getText(errorCode,params),cause);
		this.message = ErrorCodeManager.getText(errorCode,params);
		this.errorCode = errorCode;
		this.params = params;
		this.cause = cause;
	}
}
